package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.jsoup.ProductDto;
import com.oh3g.nextrend.service.HtmlParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final HtmlParserService htmlParserService;

    @GetMapping
    public String getProduct(Model model) throws Exception {
        // musinsa URL (임시)
        String url = "https://www.musinsa.com/search/musinsa/goods?q=%EB%B0%98%ED%8C%94&list_kind=small&sortCode=pop&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&d_cat_cd=&attribute=&plusDeliveryYn=";

        List<ProductDto> productList = htmlParserService.getAdProductItems(url);
        model.addAttribute("productList", productList);

        return "index";
    }
}
