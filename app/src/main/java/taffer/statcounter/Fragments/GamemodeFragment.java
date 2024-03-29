package taffer.statcounter.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import taffer.statcounter.R;

/**
 * Fragment for choosing the Gamemode.
 */

public class GamemodeFragment extends Fragment {
    /**
     * Creates the View.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View, the view.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gamemode, container, false);
    }
}
