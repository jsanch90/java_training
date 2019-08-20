package com.talos.javatraining.lesson6;

import com.talos.javatraining.lesson6.data.OrderData;
import com.talos.javatraining.lesson6.data.OrderEntryData;
import com.talos.javatraining.lesson6.data.ProductData;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface Main
{
	/**
	 * Filters the orders that have a price greater than the given price. The maximum amount of orders is defined by the limit argument.
	 *
	 * @param orders the original orders
	 * @param price  the price
	 * @param limit  the maximum amount of orders to retrieve
	 * @return the filtered orders
	 */
	List<OrderData> getOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price, long limit);

	/**
	 * Filters the orders that contains an entry with the given product code.
	 *
	 * @param orders      the orders
	 * @param productCode the product code
	 * @return the filtered orders
	 */
	List<OrderData> getOrdersThatContainsAProduct(List<OrderData> orders, String productCode);

	/**
	 * Gets the order codes
	 *
	 * @param orders the orders
	 * @return the order codes
	 */
	Set<String> getOrderCodes(List<OrderData> orders);

	/**
	 * Retrieves all the order entries that have a baseprice lower than the given price.
	 *
	 * @param orders the orders
	 * @param price  the price
	 * @return the entries
	 */
	List<OrderEntryData> getEntriesWithPriceLowerThan(List<OrderData> orders, BigDecimal price);

	/**
	 * Retrieves the entries of the given order code. The entries are represented as a map which has as key the entry number and as value the OrderEntryData.
	 *
	 * @param orders    the orders
	 * @param orderCode the order code
	 * @return the order entries
	 */
	Map<Integer, OrderEntryData> getEntriesAsMap(List<OrderData> orders, String orderCode);

	/**
	 * Retrieves the entries of the given order code. The entries are delimited by a pipeline.
	 *
	 * @param orders    the orders
	 * @param orderCode the order code
	 * @return the order entries
	 */
	String getEntriesAsString(List<OrderData> orders, String orderCode);

	/**
	 * Retrieves the products grouped by the order code. The map returned has as key the order code and as value the products.
	 *
	 * @param orders the orders
	 * @return the map returned has as key the order code and as value the products.
	 */
	Map<String, List<ProductData>> getProductsByOrderCode(List<OrderData> orders);

	/**
	 * Retrieves the best selling products limited to the limit argument.
	 *
	 * @param orders the orders
	 * @param limit  the maximum amount of orders to retrieve
	 * @return the products sorted from best selling to worst selling
	 */
	List<ProductData> getBestSellingProducts(List<OrderData> orders, long limit);
}
