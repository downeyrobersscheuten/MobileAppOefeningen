package be.howest.nmct.rekenen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ExerciseFragment.OnExerciseFragmentListener {

    ExerciseFragment exerciseFragment;
    ResultFragment resultFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fragments ophalen gebeurt met de method getFragmentManager().findFragmentById(blaab);
        exerciseFragment = (ExerciseFragment) getFragmentManager().findFragmentById(R.id.fragment_exercise);
        resultFragment = (ResultFragment) getFragmentManager().findFragmentById(R.id.fragment_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    //verplichte methods door interface
    @Override
    public void correctAntwoord() {
        resultFragment.updateResult(true);
    }

    @Override
    public void verkeerdAntwoord() {
        resultFragment.updateResult(false);
    }
}
