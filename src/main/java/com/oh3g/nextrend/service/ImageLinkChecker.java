package com.oh3g.nextrend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ImageLinkChecker {
    public void downloadHtmlFile(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);
        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream("crawlingResult.html");
        int b;
        while ((b = is.read()) != -1) {
            fos.write(b);
        }
        fos.close();
    }
}
