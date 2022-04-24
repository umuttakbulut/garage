package com.vodafone.garage.model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class BaseVehicle {

    private String vehicleType;
    protected String colour;
    protected String plate;
    protected int slot;
    protected List<Integer> slots;

    protected BaseVehicle(String vehicleType, String colour, String plate, int slot) {
        this.vehicleType = vehicleType;
        this.colour = colour;
        this.slot = slot;
        this.plate = plate;
        slots = new ArrayList<>();
    }
}
