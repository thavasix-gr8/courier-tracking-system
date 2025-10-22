package com.courier.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a courier package
 */
public class Package {
    private String trackingId;
    private String senderName;
    private String senderAddress;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private double weight;
    private String description;
    private Status currentStatus;
    private LocalDateTime orderDate;
    private LocalDateTime estimatedDelivery;
    private List<Checkpoint> checkpoints;
    
    // Constructors
    public Package() {
        this.checkpoints = new ArrayList<>();
    }
    
    public Package(String trackingId, String senderName, String recipientName, 
                  String recipientAddress, Status currentStatus) {
        this.trackingId = trackingId;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.currentStatus = currentStatus;
        this.checkpoints = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getSenderName() {
        return senderName;
    }
    
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
    public String getSenderAddress() {
        return senderAddress;
    }
    
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
    
    public String getRecipientName() {
        return recipientName;
    }
    
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    
    public String getRecipientAddress() {
        return recipientAddress;
    }
    
    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
    
    public String getRecipientPhone() {
        return recipientPhone;
    }
    
    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Status getCurrentStatus() {
        return currentStatus;
    }
    
    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public LocalDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }
    
    public void setEstimatedDelivery(LocalDateTime estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }
    
    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
    
    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
    
    public void addCheckpoint(Checkpoint checkpoint) {
        this.checkpoints.add(checkpoint);
    }
    
    @Override
    public String toString() {
        return "Package{" +
                "trackingId='" + trackingId + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", currentStatus=" + currentStatus +
                ", checkpoints=" + checkpoints.size() +
                '}';
    }
}