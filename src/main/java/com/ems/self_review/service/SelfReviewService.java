package com.ems.self_review.service;
import com.ems.self_review.enitity.Employee;
import com.ems.self_review.enitity.SelfReview;
import com.ems.self_review.repository.EmployeeRepository;
import com.ems.self_review.repository.SelfReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SelfReviewService {

    private final EmployeeRepository employeeRepository;
    private final SelfReviewRepository selfReviewRepository;

    @Autowired
    public SelfReviewService(EmployeeRepository employeeRepository, SelfReviewRepository selfReviewRepository) {
        this.employeeRepository = employeeRepository;
        this.selfReviewRepository = selfReviewRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfReviewService.class);

    public Optional<Employee> getEmployeeById(Long employeeId) {
        try {
            LOGGER.info("Inside get employee by Id");
            return employeeRepository.findById(employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get employee with ID: " + employeeId, e);
        }
    }

    public boolean isEligibleForSelfReview(Long employeeId) {
        LOGGER.info("Check eligibility");
        // Check if the employee exists
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new IllegalArgumentException("Employee not found with ID: " + employeeId);
        }

        //Check if the employee has submitted a self-review for the current year
        Optional<SelfReview> selfReviewOptional = selfReviewRepository.findTopByEmployeeIdOrderByReviewDateDesc(employeeId);
        if (selfReviewOptional.isEmpty()) {
            return true;
        }

        SelfReview selfReview = selfReviewOptional.get();

        // Calculate if one year has passed since the last review
        LocalDate lastReviewDate = selfReview.getReviewDate();
        LocalDate currentDate = LocalDate.now();
        LocalDate nextEligibleDate = lastReviewDate.plusYears(1);

        return currentDate.isAfter(nextEligibleDate) || currentDate.equals(nextEligibleDate);
    }

    public SelfReview saveSelfReview(SelfReview selfReview) {
        LOGGER.info("save self review form");
        selfReview.setReviewDate(LocalDate.now());
        return selfReviewRepository.save(selfReview);
    }
}
