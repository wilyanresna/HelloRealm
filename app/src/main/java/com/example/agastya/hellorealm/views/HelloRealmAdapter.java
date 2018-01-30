package com.example.agastya.hellorealm.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.agastya.hellorealm.R;
import com.example.agastya.hellorealm.models.Task;
import com.example.agastya.hellorealm.views.HelloRealmActivity;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by AgastyA on 1/24/2018.
 */

public class HelloRealmAdapter extends RealmBaseAdapter<Task> implements ListAdapter {

    private HelloRealmActivity activity;

    private static class ViewHolder {
        TextView taskName;
        CheckBox isTaskDone;
    }

    HelloRealmAdapter(HelloRealmActivity activity, OrderedRealmCollection<Task> data) {
        super(data);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.task_item_name);
            viewHolder.isTaskDone = (CheckBox) convertView.findViewById(R.id.task_item_done);
            viewHolder.isTaskDone.setOnClickListener(listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            Task task = adapterData.get(position);
            viewHolder.taskName.setText(task.getName());
            viewHolder.isTaskDone.setChecked(task.isDone());
            viewHolder.isTaskDone.setTag(position);
        }

        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                Task task = adapterData.get(position);
                activity.changeTaskDone(task.getId());
            }
        }
    };
}