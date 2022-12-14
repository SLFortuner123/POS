package com.slfortuner.navigationdrawerpos2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.slfortuner.navigationdrawerpos2.fragments.ProductNavigationDrawerFragment;
import com.slfortuner.navigationdrawerpos2.newsale.NewSaleNavigationDrawer;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener( this );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        drawer.addDrawerListener( toggle );
        toggle.syncState(); //this rotate the action bar toggle//

        if (savedInstanceState == null) {
//            Intent intent;
//            intent = new Intent(MainActivity.this, MainActivity2.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                    new NewSaleNavigationDrawer() ).commit();
            navigationView.setCheckedItem( R.id.report_menu );
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
            //else close activity as usual
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.report_menu:
//                Intent intent;
//                intent = new Intent( MainActivity.this, MainActivity2.class );
//                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                startActivity( intent );
//                break;
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                        new NewSaleNavigationDrawer()  ).commit();
                break;

            case R.id.product_menu:
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                        new ProductNavigationDrawerFragment() ).commit();
                break;

        }

        drawer.closeDrawer( GravityCompat.START );

        return true;
    }
}