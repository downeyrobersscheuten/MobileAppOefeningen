package be.howest.nmct.bmicalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.howest.nmct.bmicalculator.Model.BMIInfo;

public class BmiActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private Button button_bereken;
    private ImageView bodyView;
    private View bmicoordinatorlayout;
    private TextView txtViewIndex;
    private TextView txtViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.height = (EditText) findViewById(R.id.height);
        this.weight = (EditText) findViewById(R.id.weight);
        this.button_bereken= (Button) findViewById(R.id.btnGo);
        this.bodyView = (ImageView) findViewById(R.id.image);
        this.bmicoordinatorlayout = findViewById(R.id.coordinator);
        this.txtViewIndex = (TextView) findViewById(R.id.index);
        this.txtViewCategory = (TextView) findViewById(R.id.category) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        this.button_bereken.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                UpdateView();
            }
        });
    }

    private void UpdateView() {
        float height = Float.parseFloat(this.height.getText().toString());
        int weight = Integer.parseInt(this.weight.getText().toString());

        BMIInfo bmi = new BMIInfo(height,weight);
        float index = bmi.getBmiindex();
        BMIInfo.categorie cat =  BMIInfo.categorie.getGategory(index);

        this.txtViewCategory.setText(cat.getOmschrijving());
        this.txtViewIndex.setText(Float.toString(index));

        //haal foto op en toon ze
        this.bodyView.setImageResource(getResourceId(cat));


    }

    private int getResourceId(BMIInfo.categorie cat) {
        switch (cat){
            case GROOT_ONDER:
                return R.drawable.silhouette_1;
            case ERNSTIG_ONDER:
                return R.drawable.silhouette_2;
            case ONDERGEWICHT:
                return R.drawable.silhouette_3;
            case NORMAAL:
                return R.drawable.silhouette_4;
            case OVERGEWICHT:
                return R.drawable.silhouette_5;
            case MATIG:
                return R.drawable.silhouette_6;
            case ERNSTIG_OVER:
                return R.drawable.silhouette_7;
            case GROOT_OVER:
                return R.drawable.silhouette_8;
        }
        return 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void berekenBMI(View view) {

    }

}
