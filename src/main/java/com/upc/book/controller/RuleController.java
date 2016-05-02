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
  public String getAllRules() throws BookException {
    int minSupportValue = 2;
    int maxItemCollectionCount = 10;
    double minConfidence = 0.7;

    OrderList orderList = ruleService.createOrderList();
    List<ItemCollection> itemCollectionList = ruleService.getItemCollectionList(orderList, minSupportValue, maxItemCollectionCount);
    List<Rule> rules = ruleService.generateRules(itemCollectionList, minConfidence);

    System.out.println("=====生成规则=====");
    StringBuffer buffer = new StringBuffer();
    for (Rule rule : rules) {
      String ruleString = rule.prettyFormat();
      buffer.append(ruleString + "\n");
      System.out.println(ruleString);
    }
    System.out.println("=================");

    associationService.saveRules(rules);

    return buffer.toString();
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public boolean clearAllRules() throws BookException {

    associationService.clearAssociation();

    return true;
  }

}
