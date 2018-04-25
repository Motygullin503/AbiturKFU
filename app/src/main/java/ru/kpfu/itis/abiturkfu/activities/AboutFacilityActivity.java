package ru.kpfu.itis.abiturkfu.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.databinding.ActivityAboutFacilityBinding;

public class AboutFacilityActivity extends AppCompatActivity {
    private ActivityAboutFacilityBinding r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = DataBindingUtil.setContentView(this, R.layout.activity_about_facility);

        setSupportActionBar(r.myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        r.tvAbout.setText("Высшая школа  информационных технологий и информационных систем (ИТИС) -  инновационный ИТ-факультет КФУ, который был основан в 2011 году совместными усилиями Министерства информатизации и связи РТ, Казанского федерального университета, мировых брендов IBM,HP, Oracle, представителями крупнейших IT-компаний региона.Высшая школа  информационных технологий и информационных систем (ИТИС) -  инновационный ИТ-факультет КФУ, который был основан в 2011 году совместными усилиями Министерства информатизации и связи РТ, Казанского федерального университета, мировых брендов IBM,HP, Oracle, представителями крупнейших IT-компаний региона.");
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
