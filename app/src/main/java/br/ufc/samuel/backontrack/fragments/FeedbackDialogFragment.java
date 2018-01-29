package br.ufc.samuel.backontrack.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.util.EffortButton;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by samuel on 25/01/2018.
 */

public class FeedbackDialogFragment extends DialogFragment {

    private View rootView;
    private List<EffortButton> effortButtons;
    private FloatingActionButton feedbackAlert;
    private ImageButton increaseRepetitions;
    private ImageButton decreaseRepetitions;
    private TextView repetitionsNumber;
    private Button cardButtonConfirm;
    private Button feedbackConfirmButton;
    private Button feedbackYes;
    private Button feedbackNo;

    public FeedbackDialogFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_feedback, container);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog);

        findViews();
        setButtonsClickListeners();

        return rootView;
    }

    private void setButtonsClickListeners() {
        //sets the onClickListener for the 'No' button on the feedback finish card.
        feedbackNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                getActivity().finish();
            }
        });

        //sets the onClickListener for the floatingButton that opens the feedback alert card
        feedbackAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View myView = rootView.findViewById(R.id.card_feedback_alert);
                myView.setVisibility(View.VISIBLE);
                Animator mAnimator = getRevealAnimator(myView, feedbackAlert, true);

                mAnimator.start();
            }
        });
        //sets the onClickListener for the increase repetition button
        increaseRepetitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repetitionsNumber.setText(String.valueOf(Integer.parseInt(repetitionsNumber.getText().toString()) + 1));
            }
        });

        //sets the onClickListener for the decrease repetition button
        decreaseRepetitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentNumber = Integer.parseInt(repetitionsNumber.getText().toString());
                if(currentNumber >=1 )
                    repetitionsNumber.setText(String.valueOf(currentNumber - 1));
                else
                    repetitionsNumber.setText(String.valueOf(0));

            }
        });

        //Sets the on click listener for the Effort Buttons
        for (int i = 0; i < effortButtons.size(); i++) {
            final int j = i;
            effortButtons.get(i).getBtn().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // setDefaultButton(btnList.get(0), btnList);
                    effortButtons.get(j).select(rootView.getContext(), effortButtons);
                }
            });
        }

        //sets the onClickListener for the cards confirmation button.
        cardButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View myView = rootView.findViewById(R.id.card_feedback_alert);

                Animator anim = getRevealAnimator(myView, feedbackAlert, false);

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        myView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                anim.start();

            }
        });
        //sets the onClickListener to the confirmation button on the feedback dialog
        feedbackConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View myView = rootView.findViewById(R.id.card_feedback_confirmation);
                myView.setVisibility(View.VISIBLE);
                getRevealAnimator(myView, feedbackConfirmButton, true).start();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final TextView mainText1 = rootView.findViewById(R.id.main_text_card_feedback_confirmation);
                        TextView secondaryText = rootView.findViewById(R.id.secondary_text_card_feedback_confirmation);
                        ImageView imageView = rootView.findViewById(R.id.imageView_card_feedback_confirmation);

                        final Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.feedback_confirmation_msg_anim);
                        mainText1.startAnimation(slideAnimation);
                        secondaryText.startAnimation(slideAnimation);
                        imageView.startAnimation(slideAnimation);

                        slideAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                //LinearLayout buttonsLayout = rootView.findViewById(R.id.layout_feedback_confirmation);
                                View divisorLine = rootView.findViewById(R.id.view_divisor_line);

                                divisorLine.setVisibility(View.VISIBLE);
                                feedbackYes.setVisibility(View.VISIBLE);
                                feedbackNo.setVisibility(View.VISIBLE);

//                                buttonsLayout.setVisibility(View.VISIBLE);
                                Animation slideToLeftAnim = AnimationUtils.loadAnimation(getContext(), R.anim.feedback_confirmation_options_title_anim);
                                Animation zoomAnim = AnimationUtils.loadAnimation(getContext(), R.anim.feedback_confirmation_options_anim);
                                Animation slideLeftAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.feedback_confirmation_divisor_line_anim);

                                divisorLine.startAnimation(slideLeftAnimation);
                                mainText1.setText("Deseja continuar fazendo os exercícios?");
                                mainText1.startAnimation(slideToLeftAnim);

                                feedbackYes.startAnimation(zoomAnim);
                                feedbackNo.startAnimation(zoomAnim);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }, 3000);

            }
        });
    }

    @NonNull
    private Animator getRevealAnimator(View reveledView, View revealStarting, boolean isReveal) {
        // get the center for the clipping circle
        int cx = (int)(revealStarting.getX() + (revealStarting.getWidth()/2));
        int cy = (int)(revealStarting.getY()+ (revealStarting.getHeight()/2));

        // get the final radius for the clipping circle
        int dx = Math.max(cx, reveledView.getWidth() - cx);
        int dy = Math.max(cy, reveledView.getHeight() - cy);

        float radius = (float) Math.hypot(dx, dy);

        Animator mAnimator;
        if (isReveal)
            mAnimator = ViewAnimationUtils.createCircularReveal(reveledView, cx, cy,0, radius);
        else
            mAnimator = ViewAnimationUtils.createCircularReveal(reveledView, cx, cy, radius, 0);

        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.setDuration(500);
        return mAnimator;
    }

    private void findViews() {
        feedbackAlert = rootView.findViewById(R.id.floating_button_feedback_alert);
        increaseRepetitions = rootView.findViewById(R.id.btn_increase_repetitions);
        decreaseRepetitions = rootView.findViewById(R.id.btn_decrease_repetitions);
        repetitionsNumber = rootView.findViewById(R.id.txt_repetitions);
        cardButtonConfirm = rootView.findViewById(R.id.card_feedback_alert_button_confirm);
        feedbackConfirmButton = rootView.findViewById(R.id.btn_feedback_confirm);
        feedbackYes = rootView.findViewById(R.id.btn_feedback_yes);
        feedbackNo = rootView.findViewById(R.id.btn_feedback_no);

        effortButtons = new ArrayList<>();
        effortButtons.add(new EffortButton(1, (TextView)rootView.findViewById(R.id.title_effort_lv_1), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_1)));
        effortButtons.add(new EffortButton(2, (TextView)rootView.findViewById(R.id.title_effort_lv_2), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_2)));
        effortButtons.add(new EffortButton(3, (TextView)rootView.findViewById(R.id.title_effort_lv_3), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_3)));
        effortButtons.add(new EffortButton(4, (TextView)rootView.findViewById(R.id.title_effort_lv_4), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_4)));
        effortButtons.add(new EffortButton(5, (TextView)rootView.findViewById(R.id.title_effort_lv_5), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_5)));


    }
}
