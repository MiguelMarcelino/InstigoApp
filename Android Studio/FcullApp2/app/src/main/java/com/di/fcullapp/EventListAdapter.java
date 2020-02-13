package com.di.fcullapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*
import android.graphics.Color;
import android.os.AsyncTask;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

*/

import java.util.List;


class EventListAdapter extends BaseAdapter {

    LayoutInflater cInflater;
    List<Event> eventList;

    public EventListAdapter(Context c, List<Event> cList) {
        cInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eventList = cList;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return eventList.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        //check this later
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = cInflater.inflate(R.layout.event_list_view_template, null);
        TextView eventNameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView eventCommunity = (TextView) v.findViewById((R.id.communityName));
        TextView eventStartEnd = (TextView) v.findViewById((R.id.timeTextView));

        //set values
        eventNameTextView.setText(eventList.get(i).getName());
        eventCommunity.setText(eventList.get(i).getcName());
        eventStartEnd.setText(eventList.get(i).getStart() + " - " + eventList.get(i).getEnd());

        return v;
    }

}


