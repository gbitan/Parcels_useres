package com.example.parcels_useres.Data.reppositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.parcels_useres.Data.models.Converters;
import com.example.parcels_useres.Data.models.Parcel;


    @Database(entities = {Parcel.class}, version = 1)
    @TypeConverters({Converters.class})
    public abstract class ParcelDataBase extends RoomDatabase {

        private static ParcelDataBase instance;

        public abstract ParcelDao parcelDao();

        public static synchronized ParcelDataBase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ParcelDataBase.class, "parcels_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }

      /*  private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDbAsyncTask(instance).execute();
            }
        };

        private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
            private ParcelDao parcelDao;

            private PopulateDbAsyncTask(ParcelDataBase db) {
                parcelDao = db.parcelDao();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                parcelDao.insert(new Parcel("Title 1", "Description 1", 1));
                parcelDao.insert(new Parcel("Title 2", "Description 2", 2));
                parcelDao.insert(new Parcel("Title 3", "Description 3", 3));
                return null;

           }

       */
        }

