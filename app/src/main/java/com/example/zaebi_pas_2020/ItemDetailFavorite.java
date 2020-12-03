    package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItemDetailFavorite extends AppCompatActivity {


        Realm realm;
        RealmHelper realmHelper;
        RealmItemModel movieModel;

        Bundle extras;
        String title;
        int date;
        String deskripsi;
        String path;
        String id;

        TextView tvjudul;
        ImageView ivposter;
        TextView tvdesc;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item_detail_favorite);
            getSupportActionBar().hide();

            extras = getIntent().getExtras();
            tvjudul = (TextView)findViewById(R.id.tv_teamName);
            tvdesc = (TextView)findViewById(R.id.tv_Description);
            ivposter = (ImageView) findViewById(R.id.img_ImageIcon);

            if (extras != null) {
                title = extras.getString("teamName");
                id = extras.getString("id");
                date = extras.getInt("formedYear");
                deskripsi = extras.getString("description");
                path = extras.getString("teamBadge");
                tvjudul.setText(title);
                tvdesc.setText(deskripsi);
                Glide.with(ItemDetailFavorite.this)
                        .load(path)
                        .override(Target.SIZE_ORIGINAL)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(ivposter);
                // and get whatever type user account id is
            }

            //Set up Realm
            Realm.init(ItemDetailFavorite.this);
            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);


        }
}