package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ru.kpfu.itis.abiturkfu.R;


import ru.kpfu.itis.abiturkfu.databinding.ActivityContestBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Contest;

public class ContestActivity extends AppCompatActivity {

    private ActivityContestBinding r;
    private static final String KEY_CONTEST_ID = "CONTEST_ID";
    private int contestId;

    public static void start(Context context, int facilityId) {
        Intent starter = new Intent(context, FacilityActivity.class);
        starter.putExtra(KEY_CONTEST_ID, facilityId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        contestId = getIntent().getIntExtra(KEY_CONTEST_ID, 0);
        r = DataBindingUtil.setContentView(this, R.layout.activity_contest);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void fill(Contest contest){

        //ToDo fill
        r.contestInfo.setText(contest.getDescription());
        r.contestParticipation.setText(contest.getRequires());
        r.contestPrize.setText(contest.getReward());
        r.tvName.setText(contest.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
