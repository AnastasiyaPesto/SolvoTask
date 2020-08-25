package ru.zentsova.request;

import org.junit.Assert;
import org.junit.Test;
import ru.zentsova.request.Request;


public class RequestTest {

	@Test
	public void testGetXSetX() {
		Request request = new Request(0, "Request");
		Assert.assertEquals(0, request.getX());
		request.setX(1);
		Assert.assertEquals(1, request.getX());
	}

	@Test
	public void testGetName() {
		Request request = new Request(0, "Request");
		Assert.assertEquals("Request", request.getName());
	}

	@Test
	public void call() {
		Request request = new Request(0, "Request");
		Assert.assertNull(request.call());
	}

}