package be.howest.nmct.cheesesquare.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.howest.nmct.cheesesquare.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CheeseDetailFragment extends Fragment {

    private String cheeseName;
    @BindView(R.id.infoTextView)
    protected TextView textViewInfo;
    @BindView(R.id.friendsTextView)
    protected TextView textViewFriends;
    @BindView(R.id.relatedTextView)
    protected TextView textViewRelated;

    public CheeseDetailFragment() {
    }

    public static CheeseDetailFragment newInstance() {
        CheeseDetailFragment fragment = new CheeseDetailFragment();

        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cheese_detail, container, false);
        ButterKnife.bind(this,view);
        //nog iets doen met de view

        return view;
    }



}
