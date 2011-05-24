/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herqlweb.spi;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import me.moimoi.social.herql.services.ProfileService;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.DataCollection;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;
import org.json.JSONObject;

/**
 *
 * @author Suhail
 */
public class HerqlOpensocialDataService implements  AppDataService {
    
    private ProfileService profile;
    
    @Inject
    public HerqlOpensocialDataService(ProfileService profile) throws Exception {
        System.out.println(" ---->>>> HerqlOpensocialService " + profile.toString());
        this.profile = profile;
    }

    /**
     * Public methods for use with Authentication Classes
     *
     * @param username a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public String getPassword(String username) {
        return "password";
    }

    /**
     * Allows access to the underlying json db.
     *
     * @return a reference to the json db
     */
    public JSONObject getDb() {
        return null;
    }

    /**
     * override the json database
     * @param db a {@link org.json.JSONObject}.
     */
    public void setDb(JSONObject db) {
    }

   
    
    private static final Logger LOG = Logger.getLogger(HerqlOpensocialDataService.class.getName());

    @Override
    public Future<DataCollection> getPersonData(Set<UserId> userIds, GroupId groupId, String appId, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<Void> deletePersonData(UserId userId, GroupId groupId, String appId, Set<String> fields, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<Void> updatePersonData(UserId userId, GroupId groupId, String appId, Set<String> fields, Map<String, String> values, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
