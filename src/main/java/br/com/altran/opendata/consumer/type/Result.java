package br.com.altran.opendata.consumer.type;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author eudes.justino
 *
 */
public class Result {
	@JsonProperty(value = "results")
	ArrayList<ItemResult> results = new ArrayList<ItemResult>();

	public ArrayList<ItemResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<ItemResult> results) {
		this.results = results;
	}
}
