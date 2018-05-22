package ru.kpfu.itis.abiturkfu.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.FragmentMainBinding;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.activities.FacilityActivity;
import ru.kpfu.itis.abiturkfu.view.activities.FilterActivity;
import ru.kpfu.itis.abiturkfu.view.activities.MainActivity;
import ru.kpfu.itis.abiturkfu.view.adapters.MainPageRecyclerViewAdapter;


public class MainFragment extends Fragment {
    private MainPageRecyclerViewAdapter adapter;
    private FragmentMainBinding r;

    @Inject
    AbiturientRepository repository;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        App.getComponent().inject(this);
        r = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Главное");
        }
        setHasOptionsMenu(true);

        initRecyclerView();

        repository.getAllFacilities(savedInstanceState == null)
                .observe(
                        this,
                        facilities -> adapter.setData(facilities),
                        status -> ((MainActivity) getActivity()).showLoading(status == ResponseLiveData.Status.LOADING),
                        throwable -> Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                );

        return r.getRoot();
    }

    private void initRecyclerView() {
        adapter = new MainPageRecyclerViewAdapter((view, id) -> {
            FacilityActivity.start(getContext(), id);
        });

        r.list.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        r.list.setLayoutManager(layoutManager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Intent intent = new Intent(getContext(), FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
