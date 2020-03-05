package com.example.parcels_useres.UI.share;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.Data.reppositories.ParcelRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ShareViewModel extends AndroidViewModel {

    private ParcelRepository repository;
    private LiveData<List<Parcel>> mParcels;
    private MutableLiveData<String> mText;

    private FirebaseUser user;
    public ShareViewModel(@NonNull Application application) {
        super(application);
        repository=new ParcelRepository(getApplication());
        user = FirebaseAuth.getInstance().getCurrentUser();

        mParcels = repository.getAllMyParcels(user.getEmail());
        mText = new MutableLiveData<>();
        if (mParcels.getValue() != null)
            mText.setValue((mParcels.getValue()).get(0).getName());
        else
            mText.setValue("no parcels found");
    }



    public LiveData<List<Parcel>> getParcels() {
        return mParcels;
    }

}