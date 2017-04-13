package be.howest.nmct.demolaunchingactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.PendingIntent.getActivity;
import static be.howest.nmct.demolaunchingactivities.Main2Activity.RESULT_NOIDEA;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.askconfirmation) Button btnSwitch;
    @BindView(R.id.txtResult) TextView txtResult;
    @BindView(R.id.activity_main) View MainView;

    public static final int REQUEST_CODE_DEELNAME=1;
    public static final String EXTRA_INFO_LASTNAME = "be.howest.nmct.demolaunchingactivities.EXTRA_INFO_LASTNAME" ;
    public static final String EXTRA_INFO_AGE = "be.howest.nmct.demolaunchingactivities.EXTRA_INFO_AGE" ;
    public static final int REQUEST_CODE_SCORE=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity1");
        ButterKnife.bind(this);

        //Vervangen door butterknife lib (Zupah handig)
        //btnSwitch = (Button) findViewById(R.id.btnSwitch);


        //actionlisteners

    }
    @OnClick(R.id.askconfirmation)
     public void NextActivity() {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        //intent.putExtra(Main2Activity.EXTRA_INFO);
        startActivityForResult(intent,REQUEST_CODE_DEELNAME);
    }
    /*public static void MakeSnackbar(){
        Snackbar snackbar = Snackbar
                .make(MainView, "Jou score: " + vals.getText(), Snackbar.LENGTH_LONG);

        snackbar.show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_DEELNAME:
                switch (resultCode){

                    case RESULT_OK:
                        txtResult.setText("Deelname bevestigd.");
                        ScoreDialogFragment dialog = new ScoreDialogFragment();
                        dialog.show(getFragmentManager(),"scoreDialog");
                        LayoutInflater li = LayoutInflater.from(this);
                        View dialogView = li.inflate(R.layout.dialog,null);
                        EditText vals = (EditText) dialogView.findViewById(R.id.score);
                        System.out.println("VALUE INCOMMINNG : \n \n \n");
                        System.out.println(vals.getText());


                        break;
                    case RESULT_CANCELED:
                        txtResult.setText("Deelname geannuleerd.");
                        break;
                    case RESULT_NOIDEA:
                        String name = data.getStringExtra(MainActivity.EXTRA_INFO_LASTNAME);
                        int age = data.getIntExtra(MainActivity.EXTRA_INFO_AGE,0);
                        txtResult.setText(name + "heeft on een leeftijd van: "+ age+" nog ALTIJD geen idee!");
                        break;
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
