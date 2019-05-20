package br.com.altran.opendata.consumer.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author eudes.justino
 *
 */
public class OpenDataType {	
	@JsonProperty(value="result")
	Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result resultObject) {
		this.result = resultObject;
	}

}
