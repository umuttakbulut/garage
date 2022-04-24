package com.vodafone.garage.service.abstracts;

import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.utility.Result;

public interface IVehicleService {
    Result pushVehicleToGarage(BaseVehicle vehicle);

    Result pullCarFromGarage(int key);

    Result getStatus();
}
