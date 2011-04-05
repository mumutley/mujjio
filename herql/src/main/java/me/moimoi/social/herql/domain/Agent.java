package me.moimoi.social.herql.domain;


import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

@Entity("agent")
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

    @Embedded(concreteClass = LinkedList.class)
    private List<Account> account;
    
    public static Agent create() {
        return new Agent();
    }
    
    private Agent() { 
        super();
        account = new LinkedList<Account>();
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
    public List<Account> getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(List<Account> account) {
        this.account = account;
    }
}
