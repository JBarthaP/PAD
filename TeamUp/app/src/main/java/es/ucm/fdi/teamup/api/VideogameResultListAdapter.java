package es.ucm.fdi.teamup.api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.teamup.R;

public class VideogameResultListAdapter extends RecyclerView.Adapter<VideogameResultListAdapter.VideogameViewHolder> {

    private ArrayList<VideogameInfo> mVideogamesData;

    private Context context;

    public static class VideogameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView videogameCard;
        private TextView tituloView;

        private VideogameResultListAdapter mAdapter;

        public VideogameViewHolder(View itemView, VideogameResultListAdapter adapter) {

            super(itemView);

            //videogameCard = itemView.findViewById(R.id.videogame_card);
            //tituloView = itemView.findViewById(R.id.titulo);

            this.mAdapter = adapter;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }

    }

    public VideogameResultListAdapter(Context context, ArrayList<VideogameInfo> mVideogamesData) {
        this.mVideogamesData = mVideogamesData;
        this.context = context;
    }

    @NonNull
    @Override
    public VideogameResultListAdapter.VideogameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_recyclerview, parent, false);
        View mItemView = new TextView(parent.getContext());
        return new VideogameViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull VideogameResultListAdapter.VideogameViewHolder holder, int position) {

        VideogameInfo currentVideogame = mVideogamesData.get(position);
        String mCurrent = currentVideogame.toString();

        holder.tituloView.setText(currentVideogame.getTitle());

        holder.videogameCard.setOnClickListener(view -> {
            // Cuando le damos click al card
        });
    }

    @Override
    public int getItemCount() {
        return mVideogamesData.size();
    }

    public void setVideogamesData(List<VideogameInfo> data) {
        mVideogamesData = new ArrayList<VideogameInfo>(data);
    }

    public ArrayList<VideogameInfo> getmVideogamesData() {
        return mVideogamesData;
    }


}
