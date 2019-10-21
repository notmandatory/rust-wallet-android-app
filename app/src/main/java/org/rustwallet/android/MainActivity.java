package org.rustwallet.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.receive_addr);

        Address address = new Address();
        String receiveAddr = address.getReceiveAddress(
                "announce damage viable ticket engage curious yellow ten clock finish burden orient faculty rigid smile host offer affair suffer slogan mercy another switch park",
                "correct horse battery staple",
                0);

        tv.setText(receiveAddr);
    }
}
