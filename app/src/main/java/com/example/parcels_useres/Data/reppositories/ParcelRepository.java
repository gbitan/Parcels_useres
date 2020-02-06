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

   // private final LiveData<List<Parcel>> allParcels;
    private  MutableLiveData<List<Parcel>> mlistp = new MutableLiveData<>();
    private ParcelDao parcelsDao;
    FirebaseDatabase firebaseDatabase;
//    private DatabaseReference parcelsRef;
    private ArrayList<Parcel> parcelList= new ArrayList<Parcel>() ;

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
                        parcelList.add(parcel);
                    }

                    mlistp = new MutableLiveData<List<Parcel>>();
                    mlistp.setValue(parcelList);
                }
                else {
                    mlistp = new MutableLiveData<List<Parcel>>();


                    }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
      //  getHistoryParcels();
     //   generateRandomParcels(parcelsRef);
     //   parcelsDao = database.parcelDao();

      //  allParcels = parcelsDao.getAllParcels();
    }

    private void generateRandomParcels(DatabaseReference parcelsRef) {

        String[] ad = {"US, somewhere 99", "אשדוד, זרובבל 8","ירושלים, בגין 10"};
        String[] names = {"ראובן","שמעון","לוי","יהודה","ישככר"};
        String[] phone = {"0501231321","0529879879","0504566780"};
        String[] emails = {"exemple1@gmail.com","someone89@walla.co.il","no-one00@gmail.com"};
        Parcel.ParcelKind[] parcelKinds = {Parcel.ParcelKind.BIG_PARCEL, Parcel.ParcelKind.ENVELOPE, Parcel.ParcelKind.LITTEL_PARCEL};
        Parcel.ParcelStatus[] parcelStatus = {Parcel.ParcelStatus.ACCEPT, Parcel.ParcelStatus.HAVE_DELIVER, Parcel.ParcelStatus.ON_WAY, Parcel.ParcelStatus.WAIT};
        Parcel.Weight[] weights = {Parcel.Weight.LESS_THEN_5_KG, Parcel.Weight.LESS_THEN_20_KG, Parcel.Weight.LESS_THEN_500_G, Parcel.Weight.LESS_THEN_KG};
        boolean[] fragails = {true,false};
        Location l =new Location("");
        l.setLatitude(31.765739);
        l.setLongitude(35.191110);
        ParcelBuilder p = new ParcelBuilder();
        for (int i=0;i<10;i++) {
            p.setAddress(ad[i%ad.length]);
            p.setName(names[i%names.length]);
            p.setPhone(phone[i%phone.length]);
            p.setEmail(emails[i%emails.length]);
            p.setParcelKind(parcelKinds[i%parcelKinds.length]);
            p.setParcelStatus(parcelStatus[i%parcelStatus.length]);
            p.setW(weights[i%weights.length]);
            p.setIsFragile(fragails[i%fragails.length]);
            p.setLocation(l);
            parcelList.add(p.createParcel());
        }
        mlistp.setValue(parcelList);

    }

    public void insert(Parcel parcel) {
        new InsertParcelAsyncTask(parcelsDao).execute(parcel);

    }
    public void getHistoryParcels(){
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
        return mlistp;
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
}