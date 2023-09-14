package com.code.imgpicker.ui.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.code.imgpicker.databinding.RvAddPhotoBinding;
import com.code.imgpicker.ui.Model.AddPhotosModel;

import java.util.ArrayList;
import java.util.List;

public class AddPhotosAdapter extends RecyclerView.Adapter<AddPhotosAdapter.MyViewHolder> {

    List<AddPhotosModel> modelArrayList = new ArrayList<>();
    Context context;
    ArrayList<String> imgListCarousel = new ArrayList<>();

    public AddPhotosAdapter(List<AddPhotosModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvAddPhotoBinding binding = RvAddPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AddPhotosModel item = modelArrayList.get(position);
        holder.binding.image.setVisibility(View.VISIBLE);

        Glide.with(context).load(item.getUri()).into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RvAddPhotoBinding binding;

        public MyViewHolder(RvAddPhotoBinding historyBinding) {
            super(historyBinding.getRoot());
            this.binding = historyBinding;
        }
    }


}
