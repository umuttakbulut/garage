package com.vodafone.garage;

import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.model.dto.VehicleInstanceDto;
import com.vodafone.garage.repository.Impl.VehicleDaoManager;
import com.vodafone.garage.service.abstracts.IVehicleService;
import com.vodafone.garage.service.Imp.VehicleServiceManager;
import com.vodafone.garage.utility.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarageApplicationTests {
    @InjectMocks
    private VehicleDaoManager vehicleDao;
    @Mock
    private IVehicleService vehicleService;

    @BeforeEach
    void initVehicleService() {
        vehicleService = new VehicleServiceManager(vehicleDao);
    }

    @Test
    void pushVehicleToGarageTest() {
        BaseVehicle vehicle = VehicleInstanceDto.createInstance("Car", "Blue", "34-UT-8787");
        Result result = vehicleService.pushVehicleToGarage(vehicle);
        assert result.isSuccess();
    }

    @Test
    void popVehicleFromGarageTest() {
        BaseVehicle vehicle = VehicleInstanceDto.createInstance("Car", "Blue", "34-UT-8787");
        vehicleService.pushVehicleToGarage(vehicle);
        Result result = vehicleService.pullCarFromGarage(1);
        assert result.isSuccess();
    }

    @Test
    void pushMultiVehicleToGarageTest() {
        BaseVehicle vehicle1 = VehicleInstanceDto.createInstance("Car", "Blue", "34-UT-8787");
        BaseVehicle vehicle2 = VehicleInstanceDto.createInstance("Jeep", "Black", "34-FG-4578");
        BaseVehicle vehicle3 = VehicleInstanceDto.createInstance("Jeep", "White", "34-RE-1234");
        BaseVehicle vehicle4 = VehicleInstanceDto.createInstance("Truck", "Green", "34-CV-4578");
        vehicleService.pushVehicleToGarage(vehicle1);
        vehicleService.pushVehicleToGarage(vehicle2);
        vehicleService.pushVehicleToGarage(vehicle3);
        Result result = vehicleService.pushVehicleToGarage(vehicle4);
        assert !result.isSuccess();
    }

    @Test
    void pushAndPopVehicleToGarageTest() {
        BaseVehicle vehicle1 = VehicleInstanceDto.createInstance("Car", "Blue", "34-UT-8787");
        BaseVehicle vehicle2 = VehicleInstanceDto.createInstance("Jeep", "Black", "34-FG-4578");
        BaseVehicle vehicle3 = VehicleInstanceDto.createInstance("Jeep", "White", "34-RE-1234");
        BaseVehicle vehicle4 = VehicleInstanceDto.createInstance("Truck", "Green", "34-CV-4578");
        vehicleService.pushVehicleToGarage(vehicle1);
        vehicleService.pushVehicleToGarage(vehicle2);
        vehicleService.pushVehicleToGarage(vehicle3);
        Result result1 = vehicleService.pushVehicleToGarage(vehicle4);
        vehicleService.pullCarFromGarage(1);
        vehicleService.pullCarFromGarage(1);
        vehicleService.pullCarFromGarage(1);
        Result result2 = vehicleService.pushVehicleToGarage(vehicle4);
        assert !result1.isSuccess() && result2.isSuccess();
    }



}
