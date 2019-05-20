package br.com.altran.opendata.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.altran.opendata.api.v1.OpenDataEndpoint;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OpenDataEndpointTest {

	@Mock
	protected OpenDataEndpoint OpenDataEndpoint;

	@Value("${local.server.port}")
	protected int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void you_should_search_for_data_by_english_language() {
		given().pathParam("language", "en").get("/altran/opendata-bcn/v1/data/lg/{language}").then()
				.statusCode(HttpStatus.SC_OK).and().body("content[0].language", equalTo("en"));
	}

	@Test
	public void you_should_search_for_data_by_spanish_language() {
		given().pathParam("language", "es").get("/altran/opendata-bcn/v1/data/lg/{language}").then()
				.statusCode(HttpStatus.SC_OK).and().body("content[0].language", equalTo("es"));
	}

	@Test
	public void you_should_search_for_data_by_catalan_language() {
		given().pathParam("language", "ca").get("/altran/opendata-bcn/v1/data/lg/{language}").then()
				.statusCode(HttpStatus.SC_OK).and().body("content[0].language", equalTo("ca"));
	}

	@Test
	public void you_should_search_for_data_by_standard_language() {
		given().get("/altran/opendata-bcn/v1/data").then().statusCode(HttpStatus.SC_OK)
				.body("content[0].language", equalTo("ca"));
	}

	@Test
	public void you_should_search_for_data_with_pagination() {
		given().get("/altran/opendata-bcn/v1/data?page=0&size=2").then().statusCode(HttpStatus.SC_OK).body("content.size()",
				equalTo(2));
		given().get("/altran/opendata-bcn/v1/data?page=3&size=3").then().statusCode(HttpStatus.SC_OK).body("content.size()",
				equalTo(1));

	}

	@Test
	public void should_throw_unacceptable_language_error() {

		given().pathParam("language", "pt").get("/altran/opendata-bcn/v1/data/lg/{language}").then()
				.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
				.body("developerMessage", equalTo("br.com.altran.opendata.exception.LanguageInvalidException"));
	}

}
