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
package me.moimoi.social.herqlweb.handlers;

import com.google.code.morphia.Key;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
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
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
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
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.social.opensocial.model.Address;

/**
 *
 * @author ManzoorS
 */
@Service(name = "identity")
public class IdentityHandler {

    private final SocialIdentityService idService;
    private final SocialPersonService personService;
    private final ContainerConfig config;
    private static final int FIRST = 0;
    
    @Inject
    public IdentityHandler(SocialPersonService personService, SocialIdentityService idService, ContainerConfig config) {
        this.idService = idService;
        this.personService = personService;
        this.config = config;
    }

    @Operation(httpMethods = "POST", bodyParam = "entity")
    public Future<?> create(RequestItem request) {
        SocialIdentity identity = request.getTypedParameter("entity", SocialIdentity.class);
        identity.setJoined(new Date());
        //http://localhost:8080/social/rest/identity
        //{"profiles":[{"nickname":"ski","birthday":"1968-11-10T0:0:0.0Z","id":"suhail","defaultProfile":true}],"password":"password=","loginName":"suhail"}}        
        //TODO use task executor here.        
        Key<SocialIdentity> id = idService.create(identity);
        return ImmediateFuture.newInstance(identity.getProfiles().get(IdentityHandler.FIRST));
    }
    
    @Operation(httpMethods = "PUT", bodyParam = "entity", path = "/{userId}")
    public Future<?> addProfile(RequestItem request) {
        //http://localhost:8080/social/rest/identity/suhail
        //{"profiles":[{"id":"moto"}]}}        

        String userId = request.getParameter("userId");
        SocialIdentity input = request.getTypedParameter("entity", SocialIdentity.class);
        
        idService.save(userId, input.getProfiles().get(IdentityHandler.FIRST));        
        return ImmediateFuture.newInstance(input);
    }
    
    @Operation(httpMethods = "PUT", bodyParam = "entity", path = "/{userId}/@move")
    public Future<?> moveProfile(RequestItem request) {
        //http://localhost:8080/social/rest/identity/suhail
        //{"profiles":[{"id":"moto"}]}}        

        String userId = request.getParameter("userId");
        SocialIdentity input = request.getTypedParameter("entity", SocialIdentity.class);
        
        idService.save(userId, input.getProfiles().get(IdentityHandler.FIRST));        
        return ImmediateFuture.newInstance(input);
    }
    
    @Operation(httpMethods = "GET", path = "/{userId}")
    public Future<?> get(RequestItem request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SocialIdentity sid = idService.get(request.getParameter("userId"));
        getIdentityNames(sid, request);
        return ImmediateFuture.newInstance(sid);
        
        /*SocialIdentity identity = new SocialIdentity();
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
        id.pr.setId("suhail");
        id.pr.setBirthday(new Date());
        id.pr.setDefaultProfile(Boolean.TRUE);
        id.pr.setNickname("Ole Silver");

        identity.getProfiles().add(profile);        
        
        getMethodNames(profile);

        return ImmediateFuture.newInstance(identity);*/
    }

    private void getIdentityNames(SocialIdentity si, RequestItem request) {
        String container = Objects.firstNonNull(request.getToken().getContainer(), ContainerConfig.DEFAULT_CONTAINER);
        List<Object> fieldNames =  config.getList(container,"${Cur['gadgets.features'].opensocial.supportedFields.identity}");
        LOG.log(Level.INFO, "identity fields {0}", fieldNames);
    }
    
