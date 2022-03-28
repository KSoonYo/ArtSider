package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.api.service.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;


@Api(value = "공연 API", tags = {"Show"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;

    HashMap<String,String> map = new HashMap<String,String>(){{
        put("MU","뮤지컬");
        put("CO","콘서트");
        put("CL","클래식/오페라");
        put("DA","무용/전통예술");
        put("DR","연극");
        put("FA","아동/가족");
    }};

    // 전체 공연 목록 조회
    @GetMapping("/all")
    @ApiResponses({    // Api가 붙은 annotation은 swagger와 설정과 관련된 Annotation
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowAllList() {
        List<ShowListGetRes> showAllList = new ArrayList<ShowListGetRes>();
        showAllList = showService.getShowAllList();
        return ResponseEntity.status(200).body(showAllList);
    }


    // 카테고리별 전체 공연 목록 조회
    @GetMapping("/{category}/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowCategoryList(@PathVariable String category) {
        List<ShowListGetRes> showCategoryAllList = new ArrayList<ShowListGetRes>();
        showCategoryAllList = showService.getShowCategoryAllList(map.get(category));
        return ResponseEntity.status(200).body(showCategoryAllList);
    }

    // 개막 예정 공연 목록
    @GetMapping("startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowStartList() throws ParseException {
        List<ShowListGetRes> showStartList = new ArrayList<ShowListGetRes>();
        showStartList = showService.getShowStartList();
        return ResponseEntity.status(200).body(showStartList);
    }

    // 카테고리별 개막 예정 공연 목록
    @GetMapping("/{category}/startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowCategoryStartList(@PathVariable String category) throws ParseException {
        List<ShowListGetRes> showCategoryStartList = new ArrayList<ShowListGetRes>();
        showCategoryStartList = showService.getShowCategoryStartList(map.get(category));
        return ResponseEntity.status(200).body(showCategoryStartList);
    }

    // 종료 임박 공연 목록
    @GetMapping("endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowEndList() throws ParseException {
        List<ShowListGetRes> showEndList = new ArrayList<ShowListGetRes>();
        showEndList = showService.getShowEndList();
        return ResponseEntity.status(200).body(showEndList);
    }

    // 카테고리별 종료 임박 공연 목록
    @GetMapping("/{category}/endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowCategoryEndList(@PathVariable String category) throws ParseException {
        List<ShowListGetRes> showCategoryEndList = new ArrayList<ShowListGetRes>();
        showCategoryEndList = showService.getShowCategoryEndList(map.get(category));
        return ResponseEntity.status(200).body(showCategoryEndList);
    }

    // 전체 인기 공연 목록
    @GetMapping("/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<PopularShowListGetRes>> getPopularShowAllList() {
        List<PopularShowListGetRes> popularShowList = new ArrayList<PopularShowListGetRes>();
        popularShowList = showService.getPopularShowList();
        return ResponseEntity.status(200).body(popularShowList);
    }


    // 카테고리별 인기 공연 목록()
    @GetMapping("/{category}/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "카테고리별 인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "카테고리별 인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<PopularShowListGetRes>> getPopularShowCategoryList(@PathVariable String category) {
        List<PopularShowListGetRes> popularShowCategoryList = new ArrayList<PopularShowListGetRes>();
        popularShowCategoryList = showService.getPopularShowCategoryList(map.get(category));
        return ResponseEntity.status(200).body(popularShowCategoryList);
    }


    // 공연 상세 조회
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<ShowDetailsGetRes> getShowDetails(@PathVariable(value = "id") Long id) {

        ShowDetailsGetRes showDetails = new ShowDetailsGetRes();

        try {
            showDetails = showService.getShowDetails(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(showDetails);
        }

        return ResponseEntity.status(200).body(showDetails);
    }


    // 공연 시설 조회
    @GetMapping("/{artcentername}/artcenter")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연시설 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연시설 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<ArtCenterDetailsGetRes> getArtCenterDetails(@PathVariable(value = "artcentername") String artCenterName) {

        ArtCenterDetailsGetRes artCenterDetails = new ArtCenterDetailsGetRes();

        try {
            artCenterDetails = showService.getArtCenterDetails(artCenterName);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(artCenterDetails);
        }

        return ResponseEntity.status(200).body(artCenterDetails);
    }
}
