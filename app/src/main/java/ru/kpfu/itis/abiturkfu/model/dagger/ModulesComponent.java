package ru.kpfu.itis.abiturkfu.model.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.kpfu.itis.abiturkfu.model.database_module.DatabaseModule;
import ru.kpfu.itis.abiturkfu.model.network_module.NetModule;
import ru.kpfu.itis.abiturkfu.model.repository.AbiturientRepository;
import ru.kpfu.itis.abiturkfu.model.repository.RepositoryModule;
import ru.kpfu.itis.abiturkfu.view.activities.FacilityActivity;
import ru.kpfu.itis.abiturkfu.view.activities.FilteredFacilitiesActivity;
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
}
