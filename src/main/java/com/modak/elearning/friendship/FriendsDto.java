package com.modak.elearning.friendship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class FriendsDto {
    private Long id;
    private String name;
    private String email;
}
