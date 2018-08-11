package br.ufc.samuel.backontrack.connection.controller;

import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.ufc.samuel.backontrack.connection.client.PermitionClient;
import br.ufc.samuel.backontrack.model.Grasp;
import br.ufc.samuel.backontrack.model.Permition;
import br.ufc.samuel.backontrack.model.Token;

public class PermitionController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    //private final String url = "http://192.168.1.38:8080";

    private Gson gson;

    public PermitionController() {
        this.gson = new Gson();
    }

    public Permition[] getExercises(){
        String[] response = {"", ""};
        Permition[] permition;
        //Grasp[] graspList;

        Token token = Token.findById(Token.class, 1);
        if(token != null){
            PermitionClient client = new PermitionClient(url);
            response = client.requestPermition(token.getToken());
            Log.d("PERMITION RESPONSE: ", ""+response[1]);
        }

        else{
            //SE NÃO POSSUIR TOKEN, FAZER LOGIN
        }

        if(response[0] == ""+ HttpURLConnection.HTTP_OK){
            try {
                permition = gson.fromJson(response[1], Permition[].class);
            }catch (IllegalStateException | JsonSyntaxException exception){
                exception.printStackTrace();
                return null;
            }

            Log.d("PERMITION Controller: ", ""+permition[0].getGrasp().getExercise().getTitle());
            return permition/*graspList*/;
        }else {
            Log.d("GRASP RESPONSE: ", "ERROR");
            return null;
        }
    }
}
