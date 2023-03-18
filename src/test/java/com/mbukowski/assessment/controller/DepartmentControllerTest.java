package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.DTO.DepartmentDTO;
import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.DepartmentRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.service.AddressService;
import com.mbukowski.assessment.service.DepartmentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    DepartmentService departmentService;
    @MockBean
    AddressService addressService;
    @MockBean
    EmployeeRepository employeeRepository;
    @MockBean
    DepartmentRepository departmentRepository;
    @MockBean
    AddressRepository addressRepository;

    @Test
    void get_department_by_id_should_return_response_OK_and_response_body_of_found_department() throws Exception {
        //given
        Long id = 99L;
        DepartmentEntity departmentEntity = new DepartmentEntity("Test1", new AddressEntity("Test2",
                "Test3","Test4","Test5"));
        Mockito.when(departmentService.getDepartmentEntityById(id)).thenReturn(departmentEntity);

        //when
        ResultActions perform = mockMvc.perform(get("/department/" + id).contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", equalTo("Test1")));
    }

    @Test
    void adding_department_DTO_should_return_code_200_and_entity() throws Exception {
        //given
        DepartmentDTO inputDepartmentDTO = new DepartmentDTO("Test1", "Test2", "Test3",
                "Test4","Test5");
        DepartmentEntity outputDepartmentEntity = new DepartmentEntity("Test1", new AddressEntity("Test2",
                "Test3","Test4","Test5"));
        Mockito.when(departmentService.addDepartmentEntityToDB(inputDepartmentDTO)).thenReturn(outputDepartmentEntity);

        //when
        ResultActions perform = mockMvc.perform(post("/department/addDepartment").contentType(MediaType.APPLICATION_JSON)
                .content("{\"departmentName\":\"Test1\",\"streetOfDepartment\":\"Test2\",\"houseNumberOfDepartment\":\"Test3\",\"postcodeOfDepartment\":\"Test4\",\"cityOfDepartment\":\"Test5\"}"));

        //then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", equalTo("Test1")));
    }

    @Test
    void removing_department_entity_should_return_code_ok() throws Exception {
        //given
        Long id = 99L;
        DepartmentEntity departmentEntity = new DepartmentEntity(id,"Test1", new AddressEntity("Test2",
                "Test3","Test4","Test5"));
        Mockito.doNothing().when(departmentService).deleteDepartmentEntityById(id);

        //when
        ResultActions perform = mockMvc.perform(delete("/department/" + id).contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
