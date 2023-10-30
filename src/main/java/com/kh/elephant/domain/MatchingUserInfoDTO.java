package com.kh.elephant.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingUserInfoDTO {

    private String token;

    private int matchingUserInfoSEQ;

    private int postSEQ;

    private String matchingAccept;

    private String id;

}
