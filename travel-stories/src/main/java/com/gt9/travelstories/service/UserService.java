package com.gt9.travelstories.service;


import com.gt9.travelstories.model.User;
import com.gt9.travelstories.model.UserResponse;
import com.gt9.travelstories.repository.UserRepository;
import com.gt9.travelstories.util.EncoderFormat;
import com.gt9.travelstories.util.JWTEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public UserResponse createUser(User newUser) {
        List<User> existingUser = repository.findUserByEmail(newUser.getEmail());

        UserResponse response = new UserResponse();

        if(existingUser.size()==0){
            User createdUser =repository.save(newUser);
            response.setResult(createdUser);
            JWTEncoder encoder = new JWTEncoder();
            EncoderFormat encodeObject = new EncoderFormat(createdUser.getEmail(), createdUser.get_id());
            response.setToken(encoder.jwEncode( encodeObject ));
        }
        return response;
    }

    public UserResponse signInUser(User newUser) {
        User existingUser = repository.findUserByEmail(newUser.getEmail()).get(0);

        UserResponse response = new UserResponse();

        if( existingUser.getPassword().equals(newUser.getPassword()) ){

            response.setResult(existingUser);
            JWTEncoder encoder = new JWTEncoder();
            EncoderFormat encodeObject = new EncoderFormat(existingUser.getEmail(), existingUser.get_id());
            response.setToken(encoder.jwEncode( encodeObject ));
        }
        return response;
    }
}
