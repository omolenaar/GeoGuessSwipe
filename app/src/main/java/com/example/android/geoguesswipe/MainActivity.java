package com.example.android.geoguesswipe;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //fields
    private List<GeoObject> mGeoObjects;
    private GeoObjectAdapter mAdapter;
    private RecyclerView mGeoRecyclerView;
    private int rightSwipe=0;
    private int leftSwipe=0;
    String toastText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<GeoObject> mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++)
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));

        final RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        final GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        final int nrOfItems = mAdapter.getItemCount();
        mGeoRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallbackR =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        rightSwipe = rightSwipe + 1;
                        int position = (viewHolder.getAdapterPosition());
                        mGeoObjects.remove(position);
                        mAdapter.notifyItemRangeRemoved(position, 1);
                        int nrOfItems = mAdapter.getItemCount();
                        if (nrOfItems == 0) {
                            toastText = "Left: "+leftSwipe+", Right: "+rightSwipe+". Starting over";
                            Toast op = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_LONG);
                            op.show();
                            for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++)
                                mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
                            leftSwipe=0;
                            rightSwipe=0;
                        }
                        GeoObjectAdapter mAdapter = new GeoObjectAdapter(getApplicationContext(), mGeoObjects);         }
                };

        ItemTouchHelper.SimpleCallback simpleItemTouchCallbackL =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        leftSwipe = leftSwipe+1;
                        int position = (viewHolder.getAdapterPosition());
                        mGeoObjects.remove(position);
                        mAdapter.notifyItemRangeRemoved(position, 1);
                        int nrOfItems = mAdapter.getItemCount();
                        if (nrOfItems == 0) {
                            toastText = "Left: "+leftSwipe+", Right: "+rightSwipe+". Starting over";
                            Toast op = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_LONG);
                            op.show();
                            leftSwipe=0;
                            rightSwipe=0;
                            for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS.length; i++)
                                mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
                        }
                        GeoObjectAdapter mAdapter = new GeoObjectAdapter(getApplicationContext(), mGeoObjects);         }
                };

        ItemTouchHelper itemTouchHelperL = new ItemTouchHelper(simpleItemTouchCallbackL);
        ItemTouchHelper itemTouchHelperR = new ItemTouchHelper(simpleItemTouchCallbackR);
        itemTouchHelperL.attachToRecyclerView(mGeoRecyclerView);
        itemTouchHelperR.attachToRecyclerView(mGeoRecyclerView);
    }

}
