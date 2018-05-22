package ru.kpfu.itis.abiturkfu.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityContestBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Contest;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;

public class ContestActivity extends AppCompatActivity {

    private ActivityContestBinding r;
    private static final String KEY_CONTEST_ID = "CONTEST_ID";
    private int contestId;

    @Inject
    AbiturientRepository repository;

    public static void start(Context context, int facilityId) {
        Intent starter = new Intent(context, ContestActivity.class);
        starter.putExtra(KEY_CONTEST_ID, facilityId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_contest);
        App.getComponent().inject(this);

        contestId = getIntent().getIntExtra(KEY_CONTEST_ID, 0);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int id = getIntent().getIntExtra(KEY_CONTEST_ID, 0);
        repository.getContestById(id).observe(
                this,
                this::fill,
                status -> r.myToolbar.showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    void fill(Contest contest){
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
