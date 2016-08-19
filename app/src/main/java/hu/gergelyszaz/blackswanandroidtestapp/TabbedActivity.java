package hu.gergelyszaz.blackswanandroidtestapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import hu.gergelyszaz.blackswanandroidtestapp.model.Item;
import hu.gergelyszaz.blackswanandroidtestapp.network.TheMovieDB;


public class TabbedActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, SearchView.OnQueryTextListener {

    private SectionsPagerAdapter sectionsPagerAdapter;


    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ModelFragment modelFragment = ModelFragment.getModelFragment(getSupportFragmentManager());
        String popularmoviesAddress = getString(R.string.url_movies_popular) + "?api_key=" + getString(R.string.api_key);
        String popularpeopleAddress = getString(R.string.url_people_popular) + "?api_key=" + getString(R.string.api_key);
        String populartvAddress = getString(R.string.url_tv_popular) + "?api_key=" + getString(R.string.api_key);
        new TheMovieDB(modelFragment, TheMovieDB.MOVIES).getResponse(popularmoviesAddress);
        new TheMovieDB(modelFragment, TheMovieDB.PEOPLE).getResponse(popularpeopleAddress);
        new TheMovieDB(modelFragment, TheMovieDB.TV).getResponse(populartvAddress);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public void onListFragmentInteraction(Item item) {
        int tab = viewPager.getCurrentItem();
        if (tab != TheMovieDB.PEOPLE) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("type", tab);
            intent.putExtra("id", item.getId());
            startActivity(intent);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        int position = viewPager.getCurrentItem();
        ModelFragment modelFragment = ModelFragment.getModelFragment(getSupportFragmentManager());
        String address = "";
        switch (position) {
            case TheMovieDB.MOVIES:
                address = getString(R.string.url_movies_search);
                break;
            case TheMovieDB.PEOPLE:
                address = getString(R.string.url_people_search);
                break;
            case TheMovieDB.TV:
                address = getString(R.string.url_tv_search);
                break;
            default:
                throw new IllegalArgumentException();
        }
        address += "?api_key=" + getString(R.string.api_key) + "&query=" + query;
        new TheMovieDB(modelFragment, position).getResponse(address);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return ItemFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case TheMovieDB.MOVIES:
                    return getString(R.string.section_movies);
                case TheMovieDB.TV:
                    return getString(R.string.section_tv);
                case TheMovieDB.PEOPLE:
                    return getString(R.string.section_people);
            }
            return null;
        }
    }
}
