package be.howest.nmct.horoscoop.view;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import be.howest.nmct.horoscoop.R;
import be.howest.nmct.horoscoop.helper.HoroscoopImage;
import be.howest.nmct.horoscoop.model.Data;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHoroscoopFragment extends Fragment {

    //enkel in een fragment gebruiken
    @BindView(R.id.recyclerview_horoscopen)
    RecyclerView recyclerViewHoroscopen;
    private onSelectHoroscoopFragmentListener mListener;

    public SelectHoroscoopFragment() {
        // Required empty public constructor
    }

    public static SelectHoroscoopFragment newInstance() {
        SelectHoroscoopFragment fragment = new SelectHoroscoopFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if( context instanceof onSelectHoroscoopFragmentListener){
            mListener = (onSelectHoroscoopFragmentListener) context;
        }else{
            throw new RuntimeException(context +
                    " must implement interface : onSelectHoroscoopFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_horoscoop, container, false);
        ButterKnife.bind(this, v);

        //recycler config
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewHoroscopen.setLayoutManager(layoutManager);
        //hoe worden de nieuwe items toegevoegd onderaan bovenaan
        //default = alsje door de lijst gaat zie je ze mooi bewegen
        recyclerViewHoroscopen.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHoroscopen.setAdapter(new HoroscoopRecycleViewAdapter());

        return v;
    }
    //stap 1: viewholder aanmaken. Vertegenwoordigt 1 rij uit de recycleview
    class HoroscoopViewHolder extends RecyclerView.ViewHolder  {
        //som de views op die via code moeten opgevuld worden
        TextView textViewNaam;
        ImageView imageViewHoroscoop;
        Button btnInfo;
        Data.Horoscoop horoscoop;

        public HoroscoopViewHolder(View row){
            super(row);
            textViewNaam = (TextView) row.findViewById(R.id.textViewNaamHoroscoop);
            imageViewHoroscoop = (ImageView) row.findViewById(R.id.imageviewHoroscoop);
            btnInfo = (Button) row.findViewById(R.id.btnToonInfo);

            btnInfo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String periode = "periode: "
                            + horoscoop.getBeginDatum()
                            + " - " + horoscoop.getEindDatum();
                    Snackbar.make(recyclerViewHoroscopen,periode,Snackbar.LENGTH_SHORT).show();
                }});
            row.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mListener != null)
                                mListener.horoscoopGeselecteerd(horoscoop);
                        }
                    }
            );
        }
    }

    //stap 2: adapter aanmaken. Hij staat in voor opvullen van recycleView
    //achteraan vermelden we datatype van het object
    class HoroscoopRecycleViewAdapter extends RecyclerView.Adapter<HoroscoopViewHolder>{

        //viewholder aanmaken
        @Override
        public HoroscoopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_horoscoop,parent,false);

            HoroscoopViewHolder horoscoopViewHolder = new HoroscoopViewHolder(view);
            return horoscoopViewHolder;
        }

        //viewholder opvullen met data
        @Override
        public void onBindViewHolder(HoroscoopViewHolder holder, int position) {
            Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];
            holder.horoscoop = horoscoop;
            holder.textViewNaam.setText(horoscoop.getNaamHoroscoop());
            holder.imageViewHoroscoop.setImageResource(HoroscoopImage.getResourceId(horoscoop));
        }

        @Override
        public int getItemCount() {
            //zoveel items zitten er in mijn lijstd
            return Data.Horoscoop.values().length;
        }
    }

    public interface onSelectHoroscoopFragmentListener{
        public void horoscoopGeselecteerd(Data.Horoscoop geselecteerd);
    }

}
