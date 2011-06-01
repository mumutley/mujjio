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


        //getMethodNames(profile);

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
        while (properties.hasNext()) {            
            Method m =  personFields.get(properties.next());                        
            Object[] args = {null};
            try {
                m.invoke(profile, args);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    private void init() {
        if(personFields != null) return;         
        try {
             final FastClass c = FastClass.create(SocialPerson.class);

             c.getIndex("setDisplayName", new Class[]{ String.class });
             //http://www.sixlegs.com/blog/java/cglib-fastclass.html
             personFields = new ImmutableMap.Builder<String, Integer>()
            .put("profile.displayName", SocialPerson.class.getMethod("setDisplayName", String.class))                     
            .put("profile.aboutMe", SocialPerson.class.getMethod("setAboutMe", String.class)).build();
            
            /*.put("profile.age", SocialPerson.class.getMethod("setAge", Integer.class))
            .put("profile.bodyType", SocialPerson.class.getMethod("setBodyType", BodyType.class))
            .put("profile.birthday", SocialPerson.class.getMethod("setBirthday", Date.class))
            .put("profile.children", SocialPerson.class.getMethod("setChildren", String.class))
            .put("profile.ethnicity", SocialPerson.class.getMethod("setEthnicity", String.class))
            .put("profile.fashion", SocialPerson.class.getMethod("setFashion", String.class))
            .put("profile.gender", SocialPerson.class.getMethod("setGender", Gender.class))
            .put("profile.happiestWhen", SocialPerson.class.getMethod("setHappiestWhen", String.class))
            .put("profile.hasApp", SocialPerson.class.getMethod("setHasApp", Boolean.class))
            .put("profile.humor", SocialPerson.class.getMethod("setHumor", String.class))
            .put("profile.jobInterests", SocialPerson.class.getMethod("setJobInterests", String.class))
            .put("profile.updated", SocialPerson.class.getMethod("setUpdated", Date.class))
            .put("profile.livingArrangement", SocialPerson.class.getMethod("setLivingArrangement", String.class))
            .put("profile.name", SocialPerson.class.getMethod("setName", Name.class))
            .put("profile.nickname", SocialPerson.class.getMethod("setNickname", String.class))
            .put("profile.pets", SocialPerson.class.getMethod("setPets", String.class))
            .put("profile.preferredUsername", SocialPerson.class.getMethod("setPreferredUsername", String.class))
            .put("profile.politicalViews", SocialPerson.class.getMethod("setPoliticalViews", String.class))
            .put("profile.profileVideo", SocialPerson.class.getMethod("setProfileVideo", Url.class))
            .put("profile.profileSong", SocialPerson.class.getMethod("setProfileSong", Url.class))
            .put("profile.relationshipStatus", SocialPerson.class.getMethod("setRelationshipStatus", String.class))
            .put("profile.religion", SocialPerson.class.getMethod("setReligion", String.class))
            .put("profile.scaredOf", SocialPerson.class.getMethod("setScaredOf", String.class))
            .put("profile.romance", SocialPerson.class.getMethod("setRomance", String.class))
            .put("profile.sexualOrientation", SocialPerson.class.getMethod("setSexualOrientation", String.class))
            .put("profile.status", SocialPerson.class.getMethod("setStatus", String.class))
            .put("profile.utcOffset", SocialPerson.class.getMethod("setUtcOffset", Long.class))
            .put("profile.viewer", SocialPerson.class.getMethod("setIsViewer", boolean.class))
            .put("profile.profileUrl", SocialPerson.class.getMethod("setProfileUrl", String.class))
            .put("profile.thumbnailUrl", SocialPerson.class.getMethod("setThumbnailUrl", String.class))
            .put("profile.owner", SocialPerson.class.getMethod("setIsOwner", boolean.class))            
            .put("profile.nicotine", SocialPerson.class.getMethod("setSmoker", Enum.class))
            .put("profile.alcohol", SocialPerson.class.getMethod("setDrinker", Enum.class))
            .put("profile.currentLocation", SocialPerson.class.getMethod("setCurrentLocation", Address.class))
            .put("profile.type", SocialPerson.class.getMethod("setType", ProfileType.class))
            .put("profile.profileManagers", SocialPerson.class.getMethod("setProfileManagers", List.class))
            .put("profile.defaultProfile", SocialPerson.class.getMethod("setDefaultProfile",Boolean.class))
            .put("profile.presence", SocialPerson.class.getMethod("setNetworkPresence",Enum.class))                
            .put("profile.seeking", SocialPerson.class.getMethod("setLookingFor",List.class))
            .put("profile.organizations", SocialPerson.class.getMethod("setOrganizations",List.class))
            .put("profile.phoneNumbers", SocialPerson.class.getMethod("setPhoneNumbers",List.class))
            .put("profile.photos", SocialPerson.class.getMethod("setPhotos",List.class))
            .put("profile.accounts", SocialPerson.class.getMethod("setAccounts",List.class))
            .put("profile.activities", SocialPerson.class.getMethod("setActivities",List.class))
            .put("profile.addresses", SocialPerson.class.getMethod("setAddresses",List.class))
            .put("profile.food", SocialPerson.class.getMethod("setFood",List.class))
            .put("profile.languagesSpoken", SocialPerson.class.getMethod("setLanguagesSpoken",List.class))
            .put("profile.heroes", SocialPerson.class.getMethod("setHeroes",List.class))
            .put("profile.ims", SocialPerson.class.getMethod("setIms",List.class))
            .put("profile.interests", SocialPerson.class.getMethod("setInterests",List.class))
            .put("profile.books", SocialPerson.class.getMethod("setBooks",List.class))
            .put("profile.cars", SocialPerson.class.getMethod("setCars",List.class))
            .put("profile.emails", SocialPerson.class.getMethod("setEmails",List.class))
            .put("profile.music", SocialPerson.class.getMethod("setMusic",List.class))
            .put("profile.movies", SocialPerson.class.getMethod("setMovies",List.class))
            .put("profile.quotes", SocialPerson.class.getMethod("setQuotes",List.class))
            .put("profile.sports", SocialPerson.class.getMethod("setSports",List.class))
            .put("profile.tags", SocialPerson.class.getMethod("setTags",List.class))
            .put("profile.turnOffs", SocialPerson.class.getMethod("setTurnOffs",List.class))
            .put("profile.turnOns", SocialPerson.class.getMethod("setTurnOns",List.class))
            .put("profile.tvShows", SocialPerson.class.getMethod("setTvShows",List.class))
            .put("profile.urls", SocialPerson.class.getMethod("setUrls",List.class)).build();*/
             
             fse = Lists.newArrayList(personFields.keySet());
             
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(IdentityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ImmutableMap<String, Method> personFields = null;
    private static final Logger LOG = Logger.getLogger(IdentityHandler.class.getCanonicalName());
    private static List<String> fse = null;
}
