package hr.primefaces.gmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "address_components", "formatted_address", "geometry", "partial_match", "place_id", "types" })
public class Result {

	@JsonProperty("address_components")
	private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
	@JsonProperty("formatted_address")
	private String formattedAddress;
	@JsonProperty("geometry")
	private Geometry geometry;
	@JsonProperty("partial_match")
	private Boolean partialMatch;
	@JsonProperty("place_id")
	private String placeId;
	@JsonProperty("types")
	private List<String> types = new ArrayList<String>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The addressComponents
	 */
	@JsonProperty("address_components")
	public List<AddressComponent> getAddressComponents() {
		return addressComponents;
	}

	/**
	 * @param p_addressComponents
	 *            The address_components
	 */
	@JsonProperty("address_components")
	public void setAddressComponents(final List<AddressComponent> p_addressComponents) {
		this.addressComponents = p_addressComponents;
	}

	/**
	 * @return The formattedAddress
	 */
	@JsonProperty("formatted_address")
	public String getFormattedAddress() {
		return formattedAddress;
	}

	/**
	 * @param p_formattedAddress
	 *            The formatted_address
	 */
	@JsonProperty("formatted_address")
	public void setFormattedAddress(final String p_formattedAddress) {
		this.formattedAddress = p_formattedAddress;
	}

	/**
	 * @return The geometry
	 */
	@JsonProperty("geometry")
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param p_geometry
	 *            The geometry
	 */
	@JsonProperty("geometry")
	public void setGeometry(final Geometry p_geometry) {
		this.geometry = p_geometry;
	}

	/**
	 * @return The partialMatch
	 */
	@JsonProperty("partial_match")
	public Boolean getPartialMatch() {
		return partialMatch;
	}

	/**
	 * @param p_partialMatch
	 *            The partial_match
	 */
	@JsonProperty("partial_match")
	public void setPartialMatch(final Boolean p_partialMatch) {
		this.partialMatch = p_partialMatch;
	}

	/**
	 * @return The placeId
	 */
	@JsonProperty("place_id")
	public String getPlaceId() {
		return placeId;
	}

	/**
	 * @param p_placeId
	 *            The place_id
	 */
	@JsonProperty("place_id")
	public void setPlaceId(final String p_placeId) {
		this.placeId = p_placeId;
	}

	/**
	 * @return The types
	 */
	@JsonProperty("types")
	public List<String> getTypes() {
		return types;
	}

	/**
	 * @param p_types
	 *            The types
	 */
	@JsonProperty("types")
	public void setTypes(final List<String> p_types) {
		this.types = p_types;
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
