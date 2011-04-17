/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.Datastore;
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
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Organization;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Person.Gender;
import org.apache.shindig.social.opensocial.model.Smoker;
import org.apache.shindig.social.opensocial.model.Url;

/**
 *
 * @author Suhail
 */
public class MutablePerson implements MutableObject, Person {
    
    private Person delegate;
    private UpdateOperations update;        
    
    public MutablePerson() {        
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
    public void setDelegate(Person delegate) {
        this.delegate = delegate;
    }
    
    public void setUtcOffset(Long utcOffset) {
        delegate.setUtcOffset(utcOffset);
    }

    public void setUrls(List<Url> urls) {
        delegate.setUrls(urls);
    }

    public void setUpdated(Date updated) {
        delegate.setUpdated(updated);
    }

    public void setTvShows(List<String> tvShows) {
        delegate.setTvShows(tvShows);
    }

    public void setTurnOns(List<String> turnOns) {
        delegate.setTurnOns(turnOns);
    }

    public void setTurnOffs(List<String> turnOffs) {
        delegate.setTurnOffs(turnOffs);
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        delegate.setThumbnailUrl(thumbnailUrl);
    }

    public void setTags(List<String> tags) {
        delegate.setTags(tags);
    }

    public void setStatus(String status) {
        delegate.setStatus(status);
    }

    public void setSports(List<String> sports) {
        delegate.setSports(sports);
    }

    public void setSmoker(Enum<Smoker> newSmoker) {
        delegate.setSmoker(newSmoker);
    }

    public void setSexualOrientation(String sexualOrientation) {
        delegate.setSexualOrientation(sexualOrientation);
    }

    public void setScaredOf(String scaredOf) {
        delegate.setScaredOf(scaredOf);
    }

    public void setRomance(String romance) {
        delegate.setRomance(romance);
    }

    public void setReligion(String religion) {
        delegate.setReligion(religion);
    }

    public void setRelationshipStatus(String relationshipStatus) {
        delegate.setRelationshipStatus(relationshipStatus);
    }

    public void setQuotes(List<String> quotes) {
        delegate.setQuotes(quotes);
    }

    public void setProfileVideo(Url profileVideo) {
        delegate.setProfileVideo(profileVideo);
    }

    public void setProfileUrl(String profileUrl) {
        delegate.setProfileUrl(profileUrl);
    }

    public void setProfileSong(Url profileSong) {
        delegate.setProfileSong(profileSong);
    }

    public void setPreferredUsername(String preferredString) {
        delegate.setPreferredUsername(preferredString);
    }

    public void setPoliticalViews(String politicalViews) {
        delegate.setPoliticalViews(politicalViews);
    }

    public void setPhotos(List<ListField> photos) {
        delegate.setPhotos(photos);
    }

    public void setPhoneNumbers(List<ListField> phoneNumbers) {
        delegate.setPhoneNumbers(phoneNumbers);
    }

    public void setPets(String pets) {
        delegate.setPets(pets);
    }

    public void setOrganizations(List<Organization> organizations) {
        delegate.setOrganizations(organizations);
    }

    public void setNickname(String nickname) {
        delegate.setNickname(nickname);
    }

    public void setNetworkPresence(Enum<NetworkPresence> networkPresence) {
        delegate.setNetworkPresence(networkPresence);
    }

    public void setName(Name name) {
        delegate.setName(name);
    }

    public void setMusic(List<String> music) {
        delegate.setMusic(music);
    }

    public void setMovies(List<String> movies) {
        delegate.setMovies(movies);
    }

    public void setLookingFor(List<Enum<LookingFor>> lookingFor) {
        delegate.setLookingFor(lookingFor);
    }

    public void setLivingArrangement(String livingArrangement) {
        delegate.setLivingArrangement(livingArrangement);
    }

    public void setLanguagesSpoken(List<String> languagesSpoken) {
        delegate.setLanguagesSpoken(languagesSpoken);
    }

    public void setJobInterests(String jobInterests) {
        delegate.setJobInterests(jobInterests);
    }

    public void setIsViewer(boolean isViewer) {
        delegate.setIsViewer(isViewer);
    }

    public void setIsOwner(boolean isOwner) {
        delegate.setIsOwner(isOwner);
    }

    public void setInterests(List<String> interests) {
        delegate.setInterests(interests);
    }

    public void setIms(List<ListField> ims) {
        delegate.setIms(ims);
    }

    public void setId(String id) {
        delegate.setId(id);
    }

    public void setHumor(String humor) {
        delegate.setHumor(humor);
    }

    public void setHeroes(List<String> heroes) {
        delegate.setHeroes(heroes);
    }

    public void setHasApp(Boolean hasApp) {
        delegate.setHasApp(hasApp);
    }

    public void setHappiestWhen(String happiestWhen) {
        delegate.setHappiestWhen(happiestWhen);
    }

    public void setGender(Gender newGender) {
        delegate.setGender(newGender);
    }

    public void setFood(List<String> food) {
        delegate.setFood(food);
    }

    public void setFashion(String fashion) {
        delegate.setFashion(fashion);
    }

    public void setEthnicity(String ethnicity) {
        delegate.setEthnicity(ethnicity);
    }

    public void setEmails(List<ListField> emails) {
        delegate.setEmails(emails);
    }

    public void setDrinker(Enum<Drinker> newDrinker) {
        delegate.setDrinker(newDrinker);
    }

    public void setDisplayName(String displayName) {
        delegate.setDisplayName(displayName);
    }

    public void setCurrentLocation(Address currentLocation) {
        delegate.setCurrentLocation(currentLocation);
    }

    public void setChildren(String children) {
        delegate.setChildren(children);
    }

    public void setCars(List<String> cars) {
        delegate.setCars(cars);
    }

    public void setBooks(List<String> books) {
        delegate.setBooks(books);
    }

    public void setBodyType(BodyType bodyType) {
        delegate.setBodyType(bodyType);
    }

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

    public void setAddresses(List<Address> addresses) {
        delegate.setAddresses(addresses);
    }

    public void setActivities(List<String> activities) {
        delegate.setActivities(activities);
    }

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
