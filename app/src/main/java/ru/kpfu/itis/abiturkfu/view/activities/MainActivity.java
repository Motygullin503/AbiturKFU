package ru.kpfu.itis.abiturkfu.view.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.kpfu.itis.abiturkfu.R;
import ru.kpfu.itis.abiturkfu.view.fragments.AboutUsFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.CalendarFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.ContestsFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.CoursesFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.HowToApplyFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.MainFragment;
import ru.kpfu.itis.abiturkfu.view.view_models.MainActivityViewModel;
import ru.kpfu.itis.abiturkfu.view.views.CenteredToolbar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String SUFF = "MAIN";
    private CenteredToolbar toolbar;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        toolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0.0f);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_main);
        setFragmentByClass(viewModel.getLastFragmentClazz());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_main:
                setFragmentByClass(MainFragment.class);
                break;
            case R.id.nav_calculate_exams:

                break;
            case R.id.nav_abiturient_calendar:
                setFragmentByClass(CalendarFragment.class);
                break;
            case R.id.nav_courses:
                setFragmentByClass(CoursesFragment.class);
                break;
            case R.id.nav_contests:
                setFragmentByClass(ContestsFragment.class);
                break;
            case R.id.nav_how_to_enter:
                setFragmentByClass(HowToApplyFragment.class);
                break;
            case R.id.nav_about:
                setFragmentByClass(AboutUsFragment.class);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragmentByClass(Class<? extends Fragment> fragmentClass) {
        viewModel.setLastFragmentClazz(fragmentClass);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentClass.getName() + SUFF);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragmentClass.getName() + SUFF)
                .commit();
    }

    public void showLoading(boolean loading) {
        toolbar.showLoading(loading);
    }
}
