package com.troncodroide.heroadventurehelper.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tronco on 06/03/2016.
 */
public class CiticenData {

    String name, thumbnail, hair_color;
    Integer id, age;
    Double weight, height;
    List<String> professions;
    List<String> friends;
    Map<Integer, String> helped;


    public CiticenData(String name, String thumbnail, String hair_color, Integer id, Integer age, Double weight, Double height, List<String> professions, List<String> friends, Map<Integer, String> helped) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.hair_color = hair_color;
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.professions = professions;
        this.friends = friends;
        this.helped = ((helped == null) ? new HashMap<Integer, String>() : helped);
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

    public Map<Integer, String> getHelped() {
        return helped;
    }

    public void help(HeroData hero, String profession) {
        hero.addAbility(profession);
        helped.put(hero.getId(), profession);
    }

    public int getSatisfaction() {
        return (100 *  helped.size()) / professions.size();
    }
}
