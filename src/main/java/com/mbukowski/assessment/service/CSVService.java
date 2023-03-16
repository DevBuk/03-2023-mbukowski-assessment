package com.mbukowski.assessment.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mbukowski.assessment.DTO.EmployeeDTO;
import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    private void employeeCsvWriter(List<EmployeeDTO> list){
        File csvOutputFile = new File("src/main/resources/output.csv");

        CsvMapper mapper = new CsvMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("name")
                .addColumn("surname")
                .addColumn("email")
                .addColumn("phoneNumber")
                .addColumn("dateOfEmployment")
                .addColumn("salary")
                .addColumn("streetOfEmployee")
                .addColumn("houseNumberOfEmployee")
                .addColumn("postcodeOfEmployee")
                .addColumn("cityOfEmployee")
                .addColumn("departmentName")
                .addColumn("streetOfDepartment")
                .addColumn("houseNumberOfDepartment")
                .addColumn("postcodeOfDepartment")
                .addColumn("cityOfDepartment")
                .addColumn("jobName")
                .build();

        ObjectWriter writer = mapper.writerFor(EmployeeDTO.class).with(schema);

        try{
            writer.writeValues(csvOutputFile).writeAll(list);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException("IOError during writing to csv file");
        }
    }

    public void exportCSVFileWithEmployeeData(String nameOfEmployee, String surnameOfEmployee, HttpServletResponse response){

        EmployeeEntity employeeEntity =  employeeRepository.findEmployeeEntityByNameAndSurname(nameOfEmployee, surnameOfEmployee);
        EmployeeDTO employeeDTO = employeeService.convertEntityToDto(employeeEntity);
        employeeCsvWriter(Arrays.asList(employeeDTO));

        try {
            InputStream getJSONFileAsInputStream = new FileInputStream("src/main/resources/output.csv");
            IOUtils.copy(getJSONFileAsInputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    public void importCSVFileWithEmployeeData(MultipartFile csvFile){
        File file = new File("src/main/resources/input.csv");

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(csvFile.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException("IOError during writing to file");
        }

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(EmployeeDTO.class)
                .withHeader()
                .withColumnSeparator(',')
                .withComments();

        try{
            MappingIterator<EmployeeDTO> complexUsersIter = csvMapper
                    .readerWithTypedSchemaFor(EmployeeDTO.class)
                    .with(csvSchema)
                    .readValues(file);
            List<EmployeeDTO> employeeDTOs = complexUsersIter.readAll();
            for(EmployeeDTO unit : employeeDTOs){
                employeeService.saveEmployeeEntityInDB(employeeService.convertEmployeeDTOToEmployeeEntity(unit));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException("IOError during reading file");
        }
    }

}
