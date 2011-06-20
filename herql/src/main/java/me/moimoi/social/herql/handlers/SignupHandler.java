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
package me.moimoi.social.herql.handlers;

import com.google.inject.Inject;
import java.net.ProtocolException;
import java.util.Date;
import java.util.concurrent.Future;
import me.moimoi.social.herql.domain.ProfileType;
import me.moimoi.social.herql.domain.SocialIdentity;
import me.moimoi.social.herql.domain.SocialPerson;
import me.moimoi.social.herql.domain.form.JoinForm;
import me.moimoi.social.herql.integration.MessangerService;
import me.moimoi.social.herql.services.SocialIdentityService;
import me.moimoi.social.herql.services.SocialPersonService;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.protocol.Operation;
import org.apache.shindig.protocol.Service;
import org.apache.shindig.social.core.model.NameImpl;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.service.SocialRequestItem;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;

/**
 *
 * @author suhail
 */
@Service(name = "signup")
public class SignupHandler {

    private final SocialIdentityService identityService;
    private final ContainerConfig config;
    private final SocialPersonService personService;
    private final MessangerService messanger;
    
    @Inject
    public SignupHandler(SocialIdentityService identityService, 
            SocialPersonService personService,
            MessangerService messanger,
            ContainerConfig config) {
        
        this.config = config;
        this.identityService = identityService;
        this.personService = personService;
        this.messanger = messanger;
    }

    @Operation(httpMethods = "POST", bodyParam = "entity")
    public Future<?> create(SocialRequestItem request) throws ProtocolException {
        JoinForm member = request.getTypedParameter("entity", JoinForm.class);

        NameImpl name = new NameImpl(member.getGivenName() + " " + member.getFamilyName());
        name.setFamilyName(member.getFamilyName());
        name.setGivenName(member.getGivenName());
        
        DateMidnight birthday = 
                new DateMidnight(Integer.parseInt(member.getYyyy()), 
                             Integer.parseInt(member.getMm()), 
                             Integer.parseInt(member.getDd()));
        
        SocialPerson person = new SocialPerson(member.getEmail(), name.getFormatted(), name);
        
        DateTime today = new DateTime();        
        Years age = Years.yearsBetween(birthday, today);

        person.setBirthday(birthday.toDate());
        person.setAge(age.getYears());
        
        person.setGender(Person.Gender.valueOf(member.getGender()));
        person.getLanguagesSpoken().add(member.getLanguage());        
        person.setType(ProfileType.DIRECT);
        
        SocialIdentity identity = new SocialIdentity();
        identity.setJoined(new Date());
        identity.setLoginName(member.getEmail());
        identity.setPassword(member.getPassword());
        identity.setVerified(Boolean.FALSE);
        identity.getProfiles().add(person);
        
        this.personService.register(person);
        this.identityService.create(identity);
        
        this.messanger.send("this needs to end today");
        
        return ImmediateFuture.newInstance(identity);
    }
}
