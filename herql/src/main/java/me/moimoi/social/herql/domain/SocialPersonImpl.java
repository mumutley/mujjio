/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.BodyType;
import org.apache.shindig.social.opensocial.model.Drinker;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Organization;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Smoker;
import org.apache.shindig.social.opensocial.model.Url;

/**
 * Implementing the Person Object
 * @author Suhail
 */
@Entity(value="person", noClassnameStored=false)
public class SocialPersonImpl implements Person {
    
    @Id private String id;
    
    private String displayName;
    private String aboutMe;
    private List<Account> accounts;    
    private List<String> activities;
    private List<Address> addresses;
    private Integer age;
    private BodyType bodyType;
    private Date birthday;
    private List<String> books;
    private List<String> cars;
    private String children;
    private Address currentLocation;
    private Enum<Drinker> newDrinker;
    private List<ListField> emails;
    private String ethnicity;
    private String fashion;
    private Gender gender;
    private List<String> food;
    private String happiestWhen;
    private Boolean hasApp;
    private List<String> heroes;
    private String humor;    
    private List<ListField> ims;
    private List<String> interests;
    private String jobInterests;
    private List<String> languagesSpoken;
    private Date updated;
    private String livingArrangement;
    private List<Enum<LookingFor>> lookingFor;
    private List<String> movies;
    private Name name;
    private List<String> music;
    private Enum<NetworkPresence> networkPresence;
    private String nickname;
    private String pets;
    private List<Organization> organizations;
    private List<ListField> phoneNumbers;
    private List<ListField> photos;
    private String preferredUsername;
    private String politicalViews;
    private Url profileVideo;
    private Url profileSong;
    private List<String> quotes;
    private String relationshipStatus;
    private String religion;
    private String scaredOf;
    private String romance;
    private String sexualOrientation;
    private List<String> sports;
    private String status;
    private List<String> tags;
    private Enum<Smoker> smoker;
    private List<String> turnOffs;
    private Long utcOffset;
    private List<String> turnOns;
    private List<String> tvShows;
    private List<Url> urls;
    private boolean viewer;
    private String profileUrl;
    private String thumbnailUrl;
    private boolean owner;
    
    public SocialPersonImpl() {
        accounts = new LinkedList<Account>();
    }
    
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getAboutMe() {
        return this.aboutMe;
    }

    @Override
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public List<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public List<String> getActivities() {
        return this.activities;
    }

