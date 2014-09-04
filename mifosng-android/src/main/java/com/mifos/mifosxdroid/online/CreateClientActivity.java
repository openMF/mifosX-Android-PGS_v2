package com.mifos.mifosxdroid.online;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mifos.mifosxdroid.R;
import com.mifos.objects.client.CreateClientTransactionRequest;
import com.mifos.objects.client.CreateClientTransactionResponse;
import com.mifos.objects.templates.OfficeData;
import com.mifos.objects.templates.clients.NewClientTemplate;
import com.mifos.sslworkaround.NewClientRequest;
import com.mifos.sslworkaround.PopulateSpinnersRequest;
import com.mifos.utils.Constants;
import com.mifos.utils.SafeUIBlockingUtility;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class CreateClientActivity extends ActionBarActivity {
    public final static String TAG = NewClientCreationLoginActivity.class.getSimpleName();
    SharedPreferences sharedPreferences;
    private Context context;
    private long officeId;
    private long genderId;
    SafeUIBlockingUtility safeUIBlockingUtility;
    @InjectView(R.id.sp_office) Spinner sp_office;
    @InjectView(R.id.et_first_name) EditText et_firstName;
    @InjectView(R.id.et_middle_name) EditText et_middleName;
    @InjectView(R.id.et_last_name) EditText et_lastName;
    @InjectView(R.id.et_mobile_number) EditText et_et_mobileNumber;
    @InjectView(R.id.et_dob) EditText et_dob;
    @InjectView(R.id.sp_gender) Spinner sp_gender;
    @InjectView(R.id.et_external_id) EditText et_externalId;
    //API PGSAPI = new API();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_new_client);
        context = CreateClientActivity.this;
        safeUIBlockingUtility = new SafeUIBlockingUtility(context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ButterKnife.inject(this);
        //PGSAPI.chooseInstanceUrl(2);
        populateSpinners();
    }

    public void populateSpinners(){

        /*
        //TODO Had to remove this junk of code because of the SSL issues I was having running the
        //PGS webapp from my local machine. This should be reinstated when that issue is solved

        PGSAPI.clientAccountsService.getClientDetailsTemplate(new Callback<NewClientTemplate>() {
            @Override
            public void success(NewClientTemplate newClientTemplate, Response response) {

                int listSize = newClientTemplate.getOfficeOptions().size();
                final ArrayList<OfficeData> officeDataArrayList = new ArrayList<OfficeData>();
                officeDataArrayList.addAll(newClientTemplate.getOfficeOptions());
                String[] officeOptions = new String[listSize];
                for (int i = 0; i < listSize; i++){
                    officeOptions[i] = officeDataArrayList.get(i).getName();
                }
                ArrayAdapter<String> officeAdapter = new ArrayAdapter<String>(CreateClientActivity.this,
                        android.R.layout.simple_spinner_item, officeOptions);
                sp_office.setAdapter(officeAdapter);
                sp_office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        officeId = officeDataArrayList.get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // Default is head office, whose ID is 1
                        officeId = adapterView.getItemIdAtPosition(1);
                    }
                });

                listSize = newClientTemplate.getGenderOptions().size();
                final ArrayList<CodeValueData> genderList = new ArrayList<CodeValueData>();
                genderList.addAll(newClientTemplate.getGenderOptions());
                String[] genderOptions = new String[listSize];
                for (int i = 0; i < listSize; i++){
                    genderOptions[i] = genderList.get(i).getName();
                }
                ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(CreateClientActivity.this,
                        android.R.layout.simple_spinner_item, genderOptions);
                sp_gender.setAdapter(genderAdapter);
                sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        genderId = genderList.get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        genderId = genderList.get(0).getId();
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(CreateClientActivity.this, "Problem accessing spinner options. Please try again later."
                        , Toast.LENGTH_SHORT).show();

            }
        });
        */

        //TODO DO NOT USE THIS WORKAROUND CODE IN PRODUCTION!
        PopulateSpinnersRequest populate = new PopulateSpinnersRequest();
        String results = null;
        try {
            results = populate.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Spinners data", results);

        Gson gson = new Gson();
        NewClientTemplate newClientTemplate = gson.fromJson(results, NewClientTemplate.class);

        int listSize = newClientTemplate.getOfficeOptions().size();
        final ArrayList<OfficeData> officeDataArrayList = new ArrayList<OfficeData>();
        officeDataArrayList.addAll(newClientTemplate.getOfficeOptions());
        String[] officeOptions = new String[listSize];
        for (int i = 0; i < listSize; i++){
            officeOptions[i] = officeDataArrayList.get(i).getName();
        }
        ArrayAdapter<String> officeAdapter = new ArrayAdapter<String>(CreateClientActivity.this,
                android.R.layout.simple_spinner_item, officeOptions);
        sp_office.setAdapter(officeAdapter);
        sp_office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                officeId = officeDataArrayList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Default is head office, whose ID is 1
                officeId = adapterView.getItemIdAtPosition(1);
            }
        });

        final String[] genderOptions = {"Male", "Female"};

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(CreateClientActivity.this,
                android.R.layout.simple_spinner_item, genderOptions);
        sp_gender.setAdapter(genderAdapter);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderId = i + 10; //male's code value is 10, female's is 11
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                genderId = 10;
            }
        });
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            String monthString = new DateFormatSymbols().getMonths()[month-1];
            String dayString = (day < 10) ? "0" + String.valueOf(day) : String.valueOf(day);
            String yearString = String.valueOf(year);
             et_dob.setText(dayString + " " + monthString + " " + yearString);
        }
    }

    @OnClick(R.id.bt_submit_new_client)
    public void submitNewClientOnClick(){

        final CreateClientTransactionRequest createClientTransactionRequest =
                new CreateClientTransactionRequest();

        createClientTransactionRequest.setOfficeId((int)officeId);
        createClientTransactionRequest.setFirstname(et_firstName.getText().toString());
        createClientTransactionRequest.setMiddlename(et_middleName.getText().toString());
        createClientTransactionRequest.setLastname(et_lastName.getText().toString());
        createClientTransactionRequest.setMobileNo(et_et_mobileNumber.getText().toString());
        createClientTransactionRequest.setDateFormat("dd MMMM yyyy");
        createClientTransactionRequest.setSavingsProductId(null);
        createClientTransactionRequest.setDateOfBirth(String.valueOf(et_dob.getText()));
        createClientTransactionRequest.setGenderId((int) genderId);
        createClientTransactionRequest.setExternalId(et_externalId.getText().toString());
        createClientTransactionRequest.setLocale("en");
        //TODO offer the option to activate or not
        createClientTransactionRequest.setActive(true);

        // Set activation date to current date
        Date cDate = new Date();
        String activationDateNow = new SimpleDateFormat("dd MMMM yyyy").format(cDate);

        createClientTransactionRequest.setActivationDate(activationDateNow);

        String builtTransactionRequestAsJson = new Gson().toJson(createClientTransactionRequest);
        Log.d("Transaction Request Body", builtTransactionRequestAsJson);

        String response = null;
        try {
             response = new NewClientRequest().execute(createClientTransactionRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        CreateClientTransactionResponse createClientTransactionResponse =
                gson.fromJson(response, CreateClientTransactionResponse.class);

        Intent intent = new Intent(CreateClientActivity.this, ClientActivity.class);
        intent.putExtra(Constants.CLIENT_ID, createClientTransactionResponse.getClientId());
        intent.putExtra(Constants.DID_USE_WORKAROUND, true);
        startActivity(intent);

        /*

        API.clientService.createNewClient(createClientTransactionRequest, new Callback<CreateClientTransactionResponse>() {
            @Override
            public void success(CreateClientTransactionResponse createClientTransactionResponse, Response response) {

                Intent intent = new Intent(CreateClientActivity.this, ClientActivity.class);
                intent.putExtra(Constants.CLIENT_ID, createClientTransactionResponse.getClientId());
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(CreateClientActivity.this, "Problem creating client. Please try again later."
                        , Toast.LENGTH_SHORT).show();
            }
        });

        */
    }

}
