package com.zasa.superduper.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.zasa.superduper.Fragment.ChartFragment;
import com.zasa.superduper.Fragment.MenuFragment;
import com.zasa.superduper.Fragment.TrophyFragment;
import com.zasa.superduper.Models.Scoring_Adapter;
import com.zasa.superduper.R;
import com.zasa.superduper.Scoring_Criteria_Model;
import com.zasa.superduper.databinding.FragmentHomeBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ProgressDialog progressDialog;
    Context context;
    TextView txt_progress;
    int value;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    ArrayList<Scoring_Criteria_Model> mList = new ArrayList<>();
    MaterialAlertDialogBuilder materialAlertDialogBuilder;

    public HomeFragment() {
        // Required empty public constructor
    }

    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        context = requireActivity();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);

        mList.add(new Scoring_Criteria_Model(R.drawable.outlet, "Outlet Productivity"));
        mList.add(new Scoring_Criteria_Model(R.drawable.ic_phone, "Call Productivity"));
        mList.add(new Scoring_Criteria_Model(R.drawable.bill, "Bill Productivity"));
        mList.add(new Scoring_Criteria_Model(R.drawable.sku, "SKU Per Bill"));
        mList.add(new Scoring_Criteria_Model(R.drawable.strike, "Strike Rate"));
        Scoring_Adapter scoring_adapter = new Scoring_Adapter(mList, getContext());
        binding.mainRecyclerview.setAdapter(scoring_adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.mainRecyclerview.setLayoutManager(layoutManager);

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startProgress();
//            }
//        });
//        thread.start();

        binding.ivChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ChartFragment chartFragment = new ChartFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, chartFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new ChartFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.ivTrophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TrophyFragment trophyFragment = new TrophyFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, trophyFragment);
//                transaction.commit();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new TrophyFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuFragment menuFragment = new MenuFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, menuFragment);
                transaction.commit();
            }
        });

        return binding.getRoot();

    }


//    public void startProgress() {
//        for (value = 0; value < 100; value = value + 1) {
//            try {
//                Thread.sleep(50);
//                binding.progressbarId.setProgress(value);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    binding.textid.setText(String.valueOf(value));
//                }
//            }, 5000);
//        }
//    }


}