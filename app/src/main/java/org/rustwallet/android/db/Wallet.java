package org.rustwallet.android.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Date;

@Entity(tableName = "wallet")
public class Wallet {

    public enum Purpose {
        DEFAULT
    }

    @NonNull
    @PrimaryKey
    public Long id;

    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public Purpose purpose;

    @NonNull
    @ColumnInfo(name = "master_public")
    public String masterPublic;

    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] encrypted;

    @NonNull
    public Date birth;

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", purpose=" + purpose +
                ", masterPublic='" + masterPublic + '\'' +
                ", encrypted=" + Arrays.toString(encrypted) +
                ", birth=" + birth +
                '}';
    }
}
