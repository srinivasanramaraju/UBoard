package com.iit.t1.u_board.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.iit.t1.u_board.R;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,GoogleApiClient.OnConnectionFailedListener
         {
             private GoogleApiClient mGoogleApiClient;
    private static String TAG = MainActivity.class.getSimpleName();
    ImageButton imageButton;
    private Toolbar mToolbar;
    ImageButton FAB;
    private FragmentDrawer drawerFragment;
             SignInActivity signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new CreateNoticeFragment();
                title = getString(R.string.title_new_notice);
                break;
            case 2:
                fragment = new SalesFragment();
                title = getString(R.string.title_sales);
                break;
            case 3:
                fragment = new HousingFragment();
                title = getString(R.string.title_housing);
                break;
            case 4:
                fragment = new EventsFragment();
                title = getString(R.string.title_events);
                break;
            case 5:
                fragment = new JobsAndOpportunitiesFragment();
                title = getString(R.string.title_jobs_and_opportunities);
                break;
            case 6:

                //fragment = new SignOutFragment();
                signOut();
                title = getString(R.string.title_signout);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

             public void callCreateNotice(View v)
             {
                 Fragment fragment=new CreateNoticeFragment();
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.container_body, fragment);
                 fragmentTransaction.commit();
                 imageButton= (ImageButton)findViewById(R.id.fab_add);
                 imageButton.setVisibility(View.INVISIBLE);
                 // set the toolbar title
                 getSupportActionBar().setTitle("New Notice");
             }

             public void eventsmenu(View v)
             {
                 Fragment fragment=new EventsFragment();
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.container_body, fragment);
                 fragmentTransaction.commit();
                 imageButton= (ImageButton)findViewById(R.id.eventsButton);
                 imageButton.setVisibility(View.INVISIBLE);
                 // set the toolbar title
                 getSupportActionBar().setTitle("Events");
             }
             public void jobsmenu(View v)
             {
                 Fragment fragment=new JobsAndOpportunitiesFragment();
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.container_body, fragment);
                 fragmentTransaction.commit();
                 imageButton= (ImageButton)findViewById(R.id.jobsButton);
                 imageButton.setVisibility(View.INVISIBLE);
                 // set the toolbar title
                 getSupportActionBar().setTitle("Jobs");
             }

             public void housemenu(View v)
             {
                 Fragment fragment=new HousingFragment();
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.container_body, fragment);
                 fragmentTransaction.commit();
                 imageButton= (ImageButton)findViewById(R.id.houseButton);
                 imageButton.setVisibility(View.INVISIBLE);
                 // set the toolbar title
                 getSupportActionBar().setTitle("Housing");
             }

             public void salemenu(View v)
             {
                 Fragment fragment=new SalesFragment();
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.container_body, fragment);
                 fragmentTransaction.commit();
                 imageButton= (ImageButton)findViewById(R.id.saleButton);
                 imageButton.setVisibility(View.INVISIBLE);
                 // set the toolbar title
                 getSupportActionBar().setTitle("Sales");
             }
    public void setActionBar(String title)
    {
        imageButton.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle(title);
    }

             @Override
             public void onConnectionFailed(ConnectionResult connectionResult) {

             }


             private void signOut() {
                 Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                         new ResultCallback<Status>() {
                             @Override
                             public void onResult(Status status) {
                                 // [START_EXCLUDE]
                                 Intent logout=new Intent(MainActivity.this,SignInActivity.class);
                                 MainActivity.this.startActivity(logout);
                                 // [END_EXCLUDE]
                             }
                         });
             }
         }