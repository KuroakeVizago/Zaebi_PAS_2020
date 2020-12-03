package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFav extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavorite adapter;
    private List<RealmItemModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_fav);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rv_Data);
        arrayList = new ArrayList<>();
        // Setup Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        arrayList = realmHelper.getAllMovie();
        adapter = new DataAdapterFavorite(arrayList, new DataAdapterFavorite.Callback() {
            @Override
            public void onClick(int position) {
                Intent move = new Intent(getApplicationContext(), ItemDetailFavorite.class);
                move.putExtra("teamName",arrayList.get(position).getTeam_name());
                move.putExtra("teamBadge",arrayList.get(position).getTeam_badge_URL());
                move.putExtra("formedYear",arrayList.get(position).getFormed_year());
                move.putExtra("description",arrayList.get(position).getTeam_description());
                move.putExtra("country",arrayList.get(position).getTeam_country());

                startActivity(move);
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFav.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}