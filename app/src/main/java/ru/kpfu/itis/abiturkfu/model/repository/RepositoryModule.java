package ru.kpfu.itis.abiturkfu.model.repository;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @NonNull
    @Provides
    @Singleton
    AbiturientRepository getRepository() {
        return new AbiturientRepository();
    }
}
