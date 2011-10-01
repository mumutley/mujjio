/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.spi;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.MapMaker;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthServiceProvider;
import net.oauth.signature.RSA_SHA1;
import org.apache.shindig.auth.AuthenticationMode;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.crypto.Crypto;
import org.apache.shindig.social.core.oauth.OAuthSecurityToken;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.oauth.OAuthEntry;

/**
 *
 * @author Suhail
 */
public class HerqlOAuthDataStore implements OAuthDataStore {

    // This needs to be long enough that an attacker can't guess it.  If the attacker can guess this
    // value before they exceed the maximum number of attempts, they can complete a session fixation
    // attack against a user.
    private static final int CALLBACK_TOKEN_LENGTH = 6;
    // We limit the number of trials before disabling the request token.
    private static final int CALLBACK_TOKEN_ATTEMPTS = 5;
    // used to get samplecontainer data from canonicaldb.json
    private final OAuthServiceProvider SERVICE_PROVIDER;
    private OpenSocialDataService service;

    @Inject
    public HerqlOAuthDataStore(OpenSocialDataService dbService, @Named("oauth.base-url") String baseUrl) {
        this.service = dbService;
        this.SERVICE_PROVIDER = new OAuthServiceProvider(baseUrl + "requestToken", baseUrl + "authorize", baseUrl + "accessToken");
    }
    // All valid OAuth tokens
    private static ConcurrentMap<String, OAuthEntry> oauthEntries = new MapMaker().makeMap();

    // Get the OAuthEntry that corresponds to the oauthToken
    public OAuthEntry getEntry(String oauthToken) {
        Preconditions.checkNotNull(oauthToken);
        return oauthEntries.get(oauthToken);
    }

    //http://localhost:8080/oauth/requestToken?oauth_consumer_key=example.com&oauth_timestamp=1310817046&oauth_signature_method=RSA-SHA1&oauth_version=1.0&oauth_nonce=4572616e48616d6d65724c61686173&oauth_signature=wOJIO9A2W5mFwDgiDvZbTSMK%2FPY%3D
    private final static String CERTIFICATE =
       "-----BEGIN CERTIFICATE-----\n"
           + "MIIC7zCCAlgCCQCRTBYbEuL58jANBgkqhkiG9w0BAQUFADCBuzELMAkGA1UEBhMC\n"
           + "VUsxEjAQBgNVBAgTCU1pZGRsZXNleDETMBEGA1UEBxMKVHdpY2tlbmhhbTEZMBcG\n"
           + "A1UEChMQU3VycmVuZGVyIE1vbmtleTEaMBgGA1UECxMRU29mdHdhcmUgU2Vydmlj\n"
           + "ZXMxIzAhBgNVBAMTGnB1YmxpYy5zdXJyZW5kZXJtb25rZXkubmV0MScwJQYJKoZI\n"
           + "hvcNAQkBFhhpbmZvQHN1cnJlbmRlcm1vbmtleS5uZXQwHhcNMTEwNzE2MTEyOTEw\n"
           + "WhcNMTIwNzE1MTEyOTEwWjCBuzELMAkGA1UEBhMCVUsxEjAQBgNVBAgTCU1pZGRs\n"
           + "ZXNleDETMBEGA1UEBxMKVHdpY2tlbmhhbTEZMBcGA1UEChMQU3VycmVuZGVyIE1v\n"
           + "bmtleTEaMBgGA1UECxMRU29mdHdhcmUgU2VydmljZXMxIzAhBgNVBAMTGnB1Ymxp\n"
           + "Yy5zdXJyZW5kZXJtb25rZXkubmV0MScwJQYJKoZIhvcNAQkBFhhpbmZvQHN1cnJl\n"
           + "bmRlcm1vbmtleS5uZXQwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBANjQsTEh\n"
           + "jFR+3B6dkng1UyGOwN2fbMZQe4j2NaTEoRQrm1bhFg22Vr2a+6LeD0AZxg9nJYHt\n"
           + "bZ7XUGB2eAjZ8ZJ5SMyx9nebGeuQsP2sgFOwEockSIlbqgGE7faNgHjYVOuOphv1\n"
           + "np2yFUvs0qeURCIWyTEZq70lqJv4E1BvbDJ5AgMBAAEwDQYJKoZIhvcNAQEFBQAD\n"
           + "gYEAxEJ7neYg6QFPKDNW2fZNngkf6YvNXtoLWHtiB00g7Z4nXN0ywpEB9AzAYrIU\n"
           + "AnlYN5cdxmK+nT4YTMvitoExh2gjsblDFiR2lJlcJnPAFh50fy8gKvwK5mB+h4Tq\n"
           + "iyyeuyqf+WXJtb1ZXBjquBJKeLlqpYRGbURSe3vk4nyVwe4=\n"
           + "-----END CERTIFICATE-----";
    
