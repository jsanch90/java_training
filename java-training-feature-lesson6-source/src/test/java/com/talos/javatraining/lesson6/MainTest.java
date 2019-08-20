package com.talos.javatraining.lesson6;

import com.talos.javatraining.lesson6.data.OrderData;
import com.talos.javatraining.lesson6.data.OrderEntryData;
import com.talos.javatraining.lesson6.data.PriceData;
import com.talos.javatraining.lesson6.data.ProductData;
import com.talos.javatraining.lesson6.data.builders.OrderBuilder;
import com.talos.javatraining.lesson6.data.builders.OrderEntryBuilder;
import com.talos.javatraining.lesson6.data.builders.ProductBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.BeforeClass;
import org.junit.Test;
import static java.util.function.Function.identity;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@SuppressWarnings("Duplicates")
public class MainTest
{
	private static final String ORDER_1 = "946a";
	private static final String ORDER_2 = "595a";
	private static final String ORDER_3 = "91ed";
	private static final String ORDER_4 = "eae3";
	private static final String ORDER_5 = "bbb7";
	private static final String ORDER_6 = "cbf5";
	private static final String ORDER_7 = "4bc2";
	private static final String ORDER_8 = "a2f3";
	private static final String ORDER_9 = "d8b3";
	private static final String ORDER_10 = "00b1";
	private static final String PRODUCT_1 = "bed3";
	private static final String PRODUCT_2 = "9951";
	private static final String PRODUCT_3 = "a867";
	private static final String PRODUCT_4 = "9afc";
	private static final String PRODUCT_5 = "9455";
	private static final String PRODUCT_6 = "8dc0";
	private static final String PRODUCT_7 = "9dc3";
	private static final String PRODUCT_8 = "967b";
	private static final String PRODUCT_9 = "85ea";
	private static final String PRODUCT_10 = "a8e2";
	private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
	private static final BigDecimal FIVE_HUNDRED = BigDecimal.valueOf(500);
	private static final BigDecimal THREE_THOUSAND = BigDecimal.valueOf(3000);
	private static final Long FOUR = 4L;
	public static final String CODE = "code";
	private static List<OrderData> ORDERS;
	private Main main = new MainImpl();

	@BeforeClass
	public static void setupClass()
	{
		ORDERS = createOrders();
	}

	@Test
	public void test()
	{
		IntStream.range(0, 10).mapToObj(i -> UUID.randomUUID()).forEach(System.out::println);
	}

	@Test
	public void testGetOrdersWithPriceGreaterThan500()
	{
		List<OrderData> result = main.getOrdersWithPriceGreaterThan(ORDERS, FIVE_HUNDRED, FOUR);

		assertNotNull(result);
		assertThat(result, hasSize(FOUR.intValue()));
		List<BigDecimal> values = result.stream().map(OrderData::getSubTotal).map(PriceData::getValue).collect(Collectors.toList());
		assertThat(values, everyItem(greaterThan(FIVE_HUNDRED)));
	}

	@Test
	public void testGetOrdersWithPriceGreaterThan3000()
	{
		List<OrderData> result = main.getOrdersWithPriceGreaterThan(ORDERS, THREE_THOUSAND, FOUR);

		assertNotNull(result);
		assertThat(result, hasSize(2));
		List<BigDecimal> values = result.stream().map(OrderData::getSubTotal).map(PriceData::getValue).collect(Collectors.toList());
		assertThat(values, everyItem(greaterThan(THREE_THOUSAND)));
	}

