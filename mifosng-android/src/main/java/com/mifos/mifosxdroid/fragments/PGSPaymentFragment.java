package com.mifos.mifosxdroid.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.fliptables.FlipTable;
import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.online.ClientActivity;
import com.mifos.objects.accountTransfer.AccountTransferRequest;
import com.mifos.objects.accountTransfer.AccountTransferResponse;
import com.mifos.objects.accounts.savings.SavingsAccountWithAssociations;
import com.mifos.services.API;
import com.mifos.utils.Constants;
import com.mifos.utils.SafeUIBlockingUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by antoniocarella on 5/30/14.
 */
public class PGSPaymentFragment extends Fragment{

//TODO Test and Remove Amount Due and Fees Due from Instance Method (Don't Pass'em as arguments)

    View rootView;

    SafeUIBlockingUtility safeUIBlockingUtility;

    ActionBarActivity activity;

    SharedPreferences sharedPreferences;

    ActionBar actionBar;

    String todaysDate;

    // Arguments Passed From the Loan Account Summary Fragment
    int clientID;
    String clientName;
    int pgsAccountNumber;

    @InjectView(R.id.tv_clientName) TextView tv_clientName;
    @InjectView(R.id.et_pgs_payment_amount) EditText et_amount;

    private OnFragmentInteractionListener mListener;

    public PGSPaymentFragment() {
        // Required empty public constructor
    }

    public static PGSPaymentFragment newInstance(SavingsAccountWithAssociations pgsAccount) {
        PGSPaymentFragment fragment = new PGSPaymentFragment();
        Bundle args = new Bundle();
        if(pgsAccount != null)
        {

            args.putString(Constants.CLIENT_NAME, pgsAccount.getClientName());
            args.putString(Constants.SAVINGS_PRODUCT_NAME, pgsAccount.getSavingsProductName());
            args.putInt(Constants.CLIENT_ID, pgsAccount.getClientId());
            args.putInt(Constants.PGS_ACCOUNT_NUMBER, Integer.parseInt(pgsAccount.getAccountNo()));
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            clientID = getArguments().getInt(Constants.CLIENT_ID);
            clientName = getArguments().getString(Constants.CLIENT_NAME);
            pgsAccountNumber = getArguments().getInt(Constants.PGS_ACCOUNT_NUMBER);
            Log.d("onCreate of PGSPaymentFragment", "" + pgsAccountNumber);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.pgs_payment, container, false);
        activity = (ActionBarActivity) getActivity();
        safeUIBlockingUtility = new SafeUIBlockingUtility(PGSPaymentFragment.this.getActivity());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        actionBar = activity.getSupportActionBar();
        actionBar.setTitle("PayGoSol Payment");
        ButterKnife.inject(this, rootView);

        inflateUI();

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void inflateUI(){
        tv_clientName.setText(clientName);
        et_amount.setText("0.0");
    }

    public interface OnFragmentInteractionListener {

    }

    @OnClick(R.id.bt_paynow)
    public void reviewPaymentDetails(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        todaysDate = sdf.format(new Date());
        Time now = new Time();
        now.setToNow();

        String[] headers = {"Field", "Value"};
        String[][] data = {
                {"Amount", et_amount.getText().toString()},
                {"From Client", clientName},
                {"To Agent", "PayGoSol Agent"},
                {"From agent account", "1"},
                {"Payment Date", todaysDate},
                {"Payment Time", now.format("%k:%M:%S")}
        };

        System.out.println(FlipTable.of(headers, data));

        String formReviewString = new StringBuilder().append(data[0][0] + " : " + data[0][1])
                .append("\n")
                .append(data[1][0] + " : " + data[1][1])
                .append("\n")
                .append(data[2][0] + " : " + data[2][1])
                .append("\n")
                .append(data[3][0] + " : " + data[3][1])
                .append("\n")
                .append(data[4][0] + " : " + data[4][1])
                .append("\n")
                .append(data[5][0] + " : " + data[5][1]).toString();

        AlertDialog confirmPaymentDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Payment?")
                .setMessage(formReviewString)
                .setPositiveButton("Pay Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        submitPayment();
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }

    @OnClick(R.id.bt_cancelPayment)
    public void cancelPayment(){
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    public void submitPayment(){

        final AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
        //TODO These are hardcoded here, need to become dynamic
        accountTransferRequest.setFromOfficeId(1);
        accountTransferRequest.setFromClientId(1223);
        accountTransferRequest.setFromAccountType(2);
        accountTransferRequest.setFromAccountId(357);
        accountTransferRequest.setToOfficeId(1);
        accountTransferRequest.setToClientId(clientID);
        accountTransferRequest.setToAccountType(2);
        accountTransferRequest.setToAccountId(pgsAccountNumber);
        accountTransferRequest.setDateFormat("dd MM yyyy");
        accountTransferRequest.setLocale("en");
        accountTransferRequest.setTransferDate(todaysDate);
        accountTransferRequest.setTransferAmount(et_amount.getText().toString());
        accountTransferRequest.setTransferDescription("Transfer from PayGoSol Agent #: " +
                357 + "to client account #: " + pgsAccountNumber);

        API.accountTransfersService.createTransfer(accountTransferRequest,
                new Callback<AccountTransferResponse>() {
                    @Override
                    public void success(AccountTransferResponse accountTransferResponse, Response response) {
                        if (accountTransferResponse != null) {
                            Toast.makeText(getActivity(), "Payment Successful, Transaction ID = " + accountTransferResponse.getResourceId(),
                                    Toast.LENGTH_LONG).show();
                        }
                        safeUIBlockingUtility.safelyUnBlockUI();
                        Intent intent = new Intent(getActivity(), ClientActivity.class);
                        intent.putExtra(Constants.CLIENT_ID, clientID);
                        intent.putExtra(Constants.PGS_ACCOUNT_NUMBER, pgsAccountNumber);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(getActivity(), "Payment Failed", Toast.LENGTH_SHORT).show();
                        safeUIBlockingUtility.safelyUnBlockUI();
                    }
                });
    }
}

