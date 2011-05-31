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
import com.google.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.IdentityService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import sun.misc.BASE64Encoder;

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

        profile.setIms(null);

        identity.getProfiles().add(profile);


        getMethodNames(profile);

        return ImmediateFuture.newInstance(identity);
    }

    private void getMethodNames(SocialPerson profile) {

        Set<String> fe = new HashSet<String>();
        fe.add("profile.birthday");
        fe.add("profile.defaultProfile");

        Predicate<String> filter = new Predicate<String>() {
            @Override
            public boolean apply(String s) { 
                System.out.println(s);
                return s.toUpperCase().startsWith("Z");
            }
        };

        
        
        
        Collection<String> zWords = Collections2.filter(personFields.keySet(), filter);


        Field[] fields = profile.getClass().getDeclaredFields();
        for (Field f : fields) {
            System.out.println("personFields.put(\"" + f.getName() + "\",\"set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1) + "\");");
        }
    }
    private static final Logger LOG = Logger.getLogger(IdentityHandler.class.getCanonicalName());
    private static final HashMap<String, String> personFields = new HashMap<String, String>();

    static {
        personFields.put("profile.displayName", "setDisplayName");
        personFields.put("profile.aboutMe", "setAboutMe");
        personFields.put("profile.age", "setAge");
        personFields.put("profile.bodyType", "setBodyType");
        personFields.put("profile.birthday", "setBirthday");
        personFields.put("profile.children", "setChildren");
        personFields.put("profile.ethnicity", "setEthnicity");
        personFields.put("profile.fashion", "setFashion");
        personFields.put("profile.gender", "setGender");
        personFields.put("profile.happiestWhen", "setHappiestWhen");
        personFields.put("profile.hasApp", "setHasApp");
        personFields.put("profile.humor", "setHumor");
        personFields.put("profile.jobInterests", "setJobInterests");
        personFields.put("profile.updated", "setUpdated");
        personFields.put("profile.livingArrangement", "setLivingArrangement");
        personFields.put("profile.name", "setName");
        personFields.put("profile.nickname", "setNickname");
        personFields.put("profile.pets", "setPets");
        personFields.put("profile.preferredUsername", "setPreferredUsername");
        personFields.put("profile.politicalViews", "setPoliticalViews");
        personFields.put("profile.profileVideo", "setProfileVideo");
        personFields.put("profile.profileSong", "setProfileSong");
        personFields.put("profile.relationshipStatus", "setRelationshipStatus");
        personFields.put("profile.religion", "setReligion");
        personFields.put("profile.scaredOf", "setScaredOf");
        personFields.put("profile.romance", "setRomance");
        personFields.put("profile.sexualOrientation", "setSexualOrientation");
        personFields.put("profile.status", "setStatus");
        personFields.put("profile.utcOffset", "setUtcOffset");
        personFields.put("profile.viewer", "setViewer");
        personFields.put("profile.profileUrl", "setProfileUrl");
        personFields.put("profile.thumbnailUrl", "setThumbnailUrl");
        personFields.put("profile.owner", "setOwner");
        personFields.put("profile.presence", "setPresence");
        personFields.put("profile.nicotine", "setNicotine");
        personFields.put("profile.alcohol", "setAlcohol");
        personFields.put("profile.currentLocation", "setCurrentLocation");
        personFields.put("profile.type", "setType");
        personFields.put("profile.profileManagers", "setProfileManagers");
        personFields.put("profile.defaultProfile", "setDefaultProfile");
        personFields.put("profile.seeking", "setSeeking");
        personFields.put("profile.organizations", "setOrganizations");
        personFields.put("profile.phoneNumbers", "setPhoneNumbers");
        personFields.put("profile.photos", "setPhotos");
        personFields.put("profile.accounts", "setAccounts");
        personFields.put("profile.activities", "setActivities");
        personFields.put("profile.addresses", "setAddresses");
        personFields.put("profile.food", "setFood");
        personFields.put("profile.languagesSpoken", "setLanguagesSpoken");
        personFields.put("profile.heroes", "setHeroes");
        personFields.put("profile.ims", "setIms");
        personFields.put("profile.interests", "setInterests");
        personFields.put("profile.books", "setBooks");
        personFields.put("profile.cars", "setCars");
        personFields.put("profile.emails", "setEmails");
        personFields.put("profile.music", "setMusic");
        personFields.put("profile.movies", "setMovies");
        personFields.put("profile.quotes", "setQuotes");
        personFields.put("profile.sports", "setSports");
        personFields.put("profile.tags", "setTags");
        personFields.put("profile.turnOffs", "setTurnOffs");
        personFields.put("profile.turnOns", "setTurnOns");
        personFields.put("profile.tvShows", "setTvShows");
        personFields.put("profile.urls", "setUrls");
    }
}
