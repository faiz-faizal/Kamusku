package com.maxibi.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView)findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.test);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final ArrayList<Word> quotes = databaseAccess.getQuotes(); // dapatkan semua qoutes

        final CustomAdapter customAdapter = new CustomAdapter(this,quotes);

        listView.setAdapter(customAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                customAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Log.d("POSITON" , "POSTION " + position); //Check position
                Intent intent = new Intent(MainActivity.this, PopUp.class);
                intent.putExtra("Word", quotes.get(position).bm);
                intent.putExtra("Definition", quotes.get(position).bi);

                startActivity(intent);
            }
        });




        /*buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();

                Word word = databaseAccess.getWord(str);

                if (word == null)
                {
                    Toast.makeText(MainActivity.this, "Word Not Found",Toast.LENGTH_SHORT).show();
                }

                else{
                    Intent intent = new Intent(MainActivity.this, PopUp.class);
                    intent.putExtra("Word", word.bm);
                    intent.putExtra("Definition", word.bi);

                    startActivity(intent);
                }
            }

        });*/


    }



}
