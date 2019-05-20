package br.com.altran.opendata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.altran.opendata.consumer.OpenDataConsumer;
import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.consumer.type.ItemResult;
import br.com.altran.opendata.consumer.type.OpenDataType;
import br.com.altran.opendata.exception.LanguageInvalidException;
import br.com.altran.opendata.model.OpenDataBCN;
import br.com.altran.opendata.service.OpenDataService;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

/**
 * @author eudes.justino
 *
 */
@Service
public class OpenDataServiceImpl implements OpenDataService {

	private static final String LINGUAGEM_DEFAUT = "ca";

	@Autowired
	private OpenDataConsumer openDataConsumer;

	/**
	 * Returned data with parameterized language.
	 * 
	 * @param language
	 * @return <code>OpenDataBCN</code> Object that will be returned when the query
	 *         is performed.
	 * @throws <code>MethodArgumentInvalidException</code>  Exception in case parameter requerid. 
	 * @throws <code>ErrorJsonProcessingException</code> Exception in case of parse
	 *         processing error Json.
	 * @throws <code>AvailableLinkException</code> Link unavailability error
	 *         exception.
	 */
	@Override
	@Cacheable("searchDataByLanguage")
	public List<OpenDataBCN> searchDataByLanguage(Optional<String> language)
			throws ErrorJsonProcessingException, AvailableLinkException, LanguageInvalidException {
		validateLanguage(language);
		OpenDataType consumeApiOpenData = openDataConsumer.consumer();
		List<OpenDataBCN> model = converterToEntity(consumeApiOpenData, language);
		return model;
	}

	private void validateLanguage(Optional<String> language) throws LanguageInvalidException {
		if(language.isPresent()) {
			String languageValue = language.get();
			if(!Arrays.asList("ca","en","es").contains(languageValue)) {
				throw new LanguageInvalidException("[language = "+languageValue+"] not acceptable.") ;
			}
			
		}
		
	}

	/**
	 * 
	 * Returned data with default language (Catalan).
	 * 
	 * @return <code>OpenDataBCN</code> Object that will be returned when the query
	 *         is performed.
	 * @throws <code>ErrorJsonProcessingException</code> Exception in case of parse
	 *         processing error Json.
	 * @throws <code>AvailableLinkException</code> Link unavailability error
	 *         exception.
	 */
	@Override
	public List<OpenDataBCN> getOpenData() throws ErrorJsonProcessingException, AvailableLinkException {
		
		OpenDataType consumeApiOpenData = openDataConsumer.consumer();
		List<OpenDataBCN> model = converterToEntity(consumeApiOpenData, Optional.of(LINGUAGEM_DEFAUT));
		return model;
	}

	private List<OpenDataBCN> converterToEntity(OpenDataType type, Optional<String> linguage) {
		List<OpenDataBCN> listaItens = new ArrayList<OpenDataBCN>();

		String linguageValidation = linguage.orElse(LINGUAGEM_DEFAUT);

		ArrayList<ItemResult> results = type.getResult().getResults();

		results.forEach(item -> {

			OpenDataBCN object = new OpenDataBCN();

			object.setId(item.getId());
			object.setCode(item.getCode());
			object.setDescription(getValueByLinguagem(item.getNotes(), linguageValidation));
			object.setTitle(getValueByLinguagem(item.getTitle(), linguageValidation));
			object.setUrl(getValueByLinguagem(item.getUrl(), linguageValidation));
			object.setLanguage(linguageValidation);

			listaItens.add(object);

		});

		return listaItens;
	}

	public String getValueByLinguagem(Map<String, String> hash, String linguage) {
		String valor = null;
		if (hash.containsKey(linguage)) {
			valor = hash.get(linguage);
		}
		return valor;
	}

}
