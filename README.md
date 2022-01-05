## Android SDK for ARCaptcha

###### [Installation](#installation) | [Example](#display-a-arcaptcha-challenge) | [Customization](#config-params) | [Error handling](#error-handling)

This SDK provides a wrapper for [ARCaptcha](https://www.arcaptcha.ir). You will need to configure a `site key` and a `secret key` from your arcaptcha account in order to use it.


### Installation

<pre>
// Register JitPack Repository inside the root build.gradle file
repositories {
    <b>maven { url 'https://jitpack.io' }</b> 
}
// Add ARCaptcha sdk dependency inside the app's build.gradle file
dependencies {
    <b>implementation 'com.github.sadeghb97:arcaptcha_android_sdk:0.0.1'</b>
}
</pre>


#### Display a ARCaptcha challenge

The following snippet code will ask the user to complete a challenge. 

```java

ArcaptchaDialog.ArcaptchaListener arcaptchaListener = new ArcaptchaDialog.ArcaptchaListener() {
    @Override
    public void onSuccess(String token) {
        Toast.makeText(MainActivity.this, "Puzzle Solved, Token Generated!",
                Toast.LENGTH_LONG).show();
        arcaptchaDialog.dismiss();

        Log.d("Token: [" + token + "]");
        txvLog.setText("ArcaptchaToken: \n" + token);
    }

    @Override
    public void onCancel() {
        arcaptchaDialog.dismiss();
    }
};

arcaptchaDialog = ArcaptchaDialog.getInstance(YOUR_API_SITE_KEY,
        DOMAIN, arcaptchaListener);
arcaptchaDialog.show(getSupportFragmentManager(), "arcaptcha_dialog_tag");
```


##### Config params


|Name|Values/Type|Required|Default|Description|
|---|---|---|---|---|
|`siteKey`|String|**Yes**|-|This is your sitekey, this allows you to load challenges. If you need a sitekey, please visit [ARCaptcha](https://arcaptcha.ir/sign-up), and sign up to get your sitekey.|
|`size`|Enum|No|INVISIBLE|This specifies the "size" of the checkbox component. By default, the checkbox is invisible and the challenge is shown automatically.|


#### Verify the completed challenge

After retrieving a `token`, you should pass it to your backend in order to verify the validity of the token by doing a [server side check](https://docs.arcaptcha.ir/docs/installation#verify-the-user-response-server-side) using the ARCaptcha secret linked to your sitekey.
