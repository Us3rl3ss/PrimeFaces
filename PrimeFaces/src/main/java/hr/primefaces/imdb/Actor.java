
package hr.primefaces.imdb;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Actor {

    @SerializedName("actorId")
    @Expose
    private String actorId;
    @SerializedName("actorName")
    @Expose
    private String actorName;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("main")
    @Expose
    private Boolean main;
    @SerializedName("urlCharacter")
    @Expose
    private String urlCharacter;
    @SerializedName("urlPhoto")
    @Expose
    private String urlPhoto;
    @SerializedName("urlProfile")
    @Expose
    private String urlProfile;

    /**
     * 
     * @return
     *     The actorId
     */
    public String getActorId() {
        return actorId;
    }

    /**
     * 
     * @param actorId
     *     The actorId
     */
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    /**
     * 
     * @return
     *     The actorName
     */
    public String getActorName() {
        return actorName;
    }

    /**
     * 
     * @param actorName
     *     The actorName
     */
    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    /**
     * 
     * @return
     *     The character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 
     * @param character
     *     The character
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Boolean getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Boolean main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The urlCharacter
     */
    public String getUrlCharacter() {
        return urlCharacter;
    }

    /**
     * 
     * @param urlCharacter
     *     The urlCharacter
     */
    public void setUrlCharacter(String urlCharacter) {
        this.urlCharacter = urlCharacter;
    }

    /**
     * 
     * @return
     *     The urlPhoto
     */
    public String getUrlPhoto() {
        return urlPhoto;
    }

    /**
     * 
     * @param urlPhoto
     *     The urlPhoto
     */
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    /**
     * 
     * @return
     *     The urlProfile
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * 
     * @param urlProfile
     *     The urlProfile
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

}
