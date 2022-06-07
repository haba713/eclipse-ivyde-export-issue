package com.example;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("key", "value");
        System.out.println(obj);
    }

}