    private void getMethodNames(SocialPerson profile) {
        final List<String> fe = Lists.newArrayList("id.pr.birthday", "id.pr.defaultProfile", "id.pr.nickname");
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
                personClass.invoke(m, profile, args);
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
            .put("id.pr.displayName", personClass.getIndex("setDisplayName", new Class[]{ String.class }))
            .put("id.pr.displayName", personClass.getIndex("setDisplayName", new Class[]{ String.class }))                                          
            .put("id.pr.aboutMe", personClass.getIndex("setAboutMe", new Class[]{ String.class }))            
            .put("id.pr.age", personClass.getIndex("setAge", new Class[]{ Integer.class }))                     
            .put("id.pr.bodyType", personClass.getIndex("setBodyType", new Class[]{ BodyType.class }))                     
            .put("id.pr.birthday", personClass.getIndex("setBirthday", new Class[]{ Date.class }))                     
            .put("id.pr.children", personClass.getIndex("setChildren", new Class[]{ String.class }))                     
            .put("id.pr.ethnicity", personClass.getIndex("setEthnicity", new Class[]{ String.class }))                     
            .put("id.pr.fashion", personClass.getIndex("setFashion", new Class[]{ String.class }))                     
            .put("id.pr.gender", personClass.getIndex("setGender", new Class[]{ Gender.class }))                     
            .put("id.pr.happiestWhen", personClass.getIndex("setHappiestWhen", new Class[]{ String.class }))                     
            .put("id.pr.hasApp", personClass.getIndex("setHasApp", new Class[]{ Boolean.class }))                     
            .put("id.pr.humor", personClass.getIndex("setHumor", new Class[]{ String.class }))                     
            .put("id.pr.jobInterests", personClass.getIndex("setJobInterests", new Class[]{ String.class }))                     
            .put("id.pr.updated", personClass.getIndex("setUpdated", new Class[]{ Date.class }))                     
            .put("id.pr.livingArrangement", personClass.getIndex("setLivingArrangement", new Class[]{ String.class }))                     
            .put("id.pr.name", personClass.getIndex("setName", new Class[]{ Name.class }))                     
            .put("id.pr.nickname", personClass.getIndex("setNickname", new Class[]{ String.class }))                     
            .put("id.pr.pets", personClass.getIndex("setPets", new Class[]{ String.class }))                     
            .put("id.pr.preferredUsername", personClass.getIndex("setPreferredUsername", new Class[]{ String.class }))                     
            .put("id.pr.politicalViews", personClass.getIndex("setPoliticalViews", new Class[]{ String.class }))                     
            .put("id.pr.profileVideo", personClass.getIndex("setProfileVideo", new Class[]{ Url.class }))                     
            .put("id.pr.profileSong", personClass.getIndex("setProfileSong", new Class[]{ Url.class }))                     
            .put("id.pr.relationshipStatus", personClass.getIndex("setRelationshipStatus", new Class[]{ String.class }))                     
            .put("id.pr.religion", personClass.getIndex("setReligion", new Class[]{ String.class }))                     
            .put("id.pr.scaredOf", personClass.getIndex("setScaredOf", new Class[]{ String.class }))                     
            .put("id.pr.romance", personClass.getIndex("setRomance", new Class[]{ String.class }))                     
            .put("id.pr.sexualOrientation", personClass.getIndex("setSexualOrientation", new Class[]{ String.class }))                     
            .put("id.pr.status", personClass.getIndex("setStatus", new Class[]{ String.class }))                     
            .put("id.pr.utcOffset", personClass.getIndex("setUtcOffset", new Class[]{ Long.class }))                                 
            .put("id.pr.profileUrl", personClass.getIndex("setProfileUrl", new Class[]{ String.class }))                                          
            .put("id.pr.thumbnailUrl", personClass.getIndex("setThumbnailUrl", new Class[]{ String.class}))                     
            .put("id.pr.owner", personClass.getIndex("setIsOwner", new Class[]{ boolean.class }))
            .put("id.pr.viewer", personClass.getIndex("setIsViewer", new Class[]{ boolean.class }))                     
            .put("id.pr.nicotine", personClass.getIndex("setSmoker", new Class[]{ Enum.class }))                     
            .put("id.pr.alcohol", personClass.getIndex("setDrinker", new Class[]{ Enum.class }))                     
            .put("id.pr.currentLocation", personClass.getIndex("setCurrentLocation", new Class[]{ Address.class }))                     
            .put("id.pr.type", personClass.getIndex("setType", new Class[]{ ProfileType.class }))                     
            .put("id.pr.profileManagers", personClass.getIndex("setProfileManagers", new Class[]{ List.class }))                     
            .put("id.pr.defaultProfile", personClass.getIndex("setDefaultProfile", new Class[]{ Boolean.class }))                     
            .put("id.pr.presence", personClass.getIndex("setNetworkPresence", new Class[]{ Enum.class }))                             
            .put("id.pr.seeking", personClass.getIndex("setLookingFor", new Class[]{ List.class }))                     
            .put("id.pr.organizations", personClass.getIndex("setOrganizations", new Class[]{ List.class }))                     
            .put("id.pr.phoneNumbers", personClass.getIndex("setPhoneNumbers", new Class[]{ List.class }))                     
            .put("id.pr.photos", personClass.getIndex("setPhotos", new Class[]{ List.class }))                     
            .put("id.pr.accounts", personClass.getIndex("setAccounts", new Class[]{ List.class }))                     
            .put("id.pr.activities", personClass.getIndex("setActivities", new Class[]{ List.class }))                     
            .put("id.pr.addresses", personClass.getIndex("setAddresses", new Class[]{ List.class }))                     
            .put("id.pr.food", personClass.getIndex("setFood", new Class[]{ List.class }))                     
            .put("id.pr.languagesSpoken", personClass.getIndex("setLanguagesSpoken", new Class[]{ List.class }))                     
            .put("id.pr.heroes", personClass.getIndex("setHeroes", new Class[]{ List.class }))                     
            .put("id.pr.ims", personClass.getIndex("setIms", new Class[]{ List.class }))                     
            .put("id.pr.interests", personClass.getIndex("setInterests", new Class[]{ List.class }))                     
            .put("id.pr.books", personClass.getIndex("setBooks", new Class[]{ List.class }))                     
            .put("id.pr.cars", personClass.getIndex("setCars", new Class[]{ List.class }))                     
            .put("id.pr.emails", personClass.getIndex("setEmails", new Class[]{ List.class }))                     
            .put("id.pr.music", personClass.getIndex("setMusic", new Class[]{ List.class }))                     
            .put("id.pr.movies", personClass.getIndex("setMovies", new Class[]{ List.class }))                     
            .put("id.pr.quotes", personClass.getIndex("setQuotes", new Class[]{ List.class }))                     
            .put("id.pr.sports", personClass.getIndex("setSports", new Class[]{ List.class }))                     
            .put("id.pr.tags", personClass.getIndex("setTags", new Class[]{ List.class }))                     
            .put("id.pr.turnOffs", personClass.getIndex("setTurnOffs", new Class[]{ List.class }))                     
            .put("id.pr.turnOns", personClass.getIndex("setTurnOns", new Class[]{ List.class }))                     
            .put("id.pr.tvShows", personClass.getIndex("setTvShows", new Class[]{ List.class }))                     
            .put("id.pr.urls", personClass.getIndex("setUrls", new Class[]{ List.class })).build();
             
             fse = Lists.newArrayList(personFields.keySet());
             
        } catch (SecurityException ex) {
            Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    final FastClass personClass = FastClass.create(SocialPerson.class);             
    final FastClass identityClass = FastClass.create(SocialIdentity .class);             
    private static ImmutableMap<String, Integer> personFields = null;
    private static final Logger LOG = Logger.getLogger(IdentityHandler.class.getCanonicalName());
    private static List<String> fse = null;
}
