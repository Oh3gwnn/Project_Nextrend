package com.oh3g.nextrend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
}
