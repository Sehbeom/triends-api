package com.ssafy.triends.domain.friends.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.user.mapper.UserMapper;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.friends.mapper.FriendsMapper;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import com.ssafy.triends.global.util.preference.PreferenceSimilarityCaculator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    private FriendsMapper friendsMapper;
    private NotificationMapper notificationMapper;
    private UserMapper userMapper;
    private Logger logger = LoggerFactory.getLogger(FriendsServiceImpl.class);

    public FriendsServiceImpl(FriendsMapper friendsMapper, NotificationMapper notificationMapper,
            UserMapper userMapper) {
        super();
        this.friendsMapper = friendsMapper;
        this.notificationMapper = notificationMapper;
        this.userMapper = userMapper;
    }


    @Override
    public void acceptFriend(Map<String, Object> notificationAndSenderAndUserId)
            throws Exception {
        Map<String, Object> acceptFriendParameter = makeAcceptFriendParameter(notificationAndSenderAndUserId);

        friendsMapper.acceptFriend(acceptFriendParameter);
        notificationMapper.deleteOneNotification((Integer) notificationAndSenderAndUserId.get("notificationId"));
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
    public List<UserDto> getRecommendFriendsFromPreferenceSimilarity(int userId)
            throws Exception {
        UserPreferenceDto userPreferenceDto = userMapper.getOneUserPreferences(userId);
        List<UserPreferenceDto> others = userMapper.getAllOtherUsersPreferences(userId);

        Map<UserPreferenceDto, Double> similarities = PreferenceSimilarityCaculator.calculateOneWithOthers(
                userPreferenceDto, others);

        for (Map.Entry<UserPreferenceDto, Double> s : similarities.entrySet()) {
            logger.debug("user : {}, similarity : {}", s.getKey().getUserId(), s.getValue());
        }

        List<Integer> similarUserIds = similarities.keySet().stream()
                .map(u -> u.getUserId())
                .collect(Collectors.toList());

        Map<String, Object> preferenceSimilarityRecommendParameter = makePreferenceSimilarityRecommendParameter(
                similarUserIds, userId);

        return friendsMapper.getRecommendFriendsFromPreferenceSimilarity(
                preferenceSimilarityRecommendParameter);
    }

    @Override
    public List<UserDto> getRecommendFriendsListFromFriendsList(int userId) throws Exception {
        List<UserDto> friendsList = friendsMapper.getFriendsList(userId);
        Map<String, Object> friendsListRecommendParameter = makeFriendsListRecommendParameter(friendsList, userId);
        return friendsMapper.getRecommendFriendsFromFriendsList(friendsListRecommendParameter);
    }

    private Map<String, Object> makeDeleteFriendParameter(int friendId, int userId) {
        Map<String, Object> deleteFriendParameter = new HashMap<>();
        deleteFriendParameter.put("friendId", friendId);
        deleteFriendParameter.put("userId", userId);

        return deleteFriendParameter;
    }

    private Map<String, Object> makeAcceptFriendParameter(Map<String, Object> notificationAndSenderAndUserId) {
        Map<String, Object> acceptFriendParameter = new HashMap<>();
        acceptFriendParameter.put("senderId", notificationAndSenderAndUserId.get("senderId"));
        acceptFriendParameter.put("receiverId", notificationAndSenderAndUserId.get("userId"));

        return acceptFriendParameter;
    }

    private Map<String, Object> makePreferenceSimilarityRecommendParameter(
            List<Integer> similarUserIds, int userId) {
        Map<String, Object> preferenceSimilarityRecommendParameter = new HashMap<>();
        preferenceSimilarityRecommendParameter.put("similarUserIds", similarUserIds);
        preferenceSimilarityRecommendParameter.put("userId", userId);

        return preferenceSimilarityRecommendParameter;
    }

    private Map<String, Object> makeFriendsListRecommendParameter(
            List<UserDto> friendsList, int userId) {
        Map<String, Object> friendsListRecommendParameter = new HashMap<>();
        friendsListRecommendParameter.put("friendsList", friendsList);
        friendsListRecommendParameter.put("userId", userId);

        return friendsListRecommendParameter;
    }
}
