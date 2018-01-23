package br.ufc.samuel.backontrack.fragments;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import br.ufc.samuel.backontrack.OnFragmentTouched;
import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.activity.ExerciseExecutionActivity;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by samue on 22/01/2018.
 */


public class ConfirmationDialogFrament extends DialogFragment {

    OnFragmentTouched listener;


    public ConfirmationDialogFrament() {
    }

    public static ConfirmationDialogFrament newInstance(int centerX, int centerY, int color) {
        Bundle args = new Bundle();
        args.putInt("cx", centerX);
        args.putInt("cy", centerY);
        args.putInt("color", color);
        ConfirmationDialogFrament fragment = new ConfirmationDialogFrament();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.dialog_fragment_confirmation, container);

        //rootView.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

       /* rootView.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.d("ViewTreeObserver:", "Entrou");
                int cx = getArguments().getInt("cx");
                int cy = getArguments().getInt("cy");

                // get the hypothenuse so the radius is from one corner to the other
                int radius = Math.max(rootView.getWidth(), rootView.getHeight());

                Animator mAnimator =
                        ViewAnimationUtils.createCircularReveal(rootView.findViewById(R.id.confirmation_dialog), cx, cy, 0, radius);
                mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                mAnimator.setDuration(400);
                mAnimator.start();
            }
        });*/
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int cx = getArguments().getInt("cx");
                int cy = getArguments().getInt("cy");

                // get the hypothenuse so the radius is from one corner to the other
                int radius = (int) Math.hypot(right, bottom);

                Animator mAnimator =
                        ViewAnimationUtils.createCircularReveal(rootView.findViewById(R.id.confirmation_dialog), cx, cy, 0, radius);
                mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                mAnimator.setDuration(400);
                mAnimator.start();
            }
        });


        return rootView;
    }


}

