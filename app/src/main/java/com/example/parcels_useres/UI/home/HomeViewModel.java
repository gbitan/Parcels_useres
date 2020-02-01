package com.example.parcels_useres.UI.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.Data.reppositories.ParcelRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
  /*  private ParcelRepository repository;
    private LiveData<List<Parcel>> mParcels;
    private MutableLiveData<String> mText;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository=new ParcelRepository(getApplication());
        mParcels = repository.getAllParcels();
        mText = new MutableLiveData<>();
          mText.setValue((mParcels.getValue()).get(0).getName());
    }

    public LiveData<String> getText() {
        return mText;
    }*/

  //    -------------test-----------
  private MutableLiveData<String> mText;

    public HomeViewModel(@NonNull Application application) {
      super(application);
        mText = new MutableLiveData<>();
        Parcel p = new Parcel();
        p.setName("avi");
        p.setEmail("avi@exemple.com");
        p.setFragile(true);
        p.setId(1);
        p.setParcelKind(Parcel.ParcelKind.BIG_PARCEL);
        p.setParcelStatus(Parcel.ParcelStatus.ON_WAY);
        p.setW(Parcel.Weight.LESS_THEN_5_KG);
        mText.setValue(p.getName());
  }
    public LiveData<String> getText() {
        return mText;
    }
}