package be.howest.nmct.demoimplicitintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.*;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CALL = 1000;

    @BindView(R.id.btnCall) Button btnCall;
    @BindView(R.id.activity_main) View MainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btnBrowser)
    public void toonBrowser(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.nmct.be"));

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
    @OnClick(R.id.btnDial)
    public void dialNumber(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+32 12 34 57 89"));

        if(intent.resolveActivity(getPackageManager()) != null)
        startActivity(intent);

    }
    @OnClick(R.id.btnGeo)
    public void searchLoc(){
        Intent intent = new Intent(this,Geo.class);
        startActivity(intent);

    }
    @OnClick(R.id.btnContacts)
    public void showContacts(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("content://contacts/people/"));
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
    @OnClick(R.id.btnEditContact)
    public void editContact1(){
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(Uri.parse("content://contacts/people/1"));

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
    @OnClick(R.id.btnBmi)
    public void openBMI(){
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("be.howest.nmct.bmicalculator");
        if (launchIntent != null) {
            startActivity(launchIntent);
        }

        /*Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getPackageManager();
        packageManager.queryIntentActivities(intent,0);



        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);*/
    }
    @OnClick(R.id.btnCall)
    public void callNumber(){
        //permission check
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        //check if i have permission
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            //no permission

            //check if user refused permission before
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                //ask again and explain why u need thiss
                Snackbar.make(MainView,"Access is required to call",Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener(){
                    public void onClick(View view){
                        askCallPermission();
                    }
                }).show();

            } else {
                askCallPermission();
            }


        }else {
            //we've already got permission here
            callHelp();
        }
    }
    private void callHelp(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:911"));

        if(intent.resolveActivity(getPackageManager())!= null)
            startActivity(intent);
    }

    private void askCallPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CALL:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    callNumber();

                } else {

                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
