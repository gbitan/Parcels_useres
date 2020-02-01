package com.example.parcels_useres.UI.my_parcels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.R;
import com.example.parcels_useres.UI.ParcelsAdapter;
import com.example.parcels_useres.UI.ParcelsAdapter;

import java.util.List;

public class MyParcelsFragment extends Fragment {

    private MyParcelsViewModel myParcelsViewModel;


    private ParcelsAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public static MyParcelsFragment newInstance() {
        return new MyParcelsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_parcel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
      //  mAdapter = new AdapterParcel(getContext());
        recyclerView.setAdapter(mAdapter);

        myParcelsViewModel = ViewModelProviders.of(this).get(MyParcelsViewModel.class);
        myParcelsViewModel.getParcels().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                mAdapter.setParcels(parcels);
            }
        });
    }
 /*   public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myParcelsViewModel =
                ViewModelProviders.of(this).get(com.example.parcels_useres.UI.my_parcels.MyParcelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_parcel, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        myParcelsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
}