package com.ssafy.triends.domain.friends.controller;

import com.ssafy.triends.domain.friends.constant.FriendsResponseMessage;
import com.ssafy.triends.domain.friends.service.FriendsService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/friends")
@Api(tags = {"Friends"})
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
    @ApiImplicitParam(name = "userId", value = "로그인한 유저의 pk", required = true, dataTypeClass = Integer.class, defaultValue = "2")
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
    @ApiImplicitParam(name = "notificationAndSenderAndUserId",
                    value = "userId : 로그인한 유저의 pk \n notificationId : 해당 알림의 pk \n senderId : 친구요청 보낸 유저의 pk",
                    dataTypeClass = Map.class,
                    defaultValue = "{\"userId\":2,\"notificationId\":2,\"senderId\":3}")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId",
                    value = "삭제할 친구 유저의 pk",
                    dataTypeClass = Integer.class,
                    defaultValue = "6"),
            @ApiImplicitParam(name = "userId",
                    value = "로그인한 유저의 pk",
                    dataTypeClass = Integer.class,
                    defaultValue = "2")
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",
                    value = "friends : 함께 아는 친구 추천 / preference : 취향 기반 친구 추천",
                    dataTypeClass = String.class,
                    defaultValue = "friends"),
            @ApiImplicitParam(name = "userId",
                    value = "로그인한 유저의 pk",
                    dataTypeClass = Integer.class,
                    defaultValue = "2")
    })
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

    @GetMapping("/search")
    @LoginRequired
    @ApiImplicitParam(name = "keywordAndUserId",
            value = "keyword : 검색어 내용 \n userId : 로그인 한 유저의 pk" ,
            dataTypeClass = Map.class,
            defaultValue = "{\"keyword\":\"john\",\"userId\":2}")
    public ResponseEntity<ResponseDto<?>> searchFriend(@RequestParam Map<String, Object> keywordAndUserId) throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        FriendsResponseMessage.SEARCH_FRIEND_SUCCESS.getMessage(),
                        friendsService.searchFriendByName(keywordAndUserId)
                )
        );
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
