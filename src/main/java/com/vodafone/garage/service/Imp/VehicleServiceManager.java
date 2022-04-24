package com.vodafone.garage.service.Imp;

import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.repository.abstracts.IVehicleDao;
import com.vodafone.garage.model.dto.VehicleInstanceDto;
import com.vodafone.garage.service.abstracts.IVehicleService;
import com.vodafone.garage.utility.Result;
import com.vodafone.garage.utility.ErrorResult;
import com.vodafone.garage.utility.SuccessResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceManager implements IVehicleService {

    private final IVehicleDao VehicleDao;

    @Override
    public Result pushVehicleToGarage(BaseVehicle vehicle) {
        BaseVehicle vehicleEntity = VehicleInstanceDto.createInstance(vehicle.getVehicleType().trim(),
                vehicle.getColour().trim(), vehicle.getPlate().trim());

        if (VehicleDao.pushVehicleToGarage(vehicleEntity)) {
            return new SuccessResult("Allocated for ", vehicleEntity.getSlot());
        }
        return new ErrorResult("Garage is full");
    }

    @Override
    public Result pullCarFromGarage(int key) {
        if (VehicleDao.popCarFromGarage(key)) {
            return new SuccessResult("Vehicle left");
        }
        return new ErrorResult("Can not find vehicle");
    }

    @Override
    public Result getStatus() {
        if (!VehicleDao.emptyControlForVehicleList())
            return new SuccessResult(VehicleDao.getStatus());
        return new ErrorResult("Garage is empty");
    }
}
