package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.datalab.request.DatalabRequestDto;
import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import com.oh3g.nextrend.service.NaverShoppingInsightService;
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

    @PostMapping("/shopping-trends")
    public List<DatalabResponseDto> postShoppingTrends(@RequestBody DatalabRequestDto datalabRequestDto) {
        List<DatalabResponseDto> responseEntity
                = shoppingInsightService.callNaverShoppingTrendsAPI(datalabRequestDto);
        return responseEntity;
    }
}