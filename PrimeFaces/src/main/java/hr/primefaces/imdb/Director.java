
package hr.primefaces.imdb;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Director {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameId")
    @Expose
    private String nameId;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The nameId
     */
    public String getNameId() {
        return nameId;
    }

    /**
     * 
     * @param nameId
     *     The nameId
     */
    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

}
