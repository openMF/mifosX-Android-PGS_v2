package com.mifos.mifosxdroid.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.online.AgentActivity;
import com.mifos.mifosxdroid.online.ClientActivity;
import com.mifos.objects.CurrentAccountInformation;
import com.mifos.objects.accountTransfer.AccountTransferRequest;
import com.mifos.objects.accountTransfer.AccountTransferResponse;
import com.mifos.objects.accounts.savings.SavingsAccountWithAssociations;
import com.mifos.objects.client.Client;
import com.mifos.services.API;
import com.mifos.sslworkaround.ClientDetailsRequest;
import com.mifos.sslworkaround.CurrentAccountInformationRequest;
import com.mifos.utils.Constants;
import com.mifos.utils.SafeUIBlockingUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by antoniocarella on 6/15/14.
 */
public class AccountTransferFragment extends Fragment {
    public final static String TAG = AccountTransferFragment.class.getSimpleName();
    View rootView;

    SafeUIBlockingUtility safeUIBlockingUtility;

    ActionBarActivity activity;

    SharedPreferences sharedPreferences;

    ActionBar actionBar;
    private String savingsAccountNumber;
    private int toAccountId;
    private int serviceAccountId;
    private int toMifosClientId;
    private int clientId;
    private int fromClientOfficeId;
    private int toClientOfficeId;
    private String transferToClientName;
    private String currency;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private String transferAmount;
    private String todaysDate;

    private static final int SAVINGS_ACCOUNT_TYPE = 2;

    @InjectView(R.id.tv_clientName)
    TextView tv_clientName;
    @InjectView(R.id.et_pgs_transfer_amount)
    TextView et_transferAmount;
    @InjectView(R.id.et_pgs_transfer_account_no)
    TextView et_transferAccountNo;
    @InjectView(R.id.bt_cancelTransaction)
    TextView bt_cancel;
    @InjectView(R.id.bt_reviewTransaction)
    TextView bt_review;

    public static AccountTransferFragment newInstance(SavingsAccountWithAssociations savingsAccountWithAssociations, String transactionType) {
        AccountTransferFragment fragment = new AccountTransferFragment();

        Bundle args = new Bundle();
        args.putString(Constants.SAVINGS_ACCOUNT_NUMBER, savingsAccountWithAssociations.getAccountNo());
        args.putString(Constants.SAVINGS_ACCOUNT_TRANSACTION_TYPE, transactionType);
        args.putString(Constants.CLIENT_NAME, savingsAccountWithAssociations.getClientName());
        args.putInt(Constants.CLIENT_ID, savingsAccountWithAssociations.getClientId());
        args.putString("Currency", savingsAccountWithAssociations.getCurrency().getDisplayLabel().toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            savingsAccountNumber = getArguments().getString(Constants.SAVINGS_ACCOUNT_NUMBER);
            currency = getArguments().getString("Currency");
        }
        Log.d(TAG, "onCreate()");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        todaysDate = sdf.format(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_pgs_account_transfer, container, false);
        activity = (ActionBarActivity) getActivity();
        safeUIBlockingUtility = new SafeUIBlockingUtility(AccountTransferFragment.this.getActivity());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        actionBar = activity.getSupportActionBar();
        ButterKnife.inject(this, rootView);
        inflateView();
        return rootView;
    }

    public AccountTransferFragment() {
        // Required empty public constructor
    }

    public void inflateView() {

        safeUIBlockingUtility.safelyBlockUI();
        tv_clientName.setText(getArguments().getString(Constants.CLIENT_NAME));
        actionBar.setTitle(getResources().getString(R.string.account_transfer));
        API.clientService.getClient(getArguments().getInt(Constants.CLIENT_ID), new Callback<Client>() {
            @Override
            public void success(Client client, Response response) {
                fromClientOfficeId = client.getOfficeId();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(getActivity().getLocalClassName(), retrofitError.getLocalizedMessage());
                Toast.makeText(activity, "Error retrieving client information.", Toast.LENGTH_SHORT).show();
                safeUIBlockingUtility.safelyUnBlockUI();
            }
        });
        safeUIBlockingUtility.safelyUnBlockUI();
    }

    public interface OnFragmentInteractionListener {

        public void makeTransfer(SavingsAccountWithAssociations savingsAccountWithAssociations, String transactionType);
        //public void loadSavingsAccountSummary(int savingsAccountNumber);

    }

