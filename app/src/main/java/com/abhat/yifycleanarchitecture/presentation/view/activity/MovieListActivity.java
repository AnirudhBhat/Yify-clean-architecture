package com.abhat.yifycleanarchitecture.presentation.view.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.abhat.yifycleanarchitecture.R;
import com.abhat.yifycleanarchitecture.presentation.view.fragment.MovieListFragment;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class MovieListActivity extends AppCompatActivity{

    private String sortBy;
    private String searchQuery;
    private AppCompatSpinner optionsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        optionsSpinner = (AppCompatSpinner)findViewById(R.id.optionsSpinner);
        setupToolBar();
        setupSpinner();
        setupFragment();
    }



    private void setupToolBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsSpinner.setAdapter(adapter);
        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                searchQuery = "";
                if (position == 0) {
                    sortBy = "seeds";
                } else if (position == 1) {
                    sortBy = "download_count";
                } else if (position == 2){
                    sortBy = "rating";
                } else if (position == 3) {
                    sortBy = "year";
                } else if (position == 4) {
                    sortBy = "like_count";
                }
                setupFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void setupFragment() {
        MovieListFragment movieListFragment = MovieListFragment.newInstance(sortBy, searchQuery);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, movieListFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)
                MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setQueryHint("Search movies...");
        SearchObservable.searchView(searchView)
                .debounce(600, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s) {
                        return Observable.fromCallable(new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                return s;
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("YIFI", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        searchQuery = s;
                        sortBy = "";
                        setupFragment();
                    }
                });


        /*searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query;
                sortBy = "";
                setupFragment();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    static class SearchObservable {
        public static Observable<String> searchView(android.support.v7.widget.SearchView searchView) {
            final PublishSubject<String> subject = PublishSubject.create();

            searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    subject.onCompleted();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String text) {
                    subject.onNext(text);
                    return true;
                }
            });

            return subject;
        }
    }
}
