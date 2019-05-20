package br.com.altran.opendata.consumer;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.consumer.type.ItemResult;
import br.com.altran.opendata.consumer.type.OpenDataType;
import br.com.altran.opendata.consumer.type.Result;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenDataConsumerTest {
	
		
	@Autowired
	private OpenDataConsumer openDataConsumer;
	@Mock
	private OpenDataConsumer openDataConsumerMock;
	
	
	
	@Test(expected=AvailableLinkException.class)
	public void should_throw_exception_when_verifying_url_unavailable() throws ErrorJsonProcessingException, AvailableLinkException {
		Mockito.doThrow(AvailableLinkException.class).when(openDataConsumerMock).consumer();
		openDataConsumerMock.consumer();
	}
	
	@Test(expected=ErrorJsonProcessingException.class)
    public void should_throw_exception_when_processind_parse_json() throws AvailableLinkException, ErrorJsonProcessingException {
    	Mockito.when(openDataConsumerMock.consumer()).thenThrow(ErrorJsonProcessingException.class);
    	openDataConsumerMock.consumer();
	}
	
	
	@Test
	public void should_return_valid_values_​​data() throws ErrorJsonProcessingException, AvailableLinkException {
		OpenDataType consumerApiOpenData = openDataConsumer.consumer();
		Result result = consumerApiOpenData.getResult();
		assertNotNull(result);
		ArrayList<ItemResult> results = result.getResults();
		results.forEach(item ->{
			assertNotNull(item.getId());
			assertNotNull(item.getCode());
		});
	}

}
