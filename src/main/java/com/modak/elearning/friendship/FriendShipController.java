package com.modak.elearning.friendship;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("api/v1/friend-ship")
@AllArgsConstructor
public class FriendShipController {
    
    private final FriendShipService friendShipService;

    @PostMapping
    @ResponseBody
    public FriendShip creatFriendShip(@RequestBody FriendShipRequest request) {
        return friendShipService.createFriendShip(request);
    }

    @GetMapping("/{sourceId}")
    @ResponseBody
    public FriendShipDTO findAllFriendByUser(@PathVariable Long sourceId) {
        return friendShipService.findAllFriendByUser(sourceId);
    }

    @GetMapping
    @ResponseBody
    public List<FriendShipDTO> findAllFriendShips() {
        return friendShipService.findAllFriendShips();
    }
    
}
