package be.howest.nmct.horoscoop.view;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.howest.nmct.horoscoop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectGeboortejaarFragment extends ListFragment {

    //gebruik listview binnen een fragment
    //optie 1: laat fragment erven van ListFragment ==> Automatisch krijg je een listview cadeau
    //optie 2: maak in de layout oa een listview aan.

    //--> hier optie 1 gebruikt

    private final static List<String> GEBOORTEJAREN;
    static {
        GEBOORTEJAREN = new ArrayList<>(Calendar.getInstance().get(Calendar.YEAR) - 1900);
        for (int jaar = 1900; jaar <= Calendar.getInstance().get(Calendar.YEAR); jaar++) {
            GEBOORTEJAREN.add("" + jaar);
        }
    }

    //stap 2: aanmaken
    private onSelectGeboortejaarFragmentListener mListener;

    public SelectGeboortejaarFragment() {
    }

    public static SelectGeboortejaarFragment newInstance() {
        SelectGeboortejaarFragment fragment = new SelectGeboortejaarFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        //hier wordt geen layout file gebruikt,
        //je kan de listview toch  NIET NIET NIET NIET NIET NIET NIET NIET NIET NIET NIET opvragen:
        //ListView listView = getListView();

        return v;
    }


    //kijken als fragment interface implementeerd
    // --> Indien ja dan init mListener.
    // --> Indien nee, geef een exception
    // stap 3:
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onSelectGeboortejaarFragmentListener){
            mListener = (onSelectGeboortejaarFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()
            + " must implement onSelectGeboortejaarFragment");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //de listview gaan we opvullen met een arrayAdapter
        //opm: we maken gebruik van een intern layout file: hier stelt dit enkel een textview voor
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_single_choice,GEBOORTEJAREN);
        setListAdapter(adapter);
    }

    //deze method wordt uitgevoerd wanneer een item in de listview geselecteerd wordt
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //l.getSelectedItem()
        String geslecteerdJaar = GEBOORTEJAREN.get(position);

        //stap 4 om listener in te stellen
        if(mListener != null)
            mListener.geboortejaarGeselecteerd(geslecteerdJaar);

    }


    //stap 1
    //maak een interface voor de luisteraar aan --> dient om te tonen welke waarde geselecteerd is.
    public interface onSelectGeboortejaarFragmentListener{
        public void geboortejaarGeselecteerd(String geselecteerdJaar);
    }

}

