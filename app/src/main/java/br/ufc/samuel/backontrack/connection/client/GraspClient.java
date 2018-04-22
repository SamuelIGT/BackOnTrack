package br.ufc.samuel.backontrack.connection.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

public class GraspClient {
    private String url;
    private RestTemplate restTemplate;

    public GraspClient(String baseUrl) {
        this.url = baseUrl + "/grasp";
        this.restTemplate = new RestTemplate();
    }

    public String[] requestGrasp(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(" ", headers);

        String response[] = {"", ""};

        try{
            response[1] = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).toString();
//            response = restTemplate.getForObject(url, entity, String.class);
            response[0] = ""+HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            response[0] = e.getMessage();
            response[1] = "Token pode ter expirado";
            e.printStackTrace();
        }

        return response;
    }

}
