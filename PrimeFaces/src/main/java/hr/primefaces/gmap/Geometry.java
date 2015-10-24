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
@JsonPropertyOrder({ "bounds", "location", "location_type", "viewport" })
public class Geometry {

	@JsonProperty("bounds")
	private Bounds bounds;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("location_type")
	private String locationType;
	@JsonProperty("viewport")
	private Viewport viewport;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The bounds
	 */
	@JsonProperty("bounds")
	public Bounds getBounds() {
		return bounds;
	}

	/**
	 * @param p_bounds
	 *            The bounds
	 */
	@JsonProperty("bounds")
	public void setBounds(final Bounds p_bounds) {
		this.bounds = p_bounds;
	}

	/**
	 * @return The location
	 */
	@JsonProperty("location")
	public Location getLocation() {
		return location;
	}

	/**
	 * @param p_location
	 *            The location
	 */
	@JsonProperty("location")
	public void setLocation(final Location p_location) {
		this.location = p_location;
	}

	/**
	 * @return The locationType
	 */
	@JsonProperty("location_type")
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @param p_locationType
	 *            The location_type
	 */
	@JsonProperty("location_type")
	public void setLocationType(final String p_locationType) {
		this.locationType = p_locationType;
	}

	/**
	 * @return The viewport
	 */
	@JsonProperty("viewport")
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * @param p_viewport
	 *            The viewport
	 */
	@JsonProperty("viewport")
	public void setViewport(final Viewport p_viewport) {
		this.viewport = p_viewport;
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
