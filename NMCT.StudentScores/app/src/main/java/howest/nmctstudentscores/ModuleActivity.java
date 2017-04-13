package howest.nmctstudentscores;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import howest.nmctstudentscores.Models.Student;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_module, menu);
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
    public static void main(String[] args){
        List<Student> studs = new ArrayList<Student>();
        Student Downey = new Student("Downey the great");
        Downey.VoegScoreToe("Mobile App Development",14.0,6);
        Downey.VoegScoreToe("Servers",12.0,6);
        Downey.VoegScoreToe("New Media",2.0,3);
        System.out.println(Downey);

        Student Jos = new Student("Jos the Strever");
        Jos.VoegScoreToe("Mobile App Development",15.0,6);
        Jos.VoegScoreToe("Servers",11.0,6);
        Jos.VoegScoreToe("New Media",5.0,3);

        Student Gilles = new Student("Gilles Kappameister");
        Gilles.VoegScoreToe("Mobile App Development",6.0,6);
        Gilles.VoegScoreToe("Servers",6.0,6);
        Gilles.VoegScoreToe("New Media",6.0,3);

        studs.add(Gilles);
        studs.add(Jos);
        studs.add(Downey);

        System.out.println(Student.getGemiddeldeScoresModule(studs,"New Media"));

        System.out.println(Student.getScoresModule(studs,"New Media"));

        Student.sorteerStudenten(studs);
        System.out.println(studs);







    }
}
