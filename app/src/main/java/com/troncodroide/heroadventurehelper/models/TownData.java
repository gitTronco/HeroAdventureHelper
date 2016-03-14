package com.troncodroide.heroadventurehelper.models;

import java.util.List;

public class TownData {
    private String name;
    private List<CiticenData> citicens;

    public TownData(String name, List<CiticenData> citicens) {
        this.name = name;
        this.citicens = citicens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CiticenData> getCiticens() {
        return citicens;
    }

    public void setCiticens(List<CiticenData> citicens) {
        this.citicens = citicens;
    }

    public int getProgress() {
        int total = 0;
        int progress = 0;
        for (CiticenData citicen : citicens) {
            total += citicen.getProfessions().size();
            progress += citicen.getHelped().size();
        }
        return (100 * progress) / total;
    }
}
