package com.vodafone.garage.model.dto;


import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.model.Car;
import com.vodafone.garage.model.Jeep;
import com.vodafone.garage.model.Truck;

import java.util.Locale;

public class VehicleInstanceDto {

    public static BaseVehicle createInstance(String carName, String colourName, String plate) {
        if (carName.toLowerCase(Locale.ROOT).equals("car"))
            return new Car(colourName, plate);
        if (carName.toLowerCase(Locale.ROOT).equals("jeep"))
            return new Jeep(colourName, plate);
        else
            return new Truck(colourName, plate);
    }
}
