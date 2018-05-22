package ru.kpfu.itis.abiturkfu.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Contest;
import ru.kpfu.itis.abiturkfu.view.activities.ContestActivity;
import ru.kpfu.itis.abiturkfu.view.activities.CourseActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.ContestsAdapter;
import ru.kpfu.itis.abiturkfu.view.adapters.CoursesAdapter;


public class ContestsFragment extends Fragment {

    RecyclerView rvContests;
    private ContestsAdapter adapter;

    public ContestsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_contests, container, false);
        rvContests = view.findViewById(R.id.rv_contests);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Конкурсы");


        initializeRecyclerView();

        return view;
    }

    void initializeRecyclerView() {
        adapter = new ContestsAdapter(((view, contestId) -> ContestActivity.start(getContext(), contestId)));
        rvContests.setAdapter(adapter);
        rvContests.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    void fill(List<Contest> contests) {
        //ToDo fill
        adapter.setData(contests);
    }

}