    @OnClick(R.id.bt_cancelTransaction)
    public void onCancelButtonClicked() {
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.bt_reviewTransaction)
    public void onReviewButtonClicked() {

        if (et_transferAccountNo.getText().toString().matches("")) {
            AlertDialog noAmount = new AlertDialog.Builder(getActivity())
                    .setTitle("No Client Account Number")
                    .setMessage("Please enter client account number.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else if (et_transferAmount.getText().toString().matches("")) {
            AlertDialog noAmount = new AlertDialog.Builder(getActivity())
                    .setTitle("No Transfer Amount")
                    .setMessage("Please enter transfer amount.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {

            safeUIBlockingUtility.safelyBlockUI("One moment please.", "Retrieving client information.");
            toAccountId = Integer.parseInt(et_transferAccountNo.getText().toString());
            clientId = toAccountId;
            //TODO erase this workaround code. Instead use regular API class to get PGS client's Mifos id

            String clientDetails = null;
            try {
                clientDetails = new ClientDetailsRequest().execute(String.valueOf(toAccountId)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            Client client = gson.fromJson(clientDetails, Client.class);

            if (client != null) {
                serviceAccountId = client.getServiceAccountId();
            } else {
                // Let user know there was an issue
            }

            String currentAccountInformationString = null;
            try {
                currentAccountInformationString = new CurrentAccountInformationRequest().execute(String.valueOf(serviceAccountId)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            int indexOfOpenBracket = currentAccountInformationString.indexOf("[");
            int indexOfLastBracket = currentAccountInformationString.lastIndexOf("]");
            currentAccountInformationString = currentAccountInformationString.substring(indexOfOpenBracket+1, indexOfLastBracket);

            CurrentAccountInformation currentAccountInformation = 
                    gson.fromJson(currentAccountInformationString, CurrentAccountInformation.class);

            if (currentAccountInformation != null) {
                toAccountId = currentAccountInformation.getSavingsId();
            } else {
                // Let user know there was an issue
            }

            Log.d("TAG", "toAccountId: " + toAccountId);

            API.savingsAccountService.getSavingsAccountWithAssociations(toAccountId, "all", new Callback<SavingsAccountWithAssociations>() {
                @Override
                public void success(SavingsAccountWithAssociations savingsAccountWithAssociations, Response response) {

                    transferToClientName = savingsAccountWithAssociations.getClientName();
                    toMifosClientId = savingsAccountWithAssociations.getClientId();
                    getOfficeId(toMifosClientId);
                    safeUIBlockingUtility.safelyUnBlockUI();

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    //Log.i(getActivity().getLocalClassName(), retrofitError.getLocalizedMessage());
                    safeUIBlockingUtility.safelyUnBlockUI();

                    AlertDialog noToClientDialouge = new AlertDialog.Builder(getActivity())
                            .setTitle("Error retrieving transfer-to account")
                            .setMessage("There was an error retrieving account number: " + toAccountId + ". Please try again.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
            });
        }
    }

    public void getOfficeId(int clientId) {

    API.clientService.getClient(clientId, new Callback<Client>() {
        @Override
        public void success(Client client, Response response) {
            toClientOfficeId = client.getOfficeId();
            transferAmount = et_transferAmount.getText().toString();
            AlertDialog confirmTransferDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Review Transfer Details")
                    .setMessage("Would you like to transfer " + transferAmount + " " + currency + " to " + transferToClientName + " ?")
                    .setPositiveButton("Process Transaction", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            processTransaction();
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

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(activity, "Error retrieving receiving client information.", Toast.LENGTH_SHORT).show();
        }
    });

    }

    public void processTransaction() {

        safeUIBlockingUtility.safelyBlockUI("One moment please.", "Processing transaction.");

        final String transferDescription = "Account transfer from client " +
            String.valueOf(getArguments().getInt(Constants.CLIENT_ID)) + " to client " + String.valueOf(toMifosClientId) + ".";
        final AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
        accountTransferRequest.setFromAccountType(SAVINGS_ACCOUNT_TYPE);
        accountTransferRequest.setFromAccountId(Integer.parseInt(getArguments().getString(Constants.SAVINGS_ACCOUNT_NUMBER)));
        accountTransferRequest.setFromClientId(getArguments().getInt(Constants.CLIENT_ID));
        accountTransferRequest.setFromOfficeId(fromClientOfficeId);
        accountTransferRequest.setToClientId(toMifosClientId);
        accountTransferRequest.setToAccountId(toAccountId);
        accountTransferRequest.setToAccountType(SAVINGS_ACCOUNT_TYPE);
        accountTransferRequest.setToOfficeId(toClientOfficeId);
        accountTransferRequest.setTransferAmount(transferAmount);
        accountTransferRequest.setTransferDescription(transferDescription);
        accountTransferRequest.setLocale("en");
        accountTransferRequest.setDateFormat("dd MMMM yyyy");
        accountTransferRequest.setTransferDate(todaysDate);

        String builtTransactionRequestAsJson = new Gson().toJson(accountTransferRequest);
        Log.i("Transaction Request Body", builtTransactionRequestAsJson);

        System.out.print(builtTransactionRequestAsJson);

        API.accountTransfersService.createTransfer(accountTransferRequest, new Callback<AccountTransferResponse>() {
            @Override
            public void success(AccountTransferResponse accountTransferResponse, Response response) {
                Log.d("Account Transfer Response: ", accountTransferResponse.toString());
                AlertDialog transactionSuccessDialouge = new AlertDialog.Builder(getActivity())
                        .setTitle("Your transaction was successful.")
                        .setMessage("You have successfully transferred " + transferAmount + " " + currency + " to " + transferToClientName + "." )
                        .setPositiveButton("Return to your account.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(getActivity(), AgentActivity.class);
                                intent.putExtra(Constants.CLIENT_ID, getArguments().getInt(Constants.CLIENT_ID));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("View client's accounts.", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(getActivity(), ClientActivity.class);
                                intent.putExtra(Constants.CLIENT_ID, clientId);
                                startActivity(intent);
                            }
                        })
                        .show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, "Error: " + retrofitError.getLocalizedMessage());
                safeUIBlockingUtility.safelyUnBlockUI();
                // TODO make special case for insufficient agent balance
                Toast.makeText(activity, "Error processing transaction.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}