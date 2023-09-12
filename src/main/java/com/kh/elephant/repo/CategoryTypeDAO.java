package com.kh.elephant.repo;

import com.kh.elephant.domain.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypeDAO extends JpaRepository<CategoryType, Integer> {
}
