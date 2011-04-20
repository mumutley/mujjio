/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.query.UpdateOperations;
import java.util.Date;
import java.util.List;
import java.util.Map;
import me.moimoi.social.herql.services.MutableObject;
import me.moimoi.social.herql.services.interceptors.Mutator;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.social.opensocial.model.Account;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.BodyType;
import org.apache.shindig.social.opensocial.model.Drinker;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.apache.shindig.social.opensocial.model.MutablePerson;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Organization;
import org.apache.shindig.social.opensocial.model.MutablePerson.Gender;
import org.apache.shindig.social.opensocial.model.Smoker;
import org.apache.shindig.social.opensocial.model.Url;

/**
 *
 * @author Suhail
 */
public class MutableAgent implements MutableObject, MutablePerson {
    
    private MutablePerson delegate;
    private UpdateOperations update;        
    
    public MutableAgent() {        
    }
    
    @Override
    public void setUpdateOperations(UpdateOperations update) {
        this.update = update;
    }

    @Override
    public UpdateOperations<SocialPerson> getUpdateOperation() {
        return update;
    }
    
    @Override
    public void setDelegate(MutablePerson delegate) {
        this.delegate = delegate;
    }
    
    @Override @Mutator(name="utcOffset")
    public void setUtcOffset(Long utcOffset) {
        delegate.setUtcOffset(utcOffset);
    }

    @Override @Mutator(name="urls")
    public void setUrls(List<Url> urls) {
        delegate.setUrls(urls);
    }

    @Override @Mutator(name="updated")
    public void setUpdated(Date updated) {
        delegate.setUpdated(updated);
    }
    
    @Override @Mutator(name="tvShows")
    public void setTvShows(List<String> tvShows) {
        delegate.setTvShows(tvShows);
    }

    @Override @Mutator(name="turnOns")
    public void setTurnOns(List<String> turnOns) {
        delegate.setTurnOns(turnOns);
    }

    @Override @Mutator(name="turnOffs")
    public void setTurnOffs(List<String> turnOffs) {
        delegate.setTurnOffs(turnOffs);
    }

    @Override @Mutator(name="thumbnailUrl")
    public void setThumbnailUrl(String thumbnailUrl) {
        delegate.setThumbnailUrl(thumbnailUrl);
    }

    @Override @Mutator(name="tags")    
    public void setTags(List<String> tags) {
        delegate.setTags(tags);
    }

    @Override @Mutator(name="status")    
    public void setStatus(String status) {
        delegate.setStatus(status);
    }

    @Override @Mutator(name="sports")    
    public void setSports(List<String> sports) {
        delegate.setSports(sports);
    }

    @Override @Mutator(name="smoker")
    public void setSmoker(Enum<Smoker> newSmoker) {
        delegate.setSmoker(newSmoker);
    }

    @Override @Mutator(name="sexualOrientation")
    public void setSexualOrientation(String sexualOrientation) {
        delegate.setSexualOrientation(sexualOrientation);
    }

    @Override @Mutator(name="scaredOf")
    public void setScaredOf(String scaredOf) {
        delegate.setScaredOf(scaredOf);
    }

    @Override @Mutator(name="romance")
    public void setRomance(String romance) {
        delegate.setRomance(romance);
    }

    @Override @Mutator(name="religion")
    public void setReligion(String religion) {
        delegate.setReligion(religion);
    }

    @Override @Mutator(name="relationshipStatus")
    public void setRelationshipStatus(String relationshipStatus) {
        delegate.setRelationshipStatus(relationshipStatus);
    }

    @Override @Mutator(name="quotes")
    public void setQuotes(List<String> quotes) {
        delegate.setQuotes(quotes);
    }

    @Override @Mutator(name="profileVideo")
    public void setProfileVideo(Url profileVideo) {
        delegate.setProfileVideo(profileVideo);
    }

    @Override @Mutator(name="profileUrl")    
    public void setProfileUrl(String profileUrl) {
        delegate.setProfileUrl(profileUrl);
    }

    @Override @Mutator(name="profileSong")    
    public void setProfileSong(Url profileSong) {
        delegate.setProfileSong(profileSong);
    }

    @Override @Mutator(name="preferredString")    
    public void setPreferredUsername(String preferredString) {
        delegate.setPreferredUsername(preferredString);
    }

    @Override @Mutator(name="politicalViews")    
    public void setPoliticalViews(String politicalViews) {
        delegate.setPoliticalViews(politicalViews);
    }

    @Override @Mutator(name="photos")        
    public void setPhotos(List<ListField> photos) {
        delegate.setPhotos(photos);
    }

    @Override @Mutator(name="phoneNumbers")            
    public void setPhoneNumbers(List<ListField> phoneNumbers) {
        delegate.setPhoneNumbers(phoneNumbers);
    }

    @Override @Mutator(name="phoneNumbers")                
    public void setPets(String pets) {
        delegate.setPets(pets);
    }

