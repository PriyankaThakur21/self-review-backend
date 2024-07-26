package com.ems.self_review.repository;

import com.ems.self_review.enitity.SelfReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SelfReviewRepository extends JpaRepository<SelfReview, Long> {
    Optional<SelfReview> findTopByEmployeeIdOrderByReviewDateDesc(Long employeeId);
}
