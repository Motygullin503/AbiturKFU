package ru.kpfu.itis.abiturkfu.model.database_module;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import ru.kpfu.itis.abiturkfu.model.entities.Facility;

@Dao
public abstract class FacilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(Facility... facility);

    @Query("SELECT * FROM facilities")
    public abstract LiveData<List<Facility>> getAllFacilities();

    @Query("SELECT * FROM facilities WHERE id == :id")
    public abstract LiveData<Facility> getFacilityById(int id);

    @Query("DELETE FROM facilities")
    public abstract void clearAll();

    @Transaction
    public void clearAndInsert(Facility... facilities){
        clearAll();
        insertAll(facilities);
    }

}