package com.ssafy.triends.domain.user.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.constant.UserResponseMessage;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.service.UserService;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.error.exception.ExceptionMessage;
import com.ssafy.triends.global.interceptor.LoginRequired;
import com.ssafy.triends.global.util.jwt.JwtOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
@Api(tags = {"User"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private JwtOperator jwtOperator;

    @Autowired
    public UserController(UserService userService, JwtOperator jwtOperator) {
        super();
        this.userService = userService;
        this.jwtOperator = jwtOperator;
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiImplicitParam(name = "idAndPassword",
            value = "userId : 로그인 할 유저의 아이디 \n userPwd : 로그인 할 유저의 비밀번호",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":jane_doe,\"userPwd\":password2}")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> idAndPassword) throws Exception {
        UserDto userDto = userService.loginUser(idAndPassword);
        logger.debug("user ID : {}", userDto.getUserId());

        if (userDto != null) {
            String accessToken = jwtOperator.createAccessToken("userId", userDto.getUserId());
            String refreshToken = jwtOperator.createRefreshToken("userId", userDto.getUserId());
            userService.saveRefreshToken(userDto.getUserId(), refreshToken);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("access-token", accessToken);
            responseData.put("refresh-token", refreshToken);
            responseData.put("userInfo", userDto);

            return ResponseEntity.ok(
                    ResponseDto.createResponse(UserResponseMessage.LOGIN_SUCCESS.getMessage(), responseData)
            );
        } else {
            return ResponseEntity.accepted().body(ResponseDto.createResponse(UserResponseMessage.LOGIN_FAIL.getMessage()));
        }
    }

    @GetMapping("/logout")
    @LoginRequired
    @ApiOperation(value = "로그아웃 (LoginRequired)", notes = "로그아웃")
    @ApiImplicitParam(name = "userId", value = "로그아웃 할 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> logoutUser(int userId) throws Exception {
        userService.removeRefreshToken(userId);
        return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.LOGOUT_SUCCESS.getMessage()));
    }

    @GetMapping
    @LoginRequired
    @ApiOperation(value = "유저 정보 조회 (LoginRequired)", notes = "유저 정보 조회")
    @ApiImplicitParam(name = "userId", value = "정보를 얻어 올 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> getUser(int userId) throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(UserResponseMessage.GET_USER_INFO.getMessage(),
                        userService.getUser(userId)));
    }

    @PostMapping
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @ApiImplicitParam(name = "userDto",
            value = "id : 아이디 \n password : 비밀번호 \n name : 이름 \n tel : 전화번호 \n profileimg : 프로필 사진 경로 \n email : 이메일",
            dataTypeClass = Map.class,
            defaultValue = "{\"id\":\"j3beom\",\"password\":\"1234\",\"name\":\"장세범\",\"tel\":\"010-0000-0000\",\"profileimg\":\"profileimg.jpg\",\"email\":\"j3beom@gmail.com\"}")
    public ResponseEntity<ResponseDto<?>> joinUser(@RequestBody UserDto userDto) throws Exception {
		try {
            userService.joinUser(userDto);
        } catch (Exception e) {
            return ResponseEntity.accepted().body(ResponseDto.createResponse(UserResponseMessage.JOIN_USER_FAIL.getMessage()));
        }

        String accessToken = jwtOperator.createAccessToken("userId", userDto.getUserId());
        String refreshToken = jwtOperator.createRefreshToken("userId", userDto.getUserId());
        userService.saveRefreshToken(userDto.getUserId(), refreshToken);

        Map<String, Object> tokenAndUserInfo = new HashMap<>();
        tokenAndUserInfo.put("userInfo", userDto);
        tokenAndUserInfo.put("access-token", accessToken);
        tokenAndUserInfo.put("refresh-token", refreshToken);

        return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.JOIN_USER_SUCCESS.getMessage(),
                tokenAndUserInfo));
    }

    @PutMapping
    @LoginRequired
    @ApiOperation(value = "유저 정보 수정 (LoginRequired)", notes = "유저 정보 수정")
    @ApiImplicitParam(name = "userDto",
            value = "userId : 수정할 유저의 pk \n password : 비밀번호 \n name : 이름 \n tel : 전화번호 \n profileimg : 프로필 사진 경로 \n email : 이메일",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":2,\"password\":\"1234\",\"name\":\"장세범\",\"tel\":\"010-0000-0000\",\"profileimg\":\"profileimg.jpg\",\"email\":\"j3beom@gmail.com\"}")
    public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto) {
        try {
            userService.modifyUser(userDto);

            return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.MODIFY_USER_INFO.getMessage(),
                    userService.getUser(userDto.getUserId())));
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/preference")
    @LoginRequired
    @ApiOperation(value = "취향 조회 (LoginRequired)", notes = "로그인 한 유저가 선택한 취향 목록 조회")
    @ApiImplicitParam(name = "userId", value = "취향을 조회할 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> getPreferences(int userId) throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        UserResponseMessage.GET_PREFERENCE.getMessage(),
                        userService.getOneUserPreferences(userId)
                )
        );
    }

    @PostMapping("/preference")
    @LoginRequired
    @ApiOperation(value = "취향 등록 (LoginRequired)", notes = "로그인 한 유저의 취향 목록 등록")
    @ApiImplicitParam(name = "userIdAndPreferenceIds",
            value = "userId : 취향을 등록할 유저의 pk \n preferenceIds : 선택된 취향의 pk 배열 ",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":2,\"password\":[\"28\",\"32\",\"39\"]}")
    public ResponseEntity<?> registPreference(@RequestBody Map<String, Object> userIdAndpreferenceIds)
            throws Exception {
        userService.registPreferences(userIdAndpreferenceIds);

        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        UserResponseMessage.REGISTER_PREFERENCE.getMessage(),
                        userService.getOneUserPreferences((Integer) userIdAndpreferenceIds.get("userId"))
                )
        );
    }

    @PutMapping("/preference")
    @LoginRequired
    @ApiOperation(value = "취향 수정 (LoginRequired)", notes = "로그인 한 유저의 취향 목록 수정")
    @ApiImplicitParam(name = "userIdAndPreferenceIds",
            value = "userId : 취향을 수정할 유저의 pk \n preferenceIds : 선택된 취향의 pk 배열 ",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":2,\"password\":[\"14\",\"15\",\"39\"]}")
    public ResponseEntity<?> modifyPreferences(@RequestBody Map<String, Object> userIdAndPreferenceIds)
            throws Exception {
        userService.modifyPreferences(userIdAndPreferenceIds);

        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        UserResponseMessage.MODIFY_PREFERENCE.getMessage(),
                        userService.getOneUserPreferences((Integer) userIdAndPreferenceIds.get("userId"))
                )
        );
    }

    @DeleteMapping("/preference")
    @LoginRequired
    @ApiOperation(value = "취향 리셋 (LoginRequired)", notes = "로그인 한 유저의 취향 목록 리셋")
    @ApiImplicitParam(name = "userId", value = "취향을 리셋할 유저의 pk", required = true, dataTypeClass = Integer.class, defaultValue = "2")
    public ResponseEntity<ResponseDto<?>> deletePreferences(int userId) {
        userService.deletePreference(userId);

        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        UserResponseMessage.DELETE_PREFERENCE.getMessage()
                )
        );
    }

    @GetMapping("/authentication")
    @ApiOperation(value = "access token 유효성 검사", notes = "access token 유효성 검사")
    @ApiImplicitParam(name = "userId", value = "로그인 한 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> authentication(int userId, @ApiIgnore HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader("access-token");
        if (accessToken == null) {
            return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.USERINFO_NOT_FOUND.getMessage()));
        }

        if (jwtOperator.checkToken(accessToken)) {
            UserDto userDto = userService.getUser(userId);
            return ResponseEntity.ok(
                    ResponseDto.createResponse(
                            UserResponseMessage.AUTHORIZATION_SUCCESS.getMessage(),
                            userDto
                    )
            );
        }

        return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.TOKEN_EXPIRED.getMessage()));
    }

    @GetMapping("/authentication/reissue")
    @ApiOperation(value = "access token 재발급", notes = "refresh token 유효성 검사 후 재발급")
    @ApiImplicitParam(name = "userId", value = "로그인 한 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> reissueAccessToken(int userId, @ApiIgnore HttpServletRequest request) throws Exception {
        String refreshToken = request.getHeader("refresh-token");

        if (refreshToken == null) {
            return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.USERINFO_NOT_FOUND.getMessage()));
        }

        String originRefreshToken = userService.getRefreshToken(userId);
        if (refreshToken.equals(originRefreshToken)) {
            UserDto userDto = userService.getUser(userId);
            String accessToken = jwtOperator.createAccessToken("userId", userId);

            Map<String, Object> tokenAndUserInfo = new HashMap<>();
            tokenAndUserInfo.put("access-token", accessToken);
            tokenAndUserInfo.put("userInfo", userDto);

            return ResponseEntity.ok(
                    ResponseDto.createResponse(UserResponseMessage.ACCESS_TOKEN_REISSUE_SUCCESS.getMessage(),
                            tokenAndUserInfo)
            );
        }

        return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.TOKEN_EXPIRED.getMessage()));
    }

    private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
        e.printStackTrace();
        return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
    }
}
