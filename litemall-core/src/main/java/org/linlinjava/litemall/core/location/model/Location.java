package org.linlinjava.litemall.core.location.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {

    /**
     * 经度
     */
    private String lon;
    /**
     * 纬度
     */
    private String lat;

    public Location(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * 经纬度坐标
     * @return lon, lat
     */
    public String toPosition() {
        return this.lon + "," + this.lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lon, location.lon) &&
                Objects.equals(lat, location.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lon, lat);
    }

    @Override
    public String toString() {
        return "Location{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
