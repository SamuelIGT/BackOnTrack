package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.connection.client.ReportClient;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Report;

/**
 * Created by samuel on 8/26/18.
 */

public class ReportController extends ConnectionController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    private Gson gson;

    public ReportController(){
        gson = new Gson();
    }

    public boolean sendReport(Report report, Context context){
        if(checkInternetConnection(context)){
            String[] response;
            Boolean reportSended = false;

            String serializedReport = gson.toJson(report);

            ReportClient client = new ReportClient(url);

            String token = gson.toJson(getToken());

            response = client.postReport(serializedReport, token);

            if (response[0].equals("" + HttpURLConnection.HTTP_OK)) {
                reportSended = true;


            } else {
                Log.d("REPORT_POST: ", "ERROR");
                reportSended = false;
            }
            return reportSended;
        }
        return false;
    }

}