    @Override @Mutator(name="organizations")    
    public void setOrganizations(List<Organization> organizations) {
        delegate.setOrganizations(organizations);
    }

    @Override @Mutator(name="nickname")        
    public void setNickname(String nickname) {
        delegate.setNickname(nickname);
    }

    @Override @Mutator(name="networkPresence")    
    public void setNetworkPresence(Enum<NetworkPresence> networkPresence) {
        delegate.setNetworkPresence(networkPresence);
    }

    @Override @Mutator(name="name")        
    public void setName(Name name) {
        delegate.setName(name);
    }

    @Override @Mutator(name="music")            
    public void setMusic(List<String> music) {
        delegate.setMusic(music);
    }

    @Override @Mutator(name="movies")    
    public void setMovies(List<String> movies) {
        delegate.setMovies(movies);
    }

    @Override @Mutator(name="lookingFor")    
    public void setLookingFor(List<Enum<LookingFor>> lookingFor) {
        delegate.setLookingFor(lookingFor);
    }

    @Override @Mutator(name="livingArrangement")    
    public void setLivingArrangement(String livingArrangement) {
        delegate.setLivingArrangement(livingArrangement);
    }

    @Override @Mutator(name="languagesSpoken")    
    public void setLanguagesSpoken(List<String> languagesSpoken) {
        delegate.setLanguagesSpoken(languagesSpoken);
    }

    @Override @Mutator(name="jobInterests")    
    public void setJobInterests(String jobInterests) {
        delegate.setJobInterests(jobInterests);
    }

    @Override @Mutator(name="isViewer")        
    public void setIsViewer(boolean isViewer) {
        delegate.setIsViewer(isViewer);
    }

    @Override @Mutator(name="isOwner")            
    public void setIsOwner(boolean isOwner) {
        delegate.setIsOwner(isOwner);
    }

    @Override @Mutator(name="interests")            
    public void setInterests(List<String> interests) {
        delegate.setInterests(interests);
    }

    @Override @Mutator(name="ims")            
    public void setIms(List<ListField> ims) {
        delegate.setIms(ims);
    }

    public void setId(String id) {
        delegate.setId(id);
    }

    @Override @Mutator(name="humor")                
    public void setHumor(String humor) {
        delegate.setHumor(humor);
    }

    @Override @Mutator(name="heroes")                
    public void setHeroes(List<String> heroes) {
        delegate.setHeroes(heroes);
    }

    @Override @Mutator(name="hasApp")                
    public void setHasApp(Boolean hasApp) {
        delegate.setHasApp(hasApp);
    }

    @Override @Mutator(name="happiestWhen")                
    public void setHappiestWhen(String happiestWhen) {
        delegate.setHappiestWhen(happiestWhen);
    }

    @Override @Mutator(name="newGender")                    
    public void setGender(Gender newGender) {
        delegate.setGender(newGender);
    }

    @Override @Mutator(name="food")                        
    public void setFood(List<String> food) {
        delegate.setFood(food);
    }

    @Override @Mutator(name="fashion")                            
    public void setFashion(String fashion) {
        delegate.setFashion(fashion);
    }

    @Override @Mutator(name="ethnicity")                            
    public void setEthnicity(String ethnicity) {
        delegate.setEthnicity(ethnicity);
    }

    @Override @Mutator(name="emails")                            
    public void setEmails(List<ListField> emails) {
        delegate.setEmails(emails);
    }

    @Override @Mutator(name="newDrinker")    
    public void setDrinker(Enum<Drinker> newDrinker) {
        delegate.setDrinker(newDrinker);
    }

    @Override @Mutator(name="displayName")    
    public void setDisplayName(String displayName) {
        delegate.setDisplayName(displayName);
    }

    @Override @Mutator(name="currentLocation")    
    public void setCurrentLocation(Address currentLocation) {
        delegate.setCurrentLocation(currentLocation);
    }

    @Override @Mutator(name="children")    
    public void setChildren(String children) {
        delegate.setChildren(children);
    }

    @Override @Mutator(name="cars")    
    public void setCars(List<String> cars) {
        delegate.setCars(cars);
    }

    @Override @Mutator(name="books")    
    public void setBooks(List<String> books) {
        delegate.setBooks(books);
    }

    @Override @Mutator(name="bodyType")    
    public void setBodyType(BodyType bodyType) {
        delegate.setBodyType(bodyType);
    }

    @Override @Mutator(name="birthday")    
    public void setBirthday(Date birthday) {
        delegate.setBirthday(birthday);
    }

    public void setAppData(Map<String, ?> appData) {
        delegate.setAppData(appData);
    }
    
    @Override @Mutator(name="age")
    public void setAge(Integer age) {
        delegate.setAge(age);
    }

    @Override @Mutator(name="addresses")    
    public void setAddresses(List<Address> addresses) {
        delegate.setAddresses(addresses);
    }

    @Override @Mutator(name="activities")    
    public void setActivities(List<String> activities) {
        delegate.setActivities(activities);
    }

