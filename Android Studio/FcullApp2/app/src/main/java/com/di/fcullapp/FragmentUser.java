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

public class FragmentUser extends Fragment {

    //For Testing Only!
    private static final int testUserId = 1;

    private TextView userNameTextView;
    private TextView userSurnameTextView;
    private TextView userEmailTextView;
    private ListView userSubbedCommunitiesListView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_user, container, false);

        userNameTextView = (TextView) v.findViewById(R.id.user_Name);
        userSurnameTextView = (TextView) v.findViewById(R.id.user_surname);
        userEmailTextView = (TextView) v.findViewById(R.id.user_email);
        userSubbedCommunitiesListView = (ListView) v.findViewById(R.id.userSubbedCommunities);
        context = getContext();

        try {
            prepareSubbedCommunityAdapter();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User u = null;
        try {
            u = getUserInfo();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        userNameTextView.setText(u.getName());
        //userEmail and userSurname will be added later

        return v;
    }

    public void prepareSubbedCommunityAdapter () throws ExecutionException, InterruptedException {
        final String url = "http://10.0.2.2:8081/userSubbedCommunities/" + testUserId;
        FragmentUser.RestGet rg = new FragmentUser.RestGet();
        CommunityListAdapter cA = rg.execute(url).get();
        userSubbedCommunitiesListView.setAdapter(cA);
    }

    public User getUserInfo () throws ExecutionException, InterruptedException {
        final String url = "http://10.0.2.2:8081/getUserInfo/" + testUserId;
        FragmentUser.RestGetUser rg = new FragmentUser.RestGetUser();
        User u = rg.execute(url).get();
        return u;
    }


    /**********************************************************************************************/
    /******************************************REST Calls******************************************/
    /**********************************************************************************************/


    private class RestGet extends AsyncTask<String, String, CommunityListAdapter> {

        //verify onPreExecute() method

        @Override
        protected CommunityListAdapter doInBackground(String... uri) {

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

            CommunityListAdapter cA = null;

            try {
                CommunityListWrapper cLW = rT.getForObject(url, CommunityListWrapper.class);        //check exception
                cA = new CommunityListAdapter(context, cLW.getList());
            }
            catch(ResourceAccessException e) {
                return null;
            }

            return cA;
        }

        @Override
        protected void onPostExecute(CommunityListAdapter listAdapter) {
            super.onPostExecute(listAdapter);
            //TODO
        }
    }

    private class RestGetUser extends AsyncTask<String, String, User> {

        @Override
        protected User doInBackground(String... uri) {

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

            User u = null;
            try {
                u = rT.getForObject(url, User.class);                                               //check exception
            }
            catch(ResourceAccessException e) {
                return null;
            }

            return u;
        }

    }
}
