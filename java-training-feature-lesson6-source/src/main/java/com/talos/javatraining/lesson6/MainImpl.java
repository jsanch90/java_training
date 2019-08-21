package com.talos.javatraining.lesson6;

import com.talos.javatraining.lesson6.data.OrderData;
import com.talos.javatraining.lesson6.data.OrderEntryData;
import com.talos.javatraining.lesson6.data.ProductData;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.apache.commons.collections4.ComparatorUtils.chainedComparator;


public class MainImpl implements Main {
    private static final Comparator<Map.Entry<ProductData, Long>> COMPARATOR = chainedComparator(comparing(Map.Entry::getValue), comparing(e -> e.getKey().getCode()));
    private static final String PIPELINE = "|";

    @Override
    public List<OrderData> getOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price, long limit) {
        return orders.stream()
                .filter(order -> order.getSubTotal().getValue().compareTo(price) > 0)
                .limit(limit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<OrderData> getOrdersThatContainsAProduct(List<OrderData> orders, String productCode) {
        return orders.stream()
                .filter(order -> order.getEntries().stream()
                        .anyMatch(entry -> entry.getProduct().getCode().equals(productCode)))
                .collect(Collectors.toCollection(ArrayList::new));


    }

    @Override
    public Set<String> getOrderCodes(List<OrderData> orders) {
        return orders.stream()
                .map(OrderData::getCode)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public List<OrderEntryData> getEntriesWithPriceLowerThan(List<OrderData> orders, BigDecimal price) {
        return orders.stream()
                .map(OrderData::getEntries)
                .flatMap(List::stream)
                .filter(entry -> entry.getBasePrice().getValue().compareTo(price) < 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Map<Integer, OrderEntryData> getEntriesAsMap(List<OrderData> orders, String orderCode) {

        return orders.stream()
                .filter(order -> order.getCode().equals(orderCode))
                .map(OrderData::getEntries)
                .flatMap(List::stream)
                .collect(Collectors.toMap(OrderEntryData::getEntryNumber, orderEntry -> orderEntry));
    }

    @Override
    public String getEntriesAsString(List<OrderData> orders, String orderCode) {

        return orders.stream()
                .filter(order -> order.getCode().equals(orderCode))
                .map(OrderData::getEntries)
                .flatMap(List::stream)
                .map(OrderEntryData::toString)
                .collect(Collectors.joining(PIPELINE));
    }

    @Override
    public Map<String, List<ProductData>> getProductsByOrderCode(List<OrderData> orders) {

        return orders.stream()
                .collect(Collectors.toMap(OrderData::getCode,
                        orderAux -> orderAux.getEntries()
                                .stream()
                                .map(OrderEntryData::getProduct)
                                .collect(Collectors.toCollection(ArrayList::new))));
    }

    @Override
    public List<ProductData> getBestSellingProducts(List<OrderData> orders, long limit) {

        Map<ProductData, Long> res = orders.stream()
                .map(OrderData::getEntries)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(OrderEntryData::getProduct, Collectors.summingLong(OrderEntryData::getQuantity)));

        Set<Map.Entry<ProductData, Long>> finalList = new TreeSet<>(COMPARATOR.reversed());
        finalList.addAll(res.entrySet());

        return finalList.stream()
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
