package com.example.persister;

/**
 * Created by jamiecraane on 12/12/2016.
 */
public class RetrieveLocationRequest {
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetrieveLocationRequest{");
        sb.append("deviceId='").append(deviceId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
