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


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
        String [] words = text.trim().split(" ");
        String output = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("in")) {
                words[i] = "within the confines of";
            }
            if (words[i].equalsIgnoreCase("the")) {
                words[i] = "(noun ahead)";
            }
            if (words[i].equalsIgnoreCase("an")) {
                words[i] = "(noun ahead)";
            }
            if (words[i].equalsIgnoreCase("and")) {
                words[i] = "in addition to";
            }
            if (words[i].equalsIgnoreCase("time")) {
                words[i] = "an illusion of human perception";
            }
            if (words[i].equalsIgnoreCase("family")) {
                words[i] = "group of related things";
            }
            if (words[i].equalsIgnoreCase("he")) {
                words[i] = "the male in question";
            }
            if (words[i].equalsIgnoreCase("she")) {
                words[i] = "the female in question";
            }
            if (words[i].equalsIgnoreCase("you")) {
                words[i] = "the thing I am addressing directly";
            }
            if (words[i].equalsIgnoreCase("in")) {
                words[i] = "within the confines of";
            }
        }
        for (int i = 0; i < words.length; i++) {
            output =output + words[i] + " " ;
        }
        userInput.setText(output);
    }
    public void pigLatin(View view) {
        //here begins the API code
        final EditText textView = findViewById(R.id.editText);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.funtranslations.com/translate/piglatin.json?text=" + textView.getText();

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        String[] splitText = response.split(":");
                        String output = splitText[4];
                        output = output.substring(2, output.length() - 10);
                        textView.setText(output);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void loremIpsum(View view) {
        final EditText textView = findViewById(R.id.editText);
        String text = textView.getText().toString();
        int length = text.length() / 400;
        if (length <= 0) {
            length = 1;
        }
// ...
//here begins the API code
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://loripsum.net/api/plaintext/" + String.valueOf(length);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    //here ends the API code
    }
    //void updateDisplay() {
    //}
}
