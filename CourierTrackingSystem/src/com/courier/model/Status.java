package com.courier.model;

/**
 * Enum representing different statuses a package can have
 */
public enum Status {
    ORDER_PLACED("Order Placed"),
    PICKED_UP("Picked Up"),
    IN_TRANSIT("In Transit"),
    OUT_FOR_DELIVERY("Out for Delivery"),
    DELIVERED("Delivered"),
    FAILED_DELIVERY("Failed Delivery Attempt"),
    RETURNED("Returned to Sender");
    
    private final String displayName;
    
    Status(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}