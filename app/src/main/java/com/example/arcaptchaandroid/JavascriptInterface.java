package com.example.arcaptchaandroid;

import android.content.Context;

public class JavascriptInterface {
    ArcaptchaDialog.ArcaptchaListener arcaptchaListener;
    Context context;

    JavascriptInterface(Context c, ArcaptchaDialog.ArcaptchaListener arcaptchaListener) {
        this.context = c;
        this.arcaptchaListener = arcaptchaListener;
    }

    @android.webkit.JavascriptInterface
    public void passToken(String token){
        if(arcaptchaListener != null) arcaptchaListener.onSuccess(token);
    }
}
