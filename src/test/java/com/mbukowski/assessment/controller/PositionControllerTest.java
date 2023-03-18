package com.mbukowski.assessment.controller;


import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.entity.PositionEntity;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.repository.PositionRepository;
import com.mbukowski.assessment.service.AddressService;
import com.mbukowski.assessment.service.DepartmentService;
import com.mbukowski.assessment.service.PositionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(PositionController.class)
public class PositionControllerTest {


    @Autowired
    MockMvc mockMvc;
    @MockBean
    PositionRepository positionRepository;
    @MockBean
    PositionService positionService;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void get_department_by_id_should_return_response_OK_and_response_body_of_found_department() throws Exception {
        //given
        Long id = 99L;
        PositionEntity positionEntity = new PositionEntity(id,"Test");
        Mockito.when(positionService.getPositionEntityById(id)).thenReturn(positionEntity);
        //when
        ResultActions perform = mockMvc.perform(get("/position/" + id).contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobName", equalTo("Test")));
    }

    /*
    will be developed in the future
    */

}
