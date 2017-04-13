package be.howest.nmct.NavigationDrawer;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {

    @BindView(R.id.aantal_correct)
    TextView textViewCorrect;

    @BindView(R.id.aantal_verkeerd)
    TextView textViewVerkeerd;
    private int aantalCorrect = 0;
    private int aantalVerkeerd = 0;


    public FragmentB() {
        // Required empty public constructor
    }
    public static FragmentB newInstance(int aantalCorrect,int aantalverkeerd){
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putInt(Constants.CORRECT,aantalCorrect);
        args.putInt(Constants.VERKEERD,aantalverkeerd);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        ButterKnife.bind(this,view);

        if(getArguments() != null){
            if(getArguments().containsKey(Constants.CORRECT)){
                this.aantalCorrect = getArguments().getInt(Constants.CORRECT);
            }
            if(getArguments().containsKey(Constants.VERKEERD))
                this.aantalVerkeerd = getArguments().getInt(Constants.VERKEERD);
        }
        printFeedback();

        //terug zetten dat na het pauzeren van fragment
//        if(savedInstanceState != null){
//            if(savedInstanceState.containsKey(Constants.CORRECT)){
//
//            }
//        }

        return view;

    }
    private void printFeedback() {
        textViewCorrect.setText(getResources().getString(R.string.aantal_juiste_antwoorden) + " " + aantalCorrect);
        textViewVerkeerd.setText(getResources().getString(R.string.aantal_foutieve_antwoorden) + " " + aantalVerkeerd);
    }


}
