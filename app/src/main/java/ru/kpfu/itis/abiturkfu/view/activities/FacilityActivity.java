package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityFacilityBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Facility;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;

public class FacilityActivity extends AppCompatActivity {
    private ActivityFacilityBinding r;
    private static final String KEY_FACILITY_ID = "FACILITY_ID";

    @Inject
    AbiturientRepository repository;

    public static void start(Context context, int facilityId) {
        Intent starter = new Intent(context, FacilityActivity.class);
        starter.putExtra(KEY_FACILITY_ID, facilityId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_facility);
        App.getComponent().inject(this);
        int id = getIntent().getIntExtra(KEY_FACILITY_ID, 0);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        r.cvAboutFacility.setOnClickListener(v -> {
            Intent i = new Intent(this, AboutFacilityActivity.class);
            startActivity(i);
        });
        r.cvPlan.setOnClickListener(v -> {

        });

        repository.getFacilityById(id, savedInstanceState == null)
                .observe(
                        this,
                        this::fillInfo,
                        status -> r.myToolbar.showLoading(status == ResponseLiveData.Status.LOADING),
                        throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void fillInfo(Facility facility) {
        if (facility != null) {
            r.tvFacilityName.setText(facility.getName());
            r.tvPhone.setText(facility.getPhone());
            r.tvMail.setText(facility.getEmail());
            r.tvLocation.setText(facility.getAddress());
            r.tvExamPoints.setText(String.format(getResources().getString(R.string.average_points), facility.getMiddle_score()));
            r.btnWebSite.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(facility.getWebsite()));
                startActivity(i);
            });
            Glide.with(this)
                    .load(facility.getImage_link())
                    .into(r.ivLogo);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
