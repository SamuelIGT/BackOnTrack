package br.ufc.samuel.backontrack.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

        //calls the ImageView color animation when the focus of the edit text is changed.
        edtRegistration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d("Registration Focus: ", b+"");
                if(b)//if is focused.
                    animateDrawableColor((ImageView) findViewById(R.id.ic_registration), true); //pass the ImageView that will have the color changed and a boolean that says if it's focused or not.
                else
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
                    animateDrawableColor((ImageView) findViewById(R.id.ic_password), false);
            }
        });

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

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        //TODO: Realizar chamada de metodo de login do servidor.
        //TODO: Se o login for válido guardar no banco local o login e senha.
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

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
