package com.example.psp2.Model;

public class Faculty {
    private int id;
    private String name_fac;

    public Faculty() {
    }

    public Faculty(String name_fac) {
        this.name_fac = name_fac;
    }

    public Faculty(int id, String name_fac) {
        this.id = id;
        this.name_fac = name_fac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_fac() {
        return name_fac;
    }

    public void setName_fac(String name_fac) {
        this.name_fac = name_fac;
    }

    public void setName(String name) {
    }
}
