package com.ems.self_review.enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "self-review")
public class SelfReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long employeeId;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "q1")
    private Integer q1;

    @Column(name = "q2")
    private Integer q2;

    @Column(name = "q3")
    private Integer q3;

    @Column(name = "q4")
    private Integer q4;

    @Column(name = "q5")
    private Integer q5;

    @Column(name = "q6")
    private Integer q6;

    @Column(name = "q7")
    private Integer q7;

    @Column(name = "q8")
    private Integer q8;

    @Column(name = "q9")
    private Integer q9;

    @Column(name = "q10")
    private Integer q10;

    @Column(name = "comment1")
    private String comment1;

    @Column(name = "comment2")
    private String comment2;

    @Column(name = "comment3")
    private String comment3;

    @Column(name = "comment4")
    private String comment4;

    @Column(name = "comment5")
    private String comment5;

    @Column(name = "comment6")
    private String comment6;

    @Column(name = "comment7")
    private String comment7;

    @Column(name = "comment8")
    private String comment8;

    @Column(name = "comment9")
    private String comment9;

    @Column(name = "comment10")
    private String comment10;
}
