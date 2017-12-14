package edu.karazin.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShopApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate httpClient;

	@Test
	public void checkLandingPage() {
		String landingPage = httpClient.getForObject(String.format("http://localhost:%d/", port), String.class);
		Assert.assertThat("Title of page", landingPage, containsString("<title>Product List</title>"));
	}

}