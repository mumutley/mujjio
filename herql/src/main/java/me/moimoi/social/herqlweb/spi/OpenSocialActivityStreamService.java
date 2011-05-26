/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herqlweb.spi;

import java.util.Set;
import java.util.concurrent.Future;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.ActivityEntry;
import org.apache.shindig.social.opensocial.spi.ActivityStreamService;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 *
 * @author ManzoorS
 */
public class OpenSocialActivityStreamService implements ActivityStreamService {

    public OpenSocialActivityStreamService() {
        
    }
    
    @Override
    public Future<RestfulCollection<ActivityEntry>> getActivityEntries(Set<UserId> userIds, GroupId groupId, String appId, Set<String> fields, CollectionOptions options, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<RestfulCollection<ActivityEntry>> getActivityEntries(UserId userId, GroupId groupId, String appId, Set<String> fields, CollectionOptions options, Set<String> activityIds, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Future<ActivityEntry> getActivityEntry(UserId userId, GroupId groupId, String appId, Set<String> fields, String activityId, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Future<Void> deleteActivityEntries(UserId userId, GroupId groupId, String appId, Set<String> activityIds, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Future<Void> updateActivityEntry(UserId userId, GroupId groupId, String appId, Set<String> fields, ActivityEntry activity, String activityId, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    public Future<Void> createActivityEntry(UserId userId, GroupId groupId, String appId, Set<String> fields, ActivityEntry activity, SecurityToken token) throws ProtocolException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
