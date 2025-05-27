package com.academic.management.dto;

import com.academic.management.model.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateEnrollmentStatusRequest {
    @NotNull(message = "Status is required")
    private EnrollmentStatus status;
    
    private String rejectionReason;
    
    public UpdateEnrollmentStatusRequest() {
    }
    
    public UpdateEnrollmentStatusRequest(EnrollmentStatus status, String rejectionReason) {
        this.status = status;
        this.rejectionReason = rejectionReason;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
