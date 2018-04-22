package br.ufc.samuel.backontrack.connection.controller;

import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.connection.client.GraspClient;
import br.ufc.samuel.backontrack.connection.client.LoginClient;
import br.ufc.samuel.backontrack.model.Grasp;
import br.ufc.samuel.backontrack.model.Token;
import br.ufc.samuel.backontrack.model.User;

public class GraspController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    private Gson gson;

    public GraspController() {
        this.gson = new Gson();
    }

    public Grasp[] getExercises(){
        String[] response = {"", ""};
        Token token = Token.findById(Token.class, 1);
        if(token != null){
            GraspClient client = new GraspClient(url);
            response = client.requestGrasp(token.getToken());
        }
        else{
            //SE NÃO POSSUIR TOKEN, FAZER LOGIN
        }

        if(response[0] == ""+ HttpURLConnection.HTTP_OK){

            return gson.fromJson(response[1], Grasp[].class);
        }else {
            Log.d("GRASP RESPONSE: ", "ERROR");
            return null;
        }
    }
}
