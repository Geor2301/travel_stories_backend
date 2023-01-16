package com.gt9.travelstories.util;


import com.google.gson.Gson;

import java.util.Base64;

public class JWTEncoder {

    public static String jwEncode(EncoderFormat encodeObject){

        Base64.Encoder encoder = Base64.getEncoder();

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."+encoder.encodeToString(new Gson().toJson(encodeObject).toString().getBytes());
        return token;
    }
}
