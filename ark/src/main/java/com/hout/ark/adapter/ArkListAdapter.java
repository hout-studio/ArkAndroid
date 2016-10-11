package com.hout.ark.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brlnt on 10/10/16.
 */

public abstract class ArkListAdapter<T> extends BaseAdapter {
    protected List<T> listObject;
    private Context mContext;
    protected LayoutInflater mInflater;

    public ArkListAdapter(@NonNull Context context, @NonNull List<T> objects) {
        if(listObject == null) {
            listObject = new ArrayList<>();
        }
        this.listObject.addAll(objects);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object getItem(int position) {
        return listObject.get(position);
    }

    @Override
    public int getCount() {
        if (listObject == null) {
            return 0;
        }

        return listObject.size();
    }

    public void update(@NonNull List<T> objects){
        listObject.clear();
        listObject.addAll(objects);
        this.notifyDataSetChanged();
    }
}
