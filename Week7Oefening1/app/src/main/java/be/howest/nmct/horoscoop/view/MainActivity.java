package be.howest.nmct.horoscoop.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.howest.nmct.horoscoop.R;
import be.howest.nmct.horoscoop.model.Data;

public class MainActivity extends AppCompatActivity implements MainFragment.OnMainFragmentListener, SelectGeboortejaarFragment.onSelectGeboortejaarFragmentListener,SelectHoroscoopFragment.onSelectHoroscoopFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MainFragment mainFragment = new MainFragment();
            fragmentTransaction.replace(R.id.framelayout, mainFragment, "mainFragment");
            // met de string hier kan je ze terug halen vanuit de backstack ^^^^^^^^
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            //ik zit reeds in ChangeFragment. We beÃ«indigen de app.
            super.onBackPressed();
        } else {
            //ik zit in SelectGeboortejaarFragment of SelectHoroscoopFragment en wens terug te keren naar MainFragment
            //Backstack: laatste transaction terug spoelen
            getFragmentManager().popBackStack();
        }
    }


    @Override
    public void vraagNieuwGeboortejaar() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelectGeboortejaarFragment geboortejaarFragment = SelectGeboortejaarFragment.newInstance();
        fragmentTransaction.replace(R.id.framelayout, geboortejaarFragment);
        fragmentTransaction.addToBackStack("selectGeboortejaarFragment");
        fragmentTransaction.commit();
    }


    @Override
    public void vraagNieuwHoroscoop() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelectHoroscoopFragment selectHoroscoopFragment = SelectHoroscoopFragment.newInstance();
        fragmentTransaction.replace(R.id.framelayout, selectHoroscoopFragment);
        fragmentTransaction.addToBackStack("selectHoroscoopFragment2");
        fragmentTransaction.commit();
    }


    @Override
    public void geboortejaarGeselecteerd(String geselecteerdJaar) {
        //wordt uitgevoerd wanneer er een nieuwe wordt geselecteerd.

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag("mainFragment");

        mainFragment.showNieuwGeboortejaar(geselecteerdJaar);

    }

    @Override
    public void horoscoopGeselecteerd(Data.Horoscoop geselecteerd) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        MainFragment mainFragment =(MainFragment) fragmentManager.findFragmentByTag("mainFragment");

        mainFragment.showNieuwHoroscoop(geselecteerd);
    }
}
