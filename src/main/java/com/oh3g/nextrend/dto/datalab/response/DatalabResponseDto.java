package com.oh3g.nextrend.dto.datalab.response;

import lombok.Data;

import java.util.List;

@Data
public class DatalabResponseDto {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<ResultDto> results;
}