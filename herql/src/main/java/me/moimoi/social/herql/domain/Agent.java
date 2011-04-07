package me.moimoi.social.herql.domain;


import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

@Entity(value="agent", noClassnameStored=true)
public class Agent {

    @Id private ObjectId id;
    
    private String aboutMe;
    private Integer age;
    private Date anniversary;
    private Date birthday;
    private String bodyType;
    private Boolean connected;
    private String drinker;
    private String displayName;    
    private String ethnicity;
    private String fashion;
    private String gender;
    private String happiestWhen;
    private String humor;
    private String livingArrangement;
    private String location;
    private String lookingFor;
    private String nickname;
    private String note;
    private String preferredUsername;
    private String profileSong;
    private String profileVideo;
    private String profileUrl;
    private Date published;
    private String relationshipStatus;
    private String religion;
    private String romance;
    private String status;
    private String scaredOf;
    private String sexualOrientation;
    private String smoker;
    private String thumbnailUrl;
    private Date updated;
    private String utcOffset;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> activities;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> books;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> cars;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<Account> accounts;

    @Embedded(concreteClass = LinkedList.class)
    private List<Account> children;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<Address> addresses;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> emails;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> heroes;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> ims;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> interests;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> jobInterests;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> languagesSpoken;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> movies;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> music;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> networkPresence;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> pets;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> photos;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> politicalViews;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> quotes;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> relationships;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> sports;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> tags;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> turnOffs;
    
    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> tvShows;

    @Embedded(concreteClass = LinkedList.class)
    private List<PluralField> urls;
    
    public static Agent create() {
        return new Agent();
    }
    
    private Agent() { 
        super();
        accounts = new LinkedList<Account>();
        addresses = new LinkedList<Address>();
        activities = new LinkedList<PluralField>();
        books = new LinkedList<PluralField>();
        cars = new  LinkedList<PluralField>();
    }
    
    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe the aboutMe to set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the anniversary
     */
    public Date getAnniversary() {
        return anniversary;
    }

