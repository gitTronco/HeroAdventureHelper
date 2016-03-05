package com.troncodroide.heroadventurehelper.Base.adapters;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PopupWIndowAdapter extends BaseAdapter {
    private List<BaseMenuItem> _items;
    private LayoutInflater inflater = null;


    public PopupWIndowAdapter(Context context, List<BaseMenuItem> items) {
        _items = items;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public BaseMenuItem getItem(int position) {
        return _items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getText();
    }

    public class Holder {
        TextView tv;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseMenuItem item = getItem(position);
        Holder holder = new Holder();
        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.popupwindow_item, null);
//            holder.tv = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv.setText(item.getText());

        return convertView;
    }

    public static class BaseMenuItem implements Parcelable {
        int id;
        int text;

        public BaseMenuItem(int id, int text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public int getText() {
            return text;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.text);
        }

        protected BaseMenuItem(Parcel in) {
            this.id = in.readInt();
            this.text = in.readInt();
        }

        public static final Creator<BaseMenuItem> CREATOR = new Creator<BaseMenuItem>() {
            public BaseMenuItem createFromParcel(Parcel source) {
                return new BaseMenuItem(source);
            }

            public BaseMenuItem[] newArray(int size) {
                return new BaseMenuItem[size];
            }
        };
    }
}
