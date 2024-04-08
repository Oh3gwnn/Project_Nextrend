package com.oh3g.nextrend.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ImageLinkChecker {
    public static void main(String[] args) throws Exception {
        String targetUrl = "https://www.musinsa.com/categories/item/001005";
        URL url = new URL(targetUrl);

        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream("text.html");
        int b;
        while ((b = is.read()) != -1) {
            fos.write(b);
        }
        fos.close();
    }
}
