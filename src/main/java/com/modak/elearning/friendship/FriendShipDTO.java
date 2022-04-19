package com.modak.elearning.friendship;

import java.util.List;

import com.modak.elearning.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FriendShipDTO {
    private User user;
    private List<FriendsDto> friends;
}
