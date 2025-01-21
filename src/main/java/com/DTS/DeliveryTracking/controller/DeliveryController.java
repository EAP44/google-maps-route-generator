package com.DTS.DeliveryTracking.controller;

import com.DTS.DeliveryTracking.service.GoogleMapsService;
import com.DTS.DeliveryTracking.service.GoogleMapsService.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class DeliveryController {

    private final GoogleMapsService googleMapsService;

    public DeliveryController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @PostMapping("/url")
    public ResponseEntity<String> generateGoogleMapsUrl(@RequestBody List<Coordinate> route) {
        try {
            String url = googleMapsService.generateGoogleMapsUrl(route);
            return ResponseEntity.ok(url);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred.");
        }
    }
}
