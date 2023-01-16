package com.gt9.travelstories.repository;

import com.gt9.travelstories.model.PostMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PostRepository extends MongoRepository<PostMessage,String> {

    @Query  ("{_id : ?0}")
    List<PostMessage> findPostById(String id);

    @Query  ("{name : ?0}")
    List<PostMessage> creatorSearch(String name);

    @Query  ("{$or :[  {'title': {$regex : ?0, $options: 'i'}} , {'tags':{$in : ?1}}  ]}")   //("{title :?0}")
    List<PostMessage> querySearch(String searchQuery, String[] tags);
}

