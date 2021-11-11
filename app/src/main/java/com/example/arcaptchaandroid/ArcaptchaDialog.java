package com.example.arcaptchaandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class ArcaptchaDialog extends DialogFragment {
    Activity activity;
    View rootView;
    WebView webMain;
    ArcaptchaListener arcaptchaListener;
    public static final String ARCAPTCHA_LISTENER_TAG = "arcaptcha_listener";

    public ArcaptchaDialog(){}

    public static ArcaptchaDialog getInstance(ArcaptchaListener arcaptchaListener){
        final Bundle args = new Bundle();
        args.putSerializable(ARCAPTCHA_LISTENER_TAG, arcaptchaListener);
        final ArcaptchaDialog arcaptchaDialog = new ArcaptchaDialog();
        arcaptchaDialog.setArguments(args);
        return arcaptchaDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        arcaptchaListener = (ArcaptchaListener) bundle.getSerializable(ARCAPTCHA_LISTENER_TAG);

        activity = getActivity();
        rootView = inflater.inflate(R.layout.arcaptcha_fragment, container, false);
        webMain = rootView.findViewById(R.id.webMain);
        setupWebView();

        return rootView;
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public void setupWebView(){
        JavascriptInterface javascriptInterface = new JavascriptInterface(activity, arcaptchaListener);

        final WebSettings settings = webMain.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        webMain.setBackgroundColor(Color.TRANSPARENT);
        webMain.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webMain.addJavascriptInterface(javascriptInterface, "AndroidInterface");
        webMain.loadUrl("http://igpro.ir/arc.php");
    }

    public static abstract class ArcaptchaListener implements Parcelable, Serializable {
        public abstract void onSuccess(String token);
        public abstract void onFailure();

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
