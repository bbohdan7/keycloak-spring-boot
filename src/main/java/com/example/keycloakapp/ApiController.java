package com.example.keycloakapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    @GetMapping
    @RequestMapping("/admin")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Admin Page");
    }

    @GetMapping
    @RequestMapping("/test")
    public ResponseEntity<String> testPage() throws IOException {
        URL url = new URL("http://192.168.0.133:8080/auth/realms/dev/protocol/openid-connect/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("username", "bogdan");
        params.put("password", "bogdan_123");
        params.put("client_id", "my-app");

        connection.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterBuilder.getParamsString(params));
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String message = "";

        while( (message = reader.readLine()) != null ){
            builder.append(message).append("\n");
        }
        reader.close();

        return ResponseEntity.ok(builder.toString());
    }

    public static class ParameterBuilder {
        public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
            StringBuilder builder = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                builder.append("&");
            }

            return builder.toString().length() > 0 ? builder.toString().substring(0, builder.toString().length() - 1) : builder.toString();
        }
    }
}