	@Test
	public void testGetOrdersThatContainsAProduct1()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_1);

		assertNotNull(result);
		assertThat(result, hasSize(4));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_1))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_4))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_8))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_10))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct2()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_2);

		assertNotNull(result);
		assertThat(result, hasSize(4));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_2))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_4))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_7))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_10))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct3()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_3);

		assertNotNull(result);
		assertThat(result, hasSize(5));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_3))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_4))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_5))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_6))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_9))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct4()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_4);

		assertNotNull(result);
		assertThat(result, hasSize(0));
	}

	@Test
	public void testGetOrdersThatContainsAProduct5()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_5);

		assertNotNull(result);
		assertThat(result, hasSize(3));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_5))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_9))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_10))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct6()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_6);

		assertNotNull(result);
		assertThat(result, hasSize(4));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_3))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_4))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_5))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_8))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct7()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_7);

		assertNotNull(result);
		assertThat(result, hasSize(1));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_7))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct8()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_8);

		assertNotNull(result);
		assertThat(result, hasSize(2));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_2))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_10))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct9()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_9);

		assertNotNull(result);
		assertThat(result, hasSize(3));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_5))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_8))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_9))));
	}

	@Test
	public void testGetOrdersThatContainsAProduct10()
	{
		List<OrderData> result = main.getOrdersThatContainsAProduct(ORDERS, PRODUCT_10);

		assertNotNull(result);
		assertThat(result, hasSize(2));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_3))));
		assertThat(result, hasItem(hasProperty(CODE, equalTo(ORDER_9))));
	}

	@Test
	public void testGetOrderCodes()
	{
		Set<String> result = main.getOrderCodes(ORDERS);

		assertNotNull(result);
		assertThat(result, hasSize(10));
		assertTrue("The Set implementation should be TreeSet", result instanceof TreeSet);
		assertThat(result, hasItems(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5, ORDER_6, ORDER_7, ORDER_8, ORDER_9, ORDER_10));
	}

	@Test
	public void testGetEntriesWithPriceLowerThan100()
	{
		List<OrderEntryData> result = main.getEntriesWithPriceLowerThan(ORDERS, ONE_HUNDRED);

		assertNotNull(result);
		assertThat(result, hasSize(8));
	}

	@Test
	public void testGetEntriesWithPriceLowerThan500()
	{
		List<OrderEntryData> result = main.getEntriesWithPriceLowerThan(ORDERS, FIVE_HUNDRED);

		assertNotNull(result);
		assertThat(result, hasSize(19));
	}

	@Test
	public void testGetEntriesAsMapOrder0()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, "none");

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetEntriesAsMapOrder1()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_1);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertThat(result.keySet(), hasItems(1));
	}

	@Test
	public void testGetEntriesAsMapOrder2()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_2);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertThat(result.keySet(), hasItems(1, 2));
	}

	@Test
	public void testGetEntriesAsMapOrder3()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_3);

		assertNotNull(result);
		assertEquals(3, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3));
	}

	@Test
	public void testGetEntriesAsMapOrder4()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_4);

		assertNotNull(result);
		assertEquals(4, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3, 4));
	}

	@Test
	public void testGetEntriesAsMapOrder5()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_5);

		assertNotNull(result);
		assertEquals(4, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3, 4));
	}

	@Test
	public void testGetEntriesAsMapOrder6()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_6);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertThat(result.keySet(), hasItems(1));
	}

	@Test
	public void testGetEntriesAsMapOrder7()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_7);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertThat(result.keySet(), hasItems(1, 2));
	}

	@Test
	public void testGetEntriesAsMapOrder8()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_8);

		assertNotNull(result);
		assertEquals(3, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3));
	}

	@Test
	public void testGetEntriesAsMapOrder9()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_9);

		assertNotNull(result);
		assertEquals(4, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3, 4));
	}

	@Test
	public void testGetEntriesAsMapOrder10()
	{
		Map<Integer, OrderEntryData> result = main.getEntriesAsMap(ORDERS, ORDER_10);

		assertNotNull(result);
		assertEquals(4, result.size());
		assertThat(result.keySet(), hasItems(1, 2, 3, 4));
	}

	@Test
	public void testGetEntriesAsStringOrder1()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_1);

		assertEquals("OrderEntryData{1, qty=1, product=bed3, basePrice=234.27 USD}", result);
	}

	@Test
	public void testGetEntriesAsStringOrder2()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_2);

		assertEquals(
				"OrderEntryData{1, qty=2, product=9951, basePrice=905.26 USD}|OrderEntryData{2, qty=3, product=967b, basePrice=36.93 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder3()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_3);

		assertEquals(
				"OrderEntryData{1, qty=3, product=a867, basePrice=2195.64 USD}|OrderEntryData{2, qty=2, product=8dc0, basePrice=151.24 USD}|OrderEntryData{3, qty=2, product=a8e2, basePrice=190.38 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder4()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_4);

		assertEquals(
				"OrderEntryData{1, qty=5, product=bed3, basePrice=1171.35 USD}|OrderEntryData{2, qty=2, product=9951, basePrice=905.26 USD}|OrderEntryData{3, qty=3, product=a867, basePrice=2195.64 USD}|OrderEntryData{4, qty=6, product=8dc0, basePrice=453.72 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder5()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_5);

		assertEquals(
				"OrderEntryData{1, qty=1, product=9455, basePrice=2.89 USD}|OrderEntryData{2, qty=2, product=8dc0, basePrice=151.24 USD}|OrderEntryData{3, qty=1, product=a867, basePrice=731.88 USD}|OrderEntryData{4, qty=2, product=85ea, basePrice=15.08 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder6()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_6);

		assertEquals("OrderEntryData{1, qty=14, product=a867, basePrice=10246.32 USD}", result);
	}

	@Test
	public void testGetEntriesAsStringOrder7()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_7);

		assertEquals(
				"OrderEntryData{1, qty=5, product=9951, basePrice=2263.15 USD}|OrderEntryData{2, qty=3, product=9dc3, basePrice=300.00 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder8()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_8);

		assertEquals(
				"OrderEntryData{1, qty=3, product=8dc0, basePrice=226.86 USD}|OrderEntryData{2, qty=1, product=85ea, basePrice=7.54 USD}|OrderEntryData{3, qty=2, product=bed3, basePrice=468.54 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder9()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_9);

		assertEquals(
				"OrderEntryData{1, qty=5, product=9455, basePrice=14.45 USD}|OrderEntryData{2, qty=2, product=a867, basePrice=1463.76 USD}|OrderEntryData{3, qty=4, product=a8e2, basePrice=380.76 USD}|OrderEntryData{4, qty=6, product=85ea, basePrice=45.24 USD}",
				result);
	}

	@Test
	public void testGetEntriesAsStringOrder10()
	{
		String result = main.getEntriesAsString(ORDERS, ORDER_10);

		assertEquals(
				"OrderEntryData{1, qty=1, product=9455, basePrice=2.89 USD}|OrderEntryData{2, qty=2, product=bed3, basePrice=468.54 USD}|OrderEntryData{3, qty=1, product=9951, basePrice=452.63 USD}|OrderEntryData{4, qty=2, product=967b, basePrice=24.62 USD}",
				result);
	}

	@Test
	public void testGetProductsByOrderCode()
	{
		Map<String, List<ProductData>> result = main.getProductsByOrderCode(ORDERS);

		assertNotNull(result);
		assertEquals(10, result.size());
		assertThat(result.keySet(),
				hasItems(ORDER_1, ORDER_2, ORDER_3, ORDER_4, ORDER_5, ORDER_6, ORDER_7, ORDER_8, ORDER_9, ORDER_10));
		assertThat(result.get(ORDER_1), hasSize(1));
		assertThat(result.get(ORDER_2), hasSize(2));
		assertThat(result.get(ORDER_3), hasSize(3));
		assertThat(result.get(ORDER_4), hasSize(4));
		assertThat(result.get(ORDER_5), hasSize(4));
		assertThat(result.get(ORDER_6), hasSize(1));
		assertThat(result.get(ORDER_7), hasSize(2));
		assertThat(result.get(ORDER_8), hasSize(3));
		assertThat(result.get(ORDER_9), hasSize(4));
		assertThat(result.get(ORDER_10), hasSize(4));
	}

	@Test
	public void testGetBestSelling3()
	{
		List<ProductData> result = main.getBestSellingProducts(ORDERS, 3L);

		assertNotNull(result);
		assertEquals(3, result.size());
		List<String> codes = result.stream().map(ProductData::getCode).collect(Collectors.toList());
		assertThat(codes, contains(PRODUCT_3, PRODUCT_6, PRODUCT_1));
	}

	@Test
	public void testGetBestSelling5()
	{
		List<ProductData> result = main.getBestSellingProducts(ORDERS, 5L);

		assertNotNull(result);
		assertEquals(5, result.size());
		List<String> codes = result.stream().map(ProductData::getCode).collect(Collectors.toList());
		assertThat(codes, contains(PRODUCT_3, PRODUCT_6, PRODUCT_1, PRODUCT_2, PRODUCT_9));
	}

	private static List<OrderData> createOrders()
	{
		Map<String, ProductData> products = createProducts();
		return Arrays.asList(
				OrderBuilder.create()
						.withCode(ORDER_1)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_1)).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_2)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_2)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_8)).withQuantity(3).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_3)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_3)).withQuantity(3).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_6)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_10)).withQuantity(2).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_4)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_1)).withQuantity(5).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_2)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_3)).withQuantity(3).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_6)).withQuantity(6).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_5)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_5)).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_6)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_3)).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_9)).withQuantity(2).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_6)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_3)).withQuantity(14).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_7)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_2)).withQuantity(5).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_7)).withQuantity(3).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_8)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_6)).withQuantity(3).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_9)).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_1)).withQuantity(2).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_9)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_5)).withQuantity(5).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_3)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_10)).withQuantity(4).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_9)).withQuantity(6).build()
						).build(),
				OrderBuilder.create()
						.withCode(ORDER_10)
						.addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_5)).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_1)).withQuantity(2).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_2)).build()
						).addEntry(
								OrderEntryBuilder.create().withProduct(products.get(PRODUCT_8)).withQuantity(2).build()
						).build()
		);
	}


	private static Map<String, ProductData> createProducts()
	{
		List<ProductData> products = new ArrayList<>();
		products.add(ProductBuilder.create().withCode(PRODUCT_1).withName("Fireball").withPrice("234.27").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_2).withName("Bump").withPrice("452.63").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_3).withName("Merkur").withPrice("731.88").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_4).withName("Paint").withPrice("32.13").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_5).withName("Brush").withPrice("2.89").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_6).withName("Tube").withPrice("75.62").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_7).withName("Trash can").withPrice("100.00").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_8).withName("Toy car").withPrice("12.31").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_9).withName("Teddy bear").withPrice("7.54").build());
		products.add(ProductBuilder.create().withCode(PRODUCT_10).withName("Microscope").withPrice("95.19").build());
		return products.stream().collect(Collectors.toMap(ProductData::getCode, identity()));
	}
}
