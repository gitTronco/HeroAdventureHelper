package com.troncodroide.heroadventurehelper.models;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.troncodroide.heroadventurehelper.APP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CiticenData {

    private String name, thumbnail, hair_color;
    private Integer id, age;
    private Double weight, height;
    private List<String> professions;
    private List<String> friends;
    private Map<String, Integer> helped;

    public CiticenData() {
        this.id = -1;
    }

    public CiticenData(String name, String thumbnail, String hair_color, Integer id, Integer age, Double weight, Double height, List<String> professions, List<String> friends, Map<String, Integer> helped) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.hair_color = hair_color;
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.professions = professions;
        this.friends = friends;
        this.helped = helped;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getHair_color() {
        return hair_color;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public List<String> getFriends() {
        return friends;
    }

    public Map<String, Integer> getHelped() {
        if (helped == null) {
            loadData();
        }
        return helped;
    }

    public void help(HeroData hero, String profession) {
        if (profession == null) {
            profession = getUnhelpedProfessions();
        }
        if (profession != null) {

            hero.addAbility(profession);
            getHelped().put(profession, hero.getId());
            saveData();
        }
    }


    public String getUnhelpedProfessions() {
        for (String profesion : professions) {
            if (!hasHelped(profesion)) {
                return profesion;
            }
        }
        return null;
    }

    public boolean hasHelped(String profesion) {
        return (getHelped().keySet().contains(profesion));
    }

    public int getSatisfaction() {
        if (professions.size() > 0)
            return (100 * getHelped().size()) / professions.size();
        else return 100;
    }

    private Prefser getPrefser() {
        return new Prefser(APP.getContext().getSharedPreferences("CiticenData" + name, Context.MODE_PRIVATE));
    }

    private void saveData() {

        TypeToken<Map<String, Integer>> typetoken = new TypeToken<Map<String, Integer>>() {
        };
        getPrefser().put("helped", getHelped(), typetoken);
    }

    private void loadData() {
        TypeToken<Map<String, Integer>> typetoken = new TypeToken<Map<String, Integer>>() {
        };
        helped = getPrefser().get("helped", typetoken, new HashMap<String, Integer>());
    }

}
