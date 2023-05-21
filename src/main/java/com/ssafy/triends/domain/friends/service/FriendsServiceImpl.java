package com.ssafy.triends.domain.friends.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.friends.mapper.FriendsMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    private FriendsMapper friendsMapper;
    private NotificationMapper notificationMapper;

    public FriendsServiceImpl(FriendsMapper friendsMapper, NotificationMapper notificationMapper) {
        super();
        this.friendsMapper = friendsMapper;
        this.notificationMapper = notificationMapper;
    }


    @Override
    public void acceptFriend(Map<String, Object> notificationAndSenderId, int userId)
            throws Exception {
        Map<String, Object> acceptFriendParameter = makeAcceptFriendParameter(
                Integer.parseInt((String) notificationAndSenderId.get("senderId")),
                userId
        );

        friendsMapper.acceptFriend(acceptFriendParameter);
        notificationMapper.deleteOneNotification(
                Integer.parseInt((String) notificationAndSenderId.get("notificationId")));
    }

    @Override
    public List<UserDto> getFriendsList(int userId) throws Exception {
        return friendsMapper.getFriendsList(userId);
    }

    @Override
    public void deleteFriend(int friendId, int userId) throws Exception {
        Map<String, Object> deleteFriendParameter = makeDeleteFriendParameter(friendId, userId);

        friendsMapper.deleteFriend(deleteFriendParameter);
    }

    @Override
    public List<UserDto> getRecommendFriendsList(int userId) throws Exception {
        List<UserDto> friendsList = friendsMapper.getFriendsList(userId);
        return friendsMapper.getRecommendFriendsFromFriendsList(friendsList);
    }

    private Map<String, Object> makeDeleteFriendParameter(int friendId, int userId) {
        Map<String, Object> deleteFriendParameter = new HashMap<>();
        deleteFriendParameter.put("friendId", friendId);
        deleteFriendParameter.put("userId", userId);

        return deleteFriendParameter;
    }

    private Map<String, Object> makeAcceptFriendParameter(int senderId, int userId) {
        Map<String, Object> acceptFriendParameter = new HashMap<>();
        acceptFriendParameter.put("senderId", senderId);
        acceptFriendParameter.put("receiverId", userId);

        return acceptFriendParameter;
    }
}
