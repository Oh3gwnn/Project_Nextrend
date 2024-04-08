package com.oh3g.nextrend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String imageUrl;
    private String productBrand;
    private String productName;
    private String productLink;
    private String price;

    // 생성자, getter 및 setter 메서드는 생략합니다.

    @Override
    public String toString() {
        return "ProductDTO{" +
                "imageUrl='" + imageUrl + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productName='" + productName + '\'' +
                ", productLink='" + productLink + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

