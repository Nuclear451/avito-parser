package com.opportunity.avitoparser.reposiroty;

import com.opportunity.avitoparser.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {}
