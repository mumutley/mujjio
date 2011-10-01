/*
 * Copyright 2011 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.moimoi.social.herqlweb.handlers;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.services.ProfileService;
import me.moimoi.social.herql.spi.EnumValue;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.RequestItem;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.protocol.model.Enum;
import org.apache.shindig.protocol.model.EnumImpl;
import org.apache.shindig.social.core.model.AddressImpl;
import org.apache.shindig.social.core.model.BodyTypeImpl;
import org.apache.shindig.social.core.model.ListFieldImpl;
import org.apache.shindig.social.core.model.NameImpl;
import org.apache.shindig.social.core.model.OrganizationImpl;
import org.apache.shindig.social.core.model.UrlImpl;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.BodyType;
import org.apache.shindig.social.opensocial.model.Drinker;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.LookingFor;
import org.apache.shindig.social.opensocial.model.NetworkPresence;
import org.apache.shindig.social.opensocial.model.Organization;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Smoker;
import org.apache.shindig.social.opensocial.model.Url;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

/**
 *
 * @author suhail
 */
@Service(name = "profile", path = "/{userId}/{groupId}")
public class ProfileHandler {

    private final ProfileService profileService;
    private final ContainerConfig config;

    @Inject
    public ProfileHandler(ProfileService profileService, ContainerConfig config) {
        this.profileService = profileService;
        this.config = config;
    }

    @Operation(httpMethods = "GET")
    public Future<?> getPerson(RequestItem request) {
        return ImmediateFuture.newInstance(this.getCanonical());
    }

    @Operation(httpMethods = "GET", path = "/{userId}/@lookingFor")
    public Future<?> getLookingFor(RequestItem request) {
        return ImmediateFuture.newInstance(profileService.getLookingFor(request.getParameter("userId")));
    }

    @Operation(httpMethods = "PUT", bodyParam = "entity", path = "/{userId}/@lookingFor")
    public Future<?> addLookingFor(RequestItem request) {
        EnumValue enu = request.getTypedParameter("entity", EnumValue.class);
        Enum<LookingFor> looking = new EnumImpl<LookingFor>(LookingFor.valueOf(enu.getValue()));
        Boolean outcome = profileService.addLookingFor(request.getParameter("userId"), looking);
        return ImmediateFuture.newInstance(outcome);
    }

    @Operation(httpMethods = "DELETE", path = "/{userId}/@lookingFor")
    public Future<?> deleteLookingFor(RequestItem request) {
        return ImmediateFuture.newInstance(Boolean.TRUE);
    }

    private SocialPerson getCanonical() {

        SocialPerson person = new SocialPerson();
        person.setAboutMe("this is me");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1968);
        c.set(Calendar.MONTH, Calendar.NOVEMBER);
        c.set(Calendar.DAY_OF_MONTH, 10);
        person.setBirthday(c.getTime());
        DateTime now = new DateTime(c.getTimeInMillis());
        DateMidnight birthdate = new DateMidnight(c.getTimeInMillis());
        Years age = Years.yearsBetween(birthdate, now);
        person.setAge(age.getYears());

        BodyType body = new BodyTypeImpl();
        body.setBuild("svelte");
        body.setEyeColor("blue");
        body.setHairColor("black");
        body.setHeight(1.84F);
        body.setWeight(74F);

        person.setBodyType(body);




        NameImpl name = new NameImpl("Sir Shin H. Digg Social Butterfly");
        name.setAdditionalName("H");
        name.setFamilyName("Digg");
        name.setGivenName("Shin");
        name.setHonorificPrefix("Sir");
        name.setHonorificSuffix("Social Butterfly");

        SocialPerson canonical = new SocialPerson("canonical", "Shin Digg", name);

        canonical.setAboutMe("I have an example of every piece of data");
        canonical.setActivities(Lists.newArrayList("Coding Shindig"));

        Address address = new AddressImpl("PoBox 3565, 1 OpenStandards Way, Apache, CA");
        address.setCountry("US");
        address.setLatitude(28.3043F);
        address.setLongitude(143.0859F);
        address.setLocality("who knows");
        address.setPostalCode("12345");
        address.setRegion("Apache, CA");
        address.setStreetAddress("1 OpenStandards Way");
        address.setType("home");
        address.setFormatted("PoBox 3565, 1 OpenStandards Way, Apache, CA");
        canonical.setAddresses(Lists.newArrayList(address));

