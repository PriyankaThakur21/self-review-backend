package com.ems.self_review.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long employeeId;
    private String name;
    private String position;
    private String department;
}
