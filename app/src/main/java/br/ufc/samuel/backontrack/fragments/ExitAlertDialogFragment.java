package br.ufc.samuel.backontrack.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 24/01/2018.
 */

public class ExitAlertDialogFragment extends DialogFragment {
    private View rootView;
    private Button btnYes;
    private Button btnNo;
    
    public ExitAlertDialogFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_exit_alert, container);

        btnYes = rootView.findViewById(R.id.btn_yes);
        btnNo = rootView.findViewById(R.id.btn_no);

        setOnClickListeners();

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return rootView;
    }

    private void setOnClickListeners() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: chamar frament de feedback.
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /*@Override
    public void onStart() {
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        super.onStart();
    }*/
}
