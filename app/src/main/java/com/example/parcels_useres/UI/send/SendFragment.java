package com.example.parcels_useres.UI.send;

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
import com.example.parcels_useres.UI.home.HomeFragment;
import com.example.parcels_useres.UI.home.HomeViewModel;

import java.util.List;

public class SendFragment extends Fragment {

    private com.example.parcels_useres.UI.send.SendViewModel sendViewModel;

    private ParcelsAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public static SendFragment newInstance() {
        return new SendFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //  mAdapter = new AdapterParcel(getContext());

        mAdapter = new ParcelsAdapter(getContext());


        recyclerView.setAdapter(mAdapter);

        sendViewModel = ViewModelProviders.of(this).get(com.example.parcels_useres.UI.send.SendViewModel.class);
        sendViewModel.getParcels().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(List<Parcel> parcels) {
                mAdapter.setParcels(parcels);
            }
        });
    }

}