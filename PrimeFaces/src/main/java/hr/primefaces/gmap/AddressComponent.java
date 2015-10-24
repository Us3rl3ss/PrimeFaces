
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
@JsonPropertyOrder({
    "long_name",
    "short_name",
    "types"
})
public class AddressComponent {

    @JsonProperty("long_name")
    private String longName;
    @JsonProperty("short_name")
    private String shortName;
    @JsonProperty("types")
    private List<String> types = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return
     *     The longName
     */
    @JsonProperty("long_name")
    public String getLongName() {
        return longName;
    }

    /**
     * @param p_longName
     *     The long_name
     */
    @JsonProperty("long_name")
    public void setLongName(final String p_longName) {
        this.longName = p_longName;
    }

    /**
     * @return
     *     The shortName
     */
    @JsonProperty("short_name")
    public String getShortName() {
        return shortName;
    }

    /**
     * @param p_shortName
     *     The short_name
     */
    @JsonProperty("short_name")
    public void setShortName(final String p_shortName) {
        this.shortName = p_shortName;
    }

    /**
     * @return
     *     The types
     */
    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param p_types
     *     The types
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