        canonical.setAge(age.getYears());
        BodyTypeImpl bodyType = new BodyTypeImpl();
        bodyType.setBuild("svelte");
        bodyType.setEyeColor("blue");
        bodyType.setHairColor("black");
        bodyType.setHeight(1.84F);
        bodyType.setWeight(74F);
        canonical.setBodyType(bodyType);

        canonical.setBooks(Lists.newArrayList("The Cathedral & the Bazaar", "Catch 22"));
        canonical.setCars(Lists.newArrayList("beetle", "prius"));
        canonical.setChildren("3");
        AddressImpl location = new AddressImpl();
        location.setLatitude(48.858193F);
        location.setLongitude(2.29419F);
        canonical.setCurrentLocation(location);

        canonical.setBirthday(new Date());
        canonical.setDrinker(new EnumImpl<Drinker>(Drinker.SOCIALLY));
        ListField email = new ListFieldImpl("work", "dev@shindig.apache.org");
        canonical.setEmails(Lists.newArrayList(email));

        canonical.setEthnicity("developer");
        canonical.setFashion("t-shirts");
        canonical.setFood(Lists.newArrayList("sushi", "burgers"));
        canonical.setGender(Person.Gender.male);
        canonical.setHappiestWhen("coding");
        canonical.setHasApp(true);
        canonical.setHeroes(Lists.newArrayList("Doug Crockford", "Charles Babbage"));
        canonical.setHumor("none to speak of");
        canonical.setInterests(Lists.newArrayList("PHP", "Java"));
        canonical.setJobInterests("will work for beer");

        Organization job1 = new OrganizationImpl();
        job1.setAddress(new AddressImpl("1 Shindig Drive"));
        job1.setDescription("lots of coding");
        job1.setEndDate(new Date());
        job1.setField("Software Engineering");
        job1.setName("Apache.com");
        job1.setSalary("$1000000000");
        job1.setStartDate(new Date());
        job1.setSubField("Development");
        job1.setTitle("Grand PooBah");
        job1.setWebpage("http://shindig.apache.org/");
        job1.setType("job");

        Organization job2 = new OrganizationImpl();
        job2.setAddress(new AddressImpl("1 Skid Row"));
        job2.setDescription("");
        job2.setEndDate(new Date());
        job2.setField("College");
        job2.setName("School of hard Knocks");
        job2.setSalary("$100");
        job2.setStartDate(new Date());
        job2.setSubField("Lab Tech");
        job2.setTitle("Gopher");
        job2.setWebpage("");
        job2.setType("job");

        canonical.setOrganizations(Lists.newArrayList(job1, job2));

        canonical.setUpdated(new Date());
        canonical.setLanguagesSpoken(Lists.newArrayList("English", "Dutch", "Esperanto"));
        canonical.setLivingArrangement("in a house");
        Enum<LookingFor> lookingForRandom =
                new EnumImpl<LookingFor>(LookingFor.RANDOM, "Random");
        Enum<LookingFor> lookingForNetworking =
                new EnumImpl<LookingFor>(LookingFor.NETWORKING, "Networking");
        canonical.setLookingFor(Lists.newArrayList(lookingForRandom, lookingForNetworking));
        canonical.setMovies(Lists.newArrayList("Iron Man", "Nosferatu"));
        canonical.setMusic(Lists.newArrayList("Chieftains", "Beck"));
        canonical.setNetworkPresence(new EnumImpl<NetworkPresence>(NetworkPresence.ONLINE));
        canonical.setNickname("diggy");
        canonical.setPets("dog,cat");
        canonical.setPhoneNumbers(Lists.<ListField>newArrayList(new ListFieldImpl("work",
                "111-111-111"), new ListFieldImpl("mobile", "999-999-999")));

        canonical.setPoliticalViews("open leaning");
        canonical.setProfileSong(new UrlImpl("http://www.example.org/songs/OnlyTheLonely.mp3",
                "Feelin' blue", "road"));
        canonical.setProfileVideo(new UrlImpl("http://www.example.org/videos/Thriller.flv",
                "Thriller", "video"));

        canonical.setQuotes(Lists.newArrayList("I am therfore I code", "Doh!"));
        canonical.setRelationshipStatus("married to my job");
        canonical.setReligion("druidic");
        canonical.setRomance("twice a year");
        canonical.setScaredOf("COBOL");
        canonical.setSexualOrientation("north");
        canonical.setSmoker(new EnumImpl<Smoker>(Smoker.NO));
        canonical.setSports(Lists.newArrayList("frisbee", "rugby"));
        canonical.setStatus("happy");
        canonical.setTags(Lists.newArrayList("C#", "JSON", "template"));
        canonical.setThumbnailUrl("http://www.example.org/pic/?id=1");
        canonical.setUtcOffset(-8L);
        canonical.setTurnOffs(Lists.newArrayList("lack of unit tests", "cabbage"));
        canonical.setTurnOns(Lists.newArrayList("well document code"));
        canonical.setTvShows(Lists.newArrayList("House", "Battlestar Galactica"));

