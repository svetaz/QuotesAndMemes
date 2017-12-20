package com.chikeandroid.tutsplus_glide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;




public class SpaceGalleryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer2);



        // Enables ActionBar app icon to behave as action to toggle NavigationDrawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar222);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled( true );


            actionBar.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(SpaceGalleryActivity.this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(layoutManager);

        SpaceGalleryActivity.ImageGalleryAdapter adapter = new SpaceGalleryActivity.ImageGalleryAdapter(this, SpacePhoto.getSpacePhotos());
        recyclerView.setAdapter(adapter);








        /*getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Quotes & Memes");*/

        //getSupportActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>"));





    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the layout
            View photoView = inflater.inflate(R.layout.item_photo, parent, false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            SpacePhoto spacePhoto = mSpacePhotos[position];
            ImageView imageView = holder.mPhotoImageView;

            Glide.with(mContext)
                    .load(spacePhoto.getUrl())
                    .placeholder(R.mipmap.ic_cloud_off_red)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (mSpacePhotos.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    com.chikeandroid.tutsplus_glide.SpacePhoto spacePhoto = mSpacePhotos[position];

                    Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                    intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto);

                    startActivity(intent);
                }
            }
        }

        private SpacePhoto[] mSpacePhotos;
        private Context mContext;

        public ImageGalleryAdapter(Context context, SpacePhoto[] spacePhotos) {
            mContext = context;
            mSpacePhotos = spacePhotos;
        }


    }

    /*public void dole(View V){

        RecyclerView sv = (RecyclerView) findViewById(R.id.rv_images);
        sv.scrollToPosition(sv.getAdapter().getItemCount() - 1);

        ButtonFloat button = (ButtonFloat) findViewById(R.id.buttonFloatt);
        button.setVisibility(View.GONE);

        ButtonFloat button1 = (ButtonFloat) findViewById(R.id.buttonFloattt);
        button1.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Pali ste na dno liste.",
                Toast.LENGTH_SHORT).show();

    }

    public void gore(View V){

        RecyclerView sv = (RecyclerView) findViewById(R.id.rv_images);
        sv.scrollToPosition(sv.getAdapter().getItemCount() - 56);

        ButtonFloat button = (ButtonFloat) findViewById(R.id.buttonFloatt);
        button.setVisibility(View.VISIBLE);

        ButtonFloat button1 = (ButtonFloat) findViewById(R.id.buttonFloattt);
        button1.setVisibility(View.GONE);

        Toast.makeText(this, "Popeli ste se na vrh liste.",
                Toast.LENGTH_SHORT).show();


    }*/









    @Override
    public void onBackPressed() {



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);

            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera2) {

            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);

            finish();



        } else if (id == R.id.nav_gallery2) {



            Intent intent1 = new Intent(this, SpaceGalleryActivity.class);
            startActivity(intent1);

            finish();

        }  else if (id == R.id.nav_share2) {

            startActivity(new Intent(SpaceGalleryActivity.this, SettingsActivity.class));

        } else if (id == R.id.nav_send2) {
            Intent intent1 = new Intent(SpaceGalleryActivity.this, About.class);
            startActivity(intent1);

        }

        else if (id==R.id.nav_feedback2){

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:" + "rollbarbullbar@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            startActivity(Intent.createChooser(intent, "Send email"));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }












}
