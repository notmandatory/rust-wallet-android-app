package org.rustwallet.android.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Maybe;

@Dao
public interface WalletDao {

    @Query("SELECT * FROM wallet WHERE purpose = :purpose LIMIT 1")
    Maybe<Wallet> findByPurpose(Wallet.Purpose purpose);

    @Query("SELECT * FROM wallet WHERE id = :id LIMIT 1")
    Maybe<Wallet> findById(Long id);

    @Insert
    Maybe<Long> insert(Wallet wallet);
}
