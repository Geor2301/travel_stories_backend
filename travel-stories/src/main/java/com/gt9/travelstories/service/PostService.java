package com.gt9.travelstories.service;

import com.gt9.travelstories.model.PostList;
import com.gt9.travelstories.model.PostMessage;
import com.gt9.travelstories.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    // CREATE
    public PostMessage addPost(PostMessage post) {
        return repository.save(post);
    }
    // UPDATE
    public PostList findAllPosts(int page) {

        int LIMIT=8;
        int startingIndex = (page-1)*LIMIT;
        int pageCount = 1 + (int)Math.ceil(repository.count()/LIMIT);
        Stream<PostMessage> reqdPosts = repository.findAll(Sort.by(Sort.Direction.DESC,"createdAt")).stream().skip(startingIndex).limit(LIMIT);
        PostList posts = new PostList();
        posts.setData(reqdPosts.collect(Collectors.toList()));
        posts.setCurrentPage(page);
        posts.setNumberOfPages(Math.max(1,pageCount));
        return posts;
    }

    public PostMessage findPostById(String id) {
        return repository.findPostById(id).get(0);
    }

    public PostList findPostsByCreator(String name) {
        List<PostMessage> reqdPosts = repository.creatorSearch(name);
        PostList posts = new PostList();
        posts.setData(reqdPosts);
        posts.setCurrentPage(1);
        posts.setNumberOfPages(1);
        return posts;
    }

    public PostList findPostsBySearch(String searchQuery, String tags) {

        List<PostMessage> reqdPosts = repository.querySearch(searchQuery, tags.split(","));
        PostList posts = new PostList();
        posts.setData(reqdPosts);
        posts.setCurrentPage(1);
        posts.setNumberOfPages(1);
        return posts;
    }
    // UPDATE
    public PostMessage updatePost(String id, PostMessage post) {
        PostMessage postToUpdate = repository.findPostById(id).get(0);
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setMessage(post.getMessage());
        postToUpdate.setTags(post.getTags());
        postToUpdate.setSelectedFile(post.getSelectedFile());
        return repository.save(postToUpdate);
    }

    public PostMessage likePost(String id, String userId) {
        PostMessage postToLike = repository.findPostById(id).get(0);
        List<String> newLikesList = new ArrayList<>();

        for(int i=0; i<postToLike.getLikes().size();i++){
            if(!postToLike.getLikes().get(i).equals(userId)){
                newLikesList.add(postToLike.getLikes().get(i));
            }
        }

        if(postToLike.getLikes().size() == newLikesList.size()){
            newLikesList.add(userId);
        }

        postToLike.setLikes(newLikesList);
        return repository.save(postToLike);
    }

    public PostMessage commentPost(String id, String comment) {
        PostMessage postToUpdate = repository.findPostById(id).get(0);
        List<String> updatedComments = postToUpdate.getComments();
        updatedComments.add(comment);
        postToUpdate.setComments(updatedComments);
        return repository.save(postToUpdate);
    }
    // DELETE
    public void deletePost(String id) {
        repository.deleteById(id);
    }

}
