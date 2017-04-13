package be.howest.nmct.horoscoop.view;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.howest.nmct.horoscoop.R;
import be.howest.nmct.horoscoop.helper.HoroscoopImage;
import be.howest.nmct.horoscoop.model.Data;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment {

    private OnMainFragmentListener mListener;

    private String selected_geboortejaar = "";
    private Data.Horoscoop mijnHoroscoop;

    @BindView(R.id.textviewGeboortejaar)
    TextView textviewGeboortejaar;

    @BindView (R.id.imageViewGeselecteerdeHoroscoop)
    ImageView imageViewGeselecteerdeHoroscoop;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainFragmentListener) {
            mListener = (OnMainFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        toonMijnGeboortejaar();
        toonMijnHoroscoop();
        return v;
    }

    private void toonMijnGeboortejaar() {
        if (!selected_geboortejaar.isEmpty())
            textviewGeboortejaar.setText("Geboortejaar: " + selected_geboortejaar);
    }

    private void toonMijnHoroscoop() {
        if (mijnHoroscoop != null)
            imageViewGeselecteerdeHoroscoop
                    .setImageResource(HoroscoopImage.getResourceId(mijnHoroscoop));
    }

    @OnClick(R.id.btnSelecteerGeboortejaar)
    public void selecteerGeboortejaar() {
        if (mListener != null)
            mListener.vraagNieuwGeboortejaar();
    }

    @OnClick(R.id.btnSelecteerHoroscoop)
    public void selecteerHoroscoop() {
        if (mListener != null)
            mListener.vraagNieuwHoroscoop();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showNieuwGeboortejaar(String sGeboortejaar) {
        selected_geboortejaar = sGeboortejaar;
    }

    public void showNieuwHoroscoop(Data.Horoscoop nieuweHoroscoop) {
        mijnHoroscoop = nieuweHoroscoop;
    }

    public interface OnMainFragmentListener {
        public void vraagNieuwGeboortejaar();

        public void vraagNieuwHoroscoop();
    }


}
