package com.example.android.geoguesswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GeoObject> mGeoObjects = new ArrayList<>();

        for (int i=0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++)
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));

        RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);
    }
}
