package ru.kpfu.itis.abiturkfu.model.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.kpfu.itis.abiturkfu.model.database_module.DatabaseModule;
import ru.kpfu.itis.abiturkfu.model.network_module.NetModule;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.RepositoryModule;
import ru.kpfu.itis.abiturkfu.view.activities.AboutExamsActivity;
import ru.kpfu.itis.abiturkfu.view.activities.CourseActivity;
import ru.kpfu.itis.abiturkfu.view.activities.FacilityActivity;
import ru.kpfu.itis.abiturkfu.view.activities.FilteredFacilitiesActivity;
import ru.kpfu.itis.abiturkfu.view.activities.PlanActivity;
import ru.kpfu.itis.abiturkfu.view.fragments.AboutUsFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.CoursesFragment;
import ru.kpfu.itis.abiturkfu.view.fragments.MainFragment;

/**
 * All injects must be added there
 */
@Singleton
@Component(modules = {NetModule.class, DatabaseModule.class, RepositoryModule.class})
public interface ModulesComponent {
    void inject(MainFragment fragment);

    void inject(FacilityActivity activity);

    void inject(FilteredFacilitiesActivity activity);

    void inject(AbiturientRepository repository);

    void inject(PlanActivity planActivity);

    void inject(AboutExamsActivity aboutExamsActivity);

    void inject(CoursesFragment coursesFragment);

    void inject(CourseActivity courseActivity);

    void inject(AboutUsFragment aboutUsFragment);
}
