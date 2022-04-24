package com.vodafone.garage.utility;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Result {

    private int allocatedSlot;
    private boolean success;
    private String message;

    public Result(boolean success, String message, int allocatedSlot) {
        this.success = success;
        this.message = message;
        this.allocatedSlot = allocatedSlot;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getAllocatedSlot() {
        return allocatedSlot;
    }

    public abstract String getResultMessage();
}
