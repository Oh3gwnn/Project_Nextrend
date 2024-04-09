package com.oh3g.nextrend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NaverRequestDto {
    String query;
    Integer display;
    Integer start;
    String sort;
}
