package br.ufc.samuel.backontrack.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufc.samuel.backontrack.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtRegistration;
    private EditText edtPassword;
    //private ImageButton btnLogin;
    private TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
    }


    private void findViews() {
        edtRegistration = findViewById(R.id.editText_registration);
        edtPassword = findViewById(R.id.editText_password);
        //btnLogin = findViewById(R.id.btn_login);
        logo = findViewById(R.id.text_login_logo);
    }

    public void login(View view) {
        String registration;
        String password;

        if(edtRegistration.getText() != null){
            registration = edtRegistration.getText().toString();
        }
        if (edtPassword.getText() != null){
            password = edtPassword.getText().toString();
        }

        //TODO: Realizar chamada de metodo de login do servidor.
        //TODO: Se o login for válido guardar no banco local o login e senha.
    }
}
