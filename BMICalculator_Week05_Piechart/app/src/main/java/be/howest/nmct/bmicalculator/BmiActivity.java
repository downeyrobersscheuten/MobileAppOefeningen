package be.howest.nmct.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import be.howest.nmct.bmicalculator.Model.BMIInfo;

public class
BmiActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private Button button_bereken;
    private ImageView bodyView;
    private View bmicoordinatorlayout;
    private TextView txtViewIndex;
    private TextView txtViewCategory;
    private FloatingActionButton fab;
    public static final String FILENAME = "SavedBMI";
    public static final String USER_MASS = "be.howest.nmct.bmicalculator.Model.BMIinfo.mass";
    public static final String USER_HEIGHT = "be.howest.nmct.bmicalculator.Model.BMIinfo.height";
    public static final String USER_BMI = "be.howest.nmct.bmicalculator.Model.BMIinfo.index";
    public static final String PREFS_BMI = "MyPrefsFileMBI";
    //public static final String USER_CATEGORIE = "be.howest.nmct.bmicalculator.Model.BMIinfo.categorie";
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


        SharedPreferences settings = this.getSharedPreferences(PREFS_BMI,0);
        float height = settings.getFloat(USER_HEIGHT, 0f);
        float mass = settings.getFloat(USER_MASS,0f);
        if(height != 0f && mass !=0f){
            this.weight.setText(""+ mass);
            this.height.setText(""+  height);

            BMIInfo bmi = new BMIInfo(height,(int)mass);
            float index = bmi.getBmiindex();
            BMIInfo.categorie cat =  BMIInfo.categorie.getGategory(index);

            this.txtViewCategory.setText(cat.getOmschrijving());
            this.txtViewIndex.setText(Float.toString(index));
        }





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveNewBmiInfoToFile();
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
        float weight = Float.parseFloat(this.weight.getText().toString());
        BMIInfo bmi = new BMIInfo(height,(int)weight);
        float index = bmi.getBmiindex();
        BMIInfo.categorie cat =  BMIInfo.categorie.getGategory(index);

        this.txtViewCategory.setText(cat.getOmschrijving());
        this.txtViewIndex.setText(Float.toString(index));

        //haal foto op en toon ze
        this.bodyView.setImageResource(getResourceId(cat));

        //read file in en bereken Average


    }

    @Override
    protected void onResume() {
        super.onResume();
        //UpdateView();
        Log.d(getClass().getSimpleName(),"RESUME");
    }
    private void SaveNewBmiInfoToFile(){
        //if(getFilesDir())

        String data = this.txtViewIndex.getText().toString() + "\n";

        try{

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();

        }catch(Exception ex){
            System.out.println(ex);
        }

        try{
            InputStream inpStream = openFileInput(FILENAME);
            InputStreamReader inpStreamReader = new InputStreamReader(inpStream);
            BufferedReader in = new BufferedReader(inpStreamReader);
            String recieveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            String x = in.readLine();
            float total = 0;
            int counter=0;

            while(x!=null){
                total+= Float.parseFloat(x);
                counter++;
                x = in.readLine();
            }
            float average = total/counter;
            Snackbar.make(bmicoordinatorlayout,"Het gemiddelde BMI is momenteel:" + average,Snackbar.LENGTH_SHORT).show();
            System.out.println(total/counter);
            in.close();
        }catch(Exception ex){
            System.out.println(ex);
        }

    }




    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putFloat(USER_HEIGHT,Float.parseFloat(this.height.getText().toString()));
        //outState.putFloat(USER_MASS,Float.parseFloat(this.weight.getText().toString()));
        outState.putFloat(USER_BMI,Float.parseFloat(this.txtViewIndex.getText().toString()));


        Log.d(getClass().getSimpleName(),"onSaveInstance: onSaveInstanceState");
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //this.height.setText(Float.toString(savedInstanceState.getFloat(USER_HEIGHT)));
        //this.weight.setText(Float.toString(savedInstanceState.getFloat(USER_MASS)));
        Float index=savedInstanceState.getFloat(USER_BMI);
        this.txtViewIndex.setText(Float.toString(index));
        BMIInfo.categorie.getGategory(index);
        this.txtViewCategory.setText(BMIInfo.categorie.getGategory(index).getOmschrijving());

        Log.d(getClass().getSimpleName(),"onRestoreInstanceState: getting data");

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
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_BMI,0);
        SharedPreferences.Editor editor = settings.edit();
        String height = this.height.getText().toString();
        String weight = this.weight.getText().toString();
        if(height != ""){
            editor.putFloat(USER_HEIGHT,Float.parseFloat(height));
        }
        if(weight != ""){
            editor.putFloat(USER_MASS,Float.parseFloat(weight));
        }
        editor.commit();
        Log.d(getClass().getSimpleName(),"Activity: onStop()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
        if(id == R.id.action_chart){
            Intent intent = new Intent(BmiActivity.this,TestActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
