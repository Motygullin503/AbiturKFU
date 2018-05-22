package ru.kpfu.itis.abiturkfu.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.FragmentCalculatorBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Subject;
import ru.kpfu.itis.abiturkfu.view.activities.FilteredFacilitiesActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.CalculatorAdater;

public class CalculatorFragment extends Fragment {
    private CalculatorAdater adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCalculatorBinding r = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Калькулятор");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, "Русский язык"));
        subjects.add(new Subject(2, "Физика"));
        subjects.add(new Subject(3, "Обществознание"));
        subjects.add(new Subject(4, "Биология"));
        subjects.add(new Subject(5, "Математика"));
        subjects.add(new Subject(6, "География"));
        subjects.add(new Subject(7, "Химия"));
        subjects.add(new Subject(8, "История"));
        subjects.add(new Subject(9, "Иностранный язык"));
        subjects.add(new Subject(10, "Литература"));
        subjects.add(new Subject(11, "Творческий конкурс"));
        subjects.add(new Subject(12, "Рисунок, живопись, композиция"));
        subjects.add(new Subject(13, "Информатика"));

        adapter = new CalculatorAdater(subjects);
        r.rvCalc.setLayoutManager(new LinearLayoutManager(getContext()));
        r.rvCalc.setAdapter(adapter);

        r.btnShow.setOnClickListener(view -> {
            ArrayList<String> s = adapter.getSelectedSubjects();
            if (s == null || s.size() < 3) {
                Toast.makeText(getContext(), "Выберите больше 2", Toast.LENGTH_SHORT).show();
                return;
            }
            FilteredFacilitiesActivity.start(getContext(), s);
        });

        return r.getRoot();
    }
}
