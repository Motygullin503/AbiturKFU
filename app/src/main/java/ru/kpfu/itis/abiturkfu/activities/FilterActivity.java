package ru.kpfu.itis.abiturkfu.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{

    Button showFiltered;
    private ActivityFilterBinding r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_filter);

        Toolbar toolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showFiltered = findViewById(R.id.show_filtered);
        showFiltered.setOnClickListener(this);

        initFormsGroup();
        initTypesGroup();
        initOfficesGroup();

    }

    private void initTypesGroup() {
        r.button1Types.setOnClickListener(v -> {
            r.button1Types.toggle();
        });
        r.button2Types.setOnClickListener(v -> {
            r.button2Types.toggle();
        });
        r.button3Types.setOnClickListener(v -> {
            r.button3Types.toggle();
        });
    }

    private void initOfficesGroup() {
        r.button1Offices.setOnClickListener(v -> {
            r.button1Offices.toggle();
        });
        r.button2Offices.setOnClickListener(v -> {
            r.button2Offices.toggle();
        });
        r.button3Offices.setOnClickListener(v -> {
            r.button3Offices.toggle();
        });
        r.button4Offices.setOnClickListener(v -> {
            r.button4Offices.toggle();
        });
    }

    private void initFormsGroup() {
        r.button1Forms.setOnClickListener(v -> {
            r.button1Forms.toggle();
        });
        r.button2Forms.setOnClickListener(v -> {
            r.button2Forms.toggle();
        });
        r.button3Forms.setOnClickListener(v -> {
            r.button3Forms.toggle();
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_filtered:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
