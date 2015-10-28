package com.erised;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erised.base.BaseActivity;
import com.erised.dialog.LoadingDialog;
import com.erised.helper.VerifyLocation;
import com.erised.utils.SPManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rahul on 14/9/15.
 */
public class MainActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button mSignInButton;
    String city;

    private String TAG = "Hello";
    private String mSocialId, mSocialUsername, mSocialEmailId, mSocialProfilePicUrl, mGender;

    //
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /**
     * A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents. *
     */
    private boolean mIntentInProgress;

    /**
     * Track whether the sign-in button has been clicked so that we know to resolve
     * all issues preventing sign-in without waiting.  *
     */
    private boolean mSignInClicked;

    /**
     * Store the connection result from onConnectionFailed callbacks so that we can
     * resolve them when the user clicks sign-in.  *
     */
    private ConnectionResult mConnectionResult;
   /* private TextView lblProfileName;
    private ImageView imgProfile;*/

    private LoadingDialog loadingDialog;
    private Location mLastLocation;

    @Override
    protected void initUI() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        mSignInButton = (Button) findViewById(R.id.btnSignIn);
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
    }

    @Override
    protected void initData() {

        loadingDialog = LoadingDialog.getInstance();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))
                .addScope(new Scope(Scopes.PROFILE))
                .build();

        mSignInButton.setOnClickListener(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
//                info.setText(
//                        "User ID: "
//                                + loginResult.getAccessToken().getUserId()
//                                + "\n" +
//                                "Auth Token: "
//                                + loginResult.getAccessToken().getToken()
//                );

                launchMenuActivity();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login attempt canceled", Toast.LENGTH_LONG).show();
//                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(MainActivity.this, "Error! Login attempt canceled", Toast.LENGTH_LONG).show();
//              info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
        VerifyLocation verifyLocation = new VerifyLocation(this);
        verifyLocation.checkLocation();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    /*super.onActivityResult(requestCode, resultCode, data);*/

        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode != RESULT_OK) {
                    mSignInClicked = false;
                }
                mIntentInProgress = false;
                if (!mGoogleApiClient.isConnecting()) {

                    mGoogleApiClient.connect();
                }
                break;
            default:

                break;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSignIn:

                // mIntentInProgress = false;
                if (!mGoogleApiClient.isConnecting()) {
                    mSignInClicked = true;
                    resolveSignInError();
                }

                break;
           /* case R.id.btnNext:
                Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));

            Toast.makeText(this, String.valueOf(mLastLocation.getLatitude()) + String.valueOf(mLastLocation.getLongitude()), Toast.LENGTH_LONG).show();

        }

        // We've resolved any connection errors.  mGoogleApiClient can be used to
        // access Google APIs on behalf of the user.
        mSignInClicked = false;

        if (mGoogleApiClient.isConnected()) {

            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            loadingDialog.showDialog(this);
            getGooglePlusDetails();

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (!connectionResult.hasResolution()) {

            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = connectionResult;

            if (mSignInClicked) {

                // The user has already clicked 'sign-in' so we attempt to
                // resolve all errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    /**
     * A helper method to resolve the current ConnectionResult error.
     */
    private void resolveSignInError() {

        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
                //startIntentSenderForResult(mConnectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void signoutGoogle() {

        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }

    private void getGooglePlusDetails() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {

                Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

                mSocialId = person.getId();
                mSocialUsername = person.getDisplayName();
                mSocialEmailId = Plus.AccountApi.getAccountName(mGoogleApiClient);
                mSocialProfilePicUrl = person.getImage().getUrl().substring(0, person.getImage().getUrl().length() - 2) + 500/*Image Size*/;
                switch (person.getGender()) {

                    case Person.Gender.MALE:
                        mGender = "MALE";
                        break;
                    case Person.Gender.FEMALE:
                        mGender = "FEMALE";
                        break;
                    case Person.Gender.OTHER:
                        mGender = "OTHER";
                        break;
                }

              /* Picasso.with(SignInActivity.this).load(mSocialProfilePicUrl).into(imgProfile);
                lblProfileName.setText("Name "+mSocialUsername+"\nEmail ID "+mSocialEmailId);*/

                logD("Social Id == " + mSocialId);
                logD("Profile_UserName == " + mSocialUsername);
                logD("Profile_Image == " + mSocialProfilePicUrl);
                logD("Gender " + mGender);
                SPManager.save(SPManager.KEY_USERID, mSocialId);
                SPManager.save(SPManager.KEY_USERNAME, mSocialUsername);
                SPManager.save(SPManager.KEY_PROFILE_PIC, mSocialProfilePicUrl);
                SPManager.save(SPManager.KEY_GENDER, mGender);
                SPManager.saveBoolean(SPManager.KEY_LOGGED_IN, true);

                launchMenuActivity();
                /***get the google plus details and do signout. no more need of google plus session***/

                //if()
                signoutGoogle();
            } else {

                signoutGoogle();
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            loadingDialog.dismissDialog();
        }
    }


    private void launchMenuActivity() {

        finish();

        Intent intent = new Intent(MainActivity.this, ErisedMenuActivity.class);
        intent.putExtra("City",city);
        startActivity(intent);
    }
}