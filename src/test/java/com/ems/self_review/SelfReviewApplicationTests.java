package com.ems.self_review;

import com.ems.self_review.controller.SelfReviewController;
import com.ems.self_review.dto.EmployeeDto;
import com.ems.self_review.dto.SelfReviewDto;
import com.ems.self_review.enitity.Department;
import com.ems.self_review.enitity.Employee;
import com.ems.self_review.enitity.EmployeePerformance;
import com.ems.self_review.enitity.Position;
import com.ems.self_review.service.SelfReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SelfReviewApplicationTests {

	@Mock
	private SelfReviewService selfReviewService;

	@InjectMocks
	private SelfReviewController employeeController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testGetEmployeeById_Success() {
		// Mocking behavior of SelfReviewService
		Employee mockEmployee = new Employee();
		mockEmployee.setEmployeeId(1L);
		mockEmployee.setFirstName("John");
		mockEmployee.setLastName("Doe");
		mockEmployee.setPosition(new Position());
		mockEmployee.setDepartment(new Department());

		Optional<Employee> employeeOptional = Optional.of(mockEmployee);
		when(selfReviewService.getEmployeeById(1L)).thenReturn(employeeOptional);

		// Call controller method
		ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(1L);

		// Verify response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		EmployeeDto dto = response.getBody();
		assertEquals(1L, dto.getEmployeeId());
		assertEquals("John Doe", dto.getName());
		assertEquals(null, dto.getPosition());
		assertEquals(null, dto.getDepartment());

		// Verify service method was called once
		verify(selfReviewService, times(1)).getEmployeeById(1L);
	}

	@Test
	public void testGetEmployeeById_NotFound() {
		// Mocking behavior of SelfReviewService
		when(selfReviewService.getEmployeeById(anyLong())).thenReturn(Optional.empty());

		// Call controller method
		ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(2L);

		// Verify response
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

		// Verify service method was called once
		verify(selfReviewService, times(1)).getEmployeeById(2L);
	}

	@Test
	public void testGetEmployeeById_Exception() {
		// Mocking behavior of SelfReviewService to throw an exception
		when(selfReviewService.getEmployeeById(anyLong())).thenThrow(new RuntimeException("Service error"));

		// Call controller method
		ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(3L);

		// Verify response
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		// Verify service method was called once
		verify(selfReviewService, times(1)).getEmployeeById(3L);
	}

	@Test
	public void testCheckSelfReviewEligibility() {
		// Mocking behavior of SelfReviewService
		when(selfReviewService.isEligibleForSelfReview(1L)).thenReturn(true);

		// Call controller method
		boolean result = employeeController.checkSelfReviewEligibility(1L);

		// Verify result
		assertTrue(result);

		// Verify service method was called once
		verify(selfReviewService, times(1)).isEligibleForSelfReview(1L);
	}

}
