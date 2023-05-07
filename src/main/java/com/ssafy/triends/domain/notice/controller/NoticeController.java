package com.ssafy.triends.domain.notice.controller;

import com.ssafy.triends.domain.notice.constant.NoticeResponseMessage;
import com.ssafy.triends.domain.notice.model.NoticeDto;
import com.ssafy.triends.domain.notice.service.NoticeService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
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

@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        super();
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<?>> getAllNotices() throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.SEARCH_ALL_NOTICES_SUCCESS.getMessage(),
                        noticeService.list()));
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<?>> getOneNotice(@PathVariable("noticeId") int noticeId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(
                        NoticeResponseMessage.SEARCH_ONE_NOTICE_SUCCESS.getMessage(),
                        noticeService.detail(noticeId)));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> registerOneNotice(NoticeDto noticeDto,
            HttpSession session)
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
    public ResponseEntity<ResponseDto<?>> modifyOneNotice(NoticeDto noticeDto, HttpSession session)
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
