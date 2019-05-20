package br.com.altran.opendata.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.altran.opendata.api.ConverterResource;
import br.com.altran.opendata.model.OpenDataBCN;

/**
 * @author eudes.justino
 *
 */
@Component
public class OpenDataConverter implements ConverterResource<OpenDataResource, OpenDataBCN> {

	@Override
	public OpenDataResource converterEntityByResource(OpenDataBCN entity) {
		OpenDataResource resource = new OpenDataResource();

		return resource;
	}

	@Override
	public OpenDataBCN converterResourceByEntity(OpenDataResource resource) {

		OpenDataBCN entity = new OpenDataBCN();
		return entity;
	}

	@Override
	public List<OpenDataResource> converterEntityByResource(List<OpenDataBCN> entityList) {
		List<OpenDataResource> resourceList = new ArrayList<OpenDataResource>();

		entityList.forEach(entity -> {
			OpenDataResource resource = new OpenDataResource();
			resource.setCode(entity.getCode());
			resource.setTitle(entity.getTitle());
			resource.setDescription(entity.getDescription());
			resource.setUrl(entity.getUrl());
			resource.setLanguage(entity.getLanguage());
			resourceList.add(resource);
		});

		return resourceList;
	}

	@Override
	public List<OpenDataBCN> converterResourceByEntity(List<OpenDataResource> resourceList) {
		List<OpenDataBCN> listEntity = new ArrayList<OpenDataBCN>();

		resourceList.forEach(resource -> {
			OpenDataBCN object = new OpenDataBCN();
			listEntity.add(object);
		});

		return listEntity;
	}

}
