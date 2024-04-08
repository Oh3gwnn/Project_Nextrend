package com.oh3g.nextrend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public String getAdProductItems(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements productItems = document.select("div.product_item__MDtDF");
            StringBuilder result = new StringBuilder();

            for (Element productItem : productItems) {
                // 이미지 요소
                Element productInner = productItem.selectFirst(".product_inner__gr8QR");

                Element thumbnailArea = productInner.selectFirst(".product_img_area__cUrko");
                String thumbnailUrl = thumbnailArea.attr("src");

                // 정보 요소
                Element infoArea = productInner.selectFirst(".product_info_area__xxCTi");
                Element link = infoArea.selectFirst(".product_link__TrAac");
                String productLink = link.attr("href");
                String productText = link.text();

                // 가격 요소 선택
                Element price = infoArea.selectFirst(".price");
                String priceText = price.text();

                // 결과에 각 요소들의 정보를 추가합니다.
                result.append("Image URL: ").append(thumbnailUrl).append("<br>");
                result.append("productText: ").append(productText).append("<br>");
                result.append("Product Link: ").append(productLink).append("<br>");
                result.append("Price: ").append(priceText).append("<br>");
                result.append("----------------------------------------<br>");
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while fetching ad product items";
        }
    }
}
