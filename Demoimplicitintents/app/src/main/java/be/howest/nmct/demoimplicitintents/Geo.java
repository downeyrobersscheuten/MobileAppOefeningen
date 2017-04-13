package be.howest.nmct.demoimplicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Geo extends AppCompatActivity {

    @BindView(R.id.txtLat) EditText txtLat;
    @BindView(R.id.txtLong) EditText txtLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnShowLoc)
    public void showLoc(){
        //get data
        Float fLat = Float.parseFloat(txtLat.getText().toString());
        Float fLong = Float.parseFloat(txtLong.getText().toString());

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:"+fLat+"," +fLong));

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

}
