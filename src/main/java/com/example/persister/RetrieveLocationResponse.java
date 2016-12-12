package com.example.persister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiecraane on 12/12/2016.
 */
public class RetrieveLocationResponse {
    private List<DeviceLocation> locations = new ArrayList<>();

    public List<DeviceLocation> getLocations() {
        return locations;
    }

    public void setLocations(final List<DeviceLocation> locations) {
        this.locations = locations;
    }
}
