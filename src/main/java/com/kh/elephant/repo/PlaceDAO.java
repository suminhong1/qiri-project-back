package com.kh.elephant.repo;

import com.kh.elephant.domain.Matching;
import com.kh.elephant.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceDAO extends JpaRepository<Place, String> {
}
