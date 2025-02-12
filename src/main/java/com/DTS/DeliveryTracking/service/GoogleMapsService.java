package com.DTS.DeliveryTracking.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoogleMapsService {

    public String generateGoogleMapsUrl(List<Coordinate> route) {
        if (route == null || route.size() < 2) {
            throw new IllegalArgumentException("Route must contain at least two points.");
        }
        Coordinate origin = route.get(0);
        Coordinate destination = route.get(route.size() - 1);

        StringBuilder waypoints = new StringBuilder();
        if (route.size() > 2) {
            waypoints.append("&waypoints=");
            for (int i = 1; i < route.size() - 1; i++) {
                Coordinate waypoint = route.get(i);
                waypoints.append(waypoint.getLatitude())
                        .append(",")
                        .append(waypoint.getLongitude());
                if (i < route.size() - 2) {
                    waypoints.append("|");
                }
            }
        }

        return String.format(
    "https://www.google.com/maps/dir/?api=1&origin=%f,%f&destination=%f,%f&travelmode=driving",
    origin.getLatitude(), origin.getLongitude(),
    destination.getLatitude(), destination.getLongitude()
);
    }

    public static class Coordinate {
        private double latitude;
        private double longitude;

        public Coordinate(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
//
