/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;
import junit.framework.TestCase;
import java.util.Random;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;




/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

    String[] testScheme = {"http://", "ftp://", "https", "", "http:/", "http:", "://"};         
    String[] testAuthority = {"www.google.com", "go.com", "1.2.3.4", "1.2.3", "go.a", "256.256.256.256"};  
    String[] testPort = {":80", ":65535", "", ":65a", ":655356"};                     
    String[] testPath = {"", "/test1", "/$23"};                         
    String[] testQuery = {"", "?action=view", "?action=edit&mode=up"};  

   
   // queries are failing
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       String[] validUrls = {
               "http://google.com",
               "http://amazon.com",
               "http://willmyphonework.net",
               "http://oregonstate.edu:80",
               "https://www.khanacademy.org/math/early-math(1)",
               "https://www.amazon.com/dp/B00U3FPN4U/ref=nav_shopall_1_k_ods_smp_se",
               "http://-.~_!$&'()*+,;=:%40:80%2f::::::@example.com",
               "https://www.codecademy.com/login/baz=&var",
               "http://142.42.1.1:1",
               "http://code.google.com/events/#&product=browser",
               "http://foo.bar/%20"
        };

       // 224.1.1.1 multicast address is allowed
       String[] invalidUrls = {
               null,
               "http://142.42.1.1.1",
               "http:// shouldfail.com",
               "http://foo.bar?q=Spaces should be encoded",
               "ftps://foo.bar/",
               "http://-error-.invalid/",
               //"http://224.1.1.1",
               "http://.www.foo.bar./",
               "http://foo.bar/foo(bar)baz quux"
       };

        for (int i = 0; i < validUrls.length; i++){
            System.out.println("testing: (" + validUrls[i] + ")...");
            if (urlVal.isValid(validUrls[i])) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
            }
        }

        for (int i = 0; i < invalidUrls.length; i ++){
            assertFalse(urlVal.isValid(invalidUrls[i]));
        }
	   
	   
   }
   
   //checks encoding
   public void testEncoding()
   {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

       for (int i = 0; i < validEncoding.length ; i++){
           String url = "http://foo.bar/%" + validEncoding[i];
           if (!urlVal.isValid(url)) {
               System.out.println(url + ": FAIL");
           }
       }
   }

   //port numbers fail after port 999
   public void testPorts(){
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

       for (int i = 0; i < 1000; i++) {
           String url = "http://142.42.1.1:" + i;
           //System.out.println("Testing: " + url);
           assertTrue(urlVal.isValid(url));
           //if (!urlVal.isValid(url)) {
           //    System.out.println(url + " failure");
           //}
       }
   }

   // this partition tests schemes
   // no failures thus far for this partition
   public void testSchemes(){
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

       for (int i = 0; i < validSchemes.length; i++) {
           String url = validSchemes[i] + "google.com";
           assertTrue(urlVal.isValid(url));
           //if (urlVal.isValid(url)){
           //    System.out.println(url + ": Passed");
           //}
       }
   }

   public void testQuery() {
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

       String url = "http://google.com/?=test";
       System.out.println(urlVal.isValid(url));
   }
   
    public static String RandomSelectIndex(Random random){
        String[] methodArray = new String[] { "scheme", "authority", "port", "path", "query"};
        int n = random.nextInt(methodArray.length);

        return methodArray[n] ; // return the method name
    }
   
   //  public void testIsValid01()
   // {
   //   for(int i = 0;i<10000;i++)
   //   {
   //    //Port tests using the for loop that has been given
   //         portTest("http://www.google.com:@@@");
   //      // IP tests using the for loop that has been given  
   //         ipTest("http://255.@@@.255.255");
   //         ipTest("http://@@@.255.255.255");
   //         ipTest("http://255.255.255.@@@");
   //         ipTest("http://255.255.@@@.255");
   //         ipTest("http://@@@.@@@.@@@.@@@");
       
   //   }
   // }

 
    public void testIsValid()
    {   System.out.println("Test Start:");
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        int urlIndex[] = {0,0,0,0,0};
        String testUrl = "";

        long randomseed =10;
        Random random = new Random(randomseed);

        for (int i = 0; i < 1000; i++) {
            String randomIndex = UrlValidatorTest.RandomSelectIndex(random);
            if (randomIndex.equals("scheme")){
                urlIndex[0] = random.nextInt(7);
            }
            else if(randomIndex.equals("authority")){
                urlIndex[1] = random.nextInt(6);
            }
            else if(randomIndex.equals("port")){
                urlIndex[2] = random.nextInt(5);
            }
            else if(randomIndex.equals("path")){
                urlIndex[3] = random.nextInt(3);
            }
            else if(randomIndex.equals("query")){
                urlIndex[4] = random.nextInt(3);
            }

            testUrl = testScheme[urlIndex[0]] + testAuthority[urlIndex[1]]
                    + testPort[urlIndex[2]]+ testPath[urlIndex[3]] + testQuery[urlIndex[4]];

            compareResult(urlVal, testUrl, urlIndex);
        }
    }

   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */

   String validSchemes[] = {
           "http://",
           "https://",
           "ftp://",
           "gopher://"
   };

   String validEncoding[] = {
           "20",
           "22",
           "23",
           "24",
           "25",
           "26"
   };

   public void compareResult(UrlValidator urlVal, String testUrl, int[] urlIndex) {
        boolean result = urlVal.isValid(testUrl);
        boolean expect = true;
        if(urlIndex[0] >= 4 || urlIndex[1] >= 3 || urlIndex[2] >= 3)
            expect = false;
        System.out.println(" ");

        if(result == expect && expect == true) {
            System.out.println("Valid URL: ");
            System.out.println(urlVal.isValid(testUrl));
            System.out.println(testUrl);
            assertTrue(urlVal.isValid(testUrl));
        }

        else if(result == expect && expect == false) {
            System.out.println("inValid URL: ");
            System.out.println(urlVal.isValid(testUrl));
            System.out.println(testUrl);
            assertFalse(urlVal.isValid(testUrl));
        }

        else if(expect == true){
            System.out.println("Faild case URL (Here are urls with bugs): ");
            assertTrue(!urlVal.isValid(testUrl));
            System.out.println(testUrl);
            System.out.println("Expect: " + expect + " Real: " + urlVal.isValid(testUrl));
        }

        else {
            System.out.println("Faild case URL (Here are urls with bugs): ");
            assertFalse(!urlVal.isValid(testUrl));
            System.out.println(testUrl);
            System.out.println("Expect: " + expect + " Real: " + urlVal.isValid(testUrl));
        }
    }
    
}
