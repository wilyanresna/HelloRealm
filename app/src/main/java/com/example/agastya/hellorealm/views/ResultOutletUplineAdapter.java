package com.example.agastya.hellorealm.views;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.agastya.hellorealm.R;
import com.example.agastya.hellorealm.models.OutletUpline;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by AgastyA on 1/26/2018.
 */

class ResultOutletUplineAdapter extends RealmBaseAdapter<OutletUpline> implements ListAdapter {

    private ResultOutletUplineActivity activity;

    private static class ViewHolder {
        TextView textName;
        TextView textDetail;
    }

    ResultOutletUplineAdapter(ResultOutletUplineActivity activity, OrderedRealmCollection<OutletUpline> data) {
        super(data);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_outlet_upline, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) convertView.findViewById(R.id.row_outlet_upline_text_nama_outlet);
            viewHolder.textDetail = (TextView) convertView.findViewById(R.id.row_outlet_upline_text_detail);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            OutletUpline upline = adapterData.get(position);
            viewHolder.textName.setText(upline.getOutletName());
            viewHolder.textDetail.setText(""
                    + "[" + upline.getOutletCode() + "] - " + upline.getSubChannel() + "\n"
                    + upline.getAddress() + "\n"
                    + upline.getDistrict() + "\n"
                    + upline.getVillage()
            );
        }

        return convertView;
    }
}


/*extends RealmRecyclerViewAdapter<OutletUpline, ResultOutletUplineAdapter.ViewHolder> {
    ResultOutletUplineAdapter(OrderedRealmCollection<OutletUpline> data) {
        super(data, false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_outlet_upline, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OutletUpline upline = getItem(position);
        holder.upline = upline;

        holder.textViewNama.setText(upline.getOutletName());
        holder.textViewDetail.setText(""
                + "[" + upline.getOutletCode() + "] - " + upline.getSubChannel() + "\n"
                + upline.getAddress() + "\n"
                + upline.getDistrict() + "\n"
                + upline.getVillage()
        );
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewDetail;
        public OutletUpline upline;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNama = (TextView) itemView.findViewById(R.id.row_outlet_upline_text_nama_outlet);
            textViewDetail = (TextView) itemView.findViewById(R.id.row_outlet_upline_text_detail);
        }
    }
}*/