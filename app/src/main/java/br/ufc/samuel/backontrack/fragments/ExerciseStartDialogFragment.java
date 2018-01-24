package br.ufc.samuel.backontrack.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import java.util.List;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 20/01/2018.
 */

public class ExerciseStartDialogFragment extends DialogFragment {

    private View view;
    private Button confirm;
    private int checkBoxesNumber = 0;
    private int checkedCheckboxes = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_fragment_exercise_start, container);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        setUpConfimationButton();
        getCheckBoxes();
        return view;
    }

    private void getCheckBoxes() {
        RelativeLayout layout = view.findViewById(R.id.layout_check_boxes);

        for (int i = 0; i < layout.getChildCount(); i++) {
            //if the child is a CheckBox
            if (layout.getChildAt(i) instanceof CheckBox) {
                checkBoxesNumber ++;
                CheckBox checkBox = (CheckBox) layout.getChildAt(i);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked){
                            checkedCheckboxes ++;
                        }else{
                            checkedCheckboxes --;
                        }

                        if(checkedCheckboxes == checkBoxesNumber) {
                            confirm.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
                            confirm.setEnabled(true);
                        }

                        else {
                            confirm.setBackgroundColor(getResources().getColor(R.color.colorDisabled));
                            confirm.setEnabled(false);
                        }
                    }
                });
            }
        }
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

                dialogFrament.show(getActivity().getSupportFragmentManager(), "Dialog confirmation");
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
