package com.oh3g.nextrend.dto.datalab.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class DatalabRequestDto {
    String startDate;
    String endDate;
    String timeUnit;
    List<CategoryDto> category;
}