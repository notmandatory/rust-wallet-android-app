package org.rustwallet.android.ui.seed;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeedViewModel extends ViewModel {

    private MutableLiveData<List<String>> mMnemonicWords;

    private MutableLiveData<String> mText;

    private MutableLiveData<Boolean> mCheckBox;

    public SeedViewModel() {

        mMnemonicWords = new MutableLiveData<>();
        mMnemonicWords.setValue(new ArrayList<>());

        mText = new MutableLiveData<>();
        mText.setValue("");

        mCheckBox = new MutableLiveData<>();
        mCheckBox.setValue(false);
    }

    public MutableLiveData<List<String>> getMnemonicWords() {
        return mMnemonicWords;
    }

    public void setMnemonicWords(List<String> mnemonicWords) {
        this.mMnemonicWords.setValue(mnemonicWords);
        this.mText.setValue(TextUtils.join(" ", mnemonicWords));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Boolean> getCheckBox() {
        return mCheckBox;
    }

    public void setCheckBox(Boolean checkbox) {
        this.mCheckBox.setValue(checkbox);
    }
}