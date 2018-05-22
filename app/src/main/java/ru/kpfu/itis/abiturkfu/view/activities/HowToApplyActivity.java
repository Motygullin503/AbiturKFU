package ru.kpfu.itis.abiturkfu.view.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityHowToApplyBinding;

public class HowToApplyActivity extends AppCompatActivity {


    ActivityHowToApplyBinding r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_apply);
        r = DataBindingUtil.setContentView(this, R.layout.activity_how_to_apply);
        setSupportActionBar(r.myToolbar);



        r.next.setOnClickListener(view -> {
            r.card1.setVisibility(View.GONE);
            r.card2.setVisibility(View.VISIBLE);
        });

        r.next1.setOnClickListener(view -> {
            r.card2.setVisibility(View.GONE);
            r.card3.setVisibility(View.VISIBLE);
        });

        r.next2.setOnClickListener(view -> {
            r.card3.setVisibility(View.GONE);
            r.card4.setVisibility(View.VISIBLE);
        });

        r.inNext.setOnClickListener(view -> {
            r.inCard.setVisibility(View.GONE);
            r.inCard1.setVisibility(View.VISIBLE);
        });

        r.inNext1.setOnClickListener(view -> {
            r.inCard1.setVisibility(View.GONE);
            r.inCard2.setVisibility(View.VISIBLE);
        });

        r.inNext2No.setOnClickListener(view -> {
            r.inCard2.setVisibility(View.GONE);
            r.inCard3No.setVisibility(View.VISIBLE);
        });

        r.inNext3No.setOnClickListener(view -> {
            r.inCard3No.setVisibility(View.GONE);
            r.inCard4No.setVisibility(View.VISIBLE);
        });

        r.inNext4No.setOnClickListener(view -> {
            r.inCard4No.setVisibility(View.GONE);
            r.inCard5No.setVisibility(View.VISIBLE);
        });

        r.inNext2Yes.setOnClickListener(view -> {
            r.inCard2.setVisibility(View.GONE);
            r.inCard3Yes.setVisibility(View.VISIBLE);
        });

        r.inNext3Yes.setOnClickListener(view -> {
            r.inCard3Yes.setVisibility(View.GONE);
            r.inCard4Yes.setVisibility(View.VISIBLE);
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
