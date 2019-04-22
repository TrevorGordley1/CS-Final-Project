package com.example.csfinalproject;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /*
      For generating random numbers
     */
    private Random random = new Random();

    /*
      This is needed to get at resources stored in xml file.
     */
    //Resources res = getResources();

    /* Array of strings that can pop up in the recommendations bar.
       Recommendations stored in strings.xml
     */

    //private String[] recommendations = res.getStringArray(R.array.recommendations);
    /*
      The words in the text box.
     */

    //private EditText userInput;
    /*
      The drop down menu of sample texts
     */
    //private Spinner sampleText = findViewById(R.id.sampleText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // randomly pick a recommendation to be used
        Resources res = getResources();
        String[] recommendations = res.getStringArray(R.array.recommendations);
        TextView recommendation = findViewById(R.id.recommend);
        recommendation.setText(recommendations[random.nextInt(recommendations.length)]);
        // set up sampleText menu
        Spinner sampleText = findViewById(R.id.sampleText);
        ArrayAdapter<String> myAdapter =new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, res.getStringArray(R.array.spinnerItems));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sampleText.setAdapter(myAdapter);
        sampleText.setOnItemSelectedListener(this);
        //set up button handlers

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
        EditText textBox = findViewById(R.id.editText);
        Resources res = getResources();
        String hemingwayQuote = res.getString(R.string.hemingway);
        String steinbeck = res.getString(R.string.steinbeck);
        String meyer = res.getString(R.string.meyer);
        String faulkner = res.getString(R.string.faulkner);
        String salinger = res.getString(R.string.salinger);
        if (selected.equals("Pick sample text")) {
            textBox.setText("Start typing!");
        } else if (selected.equals("Hemingway")) {
            textBox.setText(hemingwayQuote);
        } else if (selected.equals("Steinbeck")) {
            textBox.setText(steinbeck);
        } else if (selected.equals("Stephanie Meyer")) {
            textBox.setText(meyer);
        } else if (selected.equals("Faulkner")) {
            textBox.setText(faulkner);
        } else if (selected.equals("J.D.Salinger")) {
            textBox.setText(salinger);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //void updateDisplay() {
    //}
}
