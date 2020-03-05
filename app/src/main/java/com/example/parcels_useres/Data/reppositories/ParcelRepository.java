package com.example.parcels_useres.Data.reppositories;

import android.app.Application;
import android.location.Location;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.Data.models.ParcelBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParcelRepository {

    private final LiveData<List<Parcel>> allParcels;
    private MutableLiveData<List<Parcel>> mlistp = new MutableLiveData<>();
    private ParcelDao parcelsDao;
    FirebaseDatabase firebaseDatabase;
    //    private DatabaseReference parcelsRef;
    private ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

    public ParcelRepository(Application application) {
        ParcelDataBase database = ParcelDataBase.getInstance(application);

        firebaseDatabase = FirebaseDatabase.getInstance();
        //mlistp = new MutableLiveData<List<Parcel>>();
        DatabaseReference parcelsRef = firebaseDatabase.getReference("parcels");
        parcelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                parcelList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Parcel parcel = snapshot.getValue(Parcel.class);
                        insert(parcel);
                    }

                    mlistp = new MutableLiveData<List<Parcel>>();
                    mlistp.setValue(parcelList);
                } else {
                    mlistp = new MutableLiveData<List<Parcel>>();


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        //  getHistoryParcels();
        parcelsDao = database.parcelDao();
     //   generateRandomParcels(parcelsRef);
//mlistp=parcelsDao.getAllParcels();
        allParcels = parcelsDao.getAllParcels();
    }

    private void generateRandomParcels(DatabaseReference parcelsRef) {

        String[] ad = {"מנחם בגין 25 כבעת שמואל","ההגנה 6 רמת גן", "קינג גורג 7 ירושלים", "זבוטינקי 20 באר שבע"};
        String[] names = {"גלעד", "יאיר", "משה", "דני", "גדי"};
        String[] phone = {"0501231321", "0529879879", "0504566780"};
        String[] emails = {"gbbitan@gmail.com","gbbitan@gmail.com","exemple1@gmail.com", "someone89@walla.co.il", "no-one00@gmail.com"};
        Parcel.ParcelKind[] parcelKinds = {Parcel.ParcelKind.BIG_PARCEL, Parcel.ParcelKind.ENVELOPE, Parcel.ParcelKind.LITTEL_PARCEL};
        Parcel.ParcelStatus[] parcelStatus = {Parcel.ParcelStatus.ACCEPT, Parcel.ParcelStatus.HAVE_DELIVER, Parcel.ParcelStatus.ON_WAY, Parcel.ParcelStatus.WAIT};
        Parcel.Weight[] weights = {Parcel.Weight.LESS_THEN_5_KG, Parcel.Weight.LESS_THEN_20_KG, Parcel.Weight.LESS_THEN_500_G, Parcel.Weight.LESS_THEN_KG};
        boolean[] fragails = {true, false};
        Location l = new Location("");
        l.setLatitude(31.765739);
        l.setLongitude(35.191110);
        ParcelBuilder p = new ParcelBuilder();
        for (int i = 0; i < 55; i++) {
            p.setAddress(ad[i % ad.length]);
            p.setName(names[i % names.length]);
            p.setPhone(phone[i % phone.length]);
            p.setEmail(emails[i % emails.length]);
            p.setParcelKind(parcelKinds[i % parcelKinds.length]);
            p.setParcelStatus(parcelStatus[i % parcelStatus.length]);
            p.setW(weights[i % weights.length]);
            p.setIsFragile(fragails[i % fragails.length]);
            p.setLocation(l);
            Parcel parcel = p.createParcel();
            parcel.setId(-10 - i * 2);
            insert(parcel);
            parcelList.add(p.createParcel());
        }
        for (int i = 55; i < 85; i++) {
            p.setAddress("מנחם בגין 25 גבעת שמואל");
            p.setName("אבי בן יקר");
            p.setPhone("0504564565");
            p.setEmail("aviby0@gmail.com");
            p.setParcelKind(parcelKinds[i % parcelKinds.length]);
            p.setParcelStatus(parcelStatus[i % parcelStatus.length]);
            p.setW(weights[i % weights.length]);
            p.setIsFragile(fragails[i % fragails.length]);
            p.setLocation(l);
            Parcel parcel = p.createParcel();
            parcel.setId(-10 - i * 2);
            insert(parcel);
            parcelList.add(p.createParcel());
        }
        mlistp.setValue(parcelList);

    }

    public void insert(Parcel parcel) {
        new InsertParcelAsyncTask(parcelsDao).execute(parcel);

    }

    public void update(Parcel parcel) {
        new UpdateParcelAsyncTask(parcelsDao).execute(parcel);

    }
    public void getHistoryParcels() {
        DatabaseReference parcelsRef = firebaseDatabase.getReference("parcels");
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

    public LiveData<List<Parcel>> getParcelsByEmail(String email){
        return parcelsDao.getParcelsByEmail(email);
    }

    public LiveData<List<Parcel>> getParcelsOfOthers(String email){
        return parcelsDao.getParcelsOfOthers(email);
    }

    public LiveData<List<Parcel>> getAllMyParcels(String email){
        return parcelsDao.getAllMyParcels(email);
    }
    //-------------test----------------


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

    private static class UpdateParcelAsyncTask extends AsyncTask<Parcel, Void, Void> {
        private ParcelDao parcelsDao;

        private UpdateParcelAsyncTask(ParcelDao parcelsDao) {
            this.parcelsDao = parcelsDao;
        }

        protected Void doInBackground(Parcel... parcels) {
            for (Parcel parcel : parcels) {
                parcelsDao.update(parcel);
            }
            return null;
        }
    }
}