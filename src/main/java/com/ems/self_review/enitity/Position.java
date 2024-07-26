package com.ems.self_review.enitity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "position_title", nullable = false, length = 100)
    private String positionTitle;

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "level", nullable = false, length = 50)
    private String level;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
