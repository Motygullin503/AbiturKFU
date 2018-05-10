package ru.kpfu.itis.abiturkfu;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.kpfu.itis.abiturkfu.model.dagger.DaggerModulesComponent;
import ru.kpfu.itis.abiturkfu.model.dagger.ModulesComponent;
import ru.kpfu.itis.abiturkfu.model.database_module.DatabaseModule;
import ru.kpfu.itis.abiturkfu.model.network_module.NetModule;

/**
 * Custom Application to have connection to dependency injection always
 */
public class App extends Application {
    private static ModulesComponent component;

    /**
     * Returns {@link ModulesComponent} that can inject fields in your class
     */
    public static ModulesComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = getBuild();
    }

    @NonNull
    protected ModulesComponent getBuild() {
        return DaggerModulesComponent.builder()
                .netModule(new NetModule())
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .build();
    }
}
