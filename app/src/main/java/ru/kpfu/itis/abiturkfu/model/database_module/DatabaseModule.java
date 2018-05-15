package ru.kpfu.itis.abiturkfu.model.database_module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    public static final String DATABASE_NAME = "main_database";
    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @NonNull
    @Provides
    @Singleton
    public AbiturientDatabase getDatabase() {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                AbiturientDatabase.class,
                DATABASE_NAME
        ).build();
    }
}
