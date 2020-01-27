package com.example.parcels_useres.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;





import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcels_useres.Data.models.Parcel;
import com.example.parcels_useres.R;

public class AdapterParcel extends androidx.recyclerview.widget.RecyclerView.Adapter<AdapterParcel.ParcelDetailsViewHolder> {

    private Context baseContext;

    List<Parcel> parcels;

    public AdapterParcel(Context baseContext, List<Parcel> parcels) {
        this.baseContext = baseContext;
        this.parcels = parcels;
    }

    @NonNull
    @Override
    public ParcelDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(baseContext).inflate(R.layout.adapter_parcel,
                parent,
                false);

        return new ParcelDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParcel.ParcelDetailsViewHolder holder, int position) {
        Parcel parcel = parcels.get(position);
        holder.textViewStatus.setText(parcel.getParcelStatus().name());
        holder.textViewType.setText(parcel.getParcelKind().name());
        holder.textViewName.setText(parcel.getName());
        holder.textViewWeight.setText(parcel.getW().name());

        //  holder.textViewAdress.setText(Utils.getPlace(baseContext, parcel.getLatitude(),parcel.getLongitude()));
        //holder.imageViewType.seti
    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public class ParcelDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView textViewStatus, textViewType, textViewWeight,textViewName;
        ImageView imageViewType;
        public ParcelDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
            // imageViewType = itemView.findViewById(R.id.image_view_type);
            textViewWeight = itemView.findViewById(R.id.text_view_weight);
            // textViewAdress = itemView.findViewById(R.id.text_view_Adress);

            textViewType = itemView.findViewById(R.id.text_view_type);
        }
    }


}
