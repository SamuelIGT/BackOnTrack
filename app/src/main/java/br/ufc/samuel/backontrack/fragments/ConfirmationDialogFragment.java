package br.ufc.samuel.backontrack.fragments;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;

import br.ufc.samuel.backontrack.OnFragmentTouched;
import br.ufc.samuel.backontrack.R;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by samue on 22/01/2018.
 */


public class ConfirmationDialogFragment extends DialogFragment {

    private OnFragmentTouched listener;
    private View rootView;

    public ConfirmationDialogFragment() {
    }

    public static ConfirmationDialogFragment newInstance(int centerX, int centerY, int color ) {
        Bundle args = new Bundle();
        args.putInt("cx", centerX);
        args.putInt("cy", centerY);
        args.putInt("color", color);
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_confirmation, container);
        setDialogProperties();

        return rootView;
    }

    private void setDialogProperties() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);


        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);

                int cx = getArguments().getInt("cx");
                int cy = getArguments().getInt("cy");

                // get the hypotenuse so the radius is from one corner to the other
                int radius = (int) Math.hypot(right, bottom);

                Animator mAnimator =
                        ViewAnimationUtils.createCircularReveal(rootView.findViewById(R.id.confirmation_dialog), cx, cy, 0, radius);
                mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                mAnimator.setDuration(500);
                mAnimator.addListener(new Animator.AnimatorListener(

                ) {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dismiss();
                            }
                        }, 700);

                        //TODO: fechar fragmento pai e esperar 1 segundo antes de fechar esse.
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                mAnimator.start();

            }
        });
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }



}

