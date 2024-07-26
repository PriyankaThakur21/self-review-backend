package com.ems.self_review.enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long performanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "rating")
    private int rating;

    @Column(name = "self_review_date")
    private LocalDate selfReviewDate;

    @Column(name = "self_rating")
    private int selfRating;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Employee reviewer;
}
