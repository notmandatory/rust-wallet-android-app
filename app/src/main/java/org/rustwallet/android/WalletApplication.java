package org.rustwallet.android;

import android.app.Application;

import androidx.room.Room;

import org.rustwallet.android.db.AppDatabase;
import org.rustwallet.android.db.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WalletApplication extends Application {

    private final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    private static String PASSPHRASE = "correct horse battery staple";

    private Single<AppDatabase> appDatabase;

    private Single<AccountService> accountService;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Single.just(Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db").build()).cache();

        accountService = Single.just(new AccountService()).cache();
    }

    private Single<AppDatabase> getAppDatabase() {
        return appDatabase.subscribeOn(Schedulers.io());
    }

    private Single<AccountService> getAccountService() {
        return accountService;
    }

    public Maybe<Wallet> getDefaultWallet() {
        return getAppDatabase().flatMapMaybe(db -> db.walletDao().findByPurpose(Wallet.Purpose.DEFAULT))
                .subscribeOn(Schedulers.io());
    }

    public Single<List<String>> getMnemonicWords() {
        return getAccountService().map(as -> as.getMnemonicWords(Entropy.Recommended))
                .subscribeOn(Schedulers.io());
    }

    public Single<Wallet> createTradeWallet(List<String> mnemonicWords) {

        log.debug("createTradeWallet from seed words: {}", mnemonicWords);

        Single<MasterAccount> masterAccount = getAccountService().map(as ->
                as.getMaster(mnemonicWords, new Date(), Network.Testnet, PASSPHRASE, null));

        Single<Wallet> wallet = masterAccount.map(a -> {
            Wallet w = new Wallet();
            w.encrypted = a.getEncrypted();
            w.masterPublic = a.getMasterPublic();
            w.birth = a.getBirth();
            w.purpose = Wallet.Purpose.DEFAULT;
            return w;
        });

        // TODO handle errors
        return appDatabase.map(AppDatabase::walletDao)
                .flatMap(dao -> wallet.flatMap(w -> dao.insert(w).flatMap(dao::findById).toSingle()))
                .subscribeOn(Schedulers.io());
    }
}
