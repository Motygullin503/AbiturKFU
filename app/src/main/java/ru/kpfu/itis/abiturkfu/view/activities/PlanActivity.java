package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityPlanBinding;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.adapters.PlanPageRecyclerViewAdapter;

public class PlanActivity extends AppCompatActivity {
    private static final String KEY_FACILITY_ID = "FACILITY_ID";
    @Inject
    AbiturientRepository repository;
    private ActivityPlanBinding r;
    private PlanPageRecyclerViewAdapter adapter;

    public static void start(Context context, int facilityId) {
        Intent starter = new Intent(context, PlanActivity.class);
        starter.putExtra(KEY_FACILITY_ID, facilityId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        r = DataBindingUtil.setContentView(this, R.layout.activity_plan);

        setSupportActionBar(r.myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecyclerView();

        int facilityId = getIntent().getIntExtra(KEY_FACILITY_ID, 0);
        repository.getSpecialitiesByFacilityId(facilityId).observe(
                this,
                specialities -> adapter.setSpecialities(specialities),
                status -> r.myToolbar.showLoading(status == ResponseLiveData.Status.LOADING),
                Throwable::printStackTrace
        );
    }

    private void initRecyclerView() {
        adapter = new PlanPageRecyclerViewAdapter((view, specialityId) -> {
            // TODO: 16.05.18 Add reaction
            AboutExamsActivity.start(this, specialityId);
        });

        r.rvPlans.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
        }
        r.rvPlans.setLayoutManager(layoutManager);
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
