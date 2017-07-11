/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";

   public ListView earthquakeListView;
    EarthquakeDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        //earthquakes=QueryUtils.extractEarthquakes();

        earthquakeListView = (ListView) findViewById(R.id.list);

        MyAsync m = new MyAsync();
        m.execute(USGS_REQUEST_URL);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

    }

    public void update(ArrayList<EarthquakeData> earthquakes){

        adapter = new EarthquakeDataAdapter(
                this, android.R.layout.simple_list_item_1, earthquakes);


        earthquakeListView.setAdapter(adapter);
    }

    private class MyAsync extends AsyncTask<String , Void , String>  {


        @Override
        protected String doInBackground(String... params) {

            if (params.length < 1 || params[0] == null) {
                return null;
            }

            URL url = QueryUtils.createUrl();
            String jsonResponse = "";
            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                return;
            }

           ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes(s);
            update(earthquakes);
        }
    }
}
