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

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.Objects;

public class ExercisesEncyclopediaActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PredefinedExerciseFragment predefinedExerciseFragment;
    PersonalExerciseFragment personalExerciseFragment;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_encyclopedia);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }

        // Variables linking
        myToolbar = findViewById(R.id.myToolbar);
        tabLayout = findViewById(R.id.tabLayoutEncyclopedia);
        viewPager = findViewById(R.id.viewPagerEncyclopedia);

        // Page layout setup
        setSupportActionBar(myToolbar);

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        predefinedExerciseFragment = new PredefinedExerciseFragment();
        personalExerciseFragment = new PersonalExerciseFragment();
        predefinedExerciseFragment.setArguments(bundle);
        personalExerciseFragment.setArguments(bundle);
        vpAdapter.addFragment(predefinedExerciseFragment, "Predefiniti");
        vpAdapter.addFragment(personalExerciseFragment, "Personali");
        viewPager.setAdapter(vpAdapter);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Enciclopedia esercizi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent goBack = new Intent(ExercisesEncyclopediaActivity.this, HomeActivity.class);
        goBack.putExtra("data", data);
        startActivity(goBack);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Cerca esercizio");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                predefinedExerciseFragment.doSearch(query);
                personalExerciseFragment.doSearch(query);
                return false;
            }
        });

        return true;
    }

}