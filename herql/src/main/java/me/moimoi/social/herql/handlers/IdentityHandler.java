/*
 * Copyright 2011 The Apache Software Foundation.
 *
 * Licensed under the Apache License,Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.moimoi.social.herql.handlers;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.moimoi.social.herql.domain.ProfileType;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.IdentityService;
import net.sf.cglib.reflect.FastClass;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.social.opensocial.model.BodyType;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.Person.Gender;
import org.apache.shindig.social.opensocial.model.Url;
import sun.misc.BASE64Encoder;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.social.opensocial.model.Address;

/**
 *
 * @author ManzoorS
 */
@Service(name = "identity")
public class IdentityHandler {

    private final IdentityService idService;
    private final ContainerConfig config;

    @Inject
    public IdentityHandler(IdentityService idService, ContainerConfig config) {
        this.idService = idService;
        this.config = config;
    }

    @Operation(httpMethods = "POST", bodyParam = "entity", path = "/{userId}")
    public Future<?> add(RequestItem request) {
        //{"lastLogin":"2011-05-31T18:25:23.846Z","joined":"2011-05-31T18:25:23.846Z","active":true,"profiles":[],"password":"mypassword","loginName":"suhail"}
        return ImmediateFuture.newInstance(Boolean.TRUE);
    }

    @Operation(httpMethods = "GET", path = "/{userId}")
    public Future<?> get(RequestItem request) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        SocialIdentity identity = new SocialIdentity();
        identity.setActive(Boolean.TRUE);
        identity.setJoined(new Date());
        identity.setLastLogin(new Date());
        identity.setLoginName(request.getParameter("userId"));

        MessageDigest md = MessageDigest.getInstance("SHA-256"); //step 2
        md.update("password".getBytes("UTF-8")); //step 3

        byte raw[] = md.digest(); //step 4
        String hash = (new BASE64Encoder()).encode(raw); //step 5
        identity.setPassword(hash);


        SocialPerson profile = new SocialPerson();
        profile.setId("suhail");
        profile.setBirthday(new Date());
        profile.setDefaultProfile(Boolean.TRUE);
        profile.setNickname("Ole Silver");

        identity.getProfiles().add(profile);

        idService.foo();
        
        getMethodNames(profile);

