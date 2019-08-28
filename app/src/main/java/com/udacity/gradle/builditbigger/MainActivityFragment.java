package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokelib.MyClass;
import com.example.myandroidlibrary.LibraryActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button button=root.findViewById(R.id.instructions_button);
        TextView textView= root.findViewById(R.id.instructions_text_view);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getContext(), "Manfred"));
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass JokeLib=new MyClass();
                String wizardJoke = JokeLib.tellAWizardJoke();
                Intent intent=new Intent(getContext(), LibraryActivity.class);
                intent.putExtra("Joke",wizardJoke);
                startActivity(intent);
                Toast.makeText(getContext(), wizardJoke, Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }


}
