package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityAboutFacilityBinding;

public class AboutFacilityActivity extends AppCompatActivity {
    private static final String KEY_DESCRIPTION = "DESCRIPTION";
    private ActivityAboutFacilityBinding r;

    public static void start(Context context, String description) {
        Intent starter = new Intent(context, AboutFacilityActivity.class);
        starter.putExtra(KEY_DESCRIPTION, description);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_about_facility);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String description = getIntent().getStringExtra(KEY_DESCRIPTION);
        r.tvAbout.setText(description);
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
