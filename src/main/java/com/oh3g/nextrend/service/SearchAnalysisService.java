package com.oh3g.nextrend.service;

import com.oh3g.nextrend.dto.datalab.response.DataDto;
import com.oh3g.nextrend.dto.datalab.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchAnalysisService {

    // 연령대별 검색량 계산 메서드
    public void calculateRatios(List<ResultDto> resultList) {
        List<DataDto> dataList = new ArrayList<>();
        for (ResultDto resultDto : resultList) dataList.addAll(resultDto.getData());

        log.info(dataList.toString());
    }
}
