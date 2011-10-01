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
package me.moimoi.social.herql.service;

import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import net.oauth.signature.OAuthSignatureMethod;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author suhail
 */
public class OAuthTest {

    /*private static final String PRIVATE_KEY =
    "MIICXAIBAAKBgQDY0LExIYxUftwenZJ4NVMhjsDdn2zGUHuI9jWkxKEUK5tW4RYN"
    + "tla9mvui3g9AGcYPZyWB7W2e11BgdngI2fGSeUjMsfZ3mxnrkLD9rIBTsBKHJEiJ"
    + "W6oBhO32jYB42FTrjqYb9Z6dshVL7NKnlEQiFskxGau9Jaib+BNQb2wyeQIDAQAB"
    + "AoGAanQ9K/Re5HXNOjL/4Ym9IXLn+2j1jYGixiKtQBNTS4YFpWiq3VyzxjC8cME7"
    + "F6wP9Swd3GdpNV5elp5UXXQ1CWCHNotZftFmp4i8BBCFk0YbZcsWhgU5L81vNfE3"
    + "0p5qlOUDHYbdERAHR0IoyFtyGXtpyn45ZllAFftt7yIZcqkCQQD0Mgb2Pvabml/+"
    + "aiJMWs7sRS+gxLledQEJv+a1awMZkB7HC7Vb4wqb+60OaF4P61Gv6srlxjD1O4Nh"
    + "pvVOVkI3AkEA40vUJQTM8862neli7+z4hgMv5/o/sJVmxW5JHz3slhLRx3W4cwwS"
    + "Rip+EFRHCHDE9yDMcylfrfbhOC4fJISYzwJAR/RBxudwpRMM/Btr9dHALTCJwzaU"
    + "ZHfXkXbhVU2uASdNWlth2iyBYMr9CETKiqhNTqfBl4kZvfkGUozutWGHawJBAJ2x"
    + "UjGMrxTHaXMNJJMIeTOgqm/q8pz0SiTXauu97pH/5ILZbWqECXmcqXFyxwVYF47M"
    + "tot/PN5Y1dBAT2Lqi4kCQD9HglvQNWFdapte2Xn1nn7gF0idFwWi3c0lH6+uNy3k"
    + "6gtvc4EC/wrktp0xzqZfI10DPhp5ycfJp6scCKWMZ+4="; */
    private static final String PRIVATE_KEY =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALponvXquxdjQ4Yw"
            + "jse6sM7cbxGADfB84BbzgOLXJUnMlMva+31I8hTNlbaAqSwVMEVepDljFf9j8vkw"
            + "wyKX0oS5pwwBKRvmzw5Pa0viShbMi8c9W/u+EOxXg++OEab1RV09Cb1tmq91t8Jj"
            + "Cu+Em+uSWZYBZMHE1Pstln679CHxAgMBAAECgYBk0HhIH68XswOt600NEBbXibg4"
            + "Cr1/NsorH36xMBJmm1JN/hzNYUornhQ+I89anAsDOXAv6TrYaMHbq1edueaMHU9r"
            + "v+N0qwqIr4kgdZNXzTqFYLVBYVj4i7MMSjXZ12dUgvpRfSb03dpnkJLnQsHmwxS7"
            + "kf16MmhpLqeAei1owQJBAN/lCa/JoNyBs5kPc95aq1r+/n1uAI3d/j/gMbiOfLKX"
            + "43drLdelYZzZMQVB5PZiDfBtrCtMiGQVJJRhvT6FrIUCQQDVI4HgAwbLjrT7DDE9"
            + "MqrL7pbNqZxhGATpPoJAn5fVZ55jfH7VQSCgn+TPVixYeyXLU1KhxB9C+SGiXAZE"
            + "NuF9AkA6JGVmlArFGJ2GIwAo/wy+vbdi5T0ZmkubVOI0ljQFwPHeIiQuexElRTW4"
            + "ssr9vKr4A9MRD3ff3am5KsSVRuttAkBO+rpcpSJ2eaoswQbZ85WAvieZjDlLbVgc"
            + "ijI/+iOMS7pQiUiCg4CwpK9n+Tmzi+akYnrjQMaIzcKd5FXaHzANAkBFu5vkCsgW"
            + "6WVM8CsSnAwWwai5K1yrHCtvTNht7y9pIRaR2tSwhzaW+Wlx/dGPmxaqF8W8+nWi"
            + "TPJweqs6inI1";
    
    private static final String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDY0LExIYxUftwenZJ4NVMhjsDd"
            + "n2zGUHuI9jWkxKEUK5tW4RYNtla9mvui3g9AGcYPZyWB7W2e11BgdngI2fGSeUjM"
            + "sfZ3mxnrkLD9rIBTsBKHJEiJW6oBhO32jYB42FTrjqYb9Z6dshVL7NKnlEQiFskx"
            + "Gau9Jaib+BNQb2wyeQIDAQAB";
    
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

    public OAuthTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(OAuthSignatureMethod.decodeBase64(PUBLIC_KEY));
        KeyFactory fac = KeyFactory.getInstance("RSA");
        PublicKey publicKey = fac.generatePublic(pubKeySpec);
        System.out.println(publicKey.toString());

        EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(OAuthSignatureMethod.decodeBase64(PRIVATE_KEY));
        fac = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = fac.generatePrivate(privKeySpec);
        System.out.println(publicKey.toString());

        CertificateFactory certFac = CertificateFactory.getInstance("X509");
        ByteArrayInputStream in = new ByteArrayInputStream(CERTIFICATE.getBytes());
        X509Certificate cert = (X509Certificate)certFac.generateCertificate(in);
        System.out.println(cert.toString());
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hello() {
    }
}
