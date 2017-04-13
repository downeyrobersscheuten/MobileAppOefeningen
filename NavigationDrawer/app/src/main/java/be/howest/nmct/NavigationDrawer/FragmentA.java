package be.howest.nmct.NavigationDrawer;


import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {

    @BindView(R.id.textview_exercise)
    TextView textview_exercise;

    @BindView(R.id.editTextAnswer)
    EditText editTextAnswer;

    @BindView(R.id.textviewfeedback)
    TextView textviewfeedback;

    private int randomNumber = 2;
    private OnExerciseFragmentListener mListener;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_a,container,false);
        ButterKnife.bind(this,view);


        return view;
    }
    public void onResume() {
        super.onResume();
        generateNextExercise();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnExerciseFragmentListener){
            mListener = (OnExerciseFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()
            + " must implement OnExerciseFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
