package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.datalab.request.DatalabRequestDto;
import com.oh3g.nextrend.dto.datalab.response.ResultDto;
import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import com.oh3g.nextrend.service.NaverShoppingInsightService;
import com.oh3g.nextrend.service.SearchAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShoppingTrendsController {
    private final NaverShoppingInsightService shoppingInsightService;
    private final SearchAnalysisService searchAnalysisService;

    @PostMapping("/shopping-trends")
    public DatalabResponseDto postShoppingTrends(@RequestBody DatalabRequestDto datalabRequestDto) {
        DatalabResponseDto responseEntity
                = shoppingInsightService.callNaverShoppingTrendsAPI(datalabRequestDto);
        log.info(responseEntity.toString());

        List<ResultDto> resultDto = responseEntity.getResults();
        searchAnalysisService.calculateRatios(resultDto);
        return responseEntity;
    }
}