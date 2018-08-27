package br.ufc.samuel.backontrack.connection.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import br.ufc.samuel.backontrack.connection.client.PermissionClient;
import br.ufc.samuel.backontrack.model.Token;

/**
 * Created by samuel on 8/26/18.
 */

public class ConnectionController {
    boolean checkInternetConnection(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        Log.d("IS_CONNECTED", "Is connected to the internet? => "+isConnected);

        return isConnected;
    }

    Token getToken(){
        Token token = Token.findById(Token.class, 1);
        if (token != null) {
            return token;
        } else {
            //SE NÃO POSSUIR TOKEN, FAZER LOGIN
        }
        return null;
    }
}
