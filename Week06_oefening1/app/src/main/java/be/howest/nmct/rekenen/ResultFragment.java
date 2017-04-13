package be.howest.nmct.rekenen;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultFragment extends Fragment {

    @BindView(R.id.aantal_correct)
    TextView textViewCorrect;

    @BindView(R.id.aantal_verkeerd)
    TextView textViewVerkeerd;

    private int aantalCorrect = 0;
    private int aantalVerkeerd = 0;

    public static final String CORRECT = "be.howest.nmct.rekenen.ResultFragment.correct";
    public static final String VERKEERD = "be.howest.nmct.rekenen.ResultFragment.verkeerd";

    public ResultFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, v);
        //interne data terug zetten
        if(savedInstanceState != null){

            if(savedInstanceState.containsKey(CORRECT))
                aantalCorrect = savedInstanceState.getInt(CORRECT);

            if (savedInstanceState.containsKey(VERKEERD))
                aantalVerkeerd = savedInstanceState.getInt(VERKEERD);

            printFeedback();

            //bij set text lege strings ook mee geven
            //textViewCorrect.setText(""+aantalCorrect);
            //textViewVerkeerd.setText(""+aantalVerkeerd);
        }


        return v;
    }


    protected void updateResult(boolean succes) {
        if (succes) aantalCorrect++;
        else aantalVerkeerd++;
        printFeedback();
    }

    private void printFeedback() {
        textViewCorrect.setText(getResources().getString(R.string.aantal_juiste_antwoorden) + " " + aantalCorrect);
        textViewVerkeerd.setText(getResources().getString(R.string.aantal_foutieve_antwoorden) + " " + aantalVerkeerd);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CORRECT,aantalCorrect);
        outState.putInt(VERKEERD,aantalVerkeerd);
    }

}
