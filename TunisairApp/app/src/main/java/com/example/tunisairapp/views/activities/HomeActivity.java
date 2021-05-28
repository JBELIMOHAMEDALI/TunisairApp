package com.example.tunisairapp.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.tunisairapp.views.fragments.AddDemandeAutorisationFrag;
import com.example.tunisairapp.views.fragments.AddNewDemandeCongeFrag;
import com.example.tunisairapp.views.fragments.DemandeAutorisationFrag;
import com.example.tunisairapp.views.fragments.DemandesCongeFrag;
import com.example.tunisairapp.views.fragments.HomeFragment;
import com.example.tunisairapp.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final float END_SCALE = 0.7f;
    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        //call function
        navigationDrawer();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HomeFragment fragObj = new HomeFragment();
        ft.add(R.id.frag1, fragObj);
        ft.commit();
    }



    /* Navigation Drawer functions */
    private void navigationDrawer() {
        // Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.myblue,getTheme()));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.frag1, new HomeFragment());

                //Bundle bundle = new Bundle();
                //bundle.putString("tv_show", "Hello everyone !!");
                //HomeFragment fragObj = new HomeFragment();
                //fragObj.setArguments(bundle);
                //ft.add(R.id.frag1, fragObj);
                ft.commit();
                break;
            case R.id.list_demande_conge:
                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                //ft1.replace(R.id.frag1, new DemandesCongeFrag());
                ft1.replace(R.id.frag1, new DemandesCongeFrag());
                ft1.commit();
                break;
            case R.id.add_demande_conge:
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                //ft1.replace(R.id.frag1, new DemandesCongeFrag());
                ft2.replace(R.id.frag1, new AddNewDemandeCongeFrag());
                ft2.commit();
                break;
            case R.id.list_demande_autorisation:
                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                //ft1.replace(R.id.frag1, new DemandesCongeFrag());
                ft3.replace(R.id.frag1, new DemandeAutorisationFrag());
                ft3.commit();
                break;
            case R.id.add_demande_autorisation:
                FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft4.replace(R.id.frag1, new AddDemandeAutorisationFrag());
                ft4.commit();
                break;
            /*case R.id.nav_history:
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft5.replace(R.id.frag1, new HistoryFrag());
                ft5.commit();
                break;*/
            case R.id.nav_logout:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sure you want to Logout ?")
                        .setMessage("Clicking Yes will make u redirected to Login page")
                        //set positive button
                        .setPositiveButton("Yes", (dialogInterface, i1) -> {
                            Intent i6 = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(i6);
                            finish();
                        })
                        //set negative button
                        .setNegativeButton("No", (dialogInterface, i12) -> {
                            //nothing happen
                        })
                        .show();
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    /* end navigation drawer functions */


}