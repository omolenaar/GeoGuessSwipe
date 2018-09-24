package com.example.android.geoguesswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GeoObjectAdapter extends RecyclerView.Adapter<GeoObjectViewHolder>{
    //fields
    private Context context;
    public List<GeoObject> listGeoObject;

    public GeoObjectAdapter(Context context, List<GeoObject> listGeoObject) {
        this.context = context;
        this.listGeoObject = listGeoObject;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<GeoObject> getListGeoObject() {
        return listGeoObject;
    }

    public void setListGeoObject(List<GeoObject> listGeoObject) {
        this.listGeoObject = listGeoObject;
    }

    @NonNull
    @Override
    public GeoObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, parent, false);
        return new GeoObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GeoObjectViewHolder holder, final int position) {
        // Get a single item in the list from its position
        final GeoObject geoObject = listGeoObject.get(position);
        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
    }

    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }
}
