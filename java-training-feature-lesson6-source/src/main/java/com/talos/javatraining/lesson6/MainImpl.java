package com.talos.javatraining.lesson6;

import com.talos.javatraining.lesson6.data.OrderData;
import com.talos.javatraining.lesson6.data.OrderEntryData;
import com.talos.javatraining.lesson6.data.ProductData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;
import static java.util.Comparator.comparing;
import static org.apache.commons.collections4.ComparatorUtils.chainedComparator;


public class MainImpl implements Main
{
	private static final Comparator<Map.Entry<ProductData, Long>> COMPARATOR = chainedComparator(comparing(Map.Entry::getValue), comparing(e -> e.getKey().getCode()));
	private static final String PIPELINE = "|";

	@Override
	public List<OrderData> getOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price, long limit)
	{
		List<OrderData> result = new ArrayList<>();
		int i = 0;
		for (OrderData order : orders)
		{
			if (order.getSubTotal().getValue().compareTo(price) > 0)
			{
				if (i++ < limit)
				{
					result.add(order);
				}
				else
				{
					break;
				}
			}
		}
		return result;
	}

	@Override
	public List<OrderData> getOrdersThatContainsAProduct(List<OrderData> orders, String productCode)
	{
		List<OrderData> result = new ArrayList<>();
		for (OrderData order : orders)
		{
			boolean contains = false;
			for (OrderEntryData entry : order.getEntries())
			{
				if (productCode.equals(entry.getProduct().getCode()))
				{
					contains = true;
					break;
				}
			}
			if (contains)
			{
				result.add(order);
			}
		}
		return result;
	}

	@Override
	public Set<String> getOrderCodes(List<OrderData> orders)
	{
		// In order to pass the test, you have to use a TreeSet
		Set<String> result = new TreeSet<>();
		for (OrderData order : orders)
		{
			result.add(order.getCode());
		}
		return result;
	}

	@Override
	public List<OrderEntryData> getEntriesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		List<OrderEntryData> result = new ArrayList<>();
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				if (entry.getBasePrice().getValue().compareTo(price) < 0)
				{
					result.add(entry);
				}
			}
		}
		return result;
	}

	@Override
	public Map<Integer, OrderEntryData> getEntriesAsMap(List<OrderData> orders, String orderCode)
	{
		OrderData selected = null;
		for (OrderData order : orders)
		{
			if (order.getCode().equals(orderCode))
			{
				selected = order;
				break;
			}
		}
		if (selected == null)
		{
			return Collections.EMPTY_MAP;
		}
		Map<Integer, OrderEntryData> entries = new HashMap<>();
		for (OrderEntryData entry : selected.getEntries())
		{
			entries.put(entry.getEntryNumber(), entry);
		}
		return entries;
	}

	@Override
	public String getEntriesAsString(List<OrderData> orders, String orderCode)
	{
		OrderData selected = null;
		for (OrderData order : orders)
		{
			if (order.getCode().equals(orderCode))
			{
				selected = order;
				break;
			}
		}
		if (selected == null)
		{
			return StringUtils.EMPTY;
		}
		StringBuilder builder = new StringBuilder();
		for (OrderEntryData entry : selected.getEntries())
		{
			builder.append(PIPELINE).append(entry.toString());
		}
		String result = builder.toString();
		return result.isEmpty() ? result : result.substring(1);
	}

	@Override
	public Map<String, List<ProductData>> getProductsByOrderCode(List<OrderData> orders)
	{
		Map<String, List<ProductData>> result = new HashMap<>();
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				result.compute(order.getCode(), (k, v) -> {
					if (v == null)
					{
						v = new ArrayList<>();
					}
					v.add(entry.getProduct());
					return v;
				});
			}
		}
		return result;
	}

	@Override
	public List<ProductData> getBestSellingProducts(List<OrderData> orders, long limit)
	{
		Map<ProductData, Long> counter = new HashMap<>();
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				counter.compute(entry.getProduct(), (k, v) -> {
					if (v == null)
					{
						v = 0L;
					}
					return v + entry.getQuantity();
				});
			}
		}
		Set<Map.Entry<ProductData, Long>> finalList = new TreeSet<>(COMPARATOR.reversed());
		finalList.addAll(counter.entrySet());
		List<ProductData> result = new ArrayList<>();
		int i = 0;
		for (Map.Entry<ProductData, Long> entry : finalList)
		{
			if (i++ < limit)
			{
				result.add(entry.getKey());
			}
			else
			{
				break;
			}
		}
		return result;
	}
}
