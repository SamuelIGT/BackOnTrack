package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.net.HttpURLConnection;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

import br.ufc.samuel.backontrack.connection.client.PermissionClient;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Token;

import static android.content.ContentValues.TAG;

public class PermissionController extends ConnectionController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    //private final String url = "http://192.168.1.38:8080";

    private Gson gson;

    public PermissionController() {
        this.gson = new Gson();
    }

    public Map<String, Permition[]> getExercises(Context context) {
        HashMap<String, Permition[]> permitionResponse = new HashMap<>();
        if(checkInternetConnection(context)){
            String[] response = {"", ""};
            Permition[] permission;
            //Grasp[] graspList;

            Token token = Token.findById(Token.class, 1);
            if (token != null) { //TODO: mudar para o metodo getToken da classe pai.
                PermissionClient client = new PermissionClient(url);
                response = client.requestPermition(token.getToken());
                Log.d("PERMITION RESPONSE: ", "" + response[1]);
            } else {
                //SE NÃO POSSUIR TOKEN, FAZER LOGIN
            }

            if (response[0] == "" + HttpURLConnection.HTTP_OK) {
                try {
                    JsonArray jsonArrayPermission = gson.fromJson(response[1], JsonArray.class);

                    permission = new Permition[jsonArrayPermission.size()];
                    for(int i = 0; i < jsonArrayPermission.size(); i++){
                        JsonObject jsonObjectPermission = jsonArrayPermission.get(i).getAsJsonObject();

                        permission[i] = gson.fromJson(jsonObjectPermission, Permition.class);

                        Long permissionId = jsonObjectPermission.get("id").getAsLong();
                        Long patientId = jsonObjectPermission.get("patient").getAsJsonObject().get("id").getAsLong();
                        Long graspId = jsonObjectPermission.get("grasp").getAsJsonObject().get("id").getAsLong();

                        Log.d(TAG, "getExercises: Permition ID = " + permissionId + "\nPatient ID = " + patientId + "\nGrasp ID" + graspId);

                        permission[i].getGrasp().setPermitionId(permissionId);
                        permission[i].getPatient().setPatientId(patientId);
                        permission[i].getGrasp().setGraspId(graspId);
                    }

                    //permission = gson.fromJson(response[1], Permition[].class);
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    exception.printStackTrace();
                    permitionResponse.put("Erro! O sistema falhou ao tentar trabalhar com os dados retornados pelo serviço." , null);
                    return permitionResponse;
                }

                Log.d("PERMITION Controller: ", "" + permission[0].getGrasp().getExercise().getTitle());
                if(permission.length == 0){

                    permitionResponse.put("Usuário não possui exercícios cadastrados", null);
                    return permitionResponse;
                }
                permitionResponse.put("Sucesso", permission);
                return permitionResponse/*graspList*/;
            } else {
                permission = new Permition[]{};
                permitionResponse.put("Falha na requisição: " + response[0], permission);
                return permitionResponse;
            }
        }
        permitionResponse.put("Sem conexão com a internet", null);
        return permitionResponse;
    }
}
