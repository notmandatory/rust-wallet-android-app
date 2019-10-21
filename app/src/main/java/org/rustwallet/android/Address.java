package org.rustwallet.android;

public class Address {

    // Used to load the 'rust_wallet_android' library on application startup.
    static {
        System.loadLibrary("rust_wallet_android");
    }

    /**
     * A native method that is implemented by the 'rust_wallet_android' native library,
     * which is packaged with this application.
     */
    public native String getReceiveAddress(String words, String passphrase, int subAccountNumber);
}