    /**
     * @param anniversary the anniversary to set
     */
    public void setAnniversary(Date anniversary) {
        this.anniversary = anniversary;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the bodyType
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType the bodyType to set
     */
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return the connected
     */
    public Boolean getConnected() {
        return connected;
    }

    /**
     * @param connected the connected to set
     */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    /**
     * @return the drinker
     */
    public String getDrinker() {
        return drinker;
    }

    /**
     * @param drinker the drinker to set
     */
    public void setDrinker(String drinker) {
        this.drinker = drinker;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }   

    /**
     * @return the ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity the ethnicity to set
     */
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return the fashion
     */
    public String getFashion() {
        return fashion;
    }

    /**
     * @param fashion the fashion to set
     */
    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the happiestWhen
     */
    public String getHappiestWhen() {
        return happiestWhen;
    }

    /**
     * @param happiestWhen the happiestWhen to set
     */
    public void setHappiestWhen(String happiestWhen) {
        this.happiestWhen = happiestWhen;
    }

    /**
     * @return the humor
     */
    public String getHumor() {
        return humor;
    }

    /**
     * @param humor the humor to set
     */
    public void setHumor(String humor) {
        this.humor = humor;
    }

    /**
     * @return the livingArrangement
     */
    public String getLivingArrangement() {
        return livingArrangement;
    }

    /**
     * @param livingArrangement the livingArrangement to set
     */
    public void setLivingArrangement(String livingArrangement) {
        this.livingArrangement = livingArrangement;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the lookingFor
     */
    public String getLookingFor() {
        return lookingFor;
    }

    /**
     * @param lookingFor the lookingFor to set
     */
    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the preferredUsername
     */
    public String getPreferredUsername() {
        return preferredUsername;
    }

    /**
     * @param preferredUsername the preferredUsername to set
     */
    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    /**
     * @return the profileSong
     */
    public String getProfileSong() {
        return profileSong;
    }

    /**
     * @param profileSong the profileSong to set
     */
    public void setProfileSong(String profileSong) {
        this.profileSong = profileSong;
    }

    /**
     * @return the profileVideo
     */
    public String getProfileVideo() {
        return profileVideo;
    }

    /**
     * @param profileVideo the profileVideo to set
     */
    public void setProfileVideo(String profileVideo) {
        this.profileVideo = profileVideo;
    }

    /**
     * @return the profileUrl
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * @param profileUrl the profileUrl to set
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * @return the published
     */
    public Date getPublished() {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished(Date published) {
        this.published = published;
    }

    /**
     * @return the relationshipStatus
     */
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    /**
     * @param relationshipStatus the relationshipStatus to set
     */
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the romance
     */
    public String getRomance() {
        return romance;
    }

    /**
     * @param romance the romance to set
     */
    public void setRomance(String romance) {
        this.romance = romance;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the scaredOf
     */
    public String getScaredOf() {
        return scaredOf;
    }

    /**
     * @param scaredOf the scaredOf to set
     */
    public void setScaredOf(String scaredOf) {
        this.scaredOf = scaredOf;
    }

    /**
     * @return the sexualOrientation
     */
    public String getSexualOrientation() {
        return sexualOrientation;
    }

    /**
     * @param sexualOrientation the sexualOrientation to set
     */
    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    /**
     * @return the smoker
     */
    public String getSmoker() {
        return smoker;
    }

    /**
     * @param smoker the smoker to set
     */
    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    /**
     * @return the thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * @param thumbnailUrl the thumbnailUrl to set
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the account
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param account the account to set
     */
    public void setAccounts(List<Account> account) {
        this.accounts = account;
    }

    /**
     * @return the address
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param address the address to set
     */
    public void setAddresses(List<Address> address) {
        this.addresses = address;
    }

    /**
     * @return the activities
     */
    public List<PluralField> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<PluralField> activities) {
        this.activities = activities;
    }

    /**
     * @return the books
     */
    public List<PluralField> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<PluralField> books) {
        this.books = books;
    }

    /**
     * @return the cars
     */
    public List<PluralField> getCars() {
        return cars;
    }

    /**
     * @param cars the cars to set
     */
    public void setCars(List<PluralField> cars) {
        this.cars = cars;
    }

    /**
     * @return the children
     */
    public List<Account> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Account> children) {
        this.children = children;
    }

    /**
     * @return the utcOffset
     */
    public String getUtcOffset() {
        return utcOffset;
    }

    /**
     * @param utcOffset the utcOffset to set
     */
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * @return the emails
     */
    public List<PluralField> getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(List<PluralField> emails) {
        this.emails = emails;
    }

    /**
     * @return the heroes
     */
    public List<PluralField> getHeroes() {
        return heroes;
    }

    /**
     * @param heroes the heroes to set
     */
    public void setHeroes(List<PluralField> heroes) {
        this.heroes = heroes;
    }

    /**
     * @return the ims
     */
    public List<PluralField> getIms() {
        return ims;
    }

    /**
     * @param ims the ims to set
     */
    public void setIms(List<PluralField> ims) {
        this.ims = ims;
    }

    /**
     * @return the interests
     */
    public List<PluralField> getInterests() {
        return interests;
    }

    /**
     * @param interests the interests to set
     */
    public void setInterests(List<PluralField> interests) {
        this.interests = interests;
    }

    /**
     * @return the jobInterests
     */
    public List<PluralField> getJobInterests() {
        return jobInterests;
    }

    /**
     * @param jobInterests the jobInterests to set
     */
    public void setJobInterests(List<PluralField> jobInterests) {
        this.jobInterests = jobInterests;
    }

    /**
     * @return the languagesSpoken
     */
    public List<PluralField> getLanguagesSpoken() {
        return languagesSpoken;
    }

    /**
     * @param languagesSpoken the languagesSpoken to set
     */
    public void setLanguagesSpoken(List<PluralField> languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    /**
     * @return the movies
     */
    public List<PluralField> getMovies() {
        return movies;
    }

    /**
     * @param movies the movies to set
     */
    public void setMovies(List<PluralField> movies) {
        this.movies = movies;
    }

    /**
     * @return the music
     */
    public List<PluralField> getMusic() {
        return music;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(List<PluralField> music) {
        this.music = music;
    }

    /**
     * @return the networkPresence
     */
    public List<PluralField> getNetworkPresence() {
        return networkPresence;
    }

    /**
     * @param networkPresence the networkPresence to set
     */
    public void setNetworkPresence(List<PluralField> networkPresence) {
        this.networkPresence = networkPresence;
    }

    /**
     * @return the pets
     */
    public List<PluralField> getPets() {
        return pets;
    }

    /**
     * @param pets the pets to set
     */
    public void setPets(List<PluralField> pets) {
        this.pets = pets;
    }

    /**
     * @return the photos
     */
    public List<PluralField> getPhotos() {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(List<PluralField> photos) {
        this.photos = photos;
    }

    /**
     * @return the politicalViews
     */
    public List<PluralField> getPoliticalViews() {
        return politicalViews;
    }

    /**
     * @param politicalViews the politicalViews to set
     */
    public void setPoliticalViews(List<PluralField> politicalViews) {
        this.politicalViews = politicalViews;
    }

    /**
     * @return the quotes
     */
    public List<PluralField> getQuotes() {
        return quotes;
    }

    /**
     * @param quotes the quotes to set
     */
    public void setQuotes(List<PluralField> quotes) {
        this.quotes = quotes;
    }

    /**
     * @return the relationships
     */
    public List<PluralField> getRelationships() {
        return relationships;
    }

    /**
     * @param relationships the relationships to set
     */
    public void setRelationships(List<PluralField> relationships) {
        this.relationships = relationships;
    }

    /**
     * @return the sports
     */
    public List<PluralField> getSports() {
        return sports;
    }

    /**
     * @param sports the sports to set
     */
    public void setSports(List<PluralField> sports) {
        this.sports = sports;
    }

    /**
     * @return the tags
     */
    public List<PluralField> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<PluralField> tags) {
        this.tags = tags;
    }

    /**
     * @return the turnOffs
     */
    public List<PluralField> getTurnOffs() {
        return turnOffs;
    }

    /**
     * @param turnOffs the turnOffs to set
     */
    public void setTurnOffs(List<PluralField> turnOffs) {
        this.turnOffs = turnOffs;
    }

    /**
     * @return the tvShows
     */
    public List<PluralField> getTvShows() {
        return tvShows;
    }

    /**
     * @param tvShows the tvShows to set
     */
    public void setTvShows(List<PluralField> tvShows) {
        this.tvShows = tvShows;
    }

    /**
     * @return the urls
     */
    public List<PluralField> getUrls() {
        return urls;
    }

    /**
     * @param urls the urls to set
     */
    public void setUrls(List<PluralField> urls) {
        this.urls = urls;
    }
}
