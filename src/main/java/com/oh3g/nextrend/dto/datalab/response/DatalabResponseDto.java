package com.oh3g.nextrend.dto.datalab.response;

import lombok.Data;

@Data
public class DatalabResponseDto {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private ResultDto results;
}