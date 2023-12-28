package com.kh.elephant.repo;

import com.kh.elephant.domain.BlockUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockUsersDAO extends JpaRepository<BlockUsers, Integer> {
    @Query(value = "SELECT B.* FROM BLOCK_USERS B JOIN POST P ON (B.BLOCK_ID = P.USER_ID) WHERE B.USER_ID = :id", nativeQuery = true)
    List<BlockUsers> findByUserId(String id);
}
