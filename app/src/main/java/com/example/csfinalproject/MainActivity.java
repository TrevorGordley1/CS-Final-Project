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
    //For generating random numbers
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // randomly pick a recommendation to be used
        Resources res = getResources();
        EditText userInput = findViewById(R.id.editText);
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
    // this will be called when sort button is clicked.
    public void sort(View view) {
        int random = this.random.nextInt(2);
        EditText userInput = findViewById(R.id.editText);
        String text = userInput.getText().toString();
        String [] words = text.replaceAll("\\p{P}", "").trim().toLowerCase().split(" ");
        String[] output = null;
        if (random == 0) {
            output = Sorters.alphabeticalSort(words);
        } else if (random == 1) {
            output = Sorters.lengthSort(words);
        }
        String sortedText = output[0] + " ";
        for (int i = 1; i < output.length; i++) {
            sortedText = sortedText + output[i] + " " ;
        }
        userInput.setText(sortedText);
    }
    //find and replace common words with phrases
    public void termPaperMode(View view) {
        //string.equalsIgnoreCase will be useful.
        //Punctuation must be ignored and put back.
        EditText userInput = findViewById(R.id.editText);
        String text = userInput.getText().toString();
    }
    public void loremIpsum() {

    }
    //void updateDisplay() {
    //}
}
