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
    private String device;
    private String gender;
    private List<String> ages;

    @Override
    public String toString() {
        return "ShoppingTrendRequestDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", timeUnit='" + timeUnit + '\'' +
                ", category=" + categoryToString() +
                ", device='" + device + '\'' +
                ", gender='" + gender + '\'' +
                ", ages=" + ages +
                '}';
    }

    private String categoryToString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (CategoryDto cat : category) {
            stringBuilder.append(cat.toString()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()); // 마지막 쉼표와 공백 제거
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}