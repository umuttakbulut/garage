package com.vodafone.garage.controller;

import com.vodafone.garage.model.dto.VehicleDto;
import com.vodafone.garage.service.abstracts.IVehicleService;
import com.vodafone.garage.utility.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final IVehicleService vehicleService;

    @PostMapping("/park")
    public String park(@Validated @RequestBody VehicleDto vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (bindingResult.getErrorCount() > 1) {
                for (FieldError item : bindingResult.getFieldErrors()) {
                    if (!item.getDefaultMessage().equals(""))
                        stringBuilder.append("[").append(item.getDefaultMessage()).append("]");
                }
                return stringBuilder.toString();
            }
            return bindingResult.getFieldError().getDefaultMessage();
        }
        Result result = vehicleService.pushVehicleToGarage(vehicle);
        return result.getResultMessage();
    }

    @DeleteMapping("/leave")
    public String leave(@RequestParam int key) {
        Result result = vehicleService.pullCarFromGarage(key);
        return result.getResultMessage();
    }

    @GetMapping("/status")
    public String status() {
        Result result = vehicleService.getStatus();
        return result.getResultMessage();
    } }
