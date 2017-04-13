package be.howest.nmct.rekenen;

import android.content.Context;

import android.os.Bundle;

//standaard platform app.fragment
import android.app.Fragment;

//volgende is voor oudere platformen waar fragments nog niet van toepassing waren
// --> vanaf V3 introductie
// gebruikt andere methodes met andere namen
//import android.support.v4.app

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//fragment klasse
public class ExerciseFragment extends Fragment {

    private int randomNumber = 2;

    @BindView(R.id.textview_exercise)
    TextView textview_exercise;

    @BindView(R.id.editTextAnswer)
    EditText editTextAnswer;

    @BindView(R.id.textviewfeedback)
    TextView textviewfeedback;

    //listener bijhouden
    private OnExerciseFragmentListener mListener;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    //cfr lifeycle fragment: dit is de eerste methode die bij het
    //aanmaken van een fragment (na de ctor) uitgevoerd wordt
    //de parameter is een link naar de parent (hier de activity)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnExerciseFragmentListener){
            mListener = (OnExerciseFragmentListener)context;
        }else{
            //foutmelding indien dit niet het geval is
            throw new ClassCastException("Activity must implement OnExcerciseFragmentListener");
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //vanaf hier kan je pas dingen opslaan of opvragen uit/in views
        //belangerijkste methode
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        //Butterknife lib hier gebruiken ipv in de onCreate
        //in een activity is dit niet zo
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        generateNextExercise();
    }

    @OnClick(R.id.controleer)
    public void submit() {
        textviewfeedback.setVisibility(View.VISIBLE);
        try {
            int number = Integer.parseInt(editTextAnswer.getText().toString());
            if (Math.pow(randomNumber, 2) != number){
                //foutief antwoord
                textviewfeedback.setText("Foutief antwoord!");
                textviewfeedback.setTextColor(ContextCompat.getColor(this.getContext(), R.color.red));
                //listener verwittigen
                mListener.verkeerdAntwoord();
            } else {
                //correct antwoord
                textviewfeedback.setText("Correct antwoord!");
                textviewfeedback.setTextColor(ContextCompat.getColor(this.getContext(), R.color.green));
                generateNextExercise();
                //listener verwittigen
                mListener.correctAntwoord();
            }

            editTextAnswer.setText("");

        } catch (NumberFormatException ex) {
            Snackbar.make(editTextAnswer,"Foutieve waarde.",Snackbar.LENGTH_LONG).show();
        }

    }


    private void generateNextExercise(){
        Random random = new Random();
        randomNumber = random.nextInt(20);
        textview_exercise.setText(getResources().getString(R.string.opdracht_bewerking) +" " +randomNumber +"?");
    }

    public interface OnExerciseFragmentListener{
        public void correctAntwoord();
        public void verkeerdAntwoord();
    }



}
