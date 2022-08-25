package com.example.yourcali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.Objects;

public class WorkoutPlanSelectionActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Data data;
    PredefinedWorkoutPlanSelectionFragment predefinedWorkoutPlanSelectionFragment;
    PersonalWorkoutPlanSelectionFragment personalWorkoutPlanSelectionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_selection);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }

        // Variables linking
        myToolbar = findViewById(R.id.myToolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Page layout setup
        setSupportActionBar(myToolbar);

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        predefinedWorkoutPlanSelectionFragment = new PredefinedWorkoutPlanSelectionFragment();
        personalWorkoutPlanSelectionFragment = new PersonalWorkoutPlanSelectionFragment();
        predefinedWorkoutPlanSelectionFragment.setArguments(bundle);
        personalWorkoutPlanSelectionFragment.setArguments(bundle);
        vpAdapter.addFragment(predefinedWorkoutPlanSelectionFragment, "Predefinite");
        vpAdapter.addFragment(personalWorkoutPlanSelectionFragment, "Personali");
        viewPager.setAdapter(vpAdapter);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Selezione scheda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(WorkoutPlanSelectionActivity.this, HomeActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Cerca Scheda");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                predefinedWorkoutPlanSelectionFragment.doSearch(query);
                personalWorkoutPlanSelectionFragment.doSearch(query);
                return false;
            }
        });

        return true;
    }



}