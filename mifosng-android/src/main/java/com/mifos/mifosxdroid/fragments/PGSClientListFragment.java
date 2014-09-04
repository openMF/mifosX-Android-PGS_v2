package com.mifos.mifosxdroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.adapters.ClientNameListAdapter;
import com.mifos.mifosxdroid.online.ClientActivity;
import com.mifos.mifosxdroid.online.ClientSearchActivity;
import com.mifos.mifosxdroid.online.LogoutActivity;
import com.mifos.objects.client.Client;
import com.mifos.objects.client.Page;
import com.mifos.services.API;
import com.mifos.utils.Constants;
import com.mifos.utils.SafeUIBlockingUtility;

import org.apache.http.HttpStatus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by acarella on 06/08/2014
 * Uses code from ClientListFragment, created by ishankhanna on 09/02/14.
 */
public class PGSClientListFragment extends Fragment {

    @InjectView(R.id.lv_clients) ListView lv_clients;

    View rootView;
    SafeUIBlockingUtility safeUIBlockingUtility;
    List<Client> pageItems;
    FragmentChangeListener activityListener;
    private Context context;

    //TODO Hardcoding this for now, need to change when goes to production
    private int agentId = 1223;

    public PGSClientListFragment() {

    }


    public static interface FragmentChangeListener {
        void replaceFragments(Fragment fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        safeUIBlockingUtility = new SafeUIBlockingUtility(PGSClientListFragment.this.getActivity());

        context = getActivity().getApplicationContext();

        API.clientService.listClientsFilteredByFirstName("Marie", new Callback<Page<Client>>() {
            @Override
            public void success(Page<Client> page, Response response) {
                pageItems = page.getPageItems();
                safeUIBlockingUtility.safelyUnBlockUI();
                ClientNameListAdapter clientNameListAdapter = new ClientNameListAdapter(context, pageItems);
                lv_clients.setAdapter(clientNameListAdapter);
                lv_clients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        /**
                         * PayGoSol version begins to fork here
                         */
                        Intent pgsClientActivityIntent = new Intent(getActivity(), ClientActivity.class);
                        pgsClientActivityIntent.putExtra(Constants.CLIENT_ID, pageItems.get(i).getId());
                        pgsClientActivityIntent.putExtra(Constants.PGS_ACCOUNT_NUMBER, pageItems.get(i).getSavingsAccountId());
                        startActivity(pgsClientActivityIntent);

                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                if(getActivity() != null) {
                    Log.i("Error", ""+retrofitError.getResponse().getStatus());
                    if(retrofitError.getResponse().getStatus() == HttpStatus.SC_UNAUTHORIZED) {
                        Toast.makeText(getActivity(), "Authorization Expired - Please Login Again", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LogoutActivity.class));
                        getActivity().finish();

                    }else {
                        Toast.makeText(getActivity(), "There was some error fetching list.", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        rootView = inflater.inflate(R.layout.fragment_client, container, false);
        ButterKnife.inject(this, rootView);

        context = getActivity().getApplicationContext();
        activityListener = (FragmentChangeListener) getActivity();

        setupUI();

        return rootView;
    }

    public void setupUI() {

        setHasOptionsMenu(true);

        lv_clients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(getActivity() != null)
                Toast.makeText(getActivity(), "Client ID = " + pageItems.get(i).getId(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        lv_clients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dashbord_client, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.agent_account:
                Intent intent = new Intent(getActivity(), ClientActivity.class);
                intent.putExtra(Constants.CLIENT_ID, agentId);
                intent.putExtra(Constants.PGS_ACCOUNT_NUMBER, 357);
                startActivity(intent);
                break;

            case R.id.mItem_search:
                startActivity(new Intent(getActivity(), ClientSearchActivity.class));
                break;

            default: //DO NOTHING
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
