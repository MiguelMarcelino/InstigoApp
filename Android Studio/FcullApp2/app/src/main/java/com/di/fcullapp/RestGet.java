package com.di.fcullapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

//Work in progress...

/*


public class RestGet extends AsyncTask<String, String, CommunityAdapter> {

    TextView progressTextView;

    protected void setTextView (TextView textView) {
        textView = progressTextView;
    }

    @Override
    protected void onPreExecute() {
        progressTextView.setText("Looking for Updates...");
    }

    @Override
    protected CommunityAdapter doInBackground(String... uri) {

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

        CommunityAdapter cA = null;

        try {
            CommunityListWrapper cLW = rT.getForObject(url, CommunityListWrapper.class);        //check exception
            cA = new CommunityAdapter(context, cLW.getList());
        } catch (ResourceAccessException e) {
            return null;
        }

        return cA;
    }

    @Override
    protected void onPostExecute(CommunityAdapter communityAdapter) {
        super.onPostExecute(communityAdapter);
        if (communityAdapter == null) {
            progressTextView.setText("Unable to connect to the server.");
        } else {
            progressTextView.setText("Everything is up to date!");
        }

    }
}
*/

