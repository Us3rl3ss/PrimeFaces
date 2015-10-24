package hr.primefaces.gmap;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "northeast", "southwest" })
public class Viewport {

	@JsonProperty("northeast")
	private Northeast_ northeast;
	@JsonProperty("southwest")
	private Southwest_ southwest;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The northeast
	 */
	@JsonProperty("northeast")
	public Northeast_ getNortheast() {
		return northeast;
	}

	/**
	 * @param p_northeast
	 *            The northeast
	 */
	@JsonProperty("northeast")
	public void setNortheast(final Northeast_ p_northeast) {
		this.northeast = p_northeast;
	}

	/**
	 * @return The southwest
	 */
	@JsonProperty("southwest")
	public Southwest_ getSouthwest() {
		return southwest;
	}

	/**
	 * @param p_southwest
	 *            The southwest
	 */
	@JsonProperty("southwest")
	public void setSouthwest(final Southwest_ p_southwest) {
		this.southwest = p_southwest;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(final String p_name, final Object p_value) {
		this.additionalProperties.put(p_name, p_value);
	}

}
