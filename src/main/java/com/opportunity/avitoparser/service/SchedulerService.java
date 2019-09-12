package com.opportunity.avitoparser.service;

import com.opportunity.avitoparser.model.Item;
import com.opportunity.avitoparser.reposiroty.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerService {

    @Autowired
    private ParserService parserService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    @Qualifier("usedPrivateFiveHundred")
    private Map<String, String> laptopsSearchParams;

    @Value("${avito.laptop.section}")
    private String section;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void checkLaptopsAnnounces() throws Exception {
        List<Item> items = parserService.parse(section, laptopsSearchParams);
        for (Item item : items) {
            if (! itemRepository.findById(item.getLink()).isPresent()) {
                itemRepository.save(item);
            }
        }
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void cleanTableFromOldRows() throws Exception {
        Iterable<Item> all = itemRepository.findAll();
        LocalDate now = LocalDate.now();
        for (Item item : all) {
            if (ChronoUnit.DAYS.between(item.getSavedDate(), now) > 30 ){
                itemRepository.delete(item);
            }
        }
    }

}
