package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.datalab.request.DatalabRequestDto;
import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import com.oh3g.nextrend.dto.datalab.result.InsightResultDto;
import com.oh3g.nextrend.service.NaverShoppingInsightService;
import com.oh3g.nextrend.service.SearchAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShoppingTrendsController {
    private final NaverShoppingInsightService shoppingInsightService;
    private final SearchAnalysisService searchAnalysisService;

    @PostMapping("/shopping-trends")
    public InsightResultDto postShoppingTrends(@RequestBody DatalabRequestDto datalabRequestDto) {
        DatalabResponseDto responseEntity
                = shoppingInsightService.callNaverShoppingTrendsAPI(datalabRequestDto);
        log.info(responseEntity.toString());

        InsightResultDto insightResultDto = new InsightResultDto();
        insightResultDto.setMessage(
                searchAnalysisService.resultLocalDate(datalabRequestDto, responseEntity));
        insightResultDto.setDatalabResponseDto(responseEntity);

        log.info(insightResultDto.toString());
        return insightResultDto;
    }
}