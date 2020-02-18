package com.di.fcullapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

public class FragmentEvents extends Fragment {

    //For Testing Only!
    private static final int testUserId = 1;

    TextView progressTextView;
    Context context;
    ListView eventListView;
    Button refreshButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        progressTextView = (TextView) view.findViewById(R.id.progressTextView);
        eventListView = (ListView) view.findViewById(R.id.eventListView);
        refreshButton = (Button) view.findViewById((R.id.refreshButton));
        context = getContext();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    prepareEventAdapter();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private class RestGet extends AsyncTask<String, String, EventListAdapter> {

        @Override
        protected void onPreExecute() {
            progressTextView.setText("Looking for Updates...");
        }

        @Override
        protected EventListAdapter doInBackground(String... uri) {

            String url = uri[0];
            RestTemplate rT = new RestTemplate();

            //add message converter
            rT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //rT.getMessageConverters().add(new StringHttpMessageConverter());

            //setup Headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("APIKey", "4423748343y5yhru4y74yr43");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            //Get RestTemplate
            //Redundant
            //CommunityListWrapper cLW = rT.exchange(url, HttpMethod.GET, entity,
            //      CommunityListWrapper.class).getBody();

            EventListAdapter cA = null;

            try {
                EventListWrapper eLW = rT.getForObject(url, EventListWrapper.class);                //check exception
                cA = new EventListAdapter(context, eLW.getList());
            } catch (ResourceAccessException e) {
                return null;
            }

            return cA;
        }

        @Override
        protected void onPostExecute(EventListAdapter listAdapter) {
            super.onPostExecute(listAdapter);
            if (listAdapter == null) {
                progressTextView.setText("Unable to connect to the server.");
            } else {
                progressTextView.setText("Everything is up to date!");
            }

        }
    }

    public void prepareEventAdapter () throws ExecutionException, InterruptedException {
        final String url = "http://10.0.2.2:8081/eventsFromSubbedCommunities/" + testUserId;
        FragmentEvents.RestGet rg = new FragmentEvents.RestGet();
        EventListAdapter cA = rg.execute(url).get();
        eventListView.setAdapter(cA);
    }

}
