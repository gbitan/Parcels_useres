package com.example.parcels_useres.UI;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.R;

public class ParcelsAdapter extends RecyclerView.Adapter<ParcelsAdapter.ParcelHolder> {
    private List<Parcel> parcels = new ArrayList<>();

    private Context baseContext;

    public ParcelsAdapter(Context baseContext) {
        this.baseContext = baseContext;
    }

    @NonNull
    @Override
    public ParcelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_parcel, parent, false);
        return new ParcelHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelHolder holder, int position) {
        Parcel parcel = parcels.get(position);
        holder.textViewStatus.setText(parcel.getParcelStatus().name());
        holder.textViewType.setText(parcel.getParcelKind().name());
        holder.textViewWeight.setText(parcel.getW().name());
       // holder.textViewAdress.setText(Utils.getPlace(baseContext, parcel.getLocation()));
    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public Parcel getParcelAt(int position) {
        return parcels.get(position);
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
        notifyDataSetChanged();
    }

    public class ParcelHolder extends RecyclerView.ViewHolder {
        TextView textViewStatus, textViewType, textViewWeight, textViewAdress;
        ImageView imageViewType;

        public ParcelHolder(@NonNull View itemView) {
            super(itemView);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
            // imageViewType = itemView.findViewById(R.id.image_view_type);
            textViewWeight = itemView.findViewById(R.id.text_view_weight);
         //   textViewAdress = itemView.findViewById(R.id.text_view_Adress);
            textViewType = itemView.findViewById(R.id.text_view_type);
        }
    }
}