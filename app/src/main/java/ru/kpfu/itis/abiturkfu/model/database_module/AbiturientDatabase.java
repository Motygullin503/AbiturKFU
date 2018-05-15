package ru.kpfu.itis.abiturkfu.model.database_module;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.kpfu.itis.abiturkfu.model.entities.Facility;

/**
 * Stores all of DAOs in {@link AbiturientDatabase}
 */
@Database(entities = {Facility.class}, version = 1, exportSchema = false)
public abstract class AbiturientDatabase extends RoomDatabase {
    public abstract FacilityDao getFacilityDao();
}
