package com.oh3g.nextrend.dto.datalab.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class DatalabRequestDto {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private String category;
    private String keyword;
    private String device;
    private String gender;
    private List<String> ages;
}