        return ImmediateFuture.newInstance(identity);
    }

    private void getMethodNames(SocialPerson profile) {
        final List<String> fe = Lists.newArrayList("profile.birthday", "profile.defaultProfile", "profile.nickname");
        Predicate<String> filter = new Predicate<String>() {

            @Override
            public boolean apply(String s) {                
                return !fe.contains(s);
            }
        };

        init();
        Collection<String> zWords = Collections2.filter(fse, filter);
        Iterator<String> properties = zWords.iterator();
        String name = null;
        while (properties.hasNext()) {     
            name = properties.next();
            int m =  personFields.get(name);                        
            Object[] args = {null};
            try {                
                c.invoke(m, profile, args);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
            }catch (InvocationTargetException ex) {             
            }           
        }
    }
    
    private void init() {
        if(personFields != null) return;         
        try {                          
             personFields = new ImmutableMap.Builder<String, Integer>()
            .put("profile.displayName", c.getIndex("setDisplayName", new Class[]{ String.class }))                                          
            .put("profile.aboutMe", c.getIndex("setAboutMe", new Class[]{ String.class }))            
            .put("profile.age", c.getIndex("setAge", new Class[]{ Integer.class }))                     
            .put("profile.bodyType", c.getIndex("setBodyType", new Class[]{ BodyType.class }))                     
            .put("profile.birthday", c.getIndex("setBirthday", new Class[]{ Date.class }))                     
            .put("profile.children", c.getIndex("setChildren", new Class[]{ String.class }))                     
            .put("profile.ethnicity", c.getIndex("setEthnicity", new Class[]{ String.class }))                     
            .put("profile.fashion", c.getIndex("setFashion", new Class[]{ String.class }))                     
            .put("profile.gender", c.getIndex("setGender", new Class[]{ Gender.class }))                     
            .put("profile.happiestWhen", c.getIndex("setHappiestWhen", new Class[]{ String.class }))                     
            .put("profile.hasApp", c.getIndex("setHasApp", new Class[]{ Boolean.class }))                     
            .put("profile.humor", c.getIndex("setHumor", new Class[]{ String.class }))                     
            .put("profile.jobInterests", c.getIndex("setJobInterests", new Class[]{ String.class }))                     
            .put("profile.updated", c.getIndex("setUpdated", new Class[]{ Date.class }))                     
            .put("profile.livingArrangement", c.getIndex("setLivingArrangement", new Class[]{ String.class }))                     
            .put("profile.name", c.getIndex("setName", new Class[]{ Name.class }))                     
            .put("profile.nickname", c.getIndex("setNickname", new Class[]{ String.class }))                     
            .put("profile.pets", c.getIndex("setPets", new Class[]{ String.class }))                     
            .put("profile.preferredUsername", c.getIndex("setPreferredUsername", new Class[]{ String.class }))                     
            .put("profile.politicalViews", c.getIndex("setPoliticalViews", new Class[]{ String.class }))                     
            .put("profile.profileVideo", c.getIndex("setProfileVideo", new Class[]{ Url.class }))                     
            .put("profile.profileSong", c.getIndex("setProfileSong", new Class[]{ Url.class }))                     
            .put("profile.relationshipStatus", c.getIndex("setRelationshipStatus", new Class[]{ String.class }))                     
            .put("profile.religion", c.getIndex("setReligion", new Class[]{ String.class }))                     
            .put("profile.scaredOf", c.getIndex("setScaredOf", new Class[]{ String.class }))                     
            .put("profile.romance", c.getIndex("setRomance", new Class[]{ String.class }))                     
            .put("profile.sexualOrientation", c.getIndex("setSexualOrientation", new Class[]{ String.class }))                     
            .put("profile.status", c.getIndex("setStatus", new Class[]{ String.class }))                     
            .put("profile.utcOffset", c.getIndex("setUtcOffset", new Class[]{ Long.class }))                                 
            .put("profile.profileUrl", c.getIndex("setProfileUrl", new Class[]{ String.class }))                                          
            .put("profile.thumbnailUrl", c.getIndex("setThumbnailUrl", new Class[]{ String.class}))                     
            .put("profile.owner", c.getIndex("setIsOwner", new Class[]{ boolean.class }))
            .put("profile.viewer", c.getIndex("setIsViewer", new Class[]{ boolean.class }))                     
            .put("profile.nicotine", c.getIndex("setSmoker", new Class[]{ Enum.class }))                     
            .put("profile.alcohol", c.getIndex("setDrinker", new Class[]{ Enum.class }))                     
            .put("profile.currentLocation", c.getIndex("setCurrentLocation", new Class[]{ Address.class }))                     
            .put("profile.type", c.getIndex("setType", new Class[]{ ProfileType.class }))                     
            .put("profile.profileManagers", c.getIndex("setProfileManagers", new Class[]{ List.class }))                     
            .put("profile.defaultProfile", c.getIndex("setDefaultProfile", new Class[]{ Boolean.class }))                     
            .put("profile.presence", c.getIndex("setNetworkPresence", new Class[]{ Enum.class }))                             
            .put("profile.seeking", c.getIndex("setLookingFor", new Class[]{ List.class }))                     
            .put("profile.organizations", c.getIndex("setOrganizations", new Class[]{ List.class }))                     
            .put("profile.phoneNumbers", c.getIndex("setPhoneNumbers", new Class[]{ List.class }))                     
            .put("profile.photos", c.getIndex("setPhotos", new Class[]{ List.class }))                     
            .put("profile.accounts", c.getIndex("setAccounts", new Class[]{ List.class }))                     
            .put("profile.activities", c.getIndex("setActivities", new Class[]{ List.class }))                     
            .put("profile.addresses", c.getIndex("setAddresses", new Class[]{ List.class }))                     
            .put("profile.food", c.getIndex("setFood", new Class[]{ List.class }))                     
            .put("profile.languagesSpoken", c.getIndex("setLanguagesSpoken", new Class[]{ List.class }))                     
            .put("profile.heroes", c.getIndex("setHeroes", new Class[]{ List.class }))                     
            .put("profile.ims", c.getIndex("setIms", new Class[]{ List.class }))                     
            .put("profile.interests", c.getIndex("setInterests", new Class[]{ List.class }))                     
            .put("profile.books", c.getIndex("setBooks", new Class[]{ List.class }))                     
            .put("profile.cars", c.getIndex("setCars", new Class[]{ List.class }))                     
            .put("profile.emails", c.getIndex("setEmails", new Class[]{ List.class }))                     
            .put("profile.music", c.getIndex("setMusic", new Class[]{ List.class }))                     
            .put("profile.movies", c.getIndex("setMovies", new Class[]{ List.class }))                     
            .put("profile.quotes", c.getIndex("setQuotes", new Class[]{ List.class }))                     
            .put("profile.sports", c.getIndex("setSports", new Class[]{ List.class }))                     
            .put("profile.tags", c.getIndex("setTags", new Class[]{ List.class }))                     
            .put("profile.turnOffs", c.getIndex("setTurnOffs", new Class[]{ List.class }))                     
            .put("profile.turnOns", c.getIndex("setTurnOns", new Class[]{ List.class }))                     
            .put("profile.tvShows", c.getIndex("setTvShows", new Class[]{ List.class }))                     
            .put("profile.urls", c.getIndex("setUrls", new Class[]{ List.class })).build();
             
             fse = Lists.newArrayList(personFields.keySet());
             
        } catch (SecurityException ex) {
            Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    final FastClass c = FastClass.create(SocialPerson.class);             
    private static ImmutableMap<String, Integer> personFields = null;
    private static final Logger LOG = Logger.getLogger(IdentityHandler.class.getCanonicalName());
    private static List<String> fse = null;
}
