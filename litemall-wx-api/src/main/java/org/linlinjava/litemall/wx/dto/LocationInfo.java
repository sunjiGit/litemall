package org.linlinjava.litemall.wx.dto;

import org.linlinjava.litemall.core.location.model.Location;
import org.linlinjava.litemall.db.domain.LitemallStore;

import java.util.Objects;

public class LocationInfo {

    private LitemallStore store;
    private Location position;
    private String distance;

    public LocationInfo(LitemallStore store, Location position, String distance) {
        this.store = store;
        this.position = position;
        this.distance = distance;
    }

    public LitemallStore getStore() {
        return store;
    }

    public void setStore(LitemallStore store) {
        this.store = store;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationInfo info = (LocationInfo) o;
        return Objects.equals(store, info.store) &&
                Objects.equals(position, info.position) &&
                Objects.equals(distance, info.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, position, distance);
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "store=" + store +
                ", position=" + position +
                ", distance='" + distance + '\'' +
                '}';
    }
}
