Rust Wallet Android Example App
===================

This is a very WIP project to demonstrate how to use the 
[rust-wallet](https://github.com/rust-bitcoin/rust-wallet) library to 
build an Android bitcoin wallet.

## Install rust-wallet-android libraries

1. Install [rust-wallet-android](https://github.com/notmandatory/rust-wallet-android) project in parent directory

    ```
    cd ..
    clone https://github.com/notmandatory/rust-wallet-android
    ```

1. Set environment variables needed to build rust-wallet-android, better
   yet add these to your `.bash_profile`

    ```
    export CC_aarch64_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android24-clang
    export CXX_aarch64_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android24-clang++
    export AR_aarch64_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android-ar
    
    export CC_armv7_linux_androideabi=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/armv7a-linux-androideabi24-clang
    export CXX_armv7_linux_androideabi=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/armv7a-linux-androideabi24-clang++
    export AR_armv7_linux_androideabi=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-ar
    
    export CC_i686_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/i686-linux-android24-clang
    export CXX_i686_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/i686-linux-android24-clang++
    export AR_i686_linux_android=$NDK_HOME/toolchains/llvm/prebuilt/darwin-x86_64/bin/i686-linux-android-ar
    ```

1. Build and install [rust-wallet-android](https://github.com/notmandatory/rust-wallet-android) project libraries
    
   ```
   cd rust-wallet-android
   ./build.sh
   ./install.sh
   ```
   

