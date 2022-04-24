package com.vodafone.garage.utility;

public class SuccessResult extends Result{
    private String message;

    public SuccessResult(String message) {
        super(true, message);
        this.message = message;
    }

    public SuccessResult(String message, int allocatedSlot) {
        super(true, message, allocatedSlot);
    }

    @Override
    public String getResultMessage() {
        if (message != null)
            return message;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMessage());
        int allocatedSlot = getAllocatedSlot();
        if (allocatedSlot > 1)
            stringBuilder.append(allocatedSlot).append(" slots");
        else
            stringBuilder.append(allocatedSlot).append(" slot");
        return stringBuilder.toString();
    }
}
