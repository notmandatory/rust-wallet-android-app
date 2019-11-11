package org.rustwallet.android;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String PASSPHRASE = "correct horse battery staple";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.receive_addr);

        AccountService accountService = new AccountService();

        List<String> mnemonicWords = accountService.getMnemonicWords(Entropy.Low);

        MasterAccount master = accountService.getMaster(mnemonicWords, new Date(), Network.Bitcoin, PASSPHRASE, null);

        Account account = accountService.getAccount(master, PASSPHRASE, 0, 0, 0, 0, 10);

        tv.setText(account.getInstantiated().get(account.getNext()));
    }
}
