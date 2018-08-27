package br.ufc.samuel.backontrack.connection.client;

import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Entity;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.model.User;

/**
 * Created by samuel on 4/9/18.
 */

public class LoginClient {
    private String url;
    private RestTemplate restTemplate;

    public LoginClient(String baseUrl) {
        this.url = baseUrl + "/login";
        restTemplate = new RestTemplate();
    }

    public String[] getToken(String credentialsJson){
        //"admin@ufc.com", "admini9move"

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add('Authorization', token);

        HttpEntity<String> entity = new HttpEntity<>(credentialsJson, headers);
        String response[] = {"", ""};
        try{
            response[1] = restTemplate.postForObject(url, entity, String.class);
            response[0] = ""+HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            e.printStackTrace();
            response[1] = "Login ou senha errados!";
            response[0] = e.getMessage();
        }
        return response;
    }

//    public String login(String token){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", token);
//
//        HttpEntity<String> entity = new HttpEntity<>(" ", headers);
//
//        String response;
//        try{
//            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).toString();
////            response = restTemplate.getForObject(url, entity, String.class);
//        }catch (Exception e){
//            response = e.getMessage();
//            e.printStackTrace();
//        }
//
//        return response;
//    }
}

