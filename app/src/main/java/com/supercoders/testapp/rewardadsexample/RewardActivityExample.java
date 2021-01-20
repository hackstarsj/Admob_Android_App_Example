package com.supercoders.testapp.rewardadsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.supercoders.testapp.R;

public class RewardActivityExample extends AppCompatActivity {

    RewardedAd rewardedAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_example);

        Button rewardshow=findViewById(R.id.show_reward);

        //==========USE TO LOAD REWARD ADS================
        rewardedAd=new RewardedAd(RewardActivityExample.this,"ca-app-pub-3940256099942544/5224354917");

        RewardedAdLoadCallback rewardedAdLoadCallback=new RewardedAdLoadCallback(){
            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
            }
        };

        rewardedAd.loadAd(new AdRequest.Builder().build(),rewardedAdLoadCallback);
        //==============END LOADING REWARD ADS=========

        rewardshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rewardedAd.isLoaded()){
                    rewardedAd.show(RewardActivityExample.this,null);
                }
            }
        });
    }
}