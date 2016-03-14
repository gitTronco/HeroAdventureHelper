package com.troncodroide.heroadventurehelper.citicens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.managers.LoadImageManager;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.views.CollapsableLayoutView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CiticensCardListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CiticensCardViewAdapter";
    private final List<CiticenData> mValues;
    private CiticensListListener listener;

    public interface CiticensListListener {
        void onItemClick(CiticenData data);

        void onFriendClick(CiticenData data, String friend);

        void onProfesionClick(CiticenData data, String profesion);
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
            holder.mHeader_friends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mCollapsable_friends.toggle();
                }
            });
            holder.mHeader_profesions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mCollapsable_profesions.toggle();
                }
            });
            holder.mCollapsable_friends.setListenerEvents(new CollapsableLayoutView.CollapsableEventsListener() {
                @Override
                public void onOpended() {

                }

                @Override
                public void onClossed() {

                }

                @Override
                public void onProgressPercent(float percent) {
                    holder.mChevron_friends.setRotation(-1 * (180 * percent));
                }
            });

            holder.mCollapsable_profesions.setListenerEvents(new CollapsableLayoutView.CollapsableEventsListener() {
                @Override
                public void onOpended() {

                }

                @Override
                public void onClossed() {

                }

                @Override
                public void onProgressPercent(float percent) {
                    holder.mChevron_profesions.setRotation(-1 * (180 * percent));
                }
            });
            LoadImageManager.loadImage(holder.mItem.getThumbnail(), holder.mImage);
            holder.mHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(holder.mItem);
                    }
                }
            });
            holder.friends.setAdapter(new ArrayAdapter<>(APP.getContext(), R.layout.list_item_text, holder.mItem.getFriends()));
            holder.profesions.setAdapter(new ArrayAdapter<String>(APP.getContext(), R.layout.list_item_text, holder.mItem.getProfessions()) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView view = (TextView) super.getView(position, convertView, parent);
                    if (holder.mItem.hasHelped(getItem(position))) {
                        view.setTextColor(APP.GetColor(android.R.color.holo_green_dark));
                    } else {
                        view.setTextColor(APP.GetColor(android.R.color.holo_red_dark));
                    }
                    return view;
                }
            });
            holder.friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String friend = parent.getItemAtPosition(position).toString();
                    if (listener != null) {
                        listener.onFriendClick(holder.mItem, friend);
                    }
                }
            });
            holder.profesions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String profesion = parent.getItemAtPosition(position).toString();
                    if (listener != null) {
                        listener.onProfesionClick(holder.mItem, profesion);
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

        @Bind(R.id.citicen_card_wrapper_collapsable_friends)
        public CollapsableLayoutView mCollapsable_friends;
        @Bind(R.id.citicen_card_wrapper_collapsable_profesions)
        public CollapsableLayoutView mCollapsable_profesions;
        @Bind(R.id.citicen_card_wrapper_header_friends)
        public View mHeader_friends;
        @Bind(R.id.citicen_card_wrapper_header_profesions)
        public View mHeader_profesions;
        @Bind(R.id.citicen_card_header_profesions_chevron)
        public View mChevron_profesions;
        @Bind(R.id.citicen_card_header_friends_chevron)
        public View mChevron_friends;

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

