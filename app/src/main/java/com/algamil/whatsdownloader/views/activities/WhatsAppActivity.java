package com.algamil.whatsdownloader.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algamil.whatsdownloader.R;
import com.algamil.whatsdownloader.adapters.RecyclerViewMediaAdapter;
import com.algamil.whatsdownloader.models.StatusWhatsapp;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class WhatsAppActivity extends AppCompatActivity {
    public static final String TAG = "Home";
    private static final String WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses";
    InterstitialAd mPublisherInterstitialAd;
    private RecyclerView mRecyclerViewMediaList;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout swipe_refreshl_whatsapp_saver;
    private ArrayList<StatusWhatsapp> inFiles;
    private RecyclerViewMediaAdapter recyclerViewMediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inFiles = new ArrayList<StatusWhatsapp>();
        swipe_refreshl_whatsapp_saver = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshl_whatsapp_saver);
        mRecyclerViewMediaList = (RecyclerView) findViewById(R.id.recyclerViewMedia);
        inFiles = getListFiles(new File(Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION));
        swipe_refreshl_whatsapp_saver.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refreshl_whatsapp_saver.setRefreshing(false);
                inFiles = getListFiles(new File(Environment.getExternalStorageDirectory().toString()+WHATSAPP_STATUSES_LOCATION));
                recyclerViewMediaAdapter.notifyDataSetChanged();
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMediaAdapter =new RecyclerViewMediaAdapter(inFiles, WhatsAppActivity.this);
        mRecyclerViewMediaList.setHasFixedSize(true);
        mRecyclerViewMediaList.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewMediaList.setAdapter(recyclerViewMediaAdapter);
        showAds();
        showmadmobe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(WhatsAppActivity.this , AboutActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

    public void showmadmobe ( ) {
        MobileAds.initialize ( this , new OnInitializationCompleteListener( ) {
            @Override
            public void onInitializationComplete ( InitializationStatus initializationStatus ) {
            }
        } );
        AdView mAdView = findViewById ( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder ( ).build ( );
        mAdView.loadAd ( adRequest );
        mAdView.setAdListener ( new AdListener ( ) {
            @Override
            public void onAdLoaded ( ) {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad ( int errorCode ) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened ( ) {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked ( ) {
            }

            @Override
            public void onAdLeftApplication ( ) {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed ( ) {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        } );

    }
    public void showAds()
    {
        mPublisherInterstitialAd = new InterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-8962554532072805/9970326030");
        mPublisherInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mPublisherInterstitialAd.isLoaded()) {

        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        Log.d("TAG", "The interstitial wasn't loaded yet.");
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                mPublisherInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

    private ArrayList<StatusWhatsapp> getListFiles(File parentDir) {
        inFiles.clear();
        File[] files;
        files = parentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".gif") ||  file.getName().endsWith(".mp4")) {
                    if (!inFiles.contains(file))
                        if (file.getName().endsWith(".mp4")){
                            inFiles.add(new StatusWhatsapp().setFile(file).setViewType(0));
                        }else{
                            inFiles.add(new StatusWhatsapp().setFile(file).setViewType(1));
                        }
                }
            }
        }
        else {
        }
        return inFiles;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.back_enter, R.anim.back_exit);
        return;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.back_enter, R.anim.back_exit);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
