package com.modak.elearning.friendship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendShipRequest {
   private Long sourceId;
   private Long targetId;
}
