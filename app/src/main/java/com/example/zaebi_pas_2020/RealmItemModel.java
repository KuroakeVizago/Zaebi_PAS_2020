package com.example.zaebi_pas_2020;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmItemModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String team_name;
    private String team_description;
    private String team_country;
    private int formed_year;
    private String team_badge_URL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_description() {
        return team_description;
    }

    public void setTeam_description(String team_description) {
        this.team_description = team_description;
    }

    public String getTeam_country() {
        return team_country;
    }

    public void setTeam_country(String team_country) {
        this.team_country = team_country;
    }

    public int getFormed_year() {
        return formed_year;
    }

    public void setFormed_year(int formed_year) {
        this.formed_year = formed_year;
    }

    public String getTeam_badge_URL() {
        return team_badge_URL;
    }

    public void setTeam_badge_URL(String team_badge_URL) {
        this.team_badge_URL = team_badge_URL;
    }
}
