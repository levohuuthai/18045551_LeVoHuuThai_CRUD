package com.example.levohuuthai_18045551_thiapi;

public class PhongBan {
    String id;
    String name;
    String type;

    public PhongBan(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public PhongBan() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
