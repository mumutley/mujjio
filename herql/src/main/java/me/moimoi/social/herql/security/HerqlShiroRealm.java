/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.security;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import java.util.Set;
import me.moimoi.social.herqlweb.spi.OpenSocialDataService;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author Suhail
 */
public class HerqlShiroRealm extends AuthorizingRealm {

    // HACK, apache.shiro relies upon no-arg constructors..
    @Inject
    private static OpenSocialDataService jsonDbService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        String password = jsonDbService.getPassword(username);

        return new SimpleAuthenticationInfo(username, password, this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) principals.fromRealm(getName()).iterator().next();
        Set<String> roleNames;

        if (username == null) {
            roleNames = ImmutableSet.of();
        } else {
            roleNames = ImmutableSet.of("foo", "goo");
        }

        return new SimpleAuthorizationInfo(roleNames);
    }
}
