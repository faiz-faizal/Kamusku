package com.maxibi.testing;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    DrawerLayout drawer;

    public ListView listView;
    //public Button buttonSearch;
    public EditText editText;
    public TextView textView;

    DayNightSwitch dayNightSwitch;
    View background_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);

                drawer.closeDrawers();

                dayNightSwitch = (DayNightSwitch) findViewById(R.id.daynight);
                background_view = findViewById(R.id.background_view);

                dayNightSwitch.setDuration(450);
                dayNightSwitch.setListener(new DayNightSwitchListener() {
                    @Override
                    public void onSwitch(boolean isNight) {
                        if (isNight)
                        {
                            Toast.makeText(MainActivity.this, "Night mode selected", Toast.LENGTH_SHORT).show();
                            background_view.setAlpha(1f);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Day mode selected", Toast.LENGTH_SHORT).show();
                            background_view.setAlpha(0f);
                        }
                    }
                });

                switch (menuItem.getItemId()) {
                    case R.id.nav_bookmark:
                        Log.d("abc", "running............");
                        Toast.makeText(MainActivity.this, "Bookmark clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_About:
                        Toast.makeText(MainActivity.this, "About clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_share:
                        //only support png image
                        Uri imageUri = Uri.parse("android.resource://com.maxibi.testing/drawable/"+R.drawable.logo);
                        Intent chooser;
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                        intent.putExtra(Intent.EXTRA_TEXT,"Please Support Us By Downloading This Apps");
                        chooser = Intent.createChooser(intent, "Send Image");
                        startActivity(chooser);
                    case R.id.nav_logout:
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Do you want to close this App?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Exit!");
                        alert.show();
                }
                return true;
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.test);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final ArrayList<Word> quotes = databaseAccess.getQuotes(); // dapatkan semua qoutes

        final CustomAdapter customAdapter = new CustomAdapter(this, quotes);

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

                Intent intent = new Intent(MainActivity.this, PopUp.class);

                Log.d("TEST", "TRY " + adapterView + " " + view + " " + position);
                for (int i = 0; i < quotes.size(); i++) {
                    if ((quotes.get(i).getBm()).equals(customAdapter.getItemIndex(position))) {
                        intent.putExtra("Word", quotes.get(i).bm);
                        intent.putExtra("Definition", quotes.get(i).bi);
                    }
                }

                startActivity(intent);
            }
        });
    }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
}}
