package com.example.parcels_useres.Data.reppositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.parcels_useres.Data.models.Parcel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ParcelRepository {

    private final LiveData<List<Parcel>> allParcels;

    private ParcelDao parcelsDao;
    private DatabaseReference parcelsRef;

    public ParcelRepository(Application application) {
        ParcelDataBase database = ParcelDataBase.getInstance(application);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        parcelsRef = firebaseDatabase.getReference("parcels");

        parcelsDao = database.parcelDao();

        allParcels = parcelsDao.getAllParcels();
    }

    public void insert(Parcel parcel) {
        new InsertParcelAsyncTask(parcelsDao).execute(parcel);

    }
    public void getHistoryParcels(){
        parcelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // read from firebase
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Parcel parcel = snapshot.getValue(Parcel.class);
                        insert(parcel);
                    }
                }
                //  new InsertParcelAsyncTask(parcelsDao).execute((Parcel[]) parcels.toArray());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Parcel>> getAllParcels() {
        return allParcels;
    }

    private static class InsertParcelAsyncTask extends AsyncTask<Parcel, Void, Void> {
        private ParcelDao parcelsDao;

        private InsertParcelAsyncTask(ParcelDao parcelsDao) {
            this.parcelsDao = parcelsDao;
        }

        protected Void doInBackground(Parcel... parcels) {
            for (Parcel parcel : parcels) {
                parcelsDao.insert(parcel);
            }
            return null;
        }
    }
}