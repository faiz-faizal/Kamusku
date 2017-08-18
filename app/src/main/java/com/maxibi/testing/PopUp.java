package com.maxibi.testing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by User on 8/14/2017.
 */

public class PopUp extends Activity{

    TextView msTextView;
    TextView enTextView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate);

        msTextView = (TextView)findViewById(R.id.bm_word);
        enTextView = (TextView)findViewById(R.id.bi_word);

        Log.d("DICTIONARY", "pop up activity Started");

        msTextView.setText(getIntent().getStringExtra("Word"));
        enTextView.setText(getIntent().getStringExtra("Definition"));

        Log.d("DICTIONARY","Set text started");
    }

}
