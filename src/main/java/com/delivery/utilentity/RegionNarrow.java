package com.delivery.utilentity;

public class RegionNarrow {
    private int id;
    private String Name;

    @Override
    public String toString() {
        return "RegionNarrow{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
