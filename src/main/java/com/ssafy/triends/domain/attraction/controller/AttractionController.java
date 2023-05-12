package com.ssafy.triends.domain.attraction.controller;

import com.ssafy.triends.domain.attraction.constant.AttractionResponseMessage;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import com.ssafy.triends.domain.attraction.service.AttractionService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

    private final Logger logger = LoggerFactory.getLogger(AttractionController.class);

    private AttractionService attractionInfoService;

    public AttractionController(AttractionService attractionInfoService) {
        super();
        this.attractionInfoService = attractionInfoService;
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<ResponseDto<?>> searchOneAttraction(
            @PathVariable("attractionId") int attractionId)
            throws Exception {
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.SEARCH_ATTRACTION_DETAIL_SUCCESS.getMessage(),
                attractionInfoService.getAttractionDetail(attractionId)));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> searchAttractions(SearchDto searchDto) throws Exception {
        logger.debug("searchDto : {}", searchDto);
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.SEARCH_ATTRACTIONS_SUCCESS.getMessage(),
                attractionInfoService.searchAttractions(searchDto)));
    }

    @GetMapping("/recommend")
    public ResponseEntity<ResponseDto<?>> getRecommendAttractions(HttpSession session) throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        if (userDto != null) {
            return null;
        } else {
            return ResponseEntity.ok(ResponseDto.createResponse(
                    AttractionResponseMessage.GET_ATTRACTIONS_ORDER_BY_RATES_SUCCESS.getMessage(),
                    attractionInfoService.getRecommendAttractions(1)
            ));
        }
    }
}
