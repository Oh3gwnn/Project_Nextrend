package com.oh3g.nextrend.dto.datalab.result;

import com.oh3g.nextrend.dto.datalab.response.DatalabResponseDto;
import lombok.Data;

@Data
public class InsightResultDto {
    String message;
    DatalabResponseDto datalabResponseDto;
}
