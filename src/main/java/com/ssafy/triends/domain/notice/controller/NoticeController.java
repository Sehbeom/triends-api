package com.ssafy.triends.domain.notice.controller;

import com.ssafy.triends.domain.notice.constant.NoticeResponseMessage;
import com.ssafy.triends.domain.notice.model.NoticeDto;
import com.ssafy.triends.domain.notice.service.NoticeService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/notice")
@Api(tags = {"공지사항 관리"})
public class NoticeController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        super();
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 목록을 조회한다.")
    public ResponseEntity<ResponseDto<?>> getAllNotices() throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.SEARCH_ALL_NOTICES_SUCCESS.getMessage(),
                        noticeService.list()));
    }

    @GetMapping("/{noticeId}")
    @ApiOperation(value = "공지글 조회", notes = "공지글 내용을 조회한다.")
    @ApiImplicitParam(name = "noticeId", value = "공지글 아이디", dataTypeClass = Integer.class, defaultValue = "3")
    public ResponseEntity<ResponseDto<?>> getOneNotice(@PathVariable("noticeId") int noticeId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.SEARCH_ONE_NOTICE_SUCCESS.getMessage(),
                        noticeService.detail(noticeId)));
    }

    @PostMapping("")
    @ApiOperation(value = "공지글 작성", notes = "공지글을 작성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "공지글 제목", dataTypeClass = String.class, defaultValue = "제목", required = true),
            @ApiImplicitParam(name = "content", value = "공지글 내용", dataTypeClass = String.class, defaultValue = "내용", required = true),
    })
    public ResponseEntity<ResponseDto<?>> registerOneNotice(NoticeDto noticeDto,
            @ApiIgnore HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        noticeDto.setUserId(userDto.getUserId());
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.CREATE_ONE_NOTICE_SUCCESS.getMessage(),
                        noticeService.register(noticeDto)
                )
        );
    }

    @PutMapping("")
    @ApiOperation(value = "공지글 수정", notes = "공지글을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "공지글 제목", dataTypeClass = String.class, defaultValue = "제목"),
            @ApiImplicitParam(name = "content", value = "공지글 내용", dataTypeClass = String.class, defaultValue = "내용"),
    })
    public ResponseEntity<ResponseDto<?>> modifyOneNotice(NoticeDto noticeDto, @ApiIgnore HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        noticeDto.setUserId(userDto.getUserId());
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.UPDATE_ONE_NOTICE_SUCCESS.getMessage(),
                        noticeService.modify(noticeDto)
                )
        );
    }

    @DeleteMapping("/{noticeId}")
    @ApiOperation(value = "공지글 삭제", notes = "공지글을 삭제한다.")
    @ApiImplicitParam(name = "noticeId", value = "삭제할 공지글 아이디", dataTypeClass = Integer.class, defaultValue = "1")
    public ResponseEntity<ResponseDto<?>> deleteOneNotice(@PathVariable("noticeId") int noticeId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.DELETE_ONE_NOTICE_SUCCESS.getMessage(),
                        noticeService.delete(noticeId)
                )
        );
    }
}
