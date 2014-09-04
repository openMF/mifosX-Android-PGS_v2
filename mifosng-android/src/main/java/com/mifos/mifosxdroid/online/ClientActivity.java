package com.mifos.mifosxdroid.online;

/**
 * Created by antoniocarella on 5/30/14.
 * Uses code from AgentActivity.java
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.fragments.AccountTransferFragment;
import com.mifos.mifosxdroid.fragments.ClientAccountDetailsFragment;
import com.mifos.mifosxdroid.fragments.ClientDetailsFragment;
import com.mifos.objects.accounts.savings.SavingsAccountWithAssociations;
import com.mifos.utils.Constants;
import com.mifos.utils.FragmentConstants;

import butterknife.ButterKnife;

public class ClientActivity extends ActionBarActivity implements
        ClientDetailsFragment.OnFragmentInteractionListener,
        ClientAccountDetailsFragment.OnFragmentInteractionListener{

    public final static String TAG = ClientActivity.class.getSimpleName();
    //TODO Hardcoding this for now, need to change when goes to production
    private int agentId = 1223;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_global_container_layout);
        ButterKnife.inject(this);
        final int clientId = getIntent().getExtras().getInt(Constants.CLIENT_ID);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ClientDetailsFragment clientDetailsFragment = ClientDetailsFragment.newInstance(clientId, true);
        fragmentTransaction.replace(R.id.global_container, clientDetailsFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.agent_home:
                Intent intent = new Intent(ClientActivity.this, AgentActivity.class);
                intent.putExtra(Constants.CLIENT_ID, agentId);
                startActivity(intent);
                break;
            case R.id.action_new_client:
                startActivity(new Intent(ClientActivity.this, NewClientCreationLoginActivity.class));
                break;

            case R.id.logout:
                startActivity(new Intent(ClientActivity.this, LogoutActivity.class));
                break;
            default: //DO NOTHING
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void loadSavingsAccountSummary(int savingsAccountNumber) {
        ClientAccountDetailsFragment clientAccountDetailsFragment
                = ClientAccountDetailsFragment.newInstance(savingsAccountNumber);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(FragmentConstants.FRAG_CLIENT_DETAILS);
        fragmentTransaction.replace(R.id.global_container, clientAccountDetailsFragment).commit();
    }

    @Override
    public void makeTransfer(SavingsAccountWithAssociations savingsAccountWithAssociations, String transactionType) {
        AccountTransferFragment accountTransferFragment = AccountTransferFragment.newInstance(savingsAccountWithAssociations, transactionType);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(FragmentConstants.FRAG_SAVINGS_ACCOUNT_SUMMARY);
        fragmentTransaction.replace(R.id.global_container, accountTransferFragment).commit();
    }
}
