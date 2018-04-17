package br.ufc.samuel.backontrack.connection;

import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.model.Token;
import br.ufc.samuel.backontrack.model.User;

/**
 * Created by samuel on 4/16/18.
 */

public class ClientController {
    private final String url = "http://i9move.quixada.ufc.br/api";
    private Gson gson;

    public ClientController() {
        this.gson = new Gson();
    }

    public String login(String email, String password){
        String credentialsJson = gson.toJson(new User(email, password));
        LoginClient loginClient = new LoginClient(url);

        String response[] = loginClient.getToken(credentialsJson);
        if(response[0] == ""+HttpURLConnection.HTTP_OK){
            Token token = new Token(response[1]);
            Log.d("Token: ", ""+token.getToken());
            token.save();
            return "Login efetuado com sucesso!";
        }else {
            Log.d("Token else ", "ERROR");
            return "Falha no login! " + response[0];
        }
    }


}
