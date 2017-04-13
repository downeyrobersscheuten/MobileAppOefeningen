
package be.howest.nmct.cheesesquare.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import be.howest.nmct.cheesesquare.R;
import be.howest.nmct.cheesesquare.model.Cheeses;

public class CheeseListFragment extends Fragment {


    public CheeseListFragment() {
    }

    public static CheeseListFragment newInstance() {
        CheeseListFragment cheeseListFragment = new CheeseListFragment();

        return cheeseListFragment;
    }
    private OnCheeseListFragmentListener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cheese_list, container, false);
        List<String> listCheeses = Cheeses.getRandomCheeseList(30); //slechts 30 kazen tonen
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewCheeses);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new CheeseRecyclerViewAdapter(getActivity(),listCheeses));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnCheeseListFragmentListener)
            mListener = (OnCheeseListFragmentListener) context;
    }

    public class CheeseViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public String mBoundString;
        public int imageResource;

        public CheeseViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.mImageView = (ImageView) itemView.findViewById(R.id.imageviewCheese);
            this.mTextView = (TextView) itemView.findViewById(R.id.textviewCheese);

        }
        @Override
        public String toString() {
            return super.toString() + " '" + mTextView;
        }
    }

    public class CheeseRecyclerViewAdapter extends RecyclerView.Adapter<CheeseViewHolder>{
        private List<String> nameCheeses;

        public CheeseRecyclerViewAdapter(Context context, List<String> items){
            nameCheeses = items;
        }

        @Override
        public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //view ophalen om een viewholder te kunnen aanmaken
            View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_cheese,parent,false);
            //viewholder aanmaken
            CheeseViewHolder cheeseViewHolder = new CheeseViewHolder(view);
            return cheeseViewHolder;
        }

        @Override
        public void onBindViewHolder(CheeseViewHolder holder, int position) {
            //hier gaan we dus de data opvullen van de cheeseviewholder die we net hebben aangemaakt
            // in de vorige method (onCreateViewHolder)

            holder.imageResource = Cheeses.getRandomCheeseDrawable();
            holder.mBoundString = Cheeses.sCheeseStrings[position];

            holder.mImageView.setImageResource(holder.imageResource);
            holder.mTextView.setText(holder.mBoundString);


        }

        @Override
        public int getItemCount() {
            return Cheeses.sCheeseStrings.length;
        }
    }

    interface OnCheeseListFragmentListener {
        public void OnCheeseClick(String zever,int id);
    }


}
