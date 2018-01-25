package br.ufc.samuel.backontrack.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samuel on 25/01/2018.
 */

public class FeedbackDialogFragment extends DialogFragment {

    private View rootView;

    public FeedbackDialogFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_feedback, container);

        return rootView;
    }
}
