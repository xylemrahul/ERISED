package com.erised.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.erised.R;

import java.util.ArrayList;

/**
 * Created by neosoft on 14/11/15.
 */
public class GridAdapter extends BaseAdapter {

    Context ctxt;
    ArrayList<String> urls;

    public GridAdapter(Context context, ArrayList<String> url) {

        this.ctxt = context;
        this.urls = url;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView gallery;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        View gridView = null;
        LayoutInflater inflater = (LayoutInflater)ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            gridView = convertView;

            vh = new ViewHolder();
            gridView = inflater.inflate(R.layout.grid_items,null,false);

            vh.gallery = (ImageView)gridView.findViewById(R.id.img_gallery);
        }
        return gridView;
    }
}
