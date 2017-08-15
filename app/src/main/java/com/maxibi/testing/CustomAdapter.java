package com.maxibi.testing;

import android.content.Context;
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

    //Constructor
    public CustomAdapter(Context context, ArrayList<Word> wordArrayList) {
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
                FilterResults filterResult = new FilterResults();
                if( charSequence == null || charSequence.length() == 0 )
                {
                    // no filter implement here
                    filterResult.values = wordArrayList;
                    filterResult.count = wordArrayList.size();

                }
                else
                {
                    // perform filter operation
                    ArrayList<Word> temp = new ArrayList<Word>();
                    for ( Word w : wordArrayList){
                        if(w.getBm().toUpperCase().startsWith(charSequence.toString()
                                .toUpperCase()))temp.add(w);
                    }
                    filterResult.values = temp;
                    filterResult.count = temp.size();
                }
                return filterResult;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                //Inform adapter about new list filtered
                if( filterResults.count == 0)
                {
                    notifyDataSetInvalidated();
                }
                else
                {
                    notifyDataSetChanged();
                }
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