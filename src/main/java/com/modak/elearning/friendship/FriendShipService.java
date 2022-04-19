package com.modak.elearning.friendship;

import java.util.List;

import com.modak.elearning.exception.BadRequestException;
import com.modak.elearning.user.User;
import com.modak.elearning.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FriendShipService {
    
    private final FriendShipRepository friendShipRepository;
    private final UserRepository userRepository;

    /**
     * Create a new friendship in the system
     * 
     * @param request
     * @return FriendShip
     */
    public FriendShip createFriendShip(FriendShipRequest request) {
        log.info("request {} {}", request.getSourceId(), request.getTargetId());
        FriendShip newFriendShip = FriendShip.builder()
                                            .sourceId(request.getSourceId())
                                            .targetId(request.getTargetId())
                                            .status(1)
                                            .build();
        
        friendShipRepository.saveAndFlush(newFriendShip);
        return newFriendShip;
    }

    /**
     * Find all friendship by user
     * 
     * @param sourceId
     * @return FriendShipDTO
     */
    public FriendShipDTO findAllFriendByUser(Long sourceId) {
        User user = userRepository.findById(sourceId)
                                    .orElseThrow(() -> 
                                    new BadRequestException
                                    (String.format("User not valid: %d", sourceId)
                                ));

        List<FriendShip> friends = friendShipRepository.findBySourceId(sourceId);
        List<FriendsDto> friendsByUser = getInfoByUser(friends);

        return FriendShipDTO.builder()
                        .friends(friendsByUser)
                        .user(user)
                        .build();
    }

    /**
     * Get all friendships from the system
     * 
     * @return List<FriendShipDTO>
     */
    public List<FriendShipDTO> findAllFriendShips() {
        return friendShipRepository.findAll()
                                    .stream()
                                    .map(FriendShip::getSourceId)
                                    .map(this::findAllFriendByUser)
                                    .toList();
    }
    
    private List<FriendsDto> getInfoByUser(List<FriendShip> friends) {
        return friends.stream()
                        .map(FriendShip::getTargetId)
                        .map(x -> userRepository.findById(x).get())
                        .toList()
                        .stream()
                        .map(u -> FriendsDto.builder()
                                    .id(u.getId())
                                    .email(u.getEmail())
                                    .name(u.getFirstName().concat(" ".concat(u.getLastName())))
                                    .build())
                        .toList();      
    }
}