package com.vodafone.garage.model;

public class Ticket {
    private String ticketNumber;
    private BaseVehicle vehicle;

    public Ticket(BaseVehicle vehicle) {
        this.vehicle = vehicle;
        this.ticketNumber = vehicle.getPlate();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public BaseVehicle getvehicle() {
        return vehicle;
    }
}
