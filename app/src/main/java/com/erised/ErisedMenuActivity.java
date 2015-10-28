package com.erised;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.erised.base.BaseActivity;

/**
 * Created by Rahul on 26/9/15.
 */

public class ErisedMenuActivity extends BaseActivity implements Animation.AnimationListener, View.OnClickListener {

    private RecyclerView menu;

    // Animation
    Animation animFadein;

    TextView tshirt, sarees, sunglass, shop, ethnic, cosmetic, bags;

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_erised_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String city = intent.getStringExtra("City");

        getSupportActionBar().setTitle(city);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tshirt = (TextView) findViewById(R.id.tx_shirts);
        sarees = (TextView) findViewById(R.id.tx_sarees);
        sunglass = (TextView) findViewById(R.id.tx_sunglass);
        shop = (TextView) findViewById(R.id.tx_shop);
        ethnic = (TextView) findViewById(R.id.tx_ethnic);
        cosmetic = (TextView) findViewById(R.id.tx_cosmetic);
        bags = (TextView) findViewById(R.id.tx_bags);
    }

    @Override
    protected void initData() {

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        // set animation listener
        animFadein.setAnimationListener(this);

        tshirt.startAnimation(animFadein);
        sarees.startAnimation(animFadein);
        sunglass.startAnimation(animFadein);
        shop.startAnimation(animFadein);
        ethnic.startAnimation(animFadein);
        cosmetic.startAnimation(animFadein);
        bags.startAnimation(animFadein);

        tshirt.setOnClickListener(this);
        sarees.setOnClickListener(this);
        sunglass.setOnClickListener(this);
        shop.setOnClickListener(this);
        ethnic.setOnClickListener(this);
        cosmetic.setOnClickListener(this);
        bags.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_erised_menu, menu);
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

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(ErisedMenuActivity.this,ProductDetails.class);

        switch (view.getId()) {
            case R.id.tx_shirts:

                startActivity(intent);
                break;

            case R.id.tx_sarees:

                startActivity(intent);
                break;

            case R.id.tx_sunglass:

                startActivity(intent);
                break;
            case R.id.tx_shop:

                startActivity(intent);
                break;
            case R.id.tx_ethnic:

                startActivity(intent);
                break;
            case R.id.tx_cosmetic:

                startActivity(intent);
                break;

            case R.id.tx_bags:

                startActivity(intent);
                break;
        }
    }
}
