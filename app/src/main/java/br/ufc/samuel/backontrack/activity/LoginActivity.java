package br.ufc.samuel.backontrack.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.connection.controller.LoginController;
import br.ufc.samuel.backontrack.model.Token;

public class LoginActivity extends AppCompatActivity {

    private EditText edtRegistration;
    private EditText edtPassword;
    //private ImageButton btnLogin;
    private TextView logo;
    private LoginController loginController;
    private String loginResponse;
    private LinearLayout loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("Token", "PASSOU PELO LOGIN!");

        loginController = new LoginController();
        findViews();
        animateLogoText(logo);

        verifyAuthorization();
    }

    private void verifyAuthorization() {//TODO: Usar isso na Splash Screen de forma que LoginActivity so seja chamada se nao houver um Token.
        Token token = Token.findById(Token.class, 1);

        if(token != null){
            if(!getString(R.string.TOKEN_EXPIRED).equals(token.getToken())) {
                Log.d("Token", "" + token.getToken());
                startMainActivity();
            }
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void findViews() {
        edtRegistration = findViewById(R.id.editText_registration);
        edtPassword = findViewById(R.id.editText_password);
        //btnLogin = findViewById(R.id.btn_login);
        logo = findViewById(R.id.text_login_logo);

        //calls the ImageView color animation when the focus of the edit text is changed.
        edtRegistration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.d("Registration Focus: ", hasFocus+"");
                if(hasFocus)//if is focused.
                    animateDrawableColor((ImageView) findViewById(R.id.ic_registration), true); //pass the ImageView that will have the color changed and a boolean that says if it's focused or not.
                else
                    if(edtRegistration.getText().toString().isEmpty())
                        animateDrawableColor((ImageView) findViewById(R.id.ic_registration), false);
            }
        });

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d("Registration Focus: ", b+"");
                if(b)
                    animateDrawableColor((ImageView) findViewById(R.id.ic_password), true);
                else
                    if(edtPassword.getText().toString().isEmpty())
                        animateDrawableColor((ImageView) findViewById(R.id.ic_password), false);
            }
        });

    }

    public void login(View view) {
        loading = findViewById(R.id.layout_loading);
        loginResponse = "";

        String registration = "";
        String password = "";

        if (edtRegistration.getText() != null) {
            registration = edtRegistration.getText().toString();
        }
        if (edtPassword.getText() != null) {
            password = edtPassword.getText().toString();
        }
        if (!registration.equals("")) {
            if (!password.equals("")) {

                loading.setVisibility(View.VISIBLE);//Mostrar Barra de progresso circular.
                new LoginTask(registration, password).execute();
            }else {
                Toast.makeText(this, "Digite uma senha.", Toast.LENGTH_SHORT).show();
                edtPassword.requestFocus();
            }
        }else {
            Toast.makeText(this, "Digite o login.", Toast.LENGTH_SHORT).show();
            edtRegistration.requestFocus();
        }
    }


    private void animateDrawableColor(final ImageView v, boolean isFocused) {
        final int color;
        if (isFocused) {
             color = getResources().getColor(android.R.color.black);
        }else{
            color = getResources().getColor(R.color.unfocused_icon);
        }
        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaColor = adjustAlpha(color, mul);
                v.setColorFilter(alphaColor, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    v.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(500);
        //colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(0);
        colorAnim.start();
    }

    private void animateLogoText(final TextView v) {

        Integer colorFrom = getResources().getColor(R.color.colorAccent);
        Integer colorTo = getResources().getColor(R.color.color_borg_scale_subtitle);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                v.setTextColor((Integer)animator.getAnimatedValue());
            }

        });
        colorAnimation.setDuration(1000);
        colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimation.setRepeatCount(-1);
        colorAnimation.start();
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }


    private class LoginTask extends AsyncTask<Void, Void, Void>{
        private String email;
        private String password;

        public LoginTask (String email, String password){
            this.email = email;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loginResponse = loginController.login(email, password, LoginActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loading.setVisibility(View.GONE);//Esconder barra de progresso circular.
            if(loginResponse.equals(getString(R.string.login_success_msg))){
                startMainActivity();
            }else{
                //If some error occur
                Log.d("LOGIN RESPONSE: ", loginResponse);
                Toast.makeText(LoginActivity.this, "Ocorreu uma falha ao iniciar a sessão. Verifique se o login ou a senha estão corretos e tente novamente.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
