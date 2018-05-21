package ru.kpfu.itis.abiturkfu.model.database_module;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.kpfu.itis.abiturkfu.model.entities.Facility;

@Dao
public interface FacilityDao {
    // TODO: 21.05.18 add transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Facility... facility);

    @Query("SELECT * FROM facilities")
    LiveData<List<Facility>> getAllFacilities();

    @Query("SELECT * FROM facilities WHERE id == :id")
    LiveData<Facility> getFacilityById(int id);

    @Query("DELETE FROM facilities")
    void clearAll();
}