package be.howest.nmct.demolaunchingactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.*;

public class Main2Activity extends AppCompatActivity {

    private TextView textview;
    public static final String EXTRA_INFO = "be.howest.nmct.demolaunchingactivities.EXTRA_INFO";
    public static final int RESULT_NOIDEA = 2;
    @BindView(R.id.ok) Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("MainActivity2");

        textview = (TextView) findViewById(R.id.extra_info);

        String value = getIntent().getStringExtra(Main2Activity.EXTRA_INFO);
        textview.setText(value);
        ButterKnife.bind(this);
    }
    //actionListeners
    @OnClick({R.id.ok})
    public void returnOK() {
        setResult(RESULT_OK);
        finish();
    }
    @OnClick({R.id.cancel})
    public void returnCancel(){
        setResult(RESULT_CANCELED);
        finish();
    }
    @OnClick({R.id.noidea})
    public void returnNoIdea(){
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_INFO_LASTNAME,"MacAwesomePants");
        intent.putExtra(MainActivity.EXTRA_INFO_AGE,21);
        setResult(Main2Activity.RESULT_NOIDEA,intent);
        finish();

    }


}
