package com.troncodroide.heroadventurehelper.citicens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.managers.LoadImageManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CiticensCardListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CiticensCardViewAdapter";
    private final List<CiticenData> mValues;
    private CiticensListListener listener;

    public interface CiticensListListener {
        void onItemClick(CiticenData data);
    }

    public CiticensCardListViewAdapter(List<CiticenData> items, CiticensListListener listener) {
        super();
        this.listener = listener;
        mValues = items;
    }

    private CiticenData getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citicen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder parentHolder, int position) {

        if (parentHolder != null) {
            final ViewHolder holder = (ViewHolder) parentHolder;
            holder.mItem = getItem(position);
            holder.mName.setText(holder.mItem.getName());
            holder.mHair.setText(String.format("Hair:%s", holder.mItem.getHair_color()));
            holder.mAge.setText(String.format("Age:%d", holder.mItem.getAge()));
            holder.mHeight.setText(String.format("Height:%s", holder.mItem.getHeight()));
            holder.mWeight.setText(String.format("Weight:%s", holder.mItem.getWeight()));
            LoadImageManager.loadImage(holder.mItem.getThumbnail(), holder.mImage);
            holder.mHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(holder.mItem);
                    }
                }
            });
            holder.friends.setAdapter(new ArrayAdapter<>(APP.getContext(), android.R.layout.simple_list_item_1, holder.mItem.getFriends()));
            holder.profesions.setAdapter(new ArrayAdapter<>(APP.getContext(), android.R.layout.simple_list_item_1, holder.mItem.getProfessions()));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public CiticenData mItem;

        @Bind(R.id.citicen_card_image)
        public ImageView mImage;
        @Bind(R.id.citicen_card_name)
        public TextView mName;
        @Bind(R.id.citicen_card_age)
        public TextView mAge;
        @Bind(R.id.citicen_card_hair)
        public TextView mHair;
        @Bind(R.id.citicen_card_height)
        public TextView mHeight;
        @Bind(R.id.citicen_card_weight)
        public TextView mWeight;
        @Bind(R.id.citicen_card_friends)
        ListView friends;
        @Bind(R.id.citicen_card_profesions)
        ListView profesions;

        @Bind(R.id.citicen_card_help)
        public Button mHelp;

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

