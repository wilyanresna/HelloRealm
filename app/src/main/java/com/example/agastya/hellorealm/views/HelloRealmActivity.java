package com.example.agastya.hellorealm.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.agastya.hellorealm.R;
import com.example.agastya.hellorealm.models.Task;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class HelloRealmActivity extends AppCompatActivity {
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_realm);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        mRealm= Realm.getDefaultInstance();

        // RealmResults are "live" views, that are automatically kept up to date, even when changes happen
        // on a background thread. The RealmBaseAdapter will automatically keep track of changes and will
        // automatically refresh when a change is detected.
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();
        tasks = tasks.sort("timestamp");
        final HelloRealmAdapter adapter = new HelloRealmAdapter(this, tasks);

        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Task task = (Task) adapterView.getAdapter().getItem(i);
                final EditText taskEditText = new EditText(HelloRealmActivity.this);
                taskEditText.setText(task.getName());
                AlertDialog dialog = new AlertDialog.Builder(HelloRealmActivity.this)
                        .setTitle("Edit Task")
                        .setView(taskEditText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                changeTaskName(task.getId(), String.valueOf(taskEditText.getText()));
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteTask(task.getId());
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(HelloRealmActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(HelloRealmActivity.this)
                        .setTitle("Add Task")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mRealm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        /*realm.createObject(Task.class, UUID.randomUUID().toString())
                                                .setName(String.valueOf(taskEditText.getText()));*/

                                        for (int j = 0; j < 100; j++) {
                                            Task task = realm.createObject(Task.class, UUID.randomUUID().toString());
                                            task.setName(String.valueOf(taskEditText.getText() + " " + j));
                                            task.setTimestamp(System.currentTimeMillis());
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    public void changeTaskDone(final String taskId) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task task = realm.where(Task.class).equalTo("id", taskId).findFirst();
                task.setDone(!task.isDone());
            }
        });
    }

    private void changeTaskName(final String taskId, final String name) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task task = realm.where(Task.class).equalTo("id", taskId).findFirst();
                task.setName(name);
            }
        });
    }

    private void deleteTask(final String taskId) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Task.class).equalTo("id", taskId)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }

    private void deleteAllDone() {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Task.class).equalTo("done", true)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }
}