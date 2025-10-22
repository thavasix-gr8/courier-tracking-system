package com.courier.model;

import java.time.LocalDateTime;

/**
 * Represents a checkpoint in the package's journey
 */
public class Checkpoint {
    private int checkpointId;
    private String location;
    private LocalDateTime timestamp;
    private Status status;
    private String description;
    
    // Constructors
    public Checkpoint() {}
    
    public Checkpoint(int checkpointId, String location, LocalDateTime timestamp, 
                     Status status, String description) {
        this.checkpointId = checkpointId;
        this.location = location;
        this.timestamp = timestamp;
        this.status = status;
        this.description = description;
    }
    
    // Getters and Setters
    public int getCheckpointId() {
        return checkpointId;
    }
    
    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Checkpoint{" +
                "checkpointId=" + checkpointId +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}