package com.troncodroide.heroadventurehelper.launch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.models.HeroData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HerosListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HerosRecyclerView";
    private final List<HeroData> mValues;
    private HerosListListener listener;

    public interface HerosListListener {
        void onItemClick(HeroData order);
    }

    public HerosListRecyclerViewAdapter(List<HeroData> items, HerosListListener listener) {
        super();
        this.listener = listener;
        mValues = items;
    }

    private HeroData getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder parentHolder, int position) {

        if (parentHolder != null) {
            final ViewHolder holder = (ViewHolder) parentHolder;
            holder.mItem = getItem(position);
            holder.mImage.setImageResource(holder.mItem.getResImage());
            holder.mName.setText(String.format("Name:%s", holder.mItem.getName()));
            holder.mLevel.setText(String.format("Level:%d", holder.mItem.getLevel()));
            holder.mView.setOnClickListener(new View.OnClickListener() {
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
        public HeroData mItem;

        @Bind(R.id.hero_image)
        public ImageView mImage;
        @Bind(R.id.hero_name)
        public TextView mName;
        @Bind(R.id.hero_level)
        public TextView mLevel;

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

