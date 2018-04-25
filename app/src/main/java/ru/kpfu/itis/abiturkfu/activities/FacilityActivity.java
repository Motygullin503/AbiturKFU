package ru.kpfu.itis.abiturkfu.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityFacilityBinding;

public class FacilityActivity extends AppCompatActivity {
    private ActivityFacilityBinding r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_facility);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        r.btnWebSite.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://kpfu.ru/itis"));
            startActivity(i);
        });

        r.cvAboutFacility.setOnClickListener(v -> {
            Intent i = new Intent(this, AboutFacilityActivity.class);
            startActivity(i);
        });
        r.cvPlan.setOnClickListener(v -> {

        });
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
