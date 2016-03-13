package com.troncodroide.heroadventurehelper.filter.models;

public class FilterValue {
    String name;
    boolean selected;
    int type;

    @Override
    public String toString() {
        return "FilterValue{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                ", type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public FilterValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void toggleSelection() {
        selected = !selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clear() {
        selected = false;
    }

    public boolean hasFlag(int flag) {
        return (type & flag) == flag;
    }
}
