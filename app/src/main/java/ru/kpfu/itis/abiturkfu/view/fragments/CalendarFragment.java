package ru.kpfu.itis.abiturkfu.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.FragmentCalendarBinding;

public class CalendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCalendarBinding r = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);

        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Календарь");
        }


        return r.getRoot();
    }

}
