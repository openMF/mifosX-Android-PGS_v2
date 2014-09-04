package com.mifos.mifosxdroid.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.mifos.mifosxdroid.R;
import com.mifos.objects.accounts.savings.Transaction;
import com.mifos.utils.DateHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by antoniocarella on 5/31/14.
 * Uses code from SavingsAccountTransactionsListAdapter by ishankhanna
 */
public class PGSAccountTransactionsListAdapter extends BaseAdapter implements Filterable {

    private List<Transaction> listOfTransactions;
    private LayoutInflater layoutInflater;

    public PGSAccountTransactionsListAdapter(Context context,List<Transaction> listOfTransactions) {

        layoutInflater = LayoutInflater.from(context);
        this.listOfTransactions = listOfTransactions;

    }

    @Override
    public int getCount() {
        return listOfTransactions.size();
    }

    @Override
    public Transaction getItem(int i) {
        return listOfTransactions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ReusableViewHolder reusableViewHolder;
        if(view==null) {

            view = layoutInflater.inflate(R.layout.row_savings_transaction_item,null);
            reusableViewHolder = new ReusableViewHolder(view);
            view.setTag(reusableViewHolder);

        }else {
            reusableViewHolder = (ReusableViewHolder) view.getTag();
        }

        reusableViewHolder.tv_transactionDate.setText(
                DateHelper.getDateAsString(listOfTransactions.get(i).getDate()));
        reusableViewHolder.tv_transactionType.setText(listOfTransactions.get(i).getTransactionType().getValue());
        reusableViewHolder.tv_transactionAmount.setText(listOfTransactions.get(i).getCurrency().getDisplaySymbol()
                +" "+String.valueOf(listOfTransactions.get(i).getAmount()));
        if(listOfTransactions.get(i).getTransactionType().getDeposit()) {
            reusableViewHolder.tv_transactionAmount.setTextColor(Color.parseColor("#08860C"));
        }else if(listOfTransactions.get(i).getTransactionType().getWithdrawal()) {
            reusableViewHolder.tv_transactionAmount.setTextColor(Color.RED);
        }else {
            reusableViewHolder.tv_transactionAmount.setTextColor(Color.BLACK);
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listOfTransactions = (List<Transaction>)results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Transaction> filteredListOfTransactions = new ArrayList<Transaction>();
                FilterResults results = new FilterResults();

                // perform your search here using the searchConstraint String.
                for (Transaction transaction : listOfTransactions) {
                    if (transaction.getTransactionType().getValue().contentEquals(constraint)) {
                        filteredListOfTransactions.add(transaction);
                    }
                }

                results.count = filteredListOfTransactions.size();
                results.values = filteredListOfTransactions;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }


    public static class ReusableViewHolder {

        @InjectView(R.id.tv_transaction_date)
        TextView tv_transactionDate;
        @InjectView(R.id.tv_transaction_type)
        TextView tv_transactionType;
        @InjectView(R.id.tv_transaction_amount)
        TextView tv_transactionAmount;

        public ReusableViewHolder(View view) {
            ButterKnife.inject(this, view);
        }


    }

}
