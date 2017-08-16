package com.maxibi.testing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 8/14/2017.
 */

public class CustomAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<Word> wordArrayList;
    private ArrayList<Word> arrayList;


    //Constructor
    public CustomAdapter(Context context, ArrayList<Word> wordArrayList) {
        this.context = context;
        this.wordArrayList = wordArrayList;
        this.arrayList = wordArrayList;
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemName.setText(wordArrayList.get(i).bm);

        return view;


    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                ArrayList<Word> tempArrayList = new ArrayList<Word>();

                if( charSequence.length() == 0)
                {
                    //set the original result to return
                    filterResults.count = arrayList.size();
                    filterResults.values = arrayList;

                    Log.d("abc", "running 00000000000000000000000000000000: ");
                }
                else
                {
                    charSequence = charSequence.toString().toLowerCase();
                    for( int i = 0; i < arrayList.size(); i++ )
                    {
                        Word data = arrayList.get(i);
                        if( data.getBm().toLowerCase().startsWith(charSequence.toString()))
                        {
                            tempArrayList.add(data);
                        }
                    }
                    //set the filtered result to return
                    filterResults.count = tempArrayList.size();
                    filterResults.values = tempArrayList;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                wordArrayList = (ArrayList<Word>) filterResults.values;

                notifyDataSetInvalidated();
                Log.d("abc", "running: ade masalah bro..." + charSequence.length());

            }
        };
    }


    /////////////////////////////////////////////////////
    private class ViewHolder {
        TextView itemName;

        public ViewHolder(View view) {
            itemName = (TextView) view.findViewById(R.id.test);
        }
    }

}