package br.ufc.samuel.backontrack.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.activity.ExerciseExecutionActivity;

/**
 * Created by samue on 20/01/2018.
 */

public class ExerciseStartDialogFragment extends DialogFragment {

    private View view;
    private Button confirm;
    private int checkBoxesNumber = 0;
    private int checkedCheckboxes = 0;

    public static ExerciseStartDialogFragment newInstance(String[] objects, String argsKey){
        Bundle args = new Bundle();
        args.putStringArray(argsKey, objects);

        ExerciseStartDialogFragment fragment = new ExerciseStartDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_fragment_exercise_start, container);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        setUpConfimationButton();

        setupCheckboxes();

        //getCheckBoxes();
        return view;
    }

    private void setupCheckboxes() {
        LinearLayout parentLayout = view.findViewById(R.id.layout_check_boxes); //Getting the checkboxes layout

        float d = getResources().getDisplayMetrics().density;
        int checkboxMarginStart = (int)(getResources().getDimension(R.dimen.checkbox_margin_start) * d);
        int checkboxMarginTop = (int)(getResources().getDimension(R.dimen.checkbox_margin_top) * d);

        //creating the layout parameters
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams checkboxLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        String[] objects = getArguments().getStringArray(getString(R.string.ARGS_EXERCISE_START_DIALOG));

        checkBoxesNumber = objects.length;

        if(objects != null){
            for(int i = 0; i < objects.length; i++){

                LinearLayout layout = new LinearLayout(view.getContext());
                TextView text = new TextView(view.getContext());
                CheckBox checkBox = new CheckBox(view.getContext());

                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutParams.setMargins(checkboxMarginStart, checkboxMarginTop, 0, 0);
                layout.setLayoutParams(layoutParams);

                checkBox.setLayoutParams(checkboxLayoutParams);
                checkBox.setScaleX(1.5f);
                checkBox.setScaleY(1.5f);
                //checkBox.setTextAppearance(view.getContext(), R.style.checkBoxStyle);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked){
                            checkedCheckboxes ++;
                        }else{
                            checkedCheckboxes --;
                        }

                        if(checkedCheckboxes == checkBoxesNumber) {
                            confirm.setBackground(getResources().getDrawable(R.drawable.shape_btn_confirm));
                            confirm.setEnabled(true);
                        }

                        else {
                            confirm.setBackground(getResources().getDrawable(R.drawable.shape_btn_confirm_disabled));
                            confirm.setEnabled(false);
                        }
                    }
                });

                text.setLayoutParams(textViewLayoutParams);
                text.setPadding(8, 0, 0, 0);
                text.setGravity(Gravity.CENTER);
                text.setTextSize(18);
                text.setText(objects[i]);

                layout.addView(checkBox);
                layout.addView(text);

                parentLayout.addView(layout);
            }
        }else{
            TextView text = new TextView(view.getContext());

            text.setLayoutParams(textViewLayoutParams);
            text.setPadding(4, 0, 0, 0);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(18);
            text.setText(R.string.NULL_OBJECTS_MESSAGE);

            confirm.setEnabled(true);
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setUpConfimationButton() {
        confirm = view.findViewById(R.id.btn_exerciseStart_confirmation);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConfirmationDialogFragment dialogFrament = ConfirmationDialogFragment.newInstance(
                        (int)(confirm.getX() + (confirm.getWidth()/2)),
                        (int)(confirm.getY()+ (confirm.getHeight()/2)),
                        getResources().getColor(R.color.colorSuccess)
                );

                dialogFrament.show(getActivity().getSupportFragmentManager(), "Dialog Confirmation");

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                        ((ExerciseExecutionActivity)getActivity()).startVideo();
                    }
                }, 450);
            }
        });
        confirm.setEnabled(false);
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
