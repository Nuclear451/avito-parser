package com.opportunity.avitoparser.utils;

import com.opportunity.avitoparser.model.Item;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AvitoParser {

    @Value("${parser.avito.url}")
    private String avitoUrl;

    public Item parseHtml(Element element) {
        String link = element.select(".js-item-slider.item-slider").get(0).attr("href");
        String title = element.select(".item-description-title-link").get(0).attr("title");
        String photoLink = element.select(".large-picture-img").get(0).attr("src");
        String price = element.select(".price").get(0).attr("content");
        String publishedDate = element.select(".js-item-date").get(0).text();

        Item item = new Item();
        item.setLink(avitoUrl.concat(link));
        item.setTitle(title);
        item.setPhotoLink(photoLink);
        item.setPrice(price);
        item.setPublishedDate(publishedDate);
        item.setSavedDate(LocalDate.now());
        item.setItemType("laptop");

        return item;
    }
}
