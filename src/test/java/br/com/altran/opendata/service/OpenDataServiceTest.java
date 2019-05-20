package br.com.altran.opendata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.exception.LanguageInvalidException;
import br.com.altran.opendata.model.OpenDataBCN;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenDataServiceTest {
	
	@Autowired
	private OpenDataService  openDataConsumerService; 
	
	@Test
	public void you_should_search_for_data_by_english_language() throws ErrorJsonProcessingException, AvailableLinkException, LanguageInvalidException {
		List<OpenDataBCN> consumeApiOpenData = openDataConsumerService.searchDataByLanguage(Optional.of("en"));
		consumeApiOpenData.forEach(item->{
		assertEquals("en", item.getLanguage());	
		});
	}
	
	@Test
	public void you_should_search_for_data_by_spanish_language() throws ErrorJsonProcessingException, AvailableLinkException, LanguageInvalidException {
		List<OpenDataBCN> consumeApiOpenData = openDataConsumerService.searchDataByLanguage(Optional.of("es"));
		consumeApiOpenData.forEach(item->{
		assertEquals("es", item.getLanguage());	
		});
	}
	
	@Test
	public void you_should_search_for_data_by_catalan_language() throws ErrorJsonProcessingException, AvailableLinkException, LanguageInvalidException {
		List<OpenDataBCN> consumeApiOpenData = openDataConsumerService.searchDataByLanguage(Optional.of("ca"));
		consumeApiOpenData.forEach(item->{
		assertEquals("ca", item.getLanguage());	
		});
	}
	
	@Test
	public void you_should_search_for_data_without_defining_language() throws ErrorJsonProcessingException, AvailableLinkException {
		List<OpenDataBCN> consumeApiOpenData = openDataConsumerService.getOpenData();
		consumeApiOpenData.forEach(item->{
		assertEquals("ca", item.getLanguage());	
		});
	}
	
}
