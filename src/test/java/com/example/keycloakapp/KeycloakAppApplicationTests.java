package com.example.keycloakapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class KeycloakAppApplicationTests {

    @Test
    void testIntReduce() {
        float length = Arrays.asList("one", "two", "three", "four")
                .parallelStream()
                .reduce(0,
                        (accumulatedInt, str) -> accumulatedInt + str.length(),
                        Integer::sum);
    }

    @Test
    void testMapReduce() {
        Map<String, String> map = new HashMap<>() {{
            put("client_id", "my-app");
            put("grant_type", "password");
            put("username", "bogdan");
            put("password", "bogdan_123");
        }};

        String urlencoded = map.entrySet().parallelStream().reduce(""
                , (acc, param) -> acc + param.getKey() + "=" + param.getValue() + "&"
                , (acc, param) -> {
                    String result = acc + param;
                    return result.endsWith("&") ? result.substring(0, result.length() - 1) : result;
                });

        System.out.println("Urlencoded query looks like: " + urlencoded);
    }

}
