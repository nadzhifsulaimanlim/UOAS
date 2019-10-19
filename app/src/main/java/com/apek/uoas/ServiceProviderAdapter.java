package com.apek.uoas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServiceProviderAdapter extends ArrayAdapter<ServicesProviderDetails> {

    private Activity context;
    private List<ServicesProviderDetails> servicesProviderlist;

    public ServiceProviderAdapter(Activity context, List<ServicesProviderDetails> servicesProviderlist) {
        super(context, R.layout.activity_list_view, servicesProviderlist);
        this.context = context;
        this.servicesProviderlist = servicesProviderlist;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.activity_list_view, null, true);

        TextView Address = listView.findViewById(R.id.address);
        TextView Companydes = listView.findViewById(R.id.companydescription);
        TextView Companyname = listView.findViewById(R.id.companyname);

        ServicesProviderDetails servicesProviderDetails = servicesProviderlist.get(position);
        Address.setText(servicesProviderDetails.getAddress());
        Companydes.setText(servicesProviderDetails.getCompanydescription());
        Companyname.setText(servicesProviderDetails.getCompanyname());

        return listView;
    }
}
