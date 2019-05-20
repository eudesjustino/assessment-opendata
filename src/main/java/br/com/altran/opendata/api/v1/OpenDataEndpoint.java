package br.com.altran.opendata.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.altran.opendata.api.RestPageImpl;
import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.exception.LanguageInvalidException;
import br.com.altran.opendata.model.OpenDataBCN;
import br.com.altran.opendata.service.OpenDataService;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

/**
 * OpenDataEndpoint
 * 
 * @author eudes.justino
 *
 */
@RestController
@RequestMapping("/opendata-bcn")
public class OpenDataEndpoint {
	@Autowired
	OpenDataConverter converter;

	@Autowired
	protected OpenDataService openDataService;

	@GetMapping(value = "/v1/data/lg/{language}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<OpenDataResource> searchDataByLanguage(@PathVariable("language") Optional<String> language,
			@PageableDefault(size=10) Pageable pageable)
			throws ErrorJsonProcessingException, AvailableLinkException, LanguageInvalidException {		
		
		List<OpenDataBCN> result = openDataService.searchDataByLanguage(language);
		Page<OpenDataResource> pageOpenDataBCN = new RestPageImpl<OpenDataResource>(
				converter.converterEntityByResource(result), pageable);

		return pageOpenDataBCN;
	}

	@GetMapping(value = "/v1/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<OpenDataResource> searchDataByLanguageDefault(@PageableDefault(page = 0, size = 3) Pageable pageable)
			throws ErrorJsonProcessingException, AvailableLinkException {
		List<OpenDataBCN> result = openDataService.getOpenData();
		Page<OpenDataResource> pageOpenDataBCN = new RestPageImpl<OpenDataResource>(
				converter.converterEntityByResource(result), pageable);
		return pageOpenDataBCN;
	}

}
