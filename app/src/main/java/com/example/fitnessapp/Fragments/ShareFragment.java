package com.example.fitnessapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.R;

public class ShareFragment extends Fragment {

    private static final int SHARE_REQUEST_CODE = 123;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        Button shareButton = view.findViewById(R.id.shareApp);

        shareButton.setOnClickListener(v -> {
            ShareApp(requireContext());
        });

        return view;
    }

    private void ShareApp(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Now: https://play.google.com/store/apps/details?id=" + context.getPackageName());
        sendIntent.setType("text/plain");

        startActivityForResult(Intent.createChooser(sendIntent, "Share via"), SHARE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHARE_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                // Show a toast message indicating successful sharing
                Toast.makeText(requireContext(), "App shared successfully", Toast.LENGTH_SHORT).show();
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // Show a toast message indicating that sharing was canceled
                Toast.makeText(requireContext(), "Sharing canceled", Toast.LENGTH_SHORT).show();
            } else {
                // Show a toast message indicating that sharing failed
                Toast.makeText(requireContext(), "Sharing failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
