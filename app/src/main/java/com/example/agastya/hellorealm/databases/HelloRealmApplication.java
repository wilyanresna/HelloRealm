package com.example.agastya.hellorealm.databases;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.example.agastya.hellorealm.models.Migrations;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by AgastyA on 1/24/2018.
 */

public class HelloRealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("tasky.realm")
                .schemaVersion(0)
                .directory(new File(getRealmPath(this)))
                .migration(new Migrations())
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public String getRealmPath(Context context) {
        String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String packageName = context.getApplicationContext().getPackageName();
        return externalStorageDir + File.separator + "Android" + File.separator + "data" + File.separator + packageName + File.separator + "realm" + File.separator;
    }
}