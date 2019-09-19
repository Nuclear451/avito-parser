package com.opportunity.avitoparser.service;

import com.opportunity.avitoparser.model.Item;
import com.opportunity.avitoparser.utils.AvitoParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ParserService {

    @Value("${parser.avito.url}")
    private String avitoUrl;

    @Autowired
    private AvitoParser avitoParser;

    public List<Item> parse(String section, Map<String, String> searchParams) throws Exception{
        Document doc = Jsoup.connect(buildUrl(section, searchParams)).get();
        Elements elements = doc.select(".item__line");

        List<Item> items = new ArrayList<>();

        for (Element element : elements) {
            try {
                items.add(avitoParser.parseHtml(element));
            } catch (Exception e) {
                System.out.println("Cannot parse announcement");
            }
        }

        return items;
    }


    private String buildUrl(String section, Map<String, String> searchParams) {
        StringBuilder url = new StringBuilder(avitoUrl);
        url.append(section);
        if (!CollectionUtils.isEmpty(searchParams)) {
            url.append("?");

            for (String key : searchParams.keySet()) {
                url.append(key).append("=").append(searchParams.get(key));
                url.append("&");
            }
        }

        return url.toString();
    }
}
