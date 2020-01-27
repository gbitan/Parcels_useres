package com.example.parcels_useres.UI.my_parcels;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.app.Application;
import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.Data.reppositories.ParcelRepository;

import java.util.List;

public class MyParcelsViewModel extends AndroidViewModel {
private ParcelRepository repository;
    private LiveData<List<Parcel>> mParcels;

    public MyParcelsViewModel(@NonNull Application application) {
        super(application);
        repository=new ParcelRepository(getApplication());
        mParcels = repository.getAllParcels();
    }



    public LiveData<List<Parcel>> getParcels() {
        return mParcels;
    }
}