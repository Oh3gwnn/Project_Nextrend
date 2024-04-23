package com.oh3g.nextrend.dto.datalab.response;

import lombok.Data;

import java.util.List;


@Data
public class ResultDto {
    private String title;
    private List<String> keyword;
    private List<DataDto> data;
}