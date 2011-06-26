/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.PersonImpl;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 *
 * @author ManzoorS
 */
public class MockPersonSpi implements PersonService {

    /** The Constant JOHN. */
    private static final UserId JOHN = new UserId(UserId.Type.userId, "john.doe");
    /** The Constant JANE. */
    private static final UserId JANE = new UserId(UserId.Type.userId, "jane.doe");
    /** The Constant FRIENDS. */
    private static final UserId[] FRIENDS = {JOHN, JANE};

    /* (non-Javadoc)
     * @see org.apache.shindig.social.opensocial.spi.PersonService#getPerson(org.apache.shindig.social.opensocial.spi.UserId, java.util.Set, org.apache.shindig.auth.SecurityToken)
     */
    @Override
    public Future<Person> getPerson(UserId userId, Set<String> fields, SecurityToken token)
            throws ProtocolException {
        Person person = new PersonImpl();
        person.setId(userId.getUserId());
        return ImmediateFuture.newInstance(person);
    }

    /* (non-Javadoc)
     * @see org.apache.shindig.social.opensocial.spi.PersonService#getPeople(java.util.Set, org.apache.shindig.social.opensocial.spi.GroupId, org.apache.shindig.social.opensocial.spi.CollectionOptions, java.util.Set, org.apache.shindig.auth.SecurityToken)
     */
    @Override
    public Future<RestfulCollection<Person>> getPeople(Set<UserId> userIds, GroupId groupId,
            CollectionOptions collectionOptions, Set<String> fields, SecurityToken token)
            throws ProtocolException {
        try {
            List<Person> people = new ArrayList<Person>();
            switch (groupId.getType()) {
                case self:
                    for (UserId userId : userIds) {
                        Person person = new PersonImpl();
                        person.setId(userId.getUserId());
                        people.add(person);
                    }
                    break;
                case friends:
                    for (UserId userId : FRIENDS) {
                        Person person = new PersonImpl();
                        person.setId(userId.getUserId());
                        people.add(person);
                    }
                    break;
                case all:
                    throw new UnsupportedOperationException("Not supported yet.");
                case groupId:
                    throw new UnsupportedOperationException("Not supported yet.");
                case deleted:
                    throw new UnsupportedOperationException("Not supported yet.");                    
                default:
                    throw new UnsupportedOperationException("Not supported yet.");
            }
            return ImmediateFuture.newInstance(new RestfulCollection<Person>(people, 0, people.size()));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");

        }
    }
}
