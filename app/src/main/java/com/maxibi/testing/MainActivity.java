package com.maxibi.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public Button buttonSearch;
    public EditText editText;
    public TextView textView;

    ArrayList<Word> definition =new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView)findViewById(R.id.listView);
        buttonSearch = (Button)findViewById(R.id.searchButton);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.test);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<String> quotes = databaseAccess.getQuotes(); // dapatkan semua qoutes

        definition = databaseAccess.getAllWords();
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return definition.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item,null);
                }
                ////add textview

                return view;
            }
        });

        databaseAccess.close();
    }
}
