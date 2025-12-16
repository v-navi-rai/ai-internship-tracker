package com.vaishnavi.backend.repository;

import com.vaishnavi.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
