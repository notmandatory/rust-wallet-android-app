package org.rustwallet.android.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Wallet.class}, version = 1)
@androidx.room.TypeConverters({TypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WalletDao walletDao();
}

