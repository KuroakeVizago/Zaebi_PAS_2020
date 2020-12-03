package com.example.zaebi_pas_2020;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final RealmItemModel movieModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(RealmItemModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    RealmItemModel model = realm.copyToRealm(movieModel);
                    final RealmResults<RealmItemModel> item = realm.where(RealmItemModel.class).findAll();

                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<RealmItemModel> getAllMovie(){
        RealmResults<RealmItemModel> results = realm.where(RealmItemModel.class).findAll();
        return results;
    }

    public void delete(Integer id){
        final RealmResults<RealmItemModel> model = realm.where(RealmItemModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}
