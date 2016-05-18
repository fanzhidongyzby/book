package com.upc.book.controller;

import com.upc.book.exception.BookException;
import com.upc.book.rule.ItemCollection;
import com.upc.book.rule.OrderList;
import com.upc.book.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//生成规则
public class RuleController extends Controller {
  private static Logger LOG = LoggerFactory.getLogger(RuleController.class);

  @RequestMapping(method = RequestMethod.GET)
  public String getAllRules(@RequestParam(value = "compress", required = false, defaultValue = "false") boolean isTransactionCompress) throws BookException {
    if (isTransactionCompress) {
      LOG.debug("启用事务压缩算法生成关联规则 ...");
    } else {
      LOG.debug("生成关联规则 ...");
    }

    int minSupportValue = 2;
    int maxItemCollectionCount = 10;
    double minConfidence = 0.7;

    OrderList orderList = ruleService.createOrderList();
    List<ItemCollection> itemCollectionList = ruleService.getItemCollectionList(orderList, minSupportValue, maxItemCollectionCount, isTransactionCompress);
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
