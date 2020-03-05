package com.example.parcels_useres.UI.home;

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
import com.example.parcels_useres.UI.FriendsParcelsAdapter;
import com.example.parcels_useres.UI.ParcelsAdapter;
import com.example.parcels_useres.UI.my_parcels.MyParcelsViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private com.example.parcels_useres.UI.home.HomeViewModel homeViewModel;


    private FriendsParcelsAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //  mAdapter = new AdapterParcel(getContext());

        mAdapter = new FriendsParcelsAdapter(getContext());


        recyclerView.setAdapter(mAdapter);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getParcels().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                mAdapter.setParcels(parcels);
            }
        });
    }

}