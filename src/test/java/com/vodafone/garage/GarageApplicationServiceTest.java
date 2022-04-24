package com.vodafone.garage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.garage.controller.VehicleController;
import com.vodafone.garage.model.BaseVehicle;
import com.vodafone.garage.model.Car;
import com.vodafone.garage.service.abstracts.IVehicleService;
import com.vodafone.garage.utility.Result;
import com.vodafone.garage.utility.SuccessResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = VehicleController.class)
@WithMockUser
public class GarageApplicationServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private IVehicleService vehicleService;

    @Test
    public void pushVehicleToGarageServiceTest() throws Exception {
        Result successResult = new SuccessResult("Allocated for ", 1);
        BaseVehicle vehicle = new Car("Green", "787897");

        Mockito.when(vehicleService.pushVehicleToGarage(vehicle)).thenReturn(successResult);
        String content = mapper.writeValueAsString(vehicle);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/vehicle/park")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void popVehicleFromGarageServiceTest() throws Exception {
        Result successResult = new SuccessResult("Vehicle left");
        int key = 1;
        Mockito.when(vehicleService.pullCarFromGarage(key)).thenReturn(successResult);
        String content = mapper.writeValueAsString(key);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/vehicle/leave?key=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getStatusTest() throws Exception {
        Result successResult = new SuccessResult("Car [1]\n" +
                "Jeep [8, 9]\n" +
                "Jeep [3, 4]");
        Mockito.when(vehicleService.getStatus()).thenReturn(successResult);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/vehicle/status/")
                        .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }
}
