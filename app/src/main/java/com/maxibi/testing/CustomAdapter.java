package com.maxibi.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 8/14/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Word> wordArrayList;

    //Constructor
    public CustomAdapter(Context context, ArrayList<Word> wordArrayList){
        this.context = context;
        this.wordArrayList = wordArrayList;
    }

    @Override
    public int getCount() {
        return wordArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if( view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null );
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemName.setText(wordArrayList.get(i).bi);

        return view;


    }

    private class ViewHolder {
        TextView itemName;

        public ViewHolder(View view){
            itemName = (TextView)view.findViewById(R.id.test);
        }
    }
}
