package com.talos.javatraining.lesson5;

import com.talos.javatraining.lesson5.data.OrderData;
import com.talos.javatraining.lesson5.data.OrderEntryData;
import com.talos.javatraining.lesson5.data.ProductData;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * This implementation uses a traditional for block. Since there are some parts with similar code we created some private methods to reuse that code.
 * However, we need to refactor this class to use streams instead. In that case the private methods are not longer necessary.
 */
public class MainImpl implements Main
{
	@Override
//	public boolean isThereAnOrderWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
//	{
//		boolean result = false;
//		for (OrderData order : orders)
//		{
//			if (order.getSubTotal().getValue().compareTo(price) < 0)
//			{
//				result = true;
//				break;
//			}
//		}
//		return result;
//	}

	public boolean isThereAnOrderWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		return orders.stream()
				.map( order -> order.getSubTotal().getValue())
				.anyMatch(val -> val.compareTo(price) < 0);
	}

	@Override
//	public boolean areThereAllOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
//	{
//		boolean result = true;
//		for (OrderData order : orders)
//		{
//			if (order.getSubTotal().getValue().compareTo(price) <= 0)
//			{
//				// If there is only one lower or equal than the given price, it is false
//				result = false;
//				break;
//			}
//		}
//		return result;
//	}

	public boolean areThereAllOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		return orders.stream()
				.map(order -> order.getSubTotal().getValue())
				.allMatch(val -> val.compareTo(price) > 0);
	}

	@Override
//	public BigDecimal getLowestOrderPrice(List<OrderData> orders)
//	{
//		BigDecimal result = null;
//		for (OrderData order : orders)
//		{
//			BigDecimal currentPrice = order.getSubTotal().getValue();
//			if (result == null || currentPrice.compareTo(result) < 0)
//			{
//				result = currentPrice;
//			}
//		}
//		return result;
//	}

	public BigDecimal getLowestOrderPrice(List<OrderData> orders)
	{
		return orders.stream()
				.map(order -> order.getSubTotal().getValue())
				.min(BigDecimal::compareTo)
				.get();
	}

	@Override
//	public BigDecimal getHighestOrderPrice(List<OrderData> orders)
//	{
//		BigDecimal result = null;
//		for (OrderData order : orders)
//		{
//			BigDecimal currentPrice = order.getSubTotal().getValue();
//			if (result == null || currentPrice.compareTo(result) > 0)
//			{
//				result = currentPrice;
//			}
//		}
//		return result;
//	}

	public BigDecimal getHighestOrderPrice(List<OrderData> orders)
	{
		return orders.stream()
				.map(order -> order.getSubTotal().getValue())
				.max(BigDecimal::compareTo)
				.orElse(BigDecimal.ZERO);
	}

	@Override
//	public long countOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
//	{
//		long count = 0;
//		for (OrderData order : orders)
//		{
//			if (order.getSubTotal().getValue().compareTo(price) > 0)
//			{
//				count++;
//			}
//		}
//		return count;
//	}

	public long countOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		return orders.stream()
				.map(order -> order.getSubTotal().getValue())
				.filter(val -> val.compareTo(price) > 0)
				.count();
	}

	@Override
//	public BigDecimal sumOrderPricesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
//	{
//		BigDecimal total = BigDecimal.ZERO;
//		for (OrderData order : orders)
//		{
//			BigDecimal currentPrice = order.getSubTotal().getValue();
//			if (currentPrice.compareTo(price) < 0)
//			{
//				total = total.add(currentPrice);
//			}
//		}
//		return total;
//	}

	public BigDecimal sumOrderPricesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		return orders.stream()
				.map(order -> order.getSubTotal().getValue())
				.filter(val -> val.compareTo(price) < 0)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	@Override
//	public long countAllEntriesWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
//	{
//		long count = 0;
//		for (OrderData order : orders)
//		{
//			for (OrderEntryData entry : order.getEntries())
//			{
//				if (entry.getBasePrice().getValue().compareTo(price) > 0)
//				{
//					count++;
//				}
//			}
//		}
//		return count;
//	}

	public long countAllEntriesWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		return orders.stream()
				.map(order -> order.getEntries())
				.flatMap(List::stream)
				.map(entry -> entry.getBasePrice().getValue())
				.filter(val -> val.compareTo(price) > 0)
				.count();
	}

	@Override
//	public long countEntriesWithProduct(List<OrderData> orders, String productCode)
//	{
//		long count = 0;
//		for (OrderData order : orders)
//		{
//			for (OrderEntryData entry : order.getEntries())
//			{
//				ProductData productData = entry.getProduct();
//				if (productData.getCode().equals(productCode))
//				{
//					count++;
//				}
//			}
//		}
//		return count;
//	}

	public long countEntriesWithProduct(List<OrderData> orders, String productCode)
	{
		return orders.stream()
				.map(order -> order.getEntries())
				.flatMap(List::stream)
				.filter(entry -> entry.getProduct().getCode().equals(productCode))
				.count();
	}

	@Override
//	public long sumQuantitiesForProduct(List<OrderData> orders, String productCode)
//	{
//		long total = 0;
//		for (OrderData order : orders)
//		{
//			for (OrderEntryData entry : order.getEntries())
//			{
//				ProductData productData = entry.getProduct();
//				if (productData.getCode().equals(productCode))
//				{
//					total += getQty(entry);
//				}
//			}
//		}
//		return total;
//	}

	public long sumQuantitiesForProduct(List<OrderData> orders, String productCode)
	{
		return orders.stream()
				.map(order -> order.getEntries())
				.flatMap(List::stream)
				.filter(entry -> entry.getProduct().getCode().equals(productCode))
				.map(entry -> entry.getQuantity())
				.reduce(Long::sum)
				.orElse(0L);
	}

	@Override
//	public long getMaxQuantityOrderedForProduct(List<OrderData> orders, String productCode)
//	{
//		long max = 0;
//		for (OrderData order : orders)
//		{
//			for (OrderEntryData entry : order.getEntries())
//			{
//				ProductData productData = entry.getProduct();
//				if (productData.getCode().equals(productCode))
//				{
//					long quantity = getQty(entry);
//					if (quantity > max)
//					{
//						max = quantity;
//					}
//				}
//			}
//		}
//		return max;
//	}

	public long getMaxQuantityOrderedForProduct(List<OrderData> orders, String productCode)
	{
		return orders.stream()
				.map(order -> order.getEntries())
				.flatMap(List::stream)
				.filter(entry -> entry.getProduct().getCode().equals(productCode))
				.map(entry -> entry.getQuantity())
				.max(Long::compareTo)
				.orElse(0L);
	}

//	private long getQty(OrderEntryData entry)
//	{
//		return Optional.ofNullable(entry.getQuantity()).orElse(0L);
//	}
}
