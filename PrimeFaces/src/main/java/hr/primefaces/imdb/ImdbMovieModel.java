
package hr.primefaces.imdb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ImdbMovieModel {

    @SerializedName("actors")
    @Expose
    private List<Actor> actors = new ArrayList<Actor>();
    @SerializedName("countries")
    @Expose
    private List<String> countries = new ArrayList<String>();
    @SerializedName("directors")
    @Expose
    private List<Director> directors = new ArrayList<Director>();
    @SerializedName("filmingLocations")
    @Expose
    private List<String> filmingLocations = new ArrayList<String>();
    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("idIMDB")
    @Expose
    private String idIMDB;
    @SerializedName("languages")
    @Expose
    private List<String> languages = new ArrayList<String>();
    @SerializedName("metascore")
    @Expose
    private String metascore;
    @SerializedName("movieTrivia")
    @Expose
    private List<String> movieTrivia = new ArrayList<String>();
    @SerializedName("originalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("plot")
    @Expose
    private String plot;
    @SerializedName("rated")
    @Expose
    private String rated;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("runtime")
    @Expose
    private List<String> runtime = new ArrayList<String>();
    @SerializedName("similarMovies")
    @Expose
    private List<SimilarMovie> similarMovies = new ArrayList<SimilarMovie>();
    @SerializedName("simplePlot")
    @Expose
    private String simplePlot;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("trailer")
    @Expose
    private Trailer trailer;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("urlIMDB")
    @Expose
    private String urlIMDB;
    @SerializedName("urlPoster")
    @Expose
    private String urlPoster;
    @SerializedName("votes")
    @Expose
    private String votes;
    @SerializedName("writers")
    @Expose
    private List<Writer> writers = new ArrayList<Writer>();
    @SerializedName("year")
    @Expose
    private String year;

    /**
     * 
     * @return
     *     The actors
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     * 
     * @param actors
     *     The actors
     */
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    /**
     * 
     * @return
     *     The countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * 
     * @param countries
     *     The countries
     */
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    /**
     * 
     * @return
     *     The directors
     */
    public List<Director> getDirectors() {
        return directors;
    }

    /**
     * 
     * @param directors
     *     The directors
     */
    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    /**
     * 
     * @return
     *     The filmingLocations
     */
    public List<String> getFilmingLocations() {
        return filmingLocations;
    }

    /**
     * 
     * @param filmingLocations
     *     The filmingLocations
     */
    public void setFilmingLocations(List<String> filmingLocations) {
        this.filmingLocations = filmingLocations;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The idIMDB
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * 
     * @param idIMDB
     *     The idIMDB
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * 
     * @return
     *     The languages
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * 
     * @param languages
     *     The languages
     */
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    /**
     * 
     * @return
     *     The metascore
     */
    public String getMetascore() {
        return metascore;
    }

    /**
     * 
     * @param metascore
     *     The metascore
     */
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    /**
     * 
     * @return
     *     The movieTrivia
     */
    public List<String> getMovieTrivia() {
        return movieTrivia;
    }

    /**
     * 
     * @param movieTrivia
     *     The movieTrivia
     */
    public void setMovieTrivia(List<String> movieTrivia) {
        this.movieTrivia = movieTrivia;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The originalTitle
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The plot
     */
    public String getPlot() {
        return plot;
    }

    /**
     * 
     * @param plot
     *     The plot
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * 
     * @return
     *     The rated
     */
    public String getRated() {
        return rated;
    }

    /**
     * 
     * @param rated
     *     The rated
     */
    public void setRated(String rated) {
        this.rated = rated;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public List<String> getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(List<String> runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The similarMovies
     */
    public List<SimilarMovie> getSimilarMovies() {
        return similarMovies;
    }

    /**
     * 
     * @param similarMovies
     *     The similarMovies
     */
    public void setSimilarMovies(List<SimilarMovie> similarMovies) {
        this.similarMovies = similarMovies;
    }

    /**
     * 
     * @return
     *     The simplePlot
     */
    public String getSimplePlot() {
        return simplePlot;
    }

    /**
     * 
     * @param simplePlot
     *     The simplePlot
     */
    public void setSimplePlot(String simplePlot) {
        this.simplePlot = simplePlot;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The trailer
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * 
     * @param trailer
     *     The trailer
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The urlIMDB
     */
    public String getUrlIMDB() {
        return urlIMDB;
    }

    /**
     * 
     * @param urlIMDB
     *     The urlIMDB
     */
    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    /**
     * 
     * @return
     *     The urlPoster
     */
    public String getUrlPoster() {
        return urlPoster;
    }

    /**
     * 
     * @param urlPoster
     *     The urlPoster
     */
    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public String getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(String votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The writers
     */
    public List<Writer> getWriters() {
        return writers;
    }

    /**
     * 
     * @param writers
     *     The writers
     */
    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    /**
     * 
     * @return
     *     The year
     */
    public String getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(String year) {
        this.year = year;
    }

}
