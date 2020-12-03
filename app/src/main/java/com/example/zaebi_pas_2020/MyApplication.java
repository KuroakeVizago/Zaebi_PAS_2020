package com.example.zaebi_pas_2020;

import  android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Teams.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);

    }

}