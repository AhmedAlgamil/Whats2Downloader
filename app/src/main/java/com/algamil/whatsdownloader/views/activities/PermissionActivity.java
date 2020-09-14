package com.algamil.whatsdownloader.views.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.algamil.whatsdownloader.R;
import com.algamil.whatsdownloader.local.PrefManager;

import static com.algamil.whatsdownloader.local.PrefManager.IS_FIRST_TIME_LAUNCH;


public class PermissionActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int REQUEST_WRITE_PERMISSION = 786;
    private CardView card_view_allow_permission;
    private PrefManager prefManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent_status  =  new Intent(getApplicationContext(), WhatsAppActivity.class);
                    startActivity(intent_status);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    setFirstTime();
                    finish();
                }
                return;
            }
        }
    }

    public void setFirstTime()
    {
        prefManager = new PrefManager(PermissionActivity.this);
        prefManager.setString(IS_FIRST_TIME_LAUNCH , "first" );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        prefManager= new PrefManager(getApplicationContext());
        this.card_view_allow_permission=(CardView) findViewById(R.id.card_view_allow_permission);
        this.card_view_allow_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
//                setFirstTime();
            }
        });
        if(prefManager.getString(IS_FIRST_TIME_LAUNCH).equals("first"))
        {
            Intent intent_status  =  new Intent(getApplicationContext(), WhatsAppActivity.class);
            startActivity(intent_status);
            finish();
        }
        else {

        }

    }

    public void onPermission(Activity activity) {
        String[] perms = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET};

        ActivityCompat.requestPermissions(activity,
                perms,
                REQUEST_WRITE_PERMISSION);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(PermissionActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_WRITE_PERMISSION);
        }
    }

}
