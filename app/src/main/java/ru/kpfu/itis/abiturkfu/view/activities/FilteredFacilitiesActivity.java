package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityFilteredFacilitiesBinding;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.view.adapters.MainPageRecyclerViewAdapter;

public class FilteredFacilitiesActivity extends AppCompatActivity {
    private static final String KEY_CITIES = "CITIES";
    private static final String KEY_FORMS = "FORMS";
    private static final String KEY_TYPES = "TYPES";
    @Inject
    AbiturientRepository repository;
    private MainPageRecyclerViewAdapter adapter;
    private ActivityFilteredFacilitiesBinding r;

    public static void start(Context context, @Nullable ArrayList<String> cities, @Nullable ArrayList<String> forms, @Nullable ArrayList<String> types) {
        Intent starter = new Intent(context, FilteredFacilitiesActivity.class);
        starter.putStringArrayListExtra(KEY_CITIES, cities);
        starter.putStringArrayListExtra(KEY_FORMS, forms);
        starter.putStringArrayListExtra(KEY_TYPES, types);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_filtered_facilities);
        App.getComponent().inject(this);

        setSupportActionBar(r.myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecyclerView();

        ArrayList<String> cities = getIntent().getStringArrayListExtra(KEY_CITIES);
        ArrayList<String> forms = getIntent().getStringArrayListExtra(KEY_FORMS);
        ArrayList<String> types = getIntent().getStringArrayListExtra(KEY_TYPES);

        r.myToolbar.showLoading(true);
        repository.getFacilitiesByFilter(cities, forms, types)
                .observe(this, facilities -> {
                    adapter.setData(facilities);
                    r.myToolbar.showLoading(false);
                });

        repository.getStatusLiveData()
                .observe(this, s -> {
                    if (s != null) {
                        r.myToolbar.showLoading(false);
                        Snackbar.make(r.getRoot(), s, Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void initRecyclerView() {
        adapter = new MainPageRecyclerViewAdapter((view, id) -> {
            FacilityActivity.start(this, id);
        });

        r.list.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
        }
        r.list.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
