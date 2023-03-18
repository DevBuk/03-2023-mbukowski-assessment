
All endpoints in apliacation:
----------

http://localhost:8080/department/{id}	GET

http://localhost:8080/department/{department_name}/{city_of_department}	GET

>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "departmentName": {
      "type": "string"
    },
    "streetOfDepartment": {
      "type": "string"
    },
    "houseNumberOfDepartment": {
      "type": "string"
    },
    "postcodeOfDepartment": {
      "type": "string"
    },
    "cityOfDepartment": {
      "type": "string"
    }
  },
  "required": [
    "departmentName",
    "streetOfDepartment",
    "houseNumberOfDepartment",
    "postcodeOfDepartment",
    "cityOfDepartment"
  ]
}

>>http://localhost:8080/department/addDepartment	POST

>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "departmentName": {
      "type": "string"
    },
    "streetOfDepartment": {
      "type": "string"
    },
    "houseNumberOfDepartment": {
      "type": "string"
    },
    "postcodeOfDepartment": {
      "type": "string"
    },
    "cityOfDepartment": {
      "type": "string"
    }
  },
  "required": [
    "departmentName",
    "streetOfDepartment",
    "houseNumberOfDepartment",
    "postcodeOfDepartment",
    "cityOfDepartment"
  ]
}

>>http://localhost:8080/department/{id}	PUT

http://localhost:8080/department/{id}	DELETE

*****
http://localhost:8080/employee/average	GET -> salary collection service - data on the average earnings of employees depending on the position held and seniority in years

http://localhost:8080/employee/{id}	GET

http://localhost:8080/employee/{name_of_employee}/{surname_of_employee}	GET

>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "surname": {
      "type": "string"
    },
    "email": {
      "type": "string"
    },
    "phoneNumber": {
      "type": "number"
    },
    "dateOfEmployment": {
      "type": "string"
    },
    "salary": {
      "type": "number"
    },
    "streetOfEmployee": {
      "type": "string"
    },
    "houseNumberOfEmployee": {
      "type": "string"
    },
    "postcodeOfEmployee": {
      "type": "string"
    },
    "cityOfEmployee": {
      "type": "string"
    },
    "departmentName": {
      "type": "string"
    },
    "streetOfDepartment": {
      "type": "string"
    },
    "houseNumberOfDepartment": {
      "type": "string"
    },
    "postcodeOfDepartment": {
      "type": "string"
    },
    "cityOfDepartment": {
      "type": "string"
    },
    "jobName": {
      "type": "string"
    }
  },
  "required": [
    "name",
    "surname",
    "email",
    "phoneNumber",
    "dateOfEmployment",
    "salary",
    "streetOfEmployee",
    "houseNumberOfEmployee",
    "postcodeOfEmployee",
    "cityOfEmployee",
    "departmentName",
    "streetOfDepartment",
    "houseNumberOfDepartment",
    "postcodeOfDepartment",
    "cityOfDepartment",
    "jobName"
  ]
}

>>http://localhost:8080/employee/addEmployee	POST

>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "surname": {
      "type": "string"
    },
    "email": {
      "type": "string"
    },
    "phoneNumber": {
      "type": "number"
    },
    "dateOfEmployment": {
      "type": "string"
    },
    "salary": {
      "type": "number"
    },
    "streetOfEmployee": {
      "type": "string"
    },
    "houseNumberOfEmployee": {
      "type": "string"
    },
    "postcodeOfEmployee": {
      "type": "string"
    },
    "cityOfEmployee": {
      "type": "string"
    },
    "departmentName": {
      "type": "string"
    },
    "streetOfDepartment": {
      "type": "string"
    },
    "houseNumberOfDepartment": {
      "type": "string"
    },
    "postcodeOfDepartment": {
      "type": "string"
    },
    "cityOfDepartment": {
      "type": "string"
    },
    "jobName": {
      "type": "string"
    }
  },
  "required": [
    "name",
    "surname",
    "email",
    "phoneNumber",
    "dateOfEmployment",
    "salary",
    "streetOfEmployee",
    "houseNumberOfEmployee",
    "postcodeOfEmployee",
    "cityOfEmployee",
    "departmentName",
    "streetOfDepartment",
    "houseNumberOfDepartment",
    "postcodeOfDepartment",
    "cityOfDepartment",
    "jobName"
  ]
}

>>http://localhost:8080/employee/{id}	PUT

http://localhost:8080/employee/{id}	DELETE
******

http://localhost:8080/position/{id}	GET

>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "jobName": {
      "type": "string"
    }
  },
  "required": [
    "jobName"
  ]
}
>>http://localhost:8080/position/addPosition	POST

{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "object",
  "properties": {
    "jobName": {
      "type": "string"
    }
  },
  "required": [
    "jobName"
  ]
}

>>http://localhost:8080/position/{id}	PUT

http://localhost:8080/position/{id}	DELETE

-------
>{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Generated schema for Root",
  "type": "array",
  "items": {
    "type": "object",
    "properties": {
      "name": {
        "type": "string"
      },
      "surname": {
        "type": "string"
      },
      "email": {
        "type": "string"
      },
      "phoneNumber": {
        "type": "number"
      },
      "dateOfEmployment": {
        "type": "string"
      },
      "salary": {
        "type": "number"
      },
      "streetOfEmployee": {
        "type": "string"
      },
      "houseNumberOfEmployee": {
        "type": "number"
      },
      "postcodeOfEmployee": {
        "type": "string"
      },
      "cityOfEmployee": {
        "type": "string"
      },
      "departmentName": {
        "type": "string"
      },
      "streetOfDepartment": {
        "type": "string"
      },
      "houseNumberOfDepartment": {
        "type": "number"
      },
      "postcodeOfDepartment": {
        "type": "string"
      },
      "cityOfDepartment": {
        "type": "string"
      },
      "jobName": {
        "type": "string"
      }
    },
    "required": [
      "name",
      "surname",
      "email",
      "phoneNumber",
      "dateOfEmployment",
      "salary",
      "streetOfEmployee",
      "houseNumberOfEmployee",
      "postcodeOfEmployee",
      "cityOfEmployee",
      "departmentName",
      "streetOfDepartment",
      "houseNumberOfDepartment",
      "postcodeOfDepartment",
      "cityOfDepartment",
      "jobName"
    ]
  }
}

>>http://localhost:8080/employee/file/addEmployee	POST -> import CSV file (key: csvFile)

http://localhost:8080/employee/file/{name_of_employee}-{surname_of_employee}.csv	GET -> export CSV file





