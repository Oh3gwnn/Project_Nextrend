package com.oh3g.nextrend.service;

import com.oh3g.nextrend.dto.datalab.request.DatalabRequestDto;
import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchAnalysisService {

    private static final Map<String, String> CATEGORY_MAP = new HashMap<>();
    static {
        CATEGORY_MAP.put("50000000", "패션의류");
        CATEGORY_MAP.put("50000001", "패션잡화");
        CATEGORY_MAP.put("50000002", "화장품/미용");
        CATEGORY_MAP.put("50000003", "디지털/가전");
        CATEGORY_MAP.put("50000004", "가구/인테리어");
        CATEGORY_MAP.put("50000005", "출산/육아");
        CATEGORY_MAP.put("50000006", "식품");
        CATEGORY_MAP.put("50000007", "스포츠/레저");
        CATEGORY_MAP.put("50000008", "생활/건강");
        CATEGORY_MAP.put("50000009", "여가/생활편의");
    }

    public String getCategoryString(String categoryCode) {
        return CATEGORY_MAP.get(categoryCode);
    }

    public String resultLocalDate(DatalabRequestDto datalabRequestDto,
                                  DatalabResponseDto datalabResponseDto) {
        // 날짜(주간 단위)
        LocalDate startDate = LocalDate.parse(datalabResponseDto.getStartDate());
        LocalDate endDate = LocalDate.parse(datalabResponseDto.getEndDate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        String categoryString = getCategoryString(datalabRequestDto.getCategory());

        String message =
                formattedStartDate + "부터 " +
                        formattedEndDate + "까지 " +
                        categoryString + " 카테고리의 '" +
                        datalabRequestDto.getKeyword() + "'에 대한 연령대 별 트렌드입니다.";

        return message;
    }
}
