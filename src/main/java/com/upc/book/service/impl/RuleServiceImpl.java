package com.upc.book.service.impl;

import com.upc.book.rule.*;
import com.upc.book.entity.Order;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import com.upc.book.service.OrderService;
import com.upc.book.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by florian on 16/4/11.
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private OrderService orderService;

    //从订单表内解析出所有的项ID列表
    public OrderList createOrderList() throws BookException {
        OrderList orderList = new OrderList();
        List<Item> items = orderList.getItems();

        List<Order> allOrders = orderService.getAllOrders();
        for (Order order : allOrders) {
            Map<String, Integer> bookCountMap = Cart.getBookCountMap(order.getCart());
            Item item = new Item(bookCountMap.keySet());
            items.add(item);
        }

        return orderList;
    }

    @Override
    public List<ItemCollection> getItemCollectionList(OrderList orderList, int minSupportValue, int maxItemCollectionCount) throws BookException {
        List<ItemCollection> itemCollectionList = new ArrayList<>();

        //初始化1项集
        ItemCollection itemCollection = new ItemCollection(orderList);

        //项集为空，或者达到最大项集个数，停止计算
        while (!itemCollection.isEmpty() && itemCollectionList.size() <= maxItemCollectionCount) {
            //对项集计数
            itemCollection.countItem(orderList);

            //移除支持度不足的项
            itemCollection = ItemCollection.filter(itemCollection, minSupportValue);

            //添加到项集列表
            itemCollectionList.add(itemCollection);

            //连接操作，产生候选项集
            ItemCollection joinedItemCollection = ItemCollection.join(itemCollection);

            //对候选项集进行剪枝操作
            itemCollection = ItemCollection.trim(joinedItemCollection, itemCollection);
        }


        return itemCollectionList;
    }

    @Override
    public List<Rule> generateRules(List<ItemCollection> itemCollectionList, double minConfidence) throws BookException {
        List<Rule> rules = new ArrayList<>();

        //合并项集列表的所有项集到一个项集内，用于查询支持度
        Map<Item, Integer> allItemCountMap = ItemCollection.merge(itemCollectionList).getItemCountMap();

        //处理所有项集
        for (ItemCollection itemCollection : itemCollectionList) {
            Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();

            //处理每一项
            Set<Item> items = itemCountMap.keySet();
            for (Item item : items) {
                //获取项的所有二分子项对
                List<ItemPair> subItemPairs = Item.getSubItemPairs(item);
                for (ItemPair itemPair : subItemPairs) {
                    Item left = itemPair.getLeft();
                    Item right = itemPair.getRight();

                    //查询子项的支持度
                    Integer leftSupportValue = allItemCountMap.get(left);
                    Integer rightSupportValue = allItemCountMap.get(right);

                    //计算可信度
                    Integer totalSupportValue = itemCountMap.get(item);
                    double leftConfidence = totalSupportValue / (double) leftSupportValue;
                    double rightConfidence = totalSupportValue / (double) rightSupportValue;

                    //生成规则
                    if (leftConfidence > minConfidence) {
                        rules.add(new Rule(left, right, leftConfidence));
                    }
                    if (rightConfidence > minConfidence) {
                        rules.add(new Rule(right, left, rightConfidence));
                    }
                }
            }
        }

        return rules;
    }
}
