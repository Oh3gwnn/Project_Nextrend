package com.oh3g.nextrend.dto.jsoup;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductDto {
    private String imageUrl;
    private String productBrand;
    private String productName;
    private String productLink;
    private String price;

    public ProductDto() {
    }

    public ProductDto(String imageUrl, String productBrand, String productName, String productLink, String price) {
        this.imageUrl = imageUrl;
        this.productBrand = productBrand;
        this.productName = productName;
        this.productLink = productLink;
        this.price = price;
    }

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

