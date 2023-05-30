package com.ssafy.triends.domain.attraction.controller;

import com.ssafy.triends.domain.attraction.constant.AttractionResponseMessage;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import com.ssafy.triends.domain.attraction.service.AttractionService;
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

import java.util.Map;

@RestController
@RequestMapping("/attraction")
@Api(tags = {"Attraction"})
public class AttractionController {

    private final Logger logger = LoggerFactory.getLogger(AttractionController.class);

    private AttractionService attractionInfoService;

    public AttractionController(AttractionService attractionInfoService) {
        super();
        this.attractionInfoService = attractionInfoService;
    }

    @GetMapping("/{attractionId}")
    @ApiOperation(value="여행지 상세정보 조회", notes = "여행지 상세 정보를 조회한다.")
    @ApiImplicitParam(name = "attractionId", value = "조회할 여행지 아이디", dataTypeClass = Integer.class, defaultValue = "126143")
    public ResponseEntity<ResponseDto<?>> searchOneAttraction(
            @PathVariable("attractionId") int attractionId)
            throws Exception {
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.SEARCH_ATTRACTION_DETAIL_SUCCESS.getMessage(),
                attractionInfoService.getAttractionDetail(attractionId)));
    }

    @GetMapping("")
    @ApiOperation(value="여행지 검색", notes = "여행지를 검색한다. (최대 10개 검색)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swLat", value = "남서 방향 모서리 Latitude", required = true, dataTypeClass = Double.class, defaultValue = "35.0"),
            @ApiImplicitParam(name = "swLng", value = "남서 방향 모서리 Longitude", required = true, dataTypeClass = Double.class, defaultValue = "129.0"),
            @ApiImplicitParam(name = "neLat", value = "북동 방향 모서리 Latitude", required = true, dataTypeClass = Double.class, defaultValue = "36.0"),
            @ApiImplicitParam(name = "neLng", value = "북동 방향 모서리 Longitude", required = true, dataTypeClass = Double.class, defaultValue = "130.0"),
            @ApiImplicitParam(name = "contentTypeId", value = "여행지 분류", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sidoCode", value = "시도 코드", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "gugunCode", value = "구군 코드", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "keyword", value = "검색 키워드", dataTypeClass = String.class),
    })
    public ResponseEntity<ResponseDto<?>> searchAttractions(SearchDto searchDto) throws Exception {
        logger.debug("searchDto : {}", searchDto);
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.SEARCH_ATTRACTIONS_SUCCESS.getMessage(),
                attractionInfoService.searchAttractions(searchDto)));
    }

    @GetMapping("/recommend")
    @LoginRequired
    @ApiOperation(value="여행지 추천 - 취향 기반", notes = "로그인한 사용자의 취향에 적합한 여행지 추천 목록을 조회한다.")
    @ApiImplicitParam(name = "userId", value = "로그인한 유저의 pk", required = true, dataTypeClass = Integer.class, defaultValue = "2")
    public ResponseEntity<ResponseDto<?>> getRecommendAttractions(@RequestParam int userId)
            throws Exception {
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.GET_RECOMMEND_ATTRACTIONS_SUCCESS.getMessage(),
                attractionInfoService.getRecommendAttractions(userId)
        ));
    }

    @GetMapping("/best")
    @ApiOperation(value = "여행지 추천 - 평점 기반", notes = "지도 상에서 평점 기준 내림차순된 여행지 추천 목록을 조회한다.")
    @ApiImplicitParam(name = "latLngInfo",
            value = "swLat : 지도의 남서쪽 모서리 lat \n swLng : 지도의 남서쪽 모서리 lng \n neLat : 지도의 북동쪽 모서리 lat \n neLng : 지도의 북동쪽 모서리 lng" ,
            dataTypeClass = Map.class,
            defaultValue = "{\"swLat\":35.0,\"swLng\":129.0,\"neLat\":36.0,\"neLng\":130.0}")
    public ResponseEntity<ResponseDto<?>> getBestAttractions(
            @RequestParam Map<String, Object> latLngInfo
    ) throws Exception {
        return ResponseEntity.ok(ResponseDto.createResponse(
                AttractionResponseMessage.GET_ATTRACTIONS_ORDER_BY_RATES_SUCCESS.getMessage(),
                attractionInfoService.getAttractionsOrderByRates(latLngInfo)
        ));
    }
}
