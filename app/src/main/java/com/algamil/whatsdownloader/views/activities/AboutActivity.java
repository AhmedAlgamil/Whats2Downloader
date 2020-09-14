package com.algamil.whatsdownloader.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.algamil.whatsdownloader.R;

public class AboutActivity extends AppCompatActivity {

    private TextView mTvTitleAbout;
    private TextView mProgrammerTv;
    private ImageView mProgFabFacebook;
    private TextView mMyFacebookName;
    private ImageView mProgFabWhats;
    private TextView mMyNumber;
    private ImageView mProgFabLinked;
    private TextView mLinkedTv;
    private ImageView mProgFabTwitter;
    private TextView mTwitterBtn;
    private ImageView mProgFabYoutube;
    private TextView mYoutubeTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
    }

    public void initViews() {
        mTvTitleAbout = (TextView) findViewById(R.id.tv_title_about);
        mProgrammerTv = (TextView) findViewById(R.id.programmer_tv);
        mProgFabFacebook = (ImageView) findViewById(R.id.prog_fab_facebook);
        mMyFacebookName = (TextView) findViewById(R.id.my_facebook_name);
        mProgFabWhats = (ImageView) findViewById(R.id.prog_fab_whats);
        mMyNumber = (TextView) findViewById(R.id.my_number);
        mProgFabLinked = (ImageView) findViewById(R.id.prog_fab_linked);
        mLinkedTv = (TextView) findViewById(R.id.linked_tv);
        mProgFabTwitter = (ImageView) findViewById(R.id.prog_fab_twitter);
        mTwitterBtn = (TextView) findViewById(R.id.twitter_btn);
        mProgFabYoutube = (ImageView) findViewById(R.id.prog_fab_youtube);
        mYoutubeTv = (TextView) findViewById(R.id.youtube_tv);
        mProgFabFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.facebook.com/eng.ahmed.5811877" );
            }
        });
        mProgFabYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.youtube.com/channel/UC7lZXUGd1ODOHtoRzu48Tfg" );
            }
        });
        mProgFabTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://twitter.com/ahmedalgamil231?s=08 " );
            }
        });
        mProgFabLinked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.linkedin.com/in/ahmed-algamil-861b1a187" );
            }
        });
        mProgFabWhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://wa.me/201277823947" );
            }
        });


        mMyFacebookName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.facebook.com/eng.ahmed.5811877" );
            }
        });
        mYoutubeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.youtube.com/channel/UC7lZXUGd1ODOHtoRzu48Tfg" );
            }
        });
        mTwitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://twitter.com/ahmedalgamil231?s=08 " );
            }
        });
        mLinkedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://www.linkedin.com/in/ahmed-algamil-861b1a187" );
            }
        });
        mMyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl ( "https://wa.me/201277823947" );
            }
        });
    }

    public void openUrl(String Url) {
        Uri uri = Uri.parse(Url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
