package br.ufc.samuel.backontrack.connection.client;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.model.Permition;
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

    public String[] postReport(String serializedReport, String token){
        String response[] = {"", ""};

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(serializedReport, headers);

        Log.d(TAG, "postReport_TOKEN: " + entity.getHeaders().getAuthorization());
        Log.d(TAG, "postReport_Report: " + entity.getBody());

        try{
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
