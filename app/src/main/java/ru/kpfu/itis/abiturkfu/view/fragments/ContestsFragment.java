package ru.kpfu.itis.abiturkfu.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.model.entities.Contest;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.activities.ContestActivity;
import ru.kpfu.itis.abiturkfu.view.activities.MainActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.ContestsAdapter;


public class ContestsFragment extends Fragment {

    RecyclerView rvContests;
    private ContestsAdapter adapter;

    @Inject
    AbiturientRepository repository;

    public ContestsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view =  inflater.inflate(R.layout.fragment_contests, container, false);
        rvContests = view.findViewById(R.id.rv_contests);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Конкурсы");


        initializeRecyclerView();

        repository.getContests().observe(
                this,
                this::fill,
                status -> ((MainActivity) getActivity()).showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );

        return view;
    }

    void initializeRecyclerView() {
        adapter = new ContestsAdapter(((view, contestId) -> ContestActivity.start(getContext(), contestId)));
        rvContests.setAdapter(adapter);
        rvContests.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    void fill(List<Contest> contests) {
        adapter.setData(contests);
    }

}
