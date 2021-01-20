package com.supercoders.testapp.nativeadsexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.supercoders.testapp.R;

import java.util.List;

public class NativeAdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> datalist;
    Context context;
    int VIEW_TYPE=1;
    int AD_VIEW_TYPE=2;

    @Override
    public int getItemViewType(int position) {
        Object item=datalist.get(position);
        if (item instanceof  UnifiedNativeAd){
            return AD_VIEW_TYPE;
        }
        return VIEW_TYPE;
    }

    public NativeAdsAdapter(Context context, List<Object> datalist){
        this.context=context;
        this.datalist=datalist;
    }

    public class UnifiedNativeAdsViewholder extends RecyclerView.ViewHolder{
        public UnifiedNativeAdView nativeAd;

        public UnifiedNativeAdView getAdview(){
            return nativeAd;
        }

        public UnifiedNativeAdsViewholder(@NonNull View itemView) {
            super(itemView);
            nativeAd=(UnifiedNativeAdView)itemView.findViewById(R.id.ad_view);
            nativeAd.setMediaView((MediaView) nativeAd.findViewById(R.id.ad_media));
            nativeAd.setHeadlineView(nativeAd.findViewById(R.id.ad_headline));
            nativeAd.setBodyView( nativeAd.findViewById(R.id.ad_body));
            nativeAd.setCallToActionView( nativeAd.findViewById(R.id.ad_call_to_action));
            nativeAd.setIconView( nativeAd.findViewById(R.id.ad_icon));
            nativeAd.setPriceView( nativeAd.findViewById(R.id.ad_price));
            nativeAd.setStarRatingView( nativeAd.findViewById(R.id.ad_stars));
            nativeAd.setStoreView( nativeAd.findViewById(R.id.ad_store));
            nativeAd.setAdvertiserView( nativeAd.findViewById(R.id.ad_advertiser));
        }
    }

    public class ItemViewholder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        public ItemViewholder(@NonNull View itemView) {
            super(itemView);
            itemTitle=itemView.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==AD_VIEW_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.ad_unified_layout,null);
            return  new UnifiedNativeAdsViewholder(view);
        }
        else{
            View view=LayoutInflater.from(context).inflate(R.layout.simple_list,null);
            return new ItemViewholder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ItemViewholder){
            ((ItemViewholder) holder).itemTitle.setText(datalist.get(position).toString());
        }
        else{
            UnifiedNativeAd nativeAd= (UnifiedNativeAd) datalist.get(position);
            UnifiedNativeAdView adView=((UnifiedNativeAdsViewholder)holder).getAdview();

            ((TextView)adView.getHeadlineView()).setText(nativeAd.getHeadline());
            ((TextView)adView.getBodyView()).setText(nativeAd.getBody());
            ((Button)adView.getCallToActionView()).setText(nativeAd.getCallToAction());

            if(nativeAd.getIcon()!=null){
                ((ImageView)adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            }
            else{
                ((ImageView)adView.getIconView()).setVisibility(View.INVISIBLE);
            }

            if(nativeAd.getPrice()!=null){
                ((TextView)adView.getPriceView()).setText(nativeAd.getPrice());
            }
            else{
               adView.getIconView().setVisibility(View.INVISIBLE);
            }

            if(nativeAd.getStore()!=null){
                ((TextView)adView.getStoreView()).setText(nativeAd.getStore());
            }
            else{
                adView.getStoreView().setVisibility(View.INVISIBLE);
            }

            if(nativeAd.getStarRating()!=null){
                ((RatingBar)adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            }
            else{
                adView.getStarRatingView().setVisibility(View.INVISIBLE);
            }

            if(nativeAd.getAdvertiser()!=null){
                ((TextView)adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            }
            else{
                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
            }
            adView.setNativeAd(nativeAd);

        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
