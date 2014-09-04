package com.mifos.mifosxdroid.online;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mifos.exceptions.ShortOfLengthException;
import com.mifos.mifosxdroid.R;
import com.mifos.objects.User;
import com.mifos.services.API;
import com.mifos.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by antoniocarella on 6/19/14.
 * Uses code from LoginActivity.java, Created by ishankhanna on 08/02/14.
 */

public class NewClientCreationLoginActivity extends ActionBarActivity implements Callback<User> {
    public final static String TAG = NewClientCreationLoginActivity.class.getSimpleName();
    public static String INSTANCE_URL_KEY = "instanceURL";
    public static String mPGSInstanceUrl = "https://localhost:8443/mifosng-provider/api/v1/";

    SharedPreferences sharedPreferences;
    @InjectView(R.id.et_username) EditText et_username;
    @InjectView(R.id.et_password) EditText et_password;
    @InjectView(R.id.bt_agent_login) Button bt_login;

    private String username;
    private String instanceURL;
    private String password;
    private Context context;
    private String authenticationToken;
    private int userId;
    private String previouslyEnteredUrl;
    private ProgressDialog progressDialog;
    private String tag = getClass().getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgs_agent_login_activity);
        Log.d(TAG, "onCreate()");
        context = NewClientCreationLoginActivity.this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        previouslyEnteredUrl = sharedPreferences.getString(INSTANCE_URL_KEY,
                mPGSInstanceUrl);
        authenticationToken = sharedPreferences.getString(User.AUTHENTICATION_KEY, "NA");
        ButterKnife.inject(this);
        setupUI();
        if(bt_login==null)
        {
            Log.i(tag, "login button is null");
        }
        else {
            Log.i(tag, "login button is not null");
        }
    }

    public void setupUI()
    {
        progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging In");
        progressDialog.setCancelable(false);
    }

    public boolean validateUserInputs() throws ShortOfLengthException {

        //TODO Create All Validations Here for all input fields

        /*
        try {
            URL url = new URL(previouslyEnteredUrl);
            mPGSInstanceUrl = url.toURI().toString();
            Log.d(tag, "instance URL: " + mPGSInstanceUrl);
            API.setInstanceUrl(mPGSInstanceUrl);
            savemPGSInstanceUrl(mPGSInstanceUrl);
        } catch (MalformedURLException e) {
            Log.e(tag, "Invalid instance URL: " + mPGSInstanceUrl, e);
            throw new ShortOfLengthException("Instance URL", 5);
        } catch (URISyntaxException uriException) {
            Log.e(tag, "Invalid instance URL: " + mPGSInstanceUrl, uriException);
            throw new ShortOfLengthException("Instance URL", 5);
        }
        */

        username = et_username.getEditableText().toString();
        if (username.length() < 5) {
            throw new ShortOfLengthException("Username", 5);
        }

        password = et_password.getEditableText().toString();
        if (password.length() < 6) {
            throw new ShortOfLengthException("Password", 6);
        }



        return true;
    }

    @Override
    public void success(User user, Response response) {
        progressDialog.dismiss();
        Toast.makeText(context, "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();
        saveAuthenticationKey("Basic " + user.getBase64EncodedAuthenticationKey());
        saveUserId(user.getUserId());
        Intent intent = new Intent(NewClientCreationLoginActivity.this, CreateClientActivity.class);
        intent.putExtra(Constants.CLIENT_ID, user.getUserId());
        startActivity(intent);
        finish();
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        progressDialog.dismiss();
        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_agent_login)
    public void onLoginClick(Button button){
        login();
    }

    private void login() {
        try {
            if (validateUserInputs())
                progressDialog.show();

            API.userAuthService.authenticate(username, password, this);
        } catch (ShortOfLengthException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnEditorAction(R.id.et_password)
    public boolean passwordSubmitted(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            login();
            return true;
        }
        return false;
    }

    public void saveAuthenticationKey(String authenticationKey) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User.AUTHENTICATION_KEY, authenticationKey);
        editor.commit();
        editor.apply();
    }

    public void savemPGSInstanceUrl(String mPGSInstanceUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INSTANCE_URL_KEY, mPGSInstanceUrl);
        editor.commit();
        editor.apply();
    }

    public void saveUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.CLIENT_ID, userId);
        editor.commit();
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.offline_menu, menu);
        return true;
    }

}
