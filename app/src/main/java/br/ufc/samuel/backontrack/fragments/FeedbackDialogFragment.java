package br.ufc.samuel.backontrack.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.util.EffortButton;

/**
 * Created by samuel on 25/01/2018.
 */

public class FeedbackDialogFragment extends DialogFragment {

    private View rootView;
    private List<EffortButton> effortButtons;
    public FeedbackDialogFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_fragment_feedback, container);

        findViews();

        return rootView;
    }

    private void findViews() {
        effortButtons = new ArrayList<>();
        effortButtons.add(new EffortButton(1, (TextView)rootView.findViewById(R.id.title_effort_lv_1), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_1)));
        effortButtons.add(new EffortButton(2, (TextView)rootView.findViewById(R.id.title_effort_lv_2), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_2)));
        effortButtons.add(new EffortButton(3, (TextView)rootView.findViewById(R.id.title_effort_lv_3), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_3)));
        effortButtons.add(new EffortButton(4, (TextView)rootView.findViewById(R.id.title_effort_lv_4), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_4)));
        effortButtons.add(new EffortButton(5, (TextView)rootView.findViewById(R.id.title_effort_lv_5), (ImageButton)rootView.findViewById(R.id.ic_effort_lv_5)));

        for (int i = 0; i < effortButtons.size(); i++) {
            final int j = i;
            effortButtons.get(i).getBtn().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // setDefaultButton(btnList.get(0), btnList);
                    effortButtons.get(j).select(rootView.getContext(), effortButtons);
                }
            });
        }
    }
}
