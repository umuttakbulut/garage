package com.vodafone.garage.repository.abstracts;

import com.vodafone.garage.model.BaseVehicle;

public interface IVehicleDao {

    boolean pushVehicleToGarage(BaseVehicle vehicle);

    boolean popCarFromGarage(int key);

    String getStatus();

    boolean emptyControlForVehicleList();

}
