package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;

import br.ufc.samuel.backontrack.connection.client.LoginClient;
import br.ufc.samuel.backontrack.model.Token;
import br.ufc.samuel.backontrack.model.User;

/**
 * Created by samuel on 4/16/18.
 */

public class LoginController extends ConnectionController{
    private final String url = "http://i9move.quixada.ufc.br/api";
    private Gson gson;

    public LoginController() {
        this.gson = new Gson();
    }

    public String login(String email, String password, Context context) {
        if(checkInternetConnection(context)){
            String credentialsJson = gson.toJson(new User(email, password));
            LoginClient loginClient = new LoginClient(url);

            String response[] = loginClient.getToken(credentialsJson);
            if (response[0] == "" + HttpURLConnection.HTTP_OK) {
                Token token = Token.findById(Token.class, 1);
                if(token != null){
                    token.setToken(response[1]);
                }else {
                    token = new Token(response[1]);
                }
                Log.d("Token: ", "" + token.getToken());
                long id = token.save();
                Log.d("Token: ", "" + Token.findById(Token.class, 1));
                return "Login efetuado com sucesso!";
            } else {
                Log.d("Token else ", "ERROR");
                return "Falha no login! " + response[0];
            }
        }else {
            return "Sem conexão com a internet.";
        }
    }

}
