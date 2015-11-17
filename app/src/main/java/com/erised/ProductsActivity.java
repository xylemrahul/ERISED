package com.erised;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Rahul on 17/9/15.
 */
public class ProductsActivity extends AppCompatActivity {

    //    private FloatingActionButton fabButton;
    private RecyclerView recList;
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initWidgets();
        String title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ContactAdapter ca = new ContactAdapter(createList(30));
//        recList.setAdapter(ca);
//        recList.setItemAnimator(new DefaultItemAnimator());
//
//        ca.setOnClick(new RecyclerViewClick() {
//            @Override
//            public void onItemClick(int pos) {
//
//            }
//        });
    }

    private void initWidgets() {

//        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("ERISED");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        recList = (RecyclerView) findViewById(R.id.cardList);
//        recList.setLayoutManager(new LinearLayoutManager(this));
    }

//    private List<ContactInfo> createList(int size) {
//
//        List<ContactInfo> result = new ArrayList<ContactInfo>();
//        for (int i = 1; i <= size; i++) {
//            ContactInfo ci = new ContactInfo();
//            ci.name = ContactInfo.NAME_PREFIX + i;
//            ci.surname = ContactInfo.SURNAME_PREFIX + i;
////            ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";
//
//            result.add(ci);
//        }
//        return result;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_products, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
