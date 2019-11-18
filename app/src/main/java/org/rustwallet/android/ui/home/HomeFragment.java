package org.rustwallet.android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.rustwallet.android.MainActivity;
import org.rustwallet.android.R;
import org.rustwallet.android.WalletApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomeFragment extends Fragment {

    private final Logger log = LoggerFactory.getLogger(MainActivity.class);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // setup wallet

        WalletApplication app = (WalletApplication) getActivity().getApplication();

        Disposable walletDisposable = app.getDefaultWallet()
                .observeOn(AndroidSchedulers.mainThread()).toSingle()
                .subscribe(w -> {
                    log.debug("Wallet found.");
                }, t -> {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.navigation_seed);
                });

        compositeDisposable.add(walletDisposable);

        return root;
    }
}