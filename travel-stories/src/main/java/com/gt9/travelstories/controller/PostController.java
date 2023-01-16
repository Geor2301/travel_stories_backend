package com.gt9.travelstories.controller;



import com.gt9.travelstories.model.CommentReq;
import com.gt9.travelstories.model.PostList;
import com.gt9.travelstories.model.PostMessage;
import com.gt9.travelstories.service.PostService;
import com.gt9.travelstories.util.JWTDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins="*")
public class PostController {

    @Autowired
    private PostService service;

    // CREATE
    @PostMapping
    public PostMessage createPost(@RequestBody PostMessage post, @RequestHeader(value = "Authorization") String jToken){

        JWTDecoder token = JWTDecoder.jwDecode(jToken);
        post.setCreator(token.id);
        post.setLikes(new ArrayList<>());
        post.setComments(new ArrayList<>());
        post.setCreatedAt(new Date());
        return service.addPost(post);
    }
    //READ
    @GetMapping
    public PostList getPosts(@RequestParam int page) {
        return service.findAllPosts(page);
    }

    @GetMapping("/{id}")
    public PostMessage getPost(@PathVariable String id){
        return service.findPostById(id);
    }

    @GetMapping("/creator")
    public PostList getPostsByCreator(@RequestParam String name){
        return service.findPostsByCreator(name);
    }

    @GetMapping("/search")
    public PostList getPostsBySearch(@RequestParam String searchQuery, @RequestParam String tags){
        return service.findPostsBySearch(searchQuery, tags);
    }
    // UPDATE
    @PatchMapping("/{id}")
    public PostMessage updatePost(@PathVariable String id, @RequestBody PostMessage post){
        return service.updatePost(id, post);
    }

    @PatchMapping("/{id}/likePost")
    public PostMessage likePost(@PathVariable String id, @RequestHeader(value = "Authorization") String jToken){
        JWTDecoder token = JWTDecoder.jwDecode(jToken);
        String userId = token.id;
        return service.likePost(id, userId);
    }

    @PostMapping("/{id}/commentPost")
    public PostMessage commentPost(@PathVariable String id, @RequestBody CommentReq comment){
        return service.commentPost(id, comment.getValue());
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        service.deletePost(id);
    }
}
