package com.mifos.mifosxdroid.online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.fragments.AccountTransferFragment;
import com.mifos.mifosxdroid.fragments.AgentAccountDetailsFragment;
import com.mifos.mifosxdroid.fragments.AgentDetailsFragment;
import com.mifos.objects.accounts.savings.SavingsAccountWithAssociations;
import com.mifos.utils.Constants;
import com.mifos.utils.FragmentConstants;

import butterknife.ButterKnife;

public class AgentActivity extends ActionBarActivity implements AgentDetailsFragment.OnFragmentInteractionListener,
                                                                 AgentAccountDetailsFragment.OnFragmentInteractionListener,
                                                                 AccountTransferFragment.OnFragmentInteractionListener {

    public final static String TAG = AgentActivity.class.getSimpleName();

    private int agentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container_layout);
        ButterKnife.inject(this);

        //TODO figure out login activity for PayGoSol agent so this doesn't need to be hardcoded
        agentId = 1223;

        // TODO get rid of this when workaround is no longer used
        boolean workaround = getIntent().getBooleanExtra(Constants.DID_USE_WORKAROUND, false);
        agentId = getIntent().getExtras().getInt(Constants.CLIENT_ID);
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        AgentDetailsFragment agentDetailsFragment = AgentDetailsFragment.newInstance(agentId, workaround);
        fragmentTransaction.replace(R.id.global_container, agentDetailsFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //TODO add settings
                break;
            case R.id.action_new_client:
                startActivity(new Intent(AgentActivity.this, NewClientCreationLoginActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(AgentActivity.this, LogoutActivity.class));
                break;
            default: //DO NOTHING
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
     * Called when a Savings Account is Selected
     * from the list of Savings Accounts on Client Details Fragment
     *
     * It displays the summary of the Selected Savings Account
     */

    @Override
    public void loadSavingsAccountSummary(int savingsAccountNumber) {
        AgentAccountDetailsFragment agentAccountDetailsFragment
                = AgentAccountDetailsFragment.newInstance(savingsAccountNumber);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(FragmentConstants.FRAG_CLIENT_DETAILS);
        fragmentTransaction.replace(R.id.global_container, agentAccountDetailsFragment).commit();
    }

    @Override
    public void makeTransfer(SavingsAccountWithAssociations savingsAccountWithAssociations, String transactionType) {
        AccountTransferFragment accountTransferFragment = AccountTransferFragment.newInstance(savingsAccountWithAssociations, transactionType);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(FragmentConstants.FRAG_SAVINGS_ACCOUNT_SUMMARY);
        fragmentTransaction.replace(R.id.global_container, accountTransferFragment).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
