package be.howest.nmct.bmicalculator;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.bmicalculator.Model.BMIInfo;
import be.howest.nmct.bmicalculator.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static be.howest.nmct.bmicalculator.Model.BMIInfo.categorie.ERNSTIG_ONDER;

public class TestActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    @BindView(R.id.lblTotalSaved) TextView lblTotal;
    @BindView(R.id.lblSavedBMI) TextView lblSelectedBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);





        //haal info op
        Float GrootOndergewicht = 0F;
        Float ErnstigOndergewicht = 0F;
        Float Ondergewicht = 0F;
        Float Normaal = 0F;
        Float Overgewicht = 0F;
        Float MatigOvergewicht = 0F;
        Float ErnstigOvergewicht = 0F;
        Float GrootOvergewicht = 0F;
        int Total = 0;

        try{
            InputStream inpStream = openFileInput(BmiActivity.FILENAME);
            InputStreamReader inpStreamReader = new InputStreamReader(inpStream);
            BufferedReader in = new BufferedReader(inpStreamReader);
            String recieveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            String x = in.readLine();





            while(x!=null){
                BMIInfo.categorie cat =  BMIInfo.categorie.getGategory(Float.parseFloat(x));

                switch (cat){
                    case GROOT_ONDER:
                        GrootOndergewicht += Float.parseFloat(x);
                        break;
                    case ERNSTIG_ONDER:
                        ErnstigOndergewicht += Float.parseFloat(x);
                        break;
                    case ONDERGEWICHT:
                        Ondergewicht += Float.parseFloat(x);
                        break;
                    case NORMAAL:
                        Normaal += Float.parseFloat(x);
                    case OVERGEWICHT:
                        Overgewicht += Float.parseFloat(x);
                    case MATIG:
                        MatigOvergewicht += Float.parseFloat(x);
                    case ERNSTIG_OVER:
                        ErnstigOvergewicht += Float.parseFloat(x);
                    case GROOT_OVER:
                        GrootOvergewicht += Float.parseFloat(x);
                }
                Total++;
                x = in.readLine();

            }
            in.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        lblTotal.setText("Er waren " + Total + " Records om deze pie aan te maken");

        final PieChart pieChart = (PieChart) findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(ErnstigOvergewicht, "Matig Overgewicht"));
        entries.add(new PieEntry(GrootOndergewicht, "Groot ondergewicht"));
        entries.add(new PieEntry(Normaal, "Normaal"));
        entries.add(new PieEntry(Overgewicht, "Overgewicht"));


        PieDataSet set = new PieDataSet(entries, "Demo");
        set.setColors(new int[]{R.color.green, R.color.yellow, R.color.red, R.color.blue}, this);
        pieChart.setOnChartValueSelectedListener(this);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {


    }

    @Override
    public void onNothingSelected() {

    }
}
