package org.rustwallet.android;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static String PASSPHRASE = "correct horse battery staple";

    // Must set environment variable: eg. (on osx)
    // export JAVA_LIBRARY_PATH=[project_home]/lib/src/main/jniLibs/x86_64
    @Test
    public void accountLib_getMaster_notNull() {
        String path = System.getProperty("java.library.path");
        System.out.println(path);

        AccountService accountService = new AccountService();

        MasterAccount master = accountService.getMaster(Entropy.Low, Network.Bitcoin, PASSPHRASE);
        assertNotNull(master);
    }
}