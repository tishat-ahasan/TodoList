package edu.univdhaka.cse2216.myplane.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.univdhaka.cse2216.myplane.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout parentLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parentLayout=findViewById(R.id.parentId);
        //fragment=findViewById(R.id.fragmentId);

        drawerLayout = findViewById(R.id.drawerId);
        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView)findViewById(R.id.navigationDrawerId);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

       // Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();

        int id= menuItem.getItemId();
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


        if(id==R.id.homeId)
        {
            Fragment fragment=new HomeFragment();
            FragmentManager fragmentManager= getFragmentManager();

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.parentId,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this,"Home is clicked",Toast.LENGTH_SHORT).show();

        }
        else if(id==R.id.donelistId)
        {
            Fragment fragment=new DoneList();
            FragmentManager fragmentManager= getFragmentManager();

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.parentId,fragment);
            fragmentTransaction.commit();

            Toast.makeText(this,"Done List  is clicked",Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(this,DoneList.class);
//            startActivity(intent);

        }
        else if(id==R.id.addId)
        {

//            Fragment fragment=new AddNew();
//            FragmentManager fragmentManager= getFragmentManager();
//
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.parentId,fragment);
//            fragmentTransaction.commit();
//            Toast.makeText(this,"Add New is clicked",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,AddNewTask.class);
            startActivity(intent);

        }
        else if(id==R.id.creditsId)
        {

            Fragment fragment=new Credits();
            FragmentManager fragmentManager= getFragmentManager();

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.parentId,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this,"Credits is clicked",Toast.LENGTH_SHORT).show();

        }
        else if(id==R.id.aboutId)
        {

            Fragment fragment=new AboutApp();
            FragmentManager fragmentManager= getFragmentManager();

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.parentId,fragment);
            fragmentTransaction.commit();
            Toast.makeText(this,"About App is clicked",Toast.LENGTH_SHORT).show();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setIcon(R.drawable.question)
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create()
                .show();

    }
}
