package com.zasa.superduper.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zasa.superduper.Home.HomeFragment;
import com.zasa.superduper.R;


public class ChartFragment extends Fragment {

    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}