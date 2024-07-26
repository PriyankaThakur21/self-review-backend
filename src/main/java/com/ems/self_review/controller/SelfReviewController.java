package com.ems.self_review.controller;

import com.ems.self_review.dto.EmployeeDto;
import com.ems.self_review.dto.SelfReviewDto;
import com.ems.self_review.enitity.Employee;
import com.ems.self_review.enitity.SelfReview;
import com.ems.self_review.mapper.SelfReviewMapper;
import com.ems.self_review.service.SelfReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/self-review")
@CrossOrigin
public class SelfReviewController {

    private final SelfReviewService selfReviewService;
    private final SelfReviewMapper selfReviewMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfReviewController.class);

    public SelfReviewController(SelfReviewService selfReviewService, SelfReviewMapper selfReviewMapper) {
        this.selfReviewService = selfReviewService;
        this.selfReviewMapper = selfReviewMapper;
    }
    @PostMapping("/submit")
    /***
     *
     */
    public ResponseEntity<SelfReviewDto> submitSelfReview(@RequestBody SelfReviewDto selfReviewDto) {
        try {
            SelfReview selfReview = selfReviewMapper.mapDtoToEntity(selfReviewDto);
            SelfReview savedPerformance = selfReviewService.saveSelfReview(selfReview);
            SelfReviewDto dto = selfReviewMapper.mapEntityToDto(savedPerformance);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/check/{employeeId}")
    public boolean checkSelfReviewEligibility(@PathVariable Long employeeId) {
        LOGGER.info("check eligibility");
        return selfReviewService.isEligibleForSelfReview(employeeId);
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
        try {
            Optional<Employee> employeeOptional = selfReviewService.getEmployeeById(employeeId);

            if (employeeOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            EmployeeDto dto = new EmployeeDto();
            Employee employee = employeeOptional.get();
            dto.setEmployeeId(employee.getEmployeeId());
            dto.setName(employee.getFirstName() + " " + employee.getLastName());

            dto.setPosition(employee.getPosition().getPositionTitle());
            dto.setDepartment(employee.getDepartment().getDepartmentName());

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch employee with ID: {}", employeeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
