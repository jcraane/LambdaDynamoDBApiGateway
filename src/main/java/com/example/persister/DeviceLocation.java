package com.example.persister;

/**
 * Created by jamiecraane on 02/12/2016.
 */
public class DeviceLocation {
    private double lat;
    private double lng;
    private String deviceId;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    public void setLng(final double lng) {
        this.lng = lng;
    }

    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceLocation{");
        sb.append("lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append(", deviceId='").append(deviceId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
