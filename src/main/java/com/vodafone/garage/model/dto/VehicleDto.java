package com.vodafone.garage.model.dto;

import com.vodafone.garage.model.BaseVehicle;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@Validated
public class VehicleDto extends BaseVehicle {
    @NotNull(message = "Vehicle type can not be null")
    private String vehicleType;


    @NotNull(message = "Vehicle's colour can not be null")
    @NotBlank(message = "Colour can not blank")
    protected String colour;


    @NotNull(message = "Vehicle's colour can not be null")
    @NotBlank(message = "Colour can not blank")
    protected String plate;

    protected VehicleDto(String vehicleType, String colour, String plate) {
        super(vehicleType, colour, plate, 0);
        this.vehicleType = vehicleType;
        this.colour = colour;
        this.plate = plate;
    }

    @AssertTrue(message = "")
    public boolean isFieldForNull() {
        if (vehicleType == null || colour == null || plate == null) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Invalid vehicle type. It must be car or jeep or truck")
    public boolean isVehicleTypeForValid() {
        if (vehicleType != null && colour != null && plate != null) {
            if (vehicleType.toLowerCase(Locale.ROOT).trim().equals("car") ||
                    vehicleType.toLowerCase(Locale.ROOT).trim().equals("jeep") || vehicleType.toLowerCase(Locale.ROOT).trim().equals("truck"))
                return true;
            else
                return false;
        }
        return true;
    }
}
