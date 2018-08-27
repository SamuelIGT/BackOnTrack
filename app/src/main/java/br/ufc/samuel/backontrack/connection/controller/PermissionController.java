package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.connection.client.PermissionClient;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Token;

public class PermissionController extends ConnectionController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    //private final String url = "http://192.168.1.38:8080";

    private Gson gson;

    public PermissionController() {
        this.gson = new Gson();
    }

    public Permition[] getExercises(Context context) {
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
                    permission = gson.fromJson(response[1], Permition[].class);
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    exception.printStackTrace();
                    return null;
                }

                Log.d("PERMITION Controller: ", "" + permission[0].getGrasp().getExercise().getTitle());
                return permission/*graspList*/;
            } else {
                Log.d("GRASP RESPONSE: ", "ERROR");
            }
        }

        return null;
    }
}
