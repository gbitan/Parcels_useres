package com.example.parcels_useres.ui.gallery;

import android.app.Application;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.Data.reppositories.ParcelRepository;

public class HistoryParcelsViewModel extends AndroidViewModel {
    private ParcelRepository repository;
    private LiveData<List<Parcel>> allparcels;

    public HistoryParcelsViewModel(@NonNull Application application) {
        super(application);
        repository = new ParcelRepository(getApplication());
        repository.getHistoryParcels();
        allparcels = repository.getAllParcels();
    }

    public LiveData<List<Parcel>> getAllparcels() {
        return allparcels;
    }
}