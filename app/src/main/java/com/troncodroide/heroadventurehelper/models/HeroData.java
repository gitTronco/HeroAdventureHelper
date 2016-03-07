package com.troncodroide.heroadventurehelper.models;

import com.troncodroide.heroadventurehelper.R;
import com.troncodroide.heroadventurehelper.managers.HeroManager;

import java.util.LinkedList;
import java.util.List;

public class HeroData {
    String name;
    int resImage;
    int level;
    int id;
    List<Ability> abilities;

    public HeroData() {
        name = "Jhon Doe";
        resImage = R.drawable.hero;
        level = 0;
        id = 1;
        abilities = new LinkedList<>();
    }

    public HeroData(String name, int resImage, int level, int id) {
        this.name = name;
        this.resImage = resImage;
        this.level = level;
        this.id = id;
        this.abilities = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getResImage() {
        return resImage;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public void addAbility(String ability) {
        if (containsAbility(ability)) {
            getAbility(ability).upgrade();
        } else {
            abilities.add(new Ability(ability));
        }
        if (getAbilityPoints() / 10 > level) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        HeroManager.levelUp();
    }

    private int getAbilityPoints() {
        int abilitipoitns = 0;
        for (HeroData.Ability ab : abilities) {
            abilitipoitns += ab.getLevel();
        }
        return abilitipoitns;
    }

    public boolean containsAbility(String ability) {
        for (Ability ab : abilities) {
            if (ab.getAbility().contentEquals(ability)) {
                return true;
            }
        }
        return false;
    }

    public Ability getAbility(String ability) {
        for (Ability ab : abilities) {
            if (ab.getAbility().contentEquals(ability)) {
                return ab;
            }
        }
        return new Ability(ability);
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public static class Ability {
        String Ability;
        int level;

        public Ability(String Ability) {
            this.Ability = Ability;
        }

        public String getAbility() {
            return Ability;
        }

        public int getLevel() {
            return level;
        }

        public void upgrade() {
            level += 1;
        }
    }
}
