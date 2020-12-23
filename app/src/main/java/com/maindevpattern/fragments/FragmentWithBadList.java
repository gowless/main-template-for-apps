package com.maindevpattern.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.maindevpattern.MainClass;
import com.maindevpattern.R;
import com.maindevpattern.activities.SplashActivity;
import com.maindevpattern.adapters.RecyclerAdapterWithBadList;


public class FragmentWithBadList extends Fragment {
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_with_bad_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //enabling cache for better view experience
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        RecyclerAdapterWithBadList recyclerAdapterWithBadList = new RecyclerAdapterWithBadList(getContext(), SplashActivity.listDataBad);
        recyclerAdapterWithBadList.setDataList(SplashActivity.listDataBad);
        recyclerView.setAdapter(recyclerAdapterWithBadList);

        return view;
    }


}