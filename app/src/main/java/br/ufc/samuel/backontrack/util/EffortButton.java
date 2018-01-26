package br.ufc.samuel.backontrack.util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 03/08/2017.
 */

public class EffortButton {
    private int level;
    private TextView title;
    private ImageButton btn;
    private boolean isSelected;

    public EffortButton(int level, TextView title, ImageButton btn) {
        this.btn = btn;
        this.level = level;
        this.title = title;
        this.isSelected = false;
    }

    public void select(Context context, List<EffortButton> btnList) {
        if (!isSelected) {
            showAll(btnList, context);//shows all the icons texts
            setDefaultButton(btnList, context); //sets all icons to the default state(deselected)
            setSelected(true);
            playIconAnimation(context);
//            DrawableCompat.setTint(btn.getDrawable(), ContextCompat.getColor(context, R.color.colorSuccess));
            title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        } else {
            showAll(btnList, context);
//            DrawableCompat.setTint(btn.getDrawable(), ContextCompat.getColor(context, R.color.colorSuccess));
            getTitle().setTextColor(ContextCompat.getColor(context, R.color.colorDisabled));
            setSelected(false);
            playIconAnimation(context);

        }
    }

    public void setDefaultButton(List<EffortButton> btnList, Context context) {
        for (EffortButton eBtn : btnList) {
            if (eBtn.getLevel() != getLevel()) {
                eBtn.deselectAll(eBtn, context);
            }
        }
    }

    public void deselectAll(EffortButton eBtn, Context context) {
//        DrawableCompat.setTint(eBtn.getBtn().getDrawable(), ContextCompat.getColor(context, R.color.colorDisabled));
        eBtn.getTitle().setTextColor(ContextCompat.getColor(context, R.color.colorDisabled));
        if(eBtn.isSelected()){
            eBtn.getBtn().startAnimation(playZoomAnimation(context, !eBtn.isSelected()));
        }
        eBtn.setSelected(false);
        animateImageButton(context, eBtn.isSelected());
        eBtn.getTitle().setVisibility(View.INVISIBLE);
    }

    public void showAll(List<EffortButton> btnList, Context context) {
        for (EffortButton eBtn : btnList) {
            eBtn.getTitle().setVisibility(View.VISIBLE);
        }
    }
    private void playIconAnimation(Context context){
        animateImageButton(context, isSelected);
        btn.startAnimation(playZoomAnimation(context, isSelected));

    }

    private Animation playZoomAnimation(Context context, boolean isZoomIn){
        if(isZoomIn) {
            return AnimationUtils.loadAnimation(context, R.anim.effort_emoticon_zoom_in);
        }
        return AnimationUtils.loadAnimation(context, R.anim.effort_emoticon_zoom_out);
    }

    public void animateImageButton(Context context, boolean isZoomIn) {
        int color;
        if(isZoomIn)
            color = context.getResources().getColor(R.color.colorPrimary);
        else
            color = context.getResources().getColor(R.color.colorDisabled);

        final int newColor = color;

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaNewColor = adjustAlpha(newColor, mul);
                btn.setColorFilter(alphaNewColor, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    btn.setColorFilter(null);
                }
            }
        });
        colorAnim.setDuration(500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
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



    public int getLevel() {
        return level;
    }

    public ImageButton getBtn() {
        return btn;
    }

    public boolean isSelected() {
        return isSelected;
    }

    private void setSelected(boolean a) {
        isSelected = a;
    }

    public TextView getTitle() {
        return title;
    }
}
