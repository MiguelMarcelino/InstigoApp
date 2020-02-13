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


class CommunityListAdapter extends BaseAdapter {

    LayoutInflater cInflater;
    List<Community> communityList;

    public CommunityListAdapter(Context c, List<Community> cList) {
        cInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        communityList = cList;
    }

    @Override
    public int getCount() {
        return communityList.size();
    }

    @Override
    public Object getItem(int i) {
        return communityList.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        //check this later
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = cInflater.inflate(R.layout.community_list_view_template, null);
        TextView communityNameTextView = (TextView) v.findViewById(R.id.NameTextView);

        //set values
        communityNameTextView.setText(communityList.get(i).getName());

        return v;
    }

}


