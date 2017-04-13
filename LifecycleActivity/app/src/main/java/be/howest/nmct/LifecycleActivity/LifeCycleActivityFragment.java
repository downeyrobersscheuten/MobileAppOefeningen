package be.howest.nmct.LifecycleActivity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class LifeCycleActivityFragment extends Fragment {

    private Button buttonClose;

    public LifeCycleActivityFragment() {
    }

    //dezemethode zal de layout voor een fragment gaan inladen
    //stap 1: vang de ingeladen view op van de inflater.Inflate() methode
    //stap 2: geef onderaan deze view terug
    //stap 3: ga analoog te werk zoals een activity


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_life_cycle, container, false);
        buttonClose = (Button) v.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sluitAf();
            }


        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(getClass().getName(),"LifeCycleFragment: onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getName(),"LifeCycleFragment: onCreate()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getClass().getName(),"LifeCycleFragment: onActivityCreated()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(getClass().getName(),"LifeCycleFragment: onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getClass().getName(),"LifeCycleFragment: onPause()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(getClass().getName(),"LifeCycleFragment: onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getName(),"LifeCycleFragment: onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(getClass().getName(),"LifeCycleFragment: onDetach()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getClass().getName(),"LifeCycleFragment: onStop()");
    }

    private void sluitAf() {
        //we geven opdracht aan de omsluitende activity om zich af te sluiten.
        //
        getActivity().finish();
    }
}
