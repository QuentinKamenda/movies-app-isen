package com.example.quentin.moviesappisen.TMDBObjects;

/**
 * Created by theo on 01/03/2018.
 */

public class TVNetwork {
    private final int id;
    private String name;

    public TVNetwork(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TVNetwork tvNetwork = (TVNetwork) o;

        return id == tvNetwork.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TVNetwork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}