    //oauth_consumer_key=example.com&oauth_version=1.0&oauth_nonce=4572616e48616d6d65724c61686174&oauth_timestamp=1310766320&oauth_signature_method=RSA-SHA1&oauth_signature=wOJIO9A2W5mFwDgiDvZbTSMK%2FPY%3D
    public OAuthConsumer getConsumer(String consumerKey) {
        //go to oauth datastore and use the consumerKey to lookup the consumerSecret
        String consumerSecret = "consumerSecret";

        // null below is for the callbackUrl, which we don't have in the db
        OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, consumerSecret, SERVICE_PROVIDER);

        
        // Set some properties loosely based on the ModulePrefs of a gadget
        for (String key : ImmutableList.of("title", "summary", "description", "thumbnail", "icon")) {
            consumer.setProperty(key, key);
        }
                        
        consumer.setProperty(RSA_SHA1.X509_CERTIFICATE, CERTIFICATE);
        
        return consumer;
    }

    // Generate a valid requestToken for the given consumerKey
    public OAuthEntry generateRequestToken(String consumerKey, String oauthVersion,
            String signedCallbackUrl) {
        OAuthEntry entry = new OAuthEntry();
        entry.setAppId(consumerKey);
        entry.setConsumerKey(consumerKey);
        entry.setDomain("samplecontainer.com");
        entry.setContainer("default");

        entry.setToken(UUID.randomUUID().toString());
        entry.setTokenSecret(UUID.randomUUID().toString());

        entry.setType(OAuthEntry.Type.REQUEST);
        entry.setIssueTime(new Date());
        entry.setOauthVersion(oauthVersion);
        if (signedCallbackUrl != null) {
            entry.setCallbackUrlSigned(true);
            entry.setCallbackUrl(signedCallbackUrl);
        }

        oauthEntries.put(entry.getToken(), entry);
        return entry;
    }

    // Turns the request token into an access token
    public OAuthEntry convertToAccessToken(OAuthEntry entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.getType() == OAuthEntry.Type.REQUEST, "Token must be a request token");

        OAuthEntry accessEntry = new OAuthEntry(entry);

        accessEntry.setToken(UUID.randomUUID().toString());
        accessEntry.setTokenSecret(UUID.randomUUID().toString());

        accessEntry.setType(OAuthEntry.Type.ACCESS);
        accessEntry.setIssueTime(new Date());

        oauthEntries.remove(entry.getToken());
        oauthEntries.put(accessEntry.getToken(), accessEntry);

        return accessEntry;
    }

    // Authorize the request token for the given user id
    public void authorizeToken(OAuthEntry entry, String userId) {
        Preconditions.checkNotNull(entry);
        entry.setAuthorized(true);
        entry.setUserId(Preconditions.checkNotNull(userId));
        if (entry.isCallbackUrlSigned()) {
            entry.setCallbackToken(Crypto.getRandomDigits(CALLBACK_TOKEN_LENGTH));
        }
    }

    public void disableToken(OAuthEntry entry) {
        Preconditions.checkNotNull(entry);
        entry.setCallbackTokenAttempts(entry.getCallbackTokenAttempts() + 1);
        if (!entry.isCallbackUrlSigned() || entry.getCallbackTokenAttempts() >= CALLBACK_TOKEN_ATTEMPTS) {
            entry.setType(OAuthEntry.Type.DISABLED);
        }

        oauthEntries.put(entry.getToken(), entry);
    }

    public void removeToken(OAuthEntry entry) {
        Preconditions.checkNotNull(entry);

        oauthEntries.remove(entry.getToken());
    }

    // Return the proper security token for a 2 legged oauth request that has been validated
    // for the given consumerKey. App specific checks like making sure the requested user has the
    // app installed should take place in this method
    public SecurityToken getSecurityTokenForConsumerRequest(String consumerKey, String userId) {
        String domain = "samplecontainer.com";
        String container = "default";

        return new OAuthSecurityToken(userId, null, consumerKey, domain, container, null,
                AuthenticationMode.OAUTH_CONSUMER_REQUEST.name());

    }
}
