package br.ufc.samuel.backontrack.connection.client;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Report;
import br.ufc.samuel.backontrack.model.Token;

import static android.content.ContentValues.TAG;

/**
 * Created by samuel on 8/26/18.
 */

public class ReportClient {
    private String url;
    private RestTemplate restTemplate;

    public ReportClient(String baseUrl){
        this.url = baseUrl + "/report";
        restTemplate = new RestTemplate();
    }

    public String[] postReport(Report report, String token){
        String response[] = {"", ""};

        //HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Authorization", token);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Report> entity = new HttpEntity<>(report, headers);

        //HttpEntity<String> entity = new HttpEntity<>(serializedReport.toString(), headers);

        Log.d(TAG, "postReport_TOKEN: " + entity.getHeaders().getAuthorization());
        Log.d(TAG, "postReport_Report: " + entity.getBody());

        try{
//            response[1] = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
            response[1] = restTemplate.postForObject(url, entity, String.class);
            response[0] = ""+HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            e.printStackTrace();
            response[1] = "Report post error!";
            response[0] = e.getMessage();
        }

        return response;
    }
}
