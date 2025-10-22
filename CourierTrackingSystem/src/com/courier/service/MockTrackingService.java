package com.courier.service;

import com.courier.model.Checkpoint;
import com.courier.model.Package;
import com.courier.model.Status;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock service that provides fake tracking data for testing the UI
 * This will be replaced with real Hibernate service later
 */
public class MockTrackingService {
    
    private Map<String, Package> mockDatabase;
    
    public MockTrackingService() {
        initializeMockData();
    }
    
    /**
     * Search for a package by tracking ID
     * @param trackingId The tracking ID to search for
     * @return Package object if found, null otherwise
     */
    public Package getPackageByTrackingId(String trackingId) {
        return mockDatabase.get(trackingId);
    }
    
    /**
     * Initialize some mock data for testing
     */
    private void initializeMockData() {
        mockDatabase = new HashMap<>();
        
        // Package 1 - Delivered
        Package pkg1 = new Package();
        pkg1.setTrackingId("TRK001");
        pkg1.setSenderName("ABC Electronics");
        pkg1.setSenderAddress("123 Business St, Mumbai, Maharashtra");
        pkg1.setRecipientName("Rajesh Kumar");
        pkg1.setRecipientAddress("456 Residency Rd, Bangalore, Karnataka");
        pkg1.setRecipientPhone("+91 98765 43210");
        pkg1.setWeight(2.5);
        pkg1.setDescription("Laptop - Dell Inspiron 15");
        pkg1.setCurrentStatus(Status.DELIVERED);
        pkg1.setOrderDate(LocalDateTime.now().minusDays(5));
        pkg1.setEstimatedDelivery(LocalDateTime.now().minusDays(1));
        
        // Add checkpoints for package 1
        pkg1.addCheckpoint(new Checkpoint(1, "Mumbai Hub", 
            LocalDateTime.now().minusDays(5), Status.ORDER_PLACED, 
            "Order received and processing started"));
        pkg1.addCheckpoint(new Checkpoint(2, "Mumbai Sorting Center", 
            LocalDateTime.now().minusDays(4).minusHours(6), Status.PICKED_UP, 
            "Package picked up from sender"));
        pkg1.addCheckpoint(new Checkpoint(3, "Pune Transit Hub", 
            LocalDateTime.now().minusDays(3).minusHours(8), Status.IN_TRANSIT, 
            "Package in transit to destination city"));
        pkg1.addCheckpoint(new Checkpoint(4, "Bangalore Distribution Center", 
            LocalDateTime.now().minusDays(2).minusHours(4), Status.IN_TRANSIT, 
            "Arrived at destination city"));
        pkg1.addCheckpoint(new Checkpoint(5, "Bangalore - Delivery Zone 3", 
            LocalDateTime.now().minusDays(1).minusHours(2), Status.OUT_FOR_DELIVERY, 
            "Out for delivery - Driver: Suresh (Vehicle: KA01AB1234)"));
        pkg1.addCheckpoint(new Checkpoint(6, "456 Residency Rd, Bangalore", 
            LocalDateTime.now().minusDays(1), Status.DELIVERED, 
            "Package delivered successfully. Received by: Rajesh Kumar"));
        
        mockDatabase.put("TRK001", pkg1);
        
        // Package 2 - In Transit
        Package pkg2 = new Package();
        pkg2.setTrackingId("TRK002");
        pkg2.setSenderName("Fashion Boutique");
        pkg2.setSenderAddress("789 Mall Rd, Delhi");
        pkg2.setRecipientName("Priya Sharma");
        pkg2.setRecipientAddress("321 Lake View, Chennai, Tamil Nadu");
        pkg2.setRecipientPhone("+91 87654 32109");
        pkg2.setWeight(1.2);
        pkg2.setDescription("Clothing - Party Wear");
        pkg2.setCurrentStatus(Status.IN_TRANSIT);
        pkg2.setOrderDate(LocalDateTime.now().minusDays(2));
        pkg2.setEstimatedDelivery(LocalDateTime.now().plusDays(1));
        
        pkg2.addCheckpoint(new Checkpoint(1, "Delhi Hub", 
            LocalDateTime.now().minusDays(2), Status.ORDER_PLACED, 
            "Order received and verified"));
        pkg2.addCheckpoint(new Checkpoint(2, "Delhi Sorting Facility", 
            LocalDateTime.now().minusDays(1).minusHours(12), Status.PICKED_UP, 
            "Package collected from sender"));
        pkg2.addCheckpoint(new Checkpoint(3, "Hyderabad Transit Hub", 
            LocalDateTime.now().minusHours(8), Status.IN_TRANSIT, 
            "Package in transit - Expected arrival in Chennai tomorrow"));
        
        mockDatabase.put("TRK002", pkg2);
        
        // Package 3 - Out for Delivery
        Package pkg3 = new Package();
        pkg3.setTrackingId("TRK003");
        pkg3.setSenderName("BookStore Online");
        pkg3.setSenderAddress("111 Library St, Kolkata");
        pkg3.setRecipientName("Amit Patel");
        pkg3.setRecipientAddress("555 Garden Ave, Ahmedabad, Gujarat");
        pkg3.setRecipientPhone("+91 76543 21098");
        pkg3.setWeight(0.8);
        pkg3.setDescription("Books - Set of 5 novels");
        pkg3.setCurrentStatus(Status.OUT_FOR_DELIVERY);
        pkg3.setOrderDate(LocalDateTime.now().minusDays(3));
        pkg3.setEstimatedDelivery(LocalDateTime.now());
        
        pkg3.addCheckpoint(new Checkpoint(1, "Kolkata Hub", 
            LocalDateTime.now().minusDays(3), Status.ORDER_PLACED, 
            "Order placed successfully"));
        pkg3.addCheckpoint(new Checkpoint(2, "Kolkata Processing Center", 
            LocalDateTime.now().minusDays(2).minusHours(18), Status.PICKED_UP, 
            "Package picked up and processed"));
        pkg3.addCheckpoint(new Checkpoint(3, "Ahmedabad Distribution Center", 
            LocalDateTime.now().minusDays(1).minusHours(6), Status.IN_TRANSIT, 
            "Reached destination city"));
        pkg3.addCheckpoint(new Checkpoint(4, "Ahmedabad - Delivery Zone 7", 
            LocalDateTime.now().minusHours(3), Status.OUT_FOR_DELIVERY, 
            "Out for delivery today - Driver: Ravi (Vehicle: GJ01CD5678)"));
        
        mockDatabase.put("TRK003", pkg3);
    }
    
    /**
     * Check if a tracking ID exists
     * @param trackingId The tracking ID to check
     * @return true if exists, false otherwise
     */
    public boolean trackingIdExists(String trackingId) {
        return mockDatabase.containsKey(trackingId);
    }
}