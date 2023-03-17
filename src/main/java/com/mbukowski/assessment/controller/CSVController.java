package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/employee")
public class CSVController {

    @Autowired
    CSVService csvService;

    @PostMapping("/file/addEmployee")
    public void importCSVFile(@RequestParam("csvFile") MultipartFile csvFile){
        csvService.importCSVFileWithEmployeeData(csvFile);
    }

    @RequestMapping(value = "/file/{name_of_employee}-{surname_of_employee}.csv", method = RequestMethod.GET)
    public void exportCSVFile(
            @PathVariable("name_of_employee") String nameOfEmployee,
            @PathVariable("surname_of_employee") String surnameOfEmployee,
            HttpServletResponse response
    ) {
        csvService.exportCSVFileWithEmployeeData(nameOfEmployee, surnameOfEmployee, response);
    }

}
