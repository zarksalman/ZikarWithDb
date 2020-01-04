package com.textile.texttospeech;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.textile.texttospeech.databinding.ActivityZikarListBinding;

import java.util.List;

public class ZikarListActivity extends AppCompatActivity {

    private ZikarViewModel zikarViewModel;
    private ZikarAdapter adapter;
    private ActivityZikarListBinding activityZikarListBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityZikarListBinding = DataBindingUtil.setContentView(this, R.layout.activity_zikar_list);

        zikarViewModel = ViewModelProviders.of(this).get(ZikarViewModel.class);
        setAdapter();
        getAllZikars();
    }

    private void setAdapter() {

        adapter = new ZikarAdapter(this);
        activityZikarListBinding.rvZikars.setAdapter(adapter);
        activityZikarListBinding.rvZikars.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void getAllZikars() {

        zikarViewModel.getAllZikars().observe(this, new Observer<List<Zikar>>() {
            @Override
            public void onChanged(List<Zikar> zikars) {
                adapter.setData(zikars);
            }
        });
    }
}