    @Override
    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    @Override
    public List<Address> getAddresses() {
        return this.addresses;
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Map<String, ?> getAppData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAppData(Map<String, ?> appData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getBirthday() {
        return this.birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public BodyType getBodyType() {
        return bodyType;
    }

    @Override
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public List<String> getBooks() {
        return books;
    }

    @Override
    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public List<String> getCars() {
        return cars;
    }

    @Override
    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    @Override
    public String getChildren() {
        return children;
    }

    @Override
    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public Address getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public Enum<Drinker> getDrinker() {
        return newDrinker;
    }

    @Override
    public void setDrinker(Enum<Drinker> newDrinker) {
        this.newDrinker = newDrinker;
    }

    @Override
    public List<ListField> getEmails() {
        return emails;
    }

    @Override
    public void setEmails(List<ListField> emails) {
        this.emails = emails;
    }

    @Override
    public String getEthnicity() {
        return ethnicity;
    }

    @Override
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    @Override
    public String getFashion() {
        return fashion;
    }

    @Override
    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

    @Override
    public List<String> getFood() {
        return food;
    }

    @Override
    public void setFood(List<String> food) {
        this.food = food;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String getHappiestWhen() {
        return happiestWhen;
    }

    @Override
    public void setHappiestWhen(String happiestWhen) {
        this.happiestWhen = happiestWhen;
    }

    @Override
    public Boolean getHasApp() {
        return hasApp;
    }

    @Override
    public void setHasApp(Boolean hasApp) {
        this.hasApp = hasApp;
    }

    @Override
    public List<String> getHeroes() {
        return heroes;
    }

    @Override
    public void setHeroes(List<String> heroes) {
        this.heroes = heroes;
    }

    @Override
    public String getHumor() {
        return this.humor;
    }

    @Override
    public void setHumor(String humor) {
        this.humor = humor;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public List<ListField> getIms() {
        return ims;
    }

    @Override
    public void setIms(List<ListField> ims) {
        this.ims = ims;
    }

    @Override
    public List<String> getInterests() {
        return interests;
    }

    @Override
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String getJobInterests() {
        return jobInterests;
    }

    @Override
    public void setJobInterests(String jobInterests) {
        this.jobInterests = jobInterests;
    }

    @Override
    public List<String> getLanguagesSpoken() {
        return languagesSpoken;
    }

    @Override
    public void setLanguagesSpoken(List<String> languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String getLivingArrangement() {
        return livingArrangement;
    }

    @Override
    public void setLivingArrangement(String livingArrangement) {
        this.livingArrangement = livingArrangement;
    }

    @Override
    public List<Enum<LookingFor>> getLookingFor() {
        return lookingFor;
    }

    @Override
    public void setLookingFor(List<Enum<LookingFor>> lookingFor) {
        this.lookingFor = lookingFor;
    }

    @Override
    public List<String> getMovies() {
        return movies;
    }

    @Override
    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    @Override
    public List<String> getMusic() {
        return music;
    }

    @Override
    public void setMusic(List<String> music) {
        this.music = music;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public Enum<NetworkPresence> getNetworkPresence() {
        return networkPresence;
    }

    @Override
    public void setNetworkPresence(Enum<NetworkPresence> networkPresence) {
        this.networkPresence = networkPresence;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public List<Organization> getOrganizations() {
        return this.organizations;
    }

    @Override
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String getPets() {
        return pets;
    }

    @Override
    public void setPets(String pets) {
        this.pets = pets;
    }

    @Override
    public List<ListField> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public void setPhoneNumbers(List<ListField> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public List<ListField> getPhotos() {
        return this.photos;
    }

    @Override
    public void setPhotos(List<ListField> photos) {
        this.photos = photos;
    }

    @Override
    public String getPoliticalViews() {
        return politicalViews;
    }

    @Override
    public void setPoliticalViews(String politicalViews) {
        this.politicalViews = politicalViews;
    }

    @Override
    public String getPreferredUsername() {
        return this.preferredUsername;
    }

    @Override
    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    @Override
    public Url getProfileSong() {
        return this.profileSong;
    }

    @Override
    public void setProfileSong(Url profileSong) {
        this.profileSong = profileSong;
    }

    @Override
    public Url getProfileVideo() {
        return this.profileVideo;
    }

    @Override
    public void setProfileVideo(Url profileVideo) {
        this.profileVideo = profileVideo;
    }

    @Override
    public List<String> getQuotes() {
        return quotes;
    }

    @Override
    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    @Override
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @Override
    public String getReligion() {
        return this.religion;
    }

    @Override
    public void setReligion(String religion) {
        this.religion = religion;
    }

    @Override
    public String getRomance() {
        return this.romance;
    }

    @Override
    public void setRomance(String romance) {
        this.romance = romance;
    }

    @Override
    public String getScaredOf() {
        return this.scaredOf;
    }

    @Override
    public void setScaredOf(String scaredOf) {
        this.scaredOf = scaredOf;
    }

    @Override
    public String getSexualOrientation() {
        return sexualOrientation;
    }

    @Override
    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    @Override
    public Enum<Smoker> getSmoker() {
        return this.smoker;
    }

    @Override
    public void setSmoker(Enum<Smoker> smoker) {
        this.smoker = smoker;
    }

    @Override
    public List<String> getSports() {
        return this.sports;
    }

    @Override
    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public List<String> getTags() {
        return this.tags;
    }

    @Override
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public Long getUtcOffset() {
        return utcOffset;
    }

    @Override
    public void setUtcOffset(Long utcOffset) {
        this.utcOffset = utcOffset;
    }

    @Override
    public List<String> getTurnOffs() {
        return this.turnOffs;
    }

    @Override
    public void setTurnOffs(List<String> turnOffs) {
        this.turnOffs = turnOffs;
    }

    @Override
    public List<String> getTurnOns() {
        return this.turnOns;
    }

    @Override
    public void setTurnOns(List<String> turnOns) {
        this.turnOns = turnOns;
    }

    @Override
    public List<String> getTvShows() {
        return this.tvShows;
    }

    @Override
    public void setTvShows(List<String> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public List<Url> getUrls() {
        return this.urls;
    }

    @Override
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    @Override
    public boolean getIsOwner() {
        return this.owner;
    }

    @Override
    public void setIsOwner(boolean owner) {
        this.owner = owner;
    }

    @Override
    public boolean getIsViewer() {
        return this.viewer;
    }

    @Override
    public void setIsViewer(boolean viewer) {
        this.viewer = viewer;
    }

    @Override
    public String getProfileUrl() {
        return profileUrl;
    }

    @Override
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Override
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
}
