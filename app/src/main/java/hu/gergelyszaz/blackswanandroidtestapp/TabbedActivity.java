package hu.gergelyszaz.blackswanandroidtestapp;


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

import hu.gergelyszaz.blackswanandroidtestapp.network.TheMovieDB;


public class TabbedActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, SearchView.OnQueryTextListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;




    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ModelFragment modelFragment = ModelFragment.getModelFragment(getSupportFragmentManager());
        new TheMovieDB(modelFragment, TheMovieDB.MOVIES).getResponse(getString(R.string.url_movies_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(modelFragment, TheMovieDB.PEOPLE).getResponse(getString(R.string.url_people_popular) + "?api_key=" + getString(R.string.api_key));
        new TheMovieDB(modelFragment, TheMovieDB.TV).getResponse(getString(R.string.url_tv_popular) + "?api_key=" + getString(R.string.api_key));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public void onListFragmentInteraction(Object item) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        int position = mViewPager.getCurrentItem();
        ModelFragment modelFragment = ModelFragment.getModelFragment(getSupportFragmentManager());
        switch (position) {
            case TheMovieDB.MOVIES:
                new TheMovieDB(modelFragment, TheMovieDB.MOVIES).getResponse(getString(R.string.url_movies_search) + "?api_key=" + getString(R.string.api_key) + "&query=" + query);
                break;
            case TheMovieDB.PEOPLE:
                new TheMovieDB(modelFragment, position).getResponse(getString(R.string.url_people_search) + "?api_key=" + getString(R.string.api_key) + "&query=" + query);
                break;
            case TheMovieDB.TV:
                new TheMovieDB(modelFragment, position).getResponse(getString(R.string.url_tv_search) + "?api_key=" + getString(R.string.api_key) + "&query=" + query);
                break;
            default:
                throw new IllegalArgumentException();
        }
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
