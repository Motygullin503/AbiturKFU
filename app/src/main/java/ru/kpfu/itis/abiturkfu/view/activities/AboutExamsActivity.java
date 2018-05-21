package ru.kpfu.itis.abiturkfu.view.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityAboutExamsBinding;
import ru.kpfu.itis.abiturkfu.model.entities.Speciality;
import ru.kpfu.itis.abiturkfu.model.entities.SpecialtyEducationType;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.ResponseLiveData;
import ru.kpfu.itis.abiturkfu.view.adapters.SubjectChipAdapter;

public class AboutExamsActivity extends AppCompatActivity {
    private static final String KEY_SPECIALITY_ID = "SPECIALITY_ID";
    @Inject
    AbiturientRepository repository;
    private ActivityAboutExamsBinding r;
    private SubjectChipAdapter adapter;

    public static void start(Context context, int specialityId) {
        Intent starter = new Intent(context, AboutExamsActivity.class);
        starter.putExtra(KEY_SPECIALITY_ID, specialityId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        r = DataBindingUtil.setContentView(this, R.layout.activity_about_exams);

        setSupportActionBar(r.myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecyclerView();

        int specialityId = getIntent().getIntExtra(KEY_SPECIALITY_ID, 0);

        repository.getSpecialityById(specialityId).observe(
                this,
                this::fillSpeciality,
                status -> r.myToolbar.showLoading(status == ResponseLiveData.Status.LOADING),
                throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    @SuppressLint("DefaultLocale")
    private void fillSpeciality(Speciality speciality) {
        adapter.setSubjects(speciality.getSubjects());
        for (SpecialtyEducationType educationType : speciality.getSpecialtyEducationTypes()) {
            switch (educationType.getEducationTypeId()) {
                case 1: //Budget id
                    r.cvBudget.setVisibility(View.VISIBLE);
                    r.tvBudgetOf.setText(String.format("/%d", 100 * speciality.getSubjects().size()));
                    r.tvBudgetScore.setText(String.valueOf(educationType.getScore()));
                    r.tvBudgetPlaceCount.setText(String.format("Количество мест: %d", educationType.getPlace()));
                    break;
                case 3: //Contract id
                    r.cvContract.setVisibility(View.VISIBLE);
                    r.tvContractOf.setText(String.format("/%d", 100 * speciality.getSubjects().size()));
                    r.tvContractScore.setText(String.valueOf(educationType.getScore()));
                    r.tvContractPlaceCount.setText(String.format("Количество мест: %d", educationType.getPlace()));
            }
        }
    }

    private void initRecyclerView() {
        adapter = new SubjectChipAdapter();
        r.rvSubjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r.rvSubjects.setAdapter(adapter);
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
