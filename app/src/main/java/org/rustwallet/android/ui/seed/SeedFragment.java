package org.rustwallet.android.ui.seed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.rustwallet.android.R;
import org.rustwallet.android.WalletApplication;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SeedFragment extends Fragment {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private SeedViewModel seedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        seedViewModel = ViewModelProviders.of(this).get(SeedViewModel.class);
        View fragmentSeed = inflater.inflate(R.layout.fragment_seed, container, false);

        BottomNavigationView nav = getActivity().findViewById(R.id.nav_view);
        nav.setVisibility(View.GONE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final TextView textView = fragmentSeed.findViewById(R.id.text_seed);
        seedViewModel.getText().observe(this, textView::setText);

        final CheckBox checkBox = fragmentSeed.findViewById(R.id.check_box_seed);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> seedViewModel.setCheckBox(isChecked));

        final Button button = fragmentSeed.findViewById(R.id.button_create_wallet);
        seedViewModel.getCheckBox().observe(this, button::setEnabled);

        button.setOnClickListener(v -> {
            Disposable showSeedDisposable = ((WalletApplication) getActivity().getApplication())
                    .createTradeWallet(seedViewModel.getMnemonicWords().getValue())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(w -> {
                        nav.setVisibility(View.VISIBLE);

                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.navigation_home);
                    });
            compositeDisposable.add(showSeedDisposable);
        });

        return fragmentSeed;
    }

    @Override
    public void onResume() {
        super.onResume();

        Disposable showSeedDisposable = ((WalletApplication) getActivity().getApplication())
                .getMnemonicWords()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sw -> seedViewModel.setMnemonicWords(sw));

        compositeDisposable.add(showSeedDisposable);
    }

    @Override
    public void onStop() {
        super.onStop();

        compositeDisposable.dispose();
    }
}