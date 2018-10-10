package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
/**
 * Created by samuel on 4/16/18.
 */

public class Token extends SugarRecord{
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}