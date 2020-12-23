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
import com.maindevpattern.adapters.AdapterAllMain;


public class FragmentAllList extends Fragment {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_all, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //enabling cache for better view experience
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        AdapterAllMain adapterAllMain = new AdapterAllMain(getContext(), MainClass.listDataAll);
        adapterAllMain.setDataList(MainClass.listDataAll);
        recyclerView.setAdapter(adapterAllMain);

        return view;


    }




}