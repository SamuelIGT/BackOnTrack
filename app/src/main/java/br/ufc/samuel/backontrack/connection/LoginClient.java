package br.ufc.samuel.backontrack.connection;

import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.ufc.samuel.backontrack.model.User;

/**
 * Created by samuel on 4/9/18.
 */

public class LoginClient {
    private final String url = "http://i9move.quixada.ufc.br/api/login";
    public RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    public String getToken(String email, String senha){
        User user = new User(email, senha);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add('Authorization', token);

        String jsonUser = gson.toJson(user);

        HttpEntity<String> entity = new HttpEntity<>(jsonUser, headers);
        String response;
        try{
            response = restTemplate.postForObject(url, entity, String.class);
        }catch (Exception e){
            e.printStackTrace();
            response = "Login ou senha errados!";
        }
        return response;
    }

    public String login(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(" ", headers);

        String response;
        try{
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).toString();
//            response = restTemplate.getForObject(url, entity, String.class);
        }catch (Exception e){
            response = e.getMessage();
            e.printStackTrace();
        }

        return response;
    }
}

