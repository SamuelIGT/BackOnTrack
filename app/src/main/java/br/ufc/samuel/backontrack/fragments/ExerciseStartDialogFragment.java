package br.ufc.samuel.backontrack.fragments;

import android.animation.Animator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 20/01/2018.
 */

public class ExerciseStartDialogFragment extends DialogFragment {

    private Button confirm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_exercise_start, container);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        confirm = view.findViewById(R.id.btn_exerciseStart_confirmation);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmationDialogFrament dialogFrament = ConfirmationDialogFrament.newInstance(
                        (int)(confirm.getX() + (confirm.getWidth()/2)),
                        (int)((confirm.getY())+ confirm.getHeight() + 56),
                        getResources().getColor(R.color.colorSuccess)
                );

                dialogFrament.show(getActivity().getSupportFragmentManager(), "Dialog confirmation");
            }
        });

        return view;
    }
}
