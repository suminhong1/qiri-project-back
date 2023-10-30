package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDTO {
    private int postSEQ;
    private String postTitle;
    private UserInfo userInfo;
    private Place place;
    private PlaceType placeType;
}
