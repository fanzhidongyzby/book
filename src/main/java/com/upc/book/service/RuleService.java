package com.upc.book.service;

import com.upc.book.rule.ItemCollection;
import com.upc.book.rule.OrderList;
import com.upc.book.exception.BookException;
import com.upc.book.rule.Rule;

import java.util.List;

public interface RuleService {

    OrderList createOrderList() throws BookException;

    List<ItemCollection> getItemCollectionList(OrderList orderList, int minSupportValue, int maxItemCollectionCount) throws BookException;

    List<Rule> generateRules(List<ItemCollection> itemCollectionList, double minConfidence) throws BookException;

}
