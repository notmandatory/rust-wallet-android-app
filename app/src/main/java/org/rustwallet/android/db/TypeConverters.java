package org.rustwallet.android.db;

import androidx.room.TypeConverter;

import java.util.Date;

public class TypeConverters {

    @TypeConverter
    public static Date epochToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToEpoch(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Wallet.Purpose nameToPurpose(String name) {
        return name == null ? null : Wallet.Purpose.valueOf(name);
    }

    @TypeConverter
    public static String purposeToName(Wallet.Purpose purpose) {
        return purpose == null ? null : purpose.toString();
    }
}

