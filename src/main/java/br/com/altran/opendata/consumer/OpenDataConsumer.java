package br.com.altran.opendata.consumer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.consumer.type.OpenDataType;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

/**
 * Class responsible for extracting information about open data from barcelona
 * 
 * @author eudes.justino
 *
 */
@Service
public class OpenDataConsumer {
	@Value("${consumer.url}")
	private String url;

	/**
	 * Consume data from an API
	 * @return <code>OpenDataType</code> returned object when performing the parse json.
	 * @throws AvailableLinkException 
	 * @throws <code>ErrorJsonProcessingException</code> Exception in case of parse
	 *         processing error Json.
	 */
	public OpenDataType consumer() throws ErrorJsonProcessingException, AvailableLinkException {
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		OpenDataType object;
		
		availableLink();
		
		try {
			object = restTemplate.getForObject(url, OpenDataType.class);
		} catch (RestClientException exception) {
			throw new ErrorJsonProcessingException("Error to Processing Parse Json.");
		}
		return object;
	}

	/**
	 * 
	 * Evaluate link availability
	 * 
	 * @throws <code>AvailableLinkException</code>> Link unavailability error exception
	 *                               
	 */
	public void availableLink() throws AvailableLinkException {
		AvailableLinkException availableLinkException = new AvailableLinkException("Link unavailability error.");
		
		try {
			URL u = new URL(url);
			int CODEResponse = 0;
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			huc.setRequestMethod("GET");
			huc.addRequestProperty("content-type", "application/json");
			huc.connect();
			CODEResponse = huc.getResponseCode();
			if (CODEResponse != 200) {
				throw availableLinkException;
			}
		} catch (IOException e) {
			throw availableLinkException;
		}

	}

}
