package com.supercoders.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.supercoders.testapp.nativeadsexample.NativeAdsExample;
import com.supercoders.testapp.rewardadsexample.RewardActivityExample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdView adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        InterstitialAd interstitialAd=new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId(getString(R.string.intersial_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        Button show_int=findViewById(R.id.show_int);

        show_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }
        });

        Button rewardpage=findViewById(R.id.show_reward_activtiy);
        Button nativepage=findViewById(R.id.show_native_activtiy);
        rewardpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardActivityExample.class));
            }
        });
        nativepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NativeAdsExample.class));
            }
        });
    }
}