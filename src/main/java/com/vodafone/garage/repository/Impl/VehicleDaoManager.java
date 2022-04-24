package com.vodafone.garage.repository.Impl;

import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.model.Ticket;
import com.vodafone.garage.repository.abstracts.IVehicleDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

@Repository
public class VehicleDaoManager implements IVehicleDao {

    private static Map<Integer, Integer> garageMap = new HashMap<>();
    private static List<BaseVehicle> vehicles = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();

    public VehicleDaoManager() {
        garageMap.put(1, 0);
        garageMap.put(2, 0);
        garageMap.put(3, 0);
        garageMap.put(4, 0);
        garageMap.put(5, 0);
        garageMap.put(6, 0);
        garageMap.put(7, 0);
        garageMap.put(8, 0);
        garageMap.put(9, 0);
        garageMap.put(10, 0);
    }

    @Override
    public boolean pushVehicleToGarage(BaseVehicle vehicle) {
        boolean flag = false;
        Iterator<Integer> iterator = garageMap.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            int parkSlot = garageMap.get(key);
            if (parkSlot == 0 && vehicle.getSlot() + key < 11) {
                if (controlForBlankSlot(garageMap, key, vehicle.getSlot() + key)) {
                    key = vehicles.isEmpty() ? key : key + 1;
                    flag = true;
                    for (int i = key; i < vehicle.getSlot() + key; i++) {
                        garageMap.replace(i, 1);
                        vehicle.getSlots().add(i);
                    }
                    vehicles.add(vehicle);
                    tickets.add(new Ticket(vehicle));
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean popCarFromGarage(int key) {
        if (key <= vehicles.size()) {
            BaseVehicle vehicle = vehicles.get(key - 1);
            for (int i = 0; i < vehicle.getSlots().size(); i++) {
                garageMap.replace(vehicle.getSlots().get(i), 0);
            }
            vehicles.remove(key - 1);
            return true;
        }
        return false;
    }

    @Override
    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (BaseVehicle item : vehicles)
            stringBuilder.append(item.getPlate()).append(" ").append(item.getColour()).append(" ").append(item.getSlots()).append("\n");
        return stringBuilder.toString();
    }

    @Override
    public boolean emptyControlForVehicleList() {
        return vehicles.isEmpty();
    }

    private boolean controlForBlankSlot(Map<Integer, Integer> map, int start, int size) {
        for (int i = start; i <= size; i++) {
            if (map.get(i) != 0)
                return false;
        }
        return true;
    }
}
