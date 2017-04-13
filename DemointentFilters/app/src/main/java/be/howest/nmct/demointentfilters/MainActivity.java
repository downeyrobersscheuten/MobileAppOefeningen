package be.howest.nmct.demointentfilters;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import butterknife.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main) View MainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLaunchWithAction)
    public void launchWithAction(View v){
        //Intent intent = new Intent("android.intent.action.VIEW");
        //startActivity(intent);
        Intent intent = new Intent(Constants.ACTION_IMPLY);
        intent.addCategory(Intent.CATEGORY_CAR_DOCK);
        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        if(isIntentSafe)
        startActivity(intent);
        else{
            Snackbar snackbar = Snackbar.make(MainView, "No Activity found", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

    }
    @OnClick(R.id.btnOnderzoek4)
    public void onderzoek4(){
        Intent intent = new Intent(Constants.ACTION_IMPLY);
        intent.setData(Uri.parse("http://www.howest.be"));
        startActivity(intent);
    }

}
