package com.supercoders.testapp.nativeadsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.supercoders.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class NativeAdsExample extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Object> datalist=new ArrayList<>();
    NativeAdsAdapter nativeAdsAdapter;
    int fetchAds=0;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ads_example);
        recyclerView=findViewById(R.id.list_item);
        progress=findViewById(R.id.progress);
        loadAds();

    }

    private void loadAds() {
        AdLoader.Builder adbuilder=new AdLoader.Builder(NativeAdsExample.this,"ca-app-pub-3940256099942544/2247696110");

        AdLoader adLoader=adbuilder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                datalist.add(unifiedNativeAd);
                fetchAds=fetchAds+1;
                if(fetchAds>=2) {
                    loadData();
                }
            }
        }).withAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                loadData();
            }
        }).build();


        adLoader.loadAds(new AdRequest.Builder().build(),2);
    }

    private void loadData() {
        progress.setVisibility(View.GONE);
        for (int i=0;i<20;i++){
            datalist.add(new String("HELLO DATA : "+i));
        }
        nativeAdsAdapter=new NativeAdsAdapter(NativeAdsExample.this,datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(NativeAdsExample.this));
        recyclerView.setAdapter(nativeAdsAdapter);
    }
}