    @Override @Mutator(name="accounts")    
    public void setAccounts(List<Account> accounts) {
        delegate.setAccounts(accounts);
    }

    
    @Override @Mutator(name="aboutMe")
    public void setAboutMe(String aboutMe) {
        delegate.setAboutMe(aboutMe);
    }

    public Long getUtcOffset() {
        return delegate.getUtcOffset();
    }

    public List<Url> getUrls() {
        return delegate.getUrls();
    }

    public Date getUpdated() {
        return delegate.getUpdated();
    }

    public List<String> getTvShows() {
        return delegate.getTvShows();
    }

    public List<String> getTurnOns() {
        return delegate.getTurnOns();
    }

    public List<String> getTurnOffs() {
        return delegate.getTurnOffs();
    }

    public String getThumbnailUrl() {
        return delegate.getThumbnailUrl();
    }

    public List<String> getTags() {
        return delegate.getTags();
    }

    public String getStatus() {
        return delegate.getStatus();
    }

    public List<String> getSports() {
        return delegate.getSports();
    }

    public Enum<Smoker> getSmoker() {
        return delegate.getSmoker();
    }

    public String getSexualOrientation() {
        return delegate.getSexualOrientation();
    }

    public String getScaredOf() {
        return delegate.getScaredOf();
    }

    public String getRomance() {
        return delegate.getRomance();
    }

    public String getReligion() {
        return delegate.getReligion();
    }

    public String getRelationshipStatus() {
        return delegate.getRelationshipStatus();
    }

    public List<String> getQuotes() {
        return delegate.getQuotes();
    }

    public Url getProfileVideo() {
        return delegate.getProfileVideo();
    }

    public String getProfileUrl() {
        return delegate.getProfileUrl();
    }

    public Url getProfileSong() {
        return delegate.getProfileSong();
    }

    public String getPreferredUsername() {
        return delegate.getPreferredUsername();
    }

    public String getPoliticalViews() {
        return delegate.getPoliticalViews();
    }

    public List<ListField> getPhotos() {
        return delegate.getPhotos();
    }

    public List<ListField> getPhoneNumbers() {
        return delegate.getPhoneNumbers();
    }

    public String getPets() {
        return delegate.getPets();
    }

    public List<Organization> getOrganizations() {
        return delegate.getOrganizations();
    }

    public String getNickname() {
        return delegate.getNickname();
    }

    public Enum<NetworkPresence> getNetworkPresence() {
        return delegate.getNetworkPresence();
    }

    public Name getName() {
        return delegate.getName();
    }

    public List<String> getMusic() {
        return delegate.getMusic();
    }

    public List<String> getMovies() {
        return delegate.getMovies();
    }

    public List<Enum<LookingFor>> getLookingFor() {
        return delegate.getLookingFor();
    }

    public String getLivingArrangement() {
        return delegate.getLivingArrangement();
    }

    public List<String> getLanguagesSpoken() {
        return delegate.getLanguagesSpoken();
    }

    public String getJobInterests() {
        return delegate.getJobInterests();
    }

    public boolean getIsViewer() {
        return delegate.getIsViewer();
    }

    public boolean getIsOwner() {
        return delegate.getIsOwner();
    }

    public List<String> getInterests() {
        return delegate.getInterests();
    }

    public List<ListField> getIms() {
        return delegate.getIms();
    }

    public String getId() {
        return delegate.getId();
    }

    public String getHumor() {
        return delegate.getHumor();
    }

    public List<String> getHeroes() {
        return delegate.getHeroes();
    }

    public Boolean getHasApp() {
        return delegate.getHasApp();
    }

    public String getHappiestWhen() {
        return delegate.getHappiestWhen();
    }

    public Gender getGender() {
        return delegate.getGender();
    }

    public List<String> getFood() {
        return delegate.getFood();
    }

    public String getFashion() {
        return delegate.getFashion();
    }

    public String getEthnicity() {
        return delegate.getEthnicity();
    }

    public List<ListField> getEmails() {
        return delegate.getEmails();
    }

    public Enum<Drinker> getDrinker() {
        return delegate.getDrinker();
    }

    public String getDisplayName() {
        return delegate.getDisplayName();
    }

    public Address getCurrentLocation() {
        return delegate.getCurrentLocation();
    }

    public String getChildren() {
        return delegate.getChildren();
    }

    public List<String> getCars() {
        return delegate.getCars();
    }

    public List<String> getBooks() {
        return delegate.getBooks();
    }

    public BodyType getBodyType() {
        return delegate.getBodyType();
    }

    public Date getBirthday() {
        return delegate.getBirthday();
    }

    public Map<String, ?> getAppData() {
        return delegate.getAppData();
    }

    public Integer getAge() {
        return delegate.getAge();
    }

    public List<Address> getAddresses() {
        return delegate.getAddresses();
    }

    public List<String> getActivities() {
        return delegate.getActivities();
    }

    public List<Account> getAccounts() {
        return delegate.getAccounts();
    }

    public String getAboutMe() {
        return delegate.getAboutMe();
    }    
        
}
