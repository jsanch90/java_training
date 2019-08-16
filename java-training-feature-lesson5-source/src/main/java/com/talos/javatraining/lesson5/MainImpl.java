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
	public boolean isThereAnOrderWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		boolean result = false;
		for (OrderData order : orders)
		{
			if (order.getSubTotal().getValue().compareTo(price) < 0)
			{
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean areThereAllOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		boolean result = true;
		for (OrderData order : orders)
		{
			if (order.getSubTotal().getValue().compareTo(price) <= 0)
			{
				// If there is only one lower or equal than the given price, it is false
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public BigDecimal getLowestOrderPrice(List<OrderData> orders)
	{
		BigDecimal result = null;
		for (OrderData order : orders)
		{
			BigDecimal currentPrice = order.getSubTotal().getValue();
			if (result == null || currentPrice.compareTo(result) < 0)
			{
				result = currentPrice;
			}
		}
		return result;
	}

	@Override
	public BigDecimal getHighestOrderPrice(List<OrderData> orders)
	{
		BigDecimal result = null;
		for (OrderData order : orders)
		{
			BigDecimal currentPrice = order.getSubTotal().getValue();
			if (result == null || currentPrice.compareTo(result) > 0)
			{
				result = currentPrice;
			}
		}
		return result;
	}

	@Override
	public long countOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		long count = 0;
		for (OrderData order : orders)
		{
			if (order.getSubTotal().getValue().compareTo(price) > 0)
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public BigDecimal sumOrderPricesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		BigDecimal total = BigDecimal.ZERO;
		for (OrderData order : orders)
		{
			BigDecimal currentPrice = order.getSubTotal().getValue();
			if (currentPrice.compareTo(price) < 0)
			{
				total = total.add(currentPrice);
			}
		}
		return total;
	}

	@Override
	public long countAllEntriesWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		long count = 0;
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				if (entry.getBasePrice().getValue().compareTo(price) > 0)
				{
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public long countEntriesWithProduct(List<OrderData> orders, String productCode)
	{
		long count = 0;
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public long sumQuantitiesForProduct(List<OrderData> orders, String productCode)
	{
		long total = 0;
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					total += getQty(entry);
				}
			}
		}
		return total;
	}

	@Override
	public long getMaxQuantityOrderedForProduct(List<OrderData> orders, String productCode)
	{
		long max = 0;
		for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					long quantity = getQty(entry);
					if (quantity > max)
					{
						max = quantity;
					}
				}
			}
		}
		return max;
	}

	private long getQty(OrderEntryData entry)
	{
		return Optional.ofNullable(entry.getQuantity()).orElse(0L);
	}
}
