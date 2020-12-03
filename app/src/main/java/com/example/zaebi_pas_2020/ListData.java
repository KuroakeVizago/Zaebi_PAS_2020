package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<ItemModel> DataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        //addData();
        addDataOnline();
    }

    void addDataOnline()
    {
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("hasiljson", "onResponse: " + response.toString());

                        DataArrayList = new ArrayList<>();
                        ItemModel jsonObjectModel;
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                jsonObjectModel = new ItemModel();

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                jsonObjectModel.setTeam_name(jsonObject.getString("strTeam"));
                                jsonObjectModel.setTeam_description(jsonObject.getString("strDescriptionEN"));
                                jsonObjectModel.setFormed_year(jsonObject.getInt("intFormedYear"));
                                jsonObjectModel.setTeam_badge_URL(jsonObject.getString("strTeamBadge"));
                                jsonObjectModel.setTeam_country(jsonObject.getString("strCountry"));

                                DataArrayList.add(jsonObjectModel);

                            }
                            adapter = new DataAdapter(DataArrayList);

                            adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    ItemModel team = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), ItemDetail.class);
                                    intent.putExtra("id",team.getTeam_id());
                                    intent.putExtra("teamName",team.getTeam_name());
                                    intent.putExtra("description",team.getTeam_description());
                                    intent.putExtra("country",team.getTeam_country());
                                    intent.putExtra("teamBadge",team.getTeam_badge_URL());
                                    intent.putExtra("formedYear",team.getFormed_year());
                                    startActivity(intent);
                                    Log.d("Test", "clicked");
                                }
                            });

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("error", "OnError Error Code : " + anError.getErrorCode());
                        Log.d("error", "OnError Error Body : " + anError.getErrorBody());
                        Log.d("error", "OnError Error Detail : " + anError.getErrorDetail());

                    }
                });
    }


}