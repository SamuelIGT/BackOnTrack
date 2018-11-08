package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.connection.client.ReportClient;
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

            //JsonElement reportJsonElement = gson.toJsonTree(report);

            //String serializedReport = gson.toJson(setReportRequestBodyObject(reportJsonElement));//reportJsonElement.getAsJsonObject();

            setReportRequestBodyObject(report);
            Log.d("SERIALIZED_REPORT: ", gson.toJson(report));

            ReportClient client = new ReportClient(url);

            String token = getToken().getToken();

            response = client.postReport(report, token);

            if (response[0].equals("" + HttpURLConnection.HTTP_OK)) {
                reportSended = true;


            } else {
                Log.d("REPORT_POST: ", "ERROR: "+response[0]);
                reportSended = false;
            }
            return reportSended;
        }
        return false;
    }
    private void setReportRequestBodyObject(Report report) {
        report.getPermition().setId(report.getPermition().getPermitionId());
        report.getPermition().getGrasp().setId(report.getPermition().getGrasp().getGraspId());
        report.getPermition().getPatient().setId(report.getPermition().getPatient().getPatientId());
    }

/*    private JsonObject setReportRequestBodyObject(JsonElement reportJsonElement) {
        JsonObject jsonObject = reportJsonElement.getAsJsonObject();
        Long id;

        //Adicionando a propriedade "id" em Permition
        id = jsonObject.get("permition").getAsJsonObject().get("permitionId").getAsLong();
        jsonObject.get("permition").getAsJsonObject().add("id", gson.toJsonTree(id));
        //Adicionando a propriedade "id" em Grasp
        id = jsonObject.get("permition").getAsJsonObject().get("grasp").getAsJsonObject().get("graspId").getAsLong();
        jsonObject.get("permition").getAsJsonObject().get("grasp").getAsJsonObject().add("id", gson.toJsonTree(id));
        //Adicionando a propriedade "id" em Patient
        id = jsonObject.get("permition").getAsJsonObject().get("patient").getAsJsonObject().get("patientId").getAsLong();
        jsonObject.get("permition").getAsJsonObject().get("patient").getAsJsonObject().add("id", gson.toJsonTree(id));

        //Removing ids propertys
        jsonObject.get("permition").getAsJsonObject().remove("permitionId");
        jsonObject.get("permition").getAsJsonObject().get("grasp").getAsJsonObject().remove("graspId");
        jsonObject.get("permition").getAsJsonObject().get("patient").getAsJsonObject().remove("patientId");

        return jsonObject;
    }*/

}
