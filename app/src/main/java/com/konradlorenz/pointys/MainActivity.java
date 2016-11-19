package com.konradlorenz.pointys;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Button btn_listado;
    Button btn_mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListadoFragment listado = new ListadoFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, listado);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        btn_listado = (Button) findViewById(R.id.btn_listaR);
        btn_listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListadoFragment listado = new ListadoFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, listado);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });
        btn_mapa = (Button) findViewById(R.id.btn_mapaR);
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapaFragment mapaFragment = new mapaFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, mapaFragment);
                ft.addToBackStack(null);
                ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == -1) {
                Intent intento = new Intent(MainActivity.this,PointsRedention.class);
                startActivity(intento);
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Pcercanoas) {
            // Handle the camera action
        } else if (id == R.id.nav_PAcumulados) {

        } else if (id == R.id.nav_Catalogo) {
            ListadoFragment listado = new ListadoFragment();
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, listado);
            ft.addToBackStack(null);
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else if (id == R.id.nav_MCuenta) {

        } else if (id == R.id.nav_Ajuste) {

        } else if (id == R.id.nav_ayuda) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
