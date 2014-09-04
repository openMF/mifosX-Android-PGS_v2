package com.mifos.sslworkaround;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;

/**
 * Created by antoniocarella on 8/4/14.
 */
public class PopulateSpinnersRequest extends AsyncTask<Void, Void, String>{
    public final static String TAG = PopulateSpinnersRequest.class.getSimpleName();

    private String result;

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground()");

        String mPGSInstanceUrl = WorkAroundConstants.WORK_AROUND_URL + "pgsclients/template";

        HttpClient client = new HttpsWorkaround().getNewHttpClient();
        HttpGet get = new HttpGet(mPGSInstanceUrl);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Mifos-Platform-TenantId", "default");
        get.setHeader("Authorization", "Basic bWlmb3M6cGFzc3dvcmQ=");

        // Execute HTTP Get Request
        HttpResponse response = null;
        try {
            response = client.execute(get);
            result = new BasicResponseHandler().handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}