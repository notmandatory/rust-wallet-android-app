package org.rustwallet.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.rustwallet.android.db.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {


    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final Logger log = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup navigation

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // setup wallet

        WalletApplication app = (WalletApplication) getApplication();

        Single<Wallet> wallet = app.getTradeWallet()
                .switchIfEmpty(app.getMnemonicWords().flatMap(app::createTradeWallet));

        compositeDisposable.add(wallet.observeOn(AndroidSchedulers.mainThread())
                .subscribe(w -> log.debug("created wallet: {}", w)));

        //TextView tv = findViewById(R.id.receive_addr);

//
//        List<String> mnemonicWords = accountService.getMnemonicWords(Entropy.Low);
//
//        MasterAccount master = accountService.getMaster(mnemonicWords, new Date(), Network.Bitcoin, PASSPHRASE, null);
//
//        Account account = accountService.getAccount(master, PASSPHRASE, 0, 0, 0, 0, 10);
//
//        tv.setText(account.getInstantiated().get(account.getNext()));
    }
}
