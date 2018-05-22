package ru.kpfu.itis.abiturkfu.view.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.FragmentHowToApplyBinding;
import ru.kpfu.itis.abiturkfu.view.activities.HowToApplyActivity;

public class HowToApplyFragment extends Fragment {

    FragmentHowToApplyBinding r;

    public HowToApplyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        r = DataBindingUtil.inflate(inflater, R.layout.fragment_how_to_apply, container, false);

        r.cvRules.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), HowToApplyActivity.class);
            startActivity(intent);
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Как поступить?");


        return r.getRoot();
    }

}
