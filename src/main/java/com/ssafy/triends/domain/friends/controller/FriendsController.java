package com.ssafy.triends.domain.friends.controller;

import com.ssafy.triends.domain.friends.constant.FriendsResponseMessage;
import com.ssafy.triends.domain.friends.service.FriendsService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friends")
@Api(tags = {"친구 관리"})
public class FriendsController {

    private final Logger logger = LoggerFactory.getLogger(FriendsController.class);
    private FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        super();
        this.friendsService = friendsService;
    }

    @GetMapping
    @LoginRequired
    @ApiOperation(value = "친구 목록 조회", notes = "친구 목록을 조회한다. (로그인 필요)")
    public ResponseEntity<ResponseDto<?>> friendsList(int userId) throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        FriendsResponseMessage.GET_FRIENDS_LIST_SUCCESS.getMessage(),
                        friendsService.getFriendsList(userId)
                )
        );
    }

    @PostMapping
    @LoginRequired
    @ApiOperation(value = "친구 요청 수락", notes = "친구 요청을 수락한다. (로그인 필요)")
    @ApiImplicitParam(name = "notificationAndSenderId",
                    value = "친구 요청 알림 아이디 및 요청을 보낸 사용자의 아이디",
                    dataTypeClass = Map.class,
                    defaultValue = "{\"notificationId\":11,\"senderId\":6}")
    public ResponseEntity<ResponseDto<?>> acceptFriend(
            @RequestBody Map<String, Object> notificationAndSenderAndUserId)
            throws Exception {
        friendsService.acceptFriend(notificationAndSenderAndUserId);

        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        FriendsResponseMessage.ACCEPT_FRIEND_SUCCESS.getMessage()
                )
        );
    }

    @DeleteMapping
    @LoginRequired
    @ApiOperation(value = "친구 삭제", notes = "친구를 삭제한다. (로그인 필요)")
    @ApiImplicitParam(name = "friendId",
            value = "삭제할 친구의 아이디",
            dataTypeClass = Integer.class,
            defaultValue = "6")
    public ResponseEntity<ResponseDto<?>> deleteFriend(int friendId, int userId)
            throws Exception {
        friendsService.deleteFriend(friendId, userId);

        return ResponseEntity.ok(ResponseDto.createResponse(
                FriendsResponseMessage.DELETE_FRIEND_SUCCESS.getMessage()
        ));
    }

    @GetMapping("/recommend")
    @LoginRequired
    @ApiOperation(value = "친구 추천 목록 조회", notes = "추천 친구 목록을 조회한다. (로그인 필요)")
    @ApiImplicitParam(name = "type",
            value = "friends : 함께 아는 친구 추천 / preference : 취향 기반 친구 추천",
            dataTypeClass = String.class,
            defaultValue = "friends")
    public ResponseEntity<ResponseDto<?>> getRecommendFriendsListFromFriendsList(String type, int userId)
            throws Exception {
        if ("friends".equals(type)) {
            List<UserDto> friendsList = friendsService.getFriendsList(userId);
            if (friendsList == null || friendsList.isEmpty()) {
                return getResponseOfSimilarityRecommendList(userId);
            }

            return getResponseOfFriendsRecommendList(userId);
        } else if ("preference".equals(type)) {
            return getResponseOfSimilarityRecommendList(userId);
        } else {
            return getResponseOfSimilarityRecommendList(userId);
        }
    }

    private ResponseEntity<ResponseDto<?>> getResponseOfSimilarityRecommendList(int userId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        FriendsResponseMessage.GET_RECOMMEND_FRIENDS_LIST_SUCCESS.getMessage(),
                        friendsService.getRecommendFriendsFromPreferenceSimilarity(userId)
                )
        );
    }

    private ResponseEntity<ResponseDto<?>> getResponseOfFriendsRecommendList(int userId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        FriendsResponseMessage.GET_RECOMMEND_FRIENDS_LIST_SUCCESS.getMessage(),
                        friendsService.getRecommendFriendsListFromFriendsList(userId)
                )
        );
    }
}
