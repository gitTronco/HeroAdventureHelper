package com.troncodroide.heroadventurehelper.towns;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.models.HeroData;
import com.troncodroide.heroadventurehelper.models.TownData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TownsCardListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TownsCardViewAdapter";
    private final List<TownData> mValues;
    private TownsListListener listener;

    public interface TownsListListener {
        void onItemClick(TownData data);
    }

    public TownsCardListViewAdapter(List<TownData> items, TownsListListener listener) {
        super();
        this.listener = listener;
        mValues = items;
    }

    private TownData getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_town, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder parentHolder, int position) {

        if (parentHolder != null) {
            final ViewHolder holder = (ViewHolder) parentHolder;
            holder.mItem = getItem(position);
            holder.mName.setText(holder.mItem.getName());
            holder.mPoluation.setText(String.format("Population: %s", holder.mItem.getCiticens().size()));
            holder.mProgress.setText(
                    String.format("Helped: %d%%", holder.mItem.getProgress()));
            holder.mVisit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(holder.mItem);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TownData mItem;

        @Bind(R.id.town_card_image)
        public ImageView mImage;
        @Bind(R.id.town_card_name)
        public TextView mName;
        @Bind(R.id.town_card_population)
        public TextView mPoluation;
        @Bind(R.id.town_card_progress)
        public TextView mProgress;
        @Bind(R.id.town_card_visit)
        public Button mVisit;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.toString() + "'";
        }
    }

}

