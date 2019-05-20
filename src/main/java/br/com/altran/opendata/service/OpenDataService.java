package br.com.altran.opendata.service;

import java.util.List;
import java.util.Optional;

import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.exception.LanguageInvalidException;
import br.com.altran.opendata.model.OpenDataBCN;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

/**
 *  Class OpenDataService
 *  
 *  @author eudes.justino
 */
public interface OpenDataService {
	/**
	 * 	Returned data with parameterized language.
	 * 
	 * @param <code>Optional<String></code> language
	 * @return <code>OpenDataBCN</code> Object that will be returned when the query is performed.
	 * @throws <code>ErrorJsonProcessingException</code>  Exception in case of parse processing error Json.
	 * @throws <code>AvailableLinkException</code> Link unavailability error exception.
	 */
	public List<OpenDataBCN> searchDataByLanguage(Optional<String> language) throws ErrorJsonProcessingException, AvailableLinkException,LanguageInvalidException;
	/**
	 * 
	 * Returned data with default language (Catalan).
	 * 
	 * @return <code>OpenDataBCN</code> Object that will be returned when the query is performed.
	 * @throws <code>ErrorJsonProcessingException</code>  Exception in case of parse processing error Json.
	 * @throws <code>AvailableLinkException</code> Link unavailability error exception.
	 */
	public List<OpenDataBCN> getOpenData() throws ErrorJsonProcessingException, AvailableLinkException;
}
