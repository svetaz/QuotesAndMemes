package com.chikeandroid.tutsplus_glide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.jrummyapps.android.animations.Technique;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;




public class SpacePhotoActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    CoordinatorLayout coordinatorLayout;

    public static String NOTIF_HELP = "notif_help";

    private SharedPreferences prefs;

    //dodatoizshera DIS

    RelativeLayout activity_character;
    static int CODE_FOR_RESULT=981;

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
       // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        /*getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Click on an image to share it");*/
        //getSupportActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>"));
        /*Toast.makeText(this, "Click on image to share it!",
                Toast.LENGTH_SHORT).show();*/

        //DIS
        activity_character=(RelativeLayout) findViewById(R.id.activity_character);

        mImageView = (ImageView) findViewById(R.id.image);
        EditText text = (EditText)findViewById(R.id.editTextSlika) ;


        text.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Technique.BOUNCE_IN_UP.getComposer().duration(650).delay(0).playOn(mImageView);


        SpacePhoto spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);

        FloatingActionButton dugme = (FloatingActionButton) findViewById(R.id.dugme);
        Technique.BOUNCE_IN_UP.getComposer().duration(400).delay(0).playOn(dugme);

        //PODESAVANJA

        prefs = PreferenceManager.getDefaultSharedPreferences(this);





        //provera podesenja
        boolean help = prefs.getBoolean(NOTIF_HELP, true);


        if (help) {

            View coordinatorLayout = (CoordinatorLayout)findViewById(R.id.layoutMain);

            final Snackbar snackbar = Snackbar.make(coordinatorLayout,"Klikni na sliku\nda bi dodao tekst", Snackbar.LENGTH_LONG);

            // Set an action on it, and a handler
            snackbar.setAction("NE PRIKAZUJ VIŠE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SpacePhotoActivity.this, SettingsActivity.class));
                }
            });

            snackbar.show();

        }






        Glide.with(this)
                .load(spacePhoto.getUrl())
                .asBitmap()
                .error(R.mipmap.ic_cloud_off_red)
                .listener(new RequestListener<String, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        //onPalette(Palette.from(resource).generate());
                        mImageView.setImageBitmap(resource);

                        return false;
                    }

                    /*public void onPalette(Palette palette) {
                        if (null != palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.WHITE));
                        }
                    }*/
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);

    }

   /* private SimpleTarget target = new SimpleTarget<Bitmap>() {

        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

           onPalette(Palette.from(bitmap).generate());
           mImageView.setImageBitmap(bitmap);
        }

        public void onPalette(Palette palette) {
            if (null != palette) {
                ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
            }
        }
    };*/



    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public void OnClickShare(View view){

        FloatingActionButton dugme = (FloatingActionButton) findViewById(R.id.dugme);
        Technique.ROTATE.getComposer().duration(400).delay(0).playOn(dugme);


        handler.postDelayed(new Runnable() {
            public void run() {

                podeli();

            }
        }, 500);





    }

    @Override
    public void onBackPressed () {

super.onBackPressed();




    }

    public void podeli (){

        Bitmap bitmap =getBitmapFromView(activity_character);
        try {
            //File file = new File(this.getExternalCacheDir(),File.separator+ "logicchip.png");
            File file = new File(this.getExternalCacheDir(),"image.png");


            // File file = new File(this.getCacheDir(),File.separator+ "logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);

            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));




        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public void clickOnImage(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);
        FloatingActionButton floatingActionButtonCrno = (FloatingActionButton)findViewById(R.id.floatingActionButtonCrno);
        FloatingActionButton floatingActionButtonBelo = (FloatingActionButton)findViewById(R.id.floatingActionButtonBelo);
        FloatingActionButton floatingActionButtonPlavo = (FloatingActionButton)findViewById(R.id.floatingActionButtonPlavo);
        FloatingActionButton floatingActionButtonDefault = (FloatingActionButton)findViewById(R.id.floatingActionButtonDefault);



        text.setVisibility(View.VISIBLE);
        floatingActionButtonDelete.setVisibility(View.VISIBLE);
        floatingActionButtonRandom.setVisibility(View.VISIBLE);
        floatingActionButtonCrno.setVisibility(View.VISIBLE);
        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonDelete);
        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonRandom);
        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonCrno);

        text.requestFocus();
        text.performClick();
        Technique.ZOOM_IN.getComposer().duration(500).delay(0).playOn(text);



    }


    public void clickDelete(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);

        Technique.ROTATE.getComposer().duration(250).delay(0).playOn(floatingActionButtonDelete);
        text.setVisibility(View.INVISIBLE);
        text.getText().clear();



    }

    public void clickRandom(View view) {



        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);

        Technique.ROTATE.getComposer().duration(250).delay(0).playOn(floatingActionButtonRandom);

        text.setVisibility(View.VISIBLE);
        Technique.ZOOM_IN.getComposer().duration(500).delay(0).playOn(text);
        text.requestFocus();
        text.performClick();



        final String[] r1 = new String[] {"NE MOŽEŠ SA MNOM TAKO!","JA SAM TI DISCIPLINOVAN! ","BRANKO! ","ALO TI! ","SIDJI DOLE U GARAŽU!","BRANKO MANI SE HRVATA!","ALO!", "SLUŠAJ ME DOBRO!", "BRANKANE!","ALO BRE!","ALO GORON!","ALO TAKILO!","ALO SVETOMIRE!",
                "ALO MANJAČA!","MIRO!","ALO BRADONJA!","NEMOJ DA VAS SVE IZBACIM NAPOLJE ","ALO!","MMM...SAVIĆ!","JA SAM SRBIN! ","ALO BRE OVO JE AJFON ŠEST!","KAPIJA!","JA SAM IMO FIRMU OD 20 RADNIKA U ZEN'CI!"};
        final int randomMsgIndex = new Random().nextInt(r1.length - 1);

        final String[] r12 = new String[] {"","JA SAM TI DISCIPLINOVAN!","","OTVORI LOKAL ŠTA SE ZAKLJUČAVATE?","","KOMEĆEŠ TI PIČKU LIZAT?","OVO JE MOJA KUĆA!","", "JA SAM BRE SRBIN!", "OVO JE SRPSKA KUĆA!","","NEĆE MI OVDE KLANJATI!","JA SAM TRENIRO BOKS" +
                " NEMOJ DA TE SAD OVDE OBORIM U BARU!","SVE JE POKRIVENO KAMERAMA " +
                "NE IGRAJTE SE!","","JOJ KAKO SAM JE IZJEBO!","","JEBO SAM SINOĆ JEDNU!","","GROK GROK!","MMM...SAVIĆ!","DOĆEŠ I TI NARED!","KAPIJA!","","","JA I BRAT SMO PREBILI DŽEPAROŠE NA NAJLONU NEMOJ I TI TAKO DA PROĐEŠ!","IMA PIČKU KO KRMEĆE OKO! ",
                "NIJE OVO DŽAMIJA! ","","ZAPAMTIĆEŠ SRBINA IZ REPUBLIKE SRPSKE! ","SVU ĆU ŠTETU BRANKU NAPLATITI!","","VATAJ SE BURAZERU MESA ŽIVOG TO JE ZDRAVO!"};
        final int randomMsgIndex2 = new Random().nextInt(r12.length - 1);

        final String[] r123 = new String[] {"","JA SAM DVADESET GODINA U BRANŠI!","PROVEO SAM STRUJU KROZ OLUKE LOPOVI SU KRV PIŠALI!","","","JOJ KAKO SMO JA I POLICAJCI TUKLI DVOJICU U PODRUMU!","",
                "DIGNITE TENDU ODUVAĆE JE VETAR!","","","JEBEM MUSLIMANKE I SVETIM IM SE ZA SVE!","","LIZAĆEŠ TI MOJ KURAC!","","OVA MOJA SE SVA SKOČANILA!","ALO!","MMM...SAVIĆ!","MIRO,KAKVE SI TO DLAKAVE SLIKE SLALA LJUDIMA?","UBIO SAM DVA MUSLIMANA U HAJDELBERGU I ODSEKO IM GLAVE!"};
        final int randomMsgIndex3 = new Random().nextInt(r123.length - 1);

        final String[] r1234 = new String[] {"","","SVE JE MINIRANO!","","JA SAM TUKO TUDJMANA U ZATVORU!","","POSTAVIO SAM PAŠTETE PO DVORIŠTU NEMOJ S NOGAMA DA SE IGRAŠ!","","ZAPAMTIĆEŠ SINOĆNU VEČE!","MIRA NIJE REPA BEZ KORENA!","ŠTO OVO MALO NE OČISTITE?",
                "","JA SAM TI SE ZATVORA NALEŽO!","","","JOJ KAKO BI JA TUKO ONOG SMOTANOG IGORA!","","MMM...SAVIĆ!","GLEDANJE U ŽENSKE DOJKE PRODUŽAVA ŽIVOT!","","","NEĆE OVI KEROVI ULICU DA MI ASVALTIRAJU!","ONAJ KROKODIL MI JE KUĆU POTOPIO!","KAPIJA!","JA SAM TI DISCIPLINOVAN NE MOŽEŠ SA MNOM TAKO!"};
        final int randomMsgIndex4 = new Random().nextInt(r1234.length - 1);

        final String[] r1235 = new String[] {"","OVO JE NJEMAČKA KVALITETA!","VISIO BI TI MENI NA BANDERI!","","IMAL PIVE?","ŠTO MI SE BRANKO NE JAVLJA?",
                "MUŠTERIJE ŠALJU IMELA!","ODVEŠĆU TE NA FRUŠKU GORU!","","","NEMOJ DA TE VODIM DOLE U GARAŽU I KAČIM NA STRUJU!","JA SAM ROĐENOM BRATU UVEO ZABRANU!","ALO!","ALO BRE!",
                "A L O !","MUŠTERIJA KAD ODE MOŠ JE JEBAT!","","ŠTO JE OVO OVAKO PRLJAVO?","","","ŠTO REĐATE OVE PALETE NA TERASU?","","JA SAM BRANKU IZDAO SAMO LOKAL A NE I OVO ISPRED!","ALO BRE!"
                ,"MIRO DAJ PIŠTOLJ!","","","","","","JOJ KAKO SAM SINOĆ JEBO JEDNU MALU DEVEDESET PETO GODIŠTE","MMM...SAVIĆ!","","JEBEM TI PORIJEKLO!","LIZAĆEŠ TI MOJ KURAC!","KOMEĆEŠ TI PIČKU LIZAT?",
                "PROSTRO ONU STEKIJU TU DA SE MOLI PIČKA MU MATERINA!","U NEMAČKOJ SVI SAD SKIDAJU OVE BEHATON PLOČE!","JA SAM HTEO ĆERKI KOŽU DA ODEREM KAD SE ISTETOVIRALA!",""};
        final int randomMsgIndex5 = new Random().nextInt(r1235.length - 1);






        text.setText(r1[randomMsgIndex]+r12[randomMsgIndex2]+r123[randomMsgIndex3]+r1234[randomMsgIndex4]+r1235[randomMsgIndex5], TextView.BufferType.EDITABLE);


    }


    public void clickCrno(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);
        FloatingActionButton floatingActionButtonCrno = (FloatingActionButton)findViewById(R.id.floatingActionButtonCrno);
        FloatingActionButton floatingActionButtonBelo = (FloatingActionButton)findViewById(R.id.floatingActionButtonBelo);
        FloatingActionButton floatingActionButtonPlavo = (FloatingActionButton)findViewById(R.id.floatingActionButtonPlavo);
        FloatingActionButton floatingActionButtonDefault = (FloatingActionButton)findViewById(R.id.floatingActionButtonDefault);

        floatingActionButtonCrno.setVisibility(View.INVISIBLE);
        floatingActionButtonBelo.setVisibility(View.VISIBLE);
        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonBelo);
        text.setTextColor(Color.parseColor("#000000"));
        text.setHintTextColor(Color.parseColor("#000000"));




    }

    public void clickBelo(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);
        FloatingActionButton floatingActionButtonCrno = (FloatingActionButton)findViewById(R.id.floatingActionButtonCrno);
        FloatingActionButton floatingActionButtonBelo = (FloatingActionButton)findViewById(R.id.floatingActionButtonBelo);
        FloatingActionButton floatingActionButtonPlavo = (FloatingActionButton)findViewById(R.id.floatingActionButtonPlavo);
        FloatingActionButton floatingActionButtonDefault = (FloatingActionButton)findViewById(R.id.floatingActionButtonDefault);


        floatingActionButtonBelo.setVisibility(View.INVISIBLE);
        floatingActionButtonPlavo.setVisibility(View.VISIBLE);

        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonPlavo);
        text.setTextColor(Color.parseColor("#FFFFFFFF"));
        text.setHintTextColor(Color.parseColor("#FFFFFFFF"));




    }

    public void clickPlavo(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);
        FloatingActionButton floatingActionButtonCrno = (FloatingActionButton)findViewById(R.id.floatingActionButtonCrno);
        FloatingActionButton floatingActionButtonBelo = (FloatingActionButton)findViewById(R.id.floatingActionButtonBelo);
        FloatingActionButton floatingActionButtonPlavo = (FloatingActionButton)findViewById(R.id.floatingActionButtonPlavo);
        FloatingActionButton floatingActionButtonDefault = (FloatingActionButton)findViewById(R.id.floatingActionButtonDefault);



        floatingActionButtonPlavo.setVisibility(View.INVISIBLE);
        floatingActionButtonDefault.setVisibility(View.VISIBLE);


        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonDefault);
        text.setTextColor(Color.parseColor("#00ff04"));
        text.setHintTextColor(Color.parseColor("#00ff04"));




    }

    public void clickDefault(View view) {

        EditText text = (EditText)findViewById(R.id.editTextSlika) ;
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton)findViewById(R.id.floatingActionButtonDelete);
        FloatingActionButton floatingActionButtonRandom = (FloatingActionButton)findViewById(R.id.floatingActionButtonRandom);
        FloatingActionButton floatingActionButtonCrno = (FloatingActionButton)findViewById(R.id.floatingActionButtonCrno);
        FloatingActionButton floatingActionButtonBelo = (FloatingActionButton)findViewById(R.id.floatingActionButtonBelo);
        FloatingActionButton floatingActionButtonPlavo = (FloatingActionButton)findViewById(R.id.floatingActionButtonPlavo);
        FloatingActionButton floatingActionButtonDefault = (FloatingActionButton)findViewById(R.id.floatingActionButtonDefault);




        floatingActionButtonDefault.setVisibility(View.INVISIBLE);
        floatingActionButtonCrno.setVisibility(View.VISIBLE);


        Technique.ROTATE.getComposer().duration(500).delay(0).playOn(floatingActionButtonCrno);
        text.setTextColor(Color.parseColor("#e91e63"));
        text.setHintTextColor(Color.parseColor("#e91e63"));




    }
}
