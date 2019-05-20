package br.com.altran.opendata.api;

import java.util.List;

/**
 * @author eudes.justino
 *
 * @param <Resource>
 * @param <Entity>
 */
public interface ConverterResource<Resource,Entity> {
	
	public abstract Resource converterEntityByResource(Entity entity);
	public abstract Entity converterResourceByEntity(Resource resource);
	public abstract List<Resource> converterEntityByResource(List<Entity> entity);
	public abstract List<Entity> converterResourceByEntity(List<Resource> resource);
}
