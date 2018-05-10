package ru.kpfu.itis.abiturkfu.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import java.util.ArrayList;

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
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_filtered:
                ArrayList<String> cities = new ArrayList<>();
                addIfNecessary(r.button1Offices, cities);
                addIfNecessary(r.button2Offices, cities);
                addIfNecessary(r.button3Offices, cities);
                addIfNecessary(r.button4Offices, cities);
                String badWord = "Н. Челны";
                String goodWord = "Набережные Челны";
                if (cities.contains(badWord)) {
                    cities.remove(badWord);
                    cities.add(goodWord);
                }

                ArrayList<String> forms = new ArrayList<>();
                addIfNecessary(r.button1Forms, forms);
                addIfNecessary(r.button2Forms, forms);
                addIfNecessary(r.button3Forms, forms);

                ArrayList<String> types = new ArrayList<>();
                addIfNecessary(r.button1Types, types);
                addIfNecessary(r.button2Types, types);
                addIfNecessary(r.button3Types, types);

                FilteredFacilitiesActivity.start(this, cities, forms, types);
                break;
        }
    }

    private void addIfNecessary(CheckedTextView view, ArrayList<String> list) {
        if (view.isChecked()) {
            list.add(view.getText().toString());
        }
    }
}
