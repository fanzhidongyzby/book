package com.upc.book.controller;

import com.upc.book.entity.Association;
import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import com.upc.book.rule.ItemCollection;
import com.upc.book.rule.OrderList;
import com.upc.book.rule.Rule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuxin on 16/3/16.
 */
@RestController
@RequestMapping("/api/rules")
public class RuleController extends Controller {
  @RequestMapping(method = RequestMethod.GET)
  public List<Rule> getAllRules() throws BookException {
    int minSupportValue = configuration.getInt("book.support.value.min");
    int maxItemCollectionCount = configuration.getInt("book.item.collection.count.max");
    double minConfidence = configuration.getDouble("book.confidence.min");

    OrderList orderList = ruleService.createOrderList();
    List<ItemCollection> itemCollectionList = ruleService.getItemCollectionList(orderList, minSupportValue, maxItemCollectionCount);
    List<Rule> rules = ruleService.generateRules(itemCollectionList, minConfidence);

    System.out.println("=====生成规则=====");
    for (Rule rule : rules) {
      System.out.println(rule.prettyFormat());
    }
    System.out.println("=================");

    associationService.saveRules(rules);

    return rules;
  }

}
