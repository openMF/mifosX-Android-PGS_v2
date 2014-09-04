package com.mifos.sslworkaround;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mifos.objects.client.CreateClientTransactionRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by antoniocarella on 8/5/14.
 */
public class NewClientRequest extends AsyncTask<CreateClientTransactionRequest, Void, String> {
    public final static String TAG = NewClientRequest.class.getSimpleName();

    private String result;

    @Override
    protected String doInBackground(CreateClientTransactionRequest... createClientTransactionRequest) {
        Log.d(TAG, "doInBackground()");
        String newClientParams = new Gson().toJson(createClientTransactionRequest[0]);

        String mPGSInstanceUrl = WorkAroundConstants.WORK_AROUND_URL + "pgsclients";

        HttpClient client = new HttpsWorkaround().getNewHttpClient();
        HttpPost post = new HttpPost(mPGSInstanceUrl);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("X-Mifos-Platform-TenantId", "default");
        post.setHeader("Authorization", "Basic bWlmb3M6cGFzc3dvcmQ=");
        try {
            post.setEntity(new StringEntity(newClientParams));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Execute HTTP Post Request
        HttpResponse response = null;
        try {
            response = client.execute(post);
            result = new BasicResponseHandler().handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}
