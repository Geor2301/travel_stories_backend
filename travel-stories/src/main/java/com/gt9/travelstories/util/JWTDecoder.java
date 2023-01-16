package com.gt9.travelstories.util;

import com.google.gson.Gson;

import java.util.Base64;

public class JWTDecoder {

    public String email;
    public String id;
    public String iat;
    public String exp;

    public static JWTDecoder jwDecode(String jToken){
        String[] chunks = jToken.split(" ")[1].split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String jsonString = new String(decoder.decode(chunks[1]));

        return new Gson().fromJson(jsonString, JWTDecoder.class);
    }
}
