package com.example.keycloakapp;

import com.example.keycloakapp.models.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AuthServerRequester {

    public String accessResource(String resourceURL, HttpMethod method) throws IOException {
        URL url = new URL(resourceURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method.name());

        connection.setRequestProperty("Authorization", String.format("Bearer %s", this.createToken().getAccessToken()));

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder builder = new StringBuilder();

        String message = "";
        while ((message = reader.readLine()) != null) {
            builder.append(message).append('\n');
        }
        reader.close();

        return builder.toString();
    }

    private Token createToken() throws IOException {
        URL url = new URL("http://192.168.0.137:8080/auth/realms/dev/protocol/openid-connect/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("username", "bogdan");
        params.put("password", "bogdan_123");
        params.put("client_id", "my-app");

        connection.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(buildURLEncodedParameters(params));
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String message = "";

        while ((message = reader.readLine()) != null) {
            builder.append(message).append("\n");
        }
        reader.close();

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(builder.toString(), Token.class);
    }

    private static String buildURLEncodedParameters(Map<String, String> params) {
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
