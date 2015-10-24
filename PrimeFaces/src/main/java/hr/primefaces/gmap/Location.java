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
@JsonPropertyOrder({ "lat", "lng" })
public class Location {

	@JsonProperty("lat")
	private Double lat;
	@JsonProperty("lng")
	private Double lng;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Location() {
	}

	public Location(final Double p_lat, final Double p_lng) {
		this.lat = p_lat;
		this.lng = p_lng;
	}

	/**
	 * @return The lat
	 */
	@JsonProperty("lat")
	public Double getLat() {
		return lat;
	}

	/**
	 * @param p_lat
	 *            The lat
	 */
	@JsonProperty("lat")
	public void setLat(final Double p_lat) {
		this.lat = p_lat;
	}

	/**
	 * @return The lng
	 */
	@JsonProperty("lng")
	public Double getLng() {
		return lng;
	}

	/**
	 * @param p_lng
	 *            The lng
	 */
	@JsonProperty("lng")
	public void setLng(final Double p_lng) {
		this.lng = p_lng;
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