        canonical.setUrls(Lists.<Url>newArrayList(
                new UrlImpl("http://www.example.org/?id=1", "my profile", "Profile"),
                new UrlImpl("http://www.example.org/pic/?id=1", "my awesome picture", "Thumbnail")));

        return canonical;
    }
}
/*
 {"entry":{"turnOffs":["lack of unit tests","cabbage"],"heroes":["Doug Crockford","Charles Babbage"],"ims":[],"sports":["frisbee","rugby"],"languagesSpoken":["English","Dutch","Esperanto"],"religion":"druidic","turnOns":["well document code"],"updated":"2011-06-06T20:27:37.224Z","cars":["beetle","prius"],"age":0,"pets":"dog,cat","gender":"male","humor":"none to speak of","activities":["Coding Shindig"],"books":["The Cathedral & the Bazaar","Catch 22"],"hasApp":true,"scaredOf":"COBOL","happiestWhen":"coding","status":"happy","nickname":"diggy","emails":[{"value":"dev@shindig.apache.org","type":"work"}],"food":["sushi","burgers"],"utcOffset":-8,"smoker":{"value":"NO","displayValue":"No"},"profileVideo":{"linkText":"Thriller","value":"http://www.example.org/videos/Thriller.flv","type":"video"},"addresses":[{"streetAddress":"1 OpenStandards Way","region":"Apache, CA","formatted":"PoBox 3565, 1 OpenStandards Way, Apache, CA","postalCode":"12345","locality":"who knows","longitude":143.0859,"latitude":28.3043,"type":"home","country":"US"}],"birthday":"2011-06-06T20:27:37.222Z","fashion":"t-shirts","drinker":{"value":"SOCIALLY","displayValue":"Socially"},"interests":["PHP","Java"],"jobInterests":"will work for beer","movies":["Iron Man","Nosferatu"],"children":"3","lookingFor":[{"value":"RANDOM","displayValue":"Random"},{"value":"NETWORKING","displayValue":"Networking"}],"networkPresence":{"value":"ONLINE","displayValue":"Online"},"romance":"twice a year","aboutMe":"I have an example of every piece of data","bodyType":{"weight":74.0,"height":1.84,"eyeColor":"blue","hairColor":"black","build":"svelte"},"id":"canonical","tvShows":["House","Battlestar Galactica"],"thumbnailUrl":"http://www.example.org/pic/?id=1","name":{"formatted":"Sir Shin H. Digg Social Butterfly","honorificPrefix":"Sir","additionalName":"H","familyName":"Digg","givenName":"Shin","honorificSuffix":"Social Butterfly"},"profileSong":{"linkText":"Feelin' blue","value":"http://www.example.org/songs/OnlyTheLonely.mp3","type":"road"},"tags":["C#","JSON","template"],"music":["Chieftains","Beck"],"accounts":[],"politicalViews":"open leaning","urls":[{"linkText":"my profile","value":"http://www.example.org/?id=1","type":"Profile"},{"linkText":"my awesome picture","value":"http://www.example.org/pic/?id=1","type":"Thumbnail"}],"organizations":[{"field":"Software Engineering","subField":"Development","startDate":"2011-06-06T20:27:37.224Z","title":"Grand PooBah","webpage":"http://shindig.apache.org/","address":{"formatted":"1 Shindig Drive"},"description":"lots of coding","name":"Apache.com","endDate":"2011-06-06T20:27:37.224Z","salary":"$1000000000","type":"job"},{"field":"College","subField":"Lab Tech","startDate":"2011-06-06T20:27:37.224Z","title":"Gopher","webpage":"","address":{"formatted":"1 Skid Row"},"description":"","name":"School of hard Knocks","endDate":"2011-06-06T20:27:37.224Z","salary":"$100","type":"job"}],"phoneNumbers":[{"value":"111-111-111","type":"work"},{"value":"999-999-999","type":"mobile"}],"relationshipStatus":"married to my job","livingArrangement":"in a house","currentLocation":{"longitude":2.29419,"latitude":48.858192},"photos":[],"presence":{"displayName":"Online","key":"ONLINE"},"quotes":["I am therfore I code","Doh!"],"sexualOrientation":"north","displayName":"Shin Digg","ethnicity":"developer"}}
 */
