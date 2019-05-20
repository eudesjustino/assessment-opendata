package br.com.altran.opendata.consumer.type;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author eudes.justino
 *
 */
public class ItemResult {

	private String id;

	private String code;

	@JsonProperty(value = "notes_translated")
	private Map<String, String> notes;
	@JsonProperty(value = "url_tornada")
	private Map<String, String> url;
	@JsonProperty(value = "title_translated")
	private Map<String, String> title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getNotes() {
		return notes;
	}

	public void setNotes(Map<String, String> notes) {
		this.notes = notes;
	}

	public Map<String, String> getUrl() {
		return url;
	}

	public void setUrl(Map<String, String> url) {
		this.url = url;
	}

	public Map<String, String> getTitle() {
		return title;
	}

	public void setTitle(Map<String, String> title) {
		this.title = title;
	}

}
