package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItemDetail extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    RealmItemModel realmItemModel;

    Bundle extras;
    String title;
    int date;
    String deskripsi;
    String path;
    String id;

    TextView tv_TeamName;
    ImageView img_TeamBadge;
    TextView tv_TeamDesc;
    Button btnbookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        getSupportActionBar().hide();



        extras = getIntent().getExtras();
        tv_TeamName = (TextView) findViewById(R.id.tv_teamName);
        tv_TeamDesc = (TextView)findViewById(R.id.tv_listDescription);
        img_TeamBadge = (ImageView) findViewById(R.id.img_ImageIcon);
        btnbookmark = (Button) findViewById(R.id.btn_Bookmark);

        if (extras != null) {
            title = extras.getString("teamName");
            id = extras.getString("id");
            date = extras.getInt("formedYear");
            deskripsi = extras.getString("description");
            path = extras.getString("teamBadge");
            tv_TeamName.setText(title);
            tv_TeamDesc.setText(deskripsi);
            Glide.with(ItemDetail.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_TeamBadge);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(ItemDetail.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmItemModel = new RealmItemModel();
                realmItemModel.setTeam_description(deskripsi);
                realmItemModel.setTeam_name(title);
                realmItemModel.setTeam_badge_URL(path);
                realmItemModel.setFormed_year(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(realmItemModel);

            }
        });

    }
}