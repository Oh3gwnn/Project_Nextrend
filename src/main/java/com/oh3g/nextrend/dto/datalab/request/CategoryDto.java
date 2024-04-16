package com.oh3g.nextrend.dto.datalab.request;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    String name;
    List<String> param;
}