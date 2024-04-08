package com.oh3g.nextrend.service;

import com.oh3g.nextrend.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HtmlParserService {
    public String getTitle(String url) {
        try {
            // 해당 URL에서 HTML을 가져옵니다.
            Document document = Jsoup.connect(url).get();

            // HTML에서 title 요소를 가져옵니다.
            Element titleElement = document.select("title").first();

            if (titleElement != null) {
                // title 요소의 내용을 반환합니다.
                return titleElement.text();
            } else {
                return "No title found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while fetching the title";
        }
    }

    public List<ProductDto> getAdProductItems(String url) {
        List<ProductDto> productList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements liBoxElements = document.select(".list-box .li_box");

            for (Element liBox : liBoxElements) {
                ProductDto productDto = new ProductDto();

                // 이미지 요소
                Element imgBlockElement = liBox.selectFirst(".img-block img");
                if (imgBlockElement != null) {
                    String imageUrl = imgBlockElement.attr("src");
                    productDto.setImageUrl(imageUrl);
                } else {
                    log.warn("Thumbnail area not found for product item: {}", liBox);
                }

                // 정보 요소(브랜드, 상품명, 상품 링크)
                Element articleInfoElement = liBox.selectFirst(".article_info");
                Element brand = articleInfoElement.selectFirst(".item_title a");
                Element title = articleInfoElement.selectFirst(".list_info a");
                productDto.setProductBrand(brand.text());
                productDto.setProductName(title.text());
                productDto.setProductLink(title.attr("href"));

                // 가격 요소 선택
                Element price = articleInfoElement.selectFirst(".price");
                productDto.setPrice(price.ownText().trim());

                // 모든 정보가 채워진 경우에만 리스트에 추가
                if (isProductInfoComplete(productDto)) {
                    productList.add(productDto);
                    log.info("Product DTO: {}", productDto);
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while fetching ad product items", e);
        }
        return productList;
    }

    // 모든 필드가 채워져 있는지 확인하는 메서드
    private boolean isProductInfoComplete(ProductDto productDto) {
        return productDto.getImageUrl() != null && !productDto.getImageUrl().isEmpty()
                && productDto.getProductBrand() != null && !productDto.getProductBrand().isEmpty()
                && productDto.getProductName() != null && !productDto.getProductName().isEmpty()
                && productDto.getProductLink() != null && !productDto.getProductLink().isEmpty()
                && productDto.getPrice() != null && !productDto.getPrice().isEmpty();
    }
}
