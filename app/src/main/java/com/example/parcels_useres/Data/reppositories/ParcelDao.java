package com.example.parcels_useres.Data.reppositories;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parcels_useres.Data.models.Parcel;

import java.util.List;

@Dao
public  interface ParcelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Parcel parcel);

    @Update
    void update(Parcel parcel);

    @Delete
    void delete(Parcel parcel);

    @Query("DELETE FROM parcel_table")
    void deleteAllParcels();

    @Query("SELECT * FROM parcel_table ORDER BY name DESC")
    LiveData<List<Parcel>> getAllParcels();

}
