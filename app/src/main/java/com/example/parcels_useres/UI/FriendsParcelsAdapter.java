package com.example.parcels_useres.UI;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FriendsParcelsAdapter extends RecyclerView.Adapter<FriendsParcelsAdapter.ParcelHolder> {
    private List<Parcel> parcels = new ArrayList<>();

    private FirebaseUser user;

    private Context baseContext;

    public FriendsParcelsAdapter(Context baseContext) {
        this.baseContext = baseContext;
        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    @NonNull
    @Override
    public ParcelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_parcel_friends, parent, false);
        return new ParcelHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelHolder holder, int position) {
        final Parcel parcel = parcels.get(position);
        holder.textViewStatus.setText(parcel.getParcelStatus().name());
        holder.textViewType.setText(parcel.getParcelKind().name());
        holder.textViewWeight.setText(parcel.getW().name());
        holder.textViewName.setText(parcel.getName());
        holder.textViewId.setText(parcel.getEmail());
        if (parcel.getApplyFriends() ==  user.getEmail() )
            holder.aSwitch.setChecked(true);
        else
            holder.aSwitch.setChecked(false);
        holder.aSwitch.setOnCheckedChangeListener((new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    parcel.setApplyFriends(user.getEmail());
                else
                    parcel.setApplyFriends("");
            }
        }));
     //   holder.textViewAdress.setText(Utils.getPlace(baseContext, parcel.getLocation()));
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
        TextView textViewStatus, textViewType, textViewWeight, textViewAdress,textViewName,textViewId;
        ImageView imageViewType;
        Switch aSwitch;

        public ParcelHolder(@NonNull View itemView) {
            super(itemView);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
            // imageViewType = itemView.findViewById(R.id.image_view_type);
            textViewWeight = itemView.findViewById(R.id.text_view_weight);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewType = itemView.findViewById(R.id.text_view_type);
            textViewId = itemView.findViewById(R.id.text_view_id);
            aSwitch = itemView.findViewById(R.id.applaySwitch);
        }
    }
}