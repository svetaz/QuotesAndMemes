package com.chikeandroid.tutsplus_glide;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import java.util.Random;


/**
 * Created by Filip 3 on 27-Nov-2017.
 */

public class Notification_reciever extends BroadcastReceiver{

    private SharedPreferences prefs;


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);


        String[] r1 = new String[] {"NE MOŽEŠ SA MNOM TAKO!","JA SAM TI DISCIPLINOVAN! ","BRANKO MANI SE HRVATA!","ALO!","NEMOJ DA VAS SVE IZBACIM NAPOLJE ","MMM...SAVIĆ!","JA SAM SRBIN! ","ALO BRE OVO JE AJFON ŠEST!","KAPIJA!",
              "JA SAM TI DISCIPLINOVAN!","OTVORI LOKAL ŠTA SE ZAKLJUČAVATE?","KOMEĆEŠ TI PIČKU LIZAT?","OVO JE MOJA KUĆA!","JA SAM BRE SRBIN!","OVO JE SRPSKA KUĆA!","NEĆE MI OVDE KLANJATI!","JOJ KAKO SAM IZJEBO JEDNU!","GROK GROK!","MMM...SAVIĆ!","DOĆEŠ I TI NARED!","IMA PIČKU KO KRMEĆE OKO! ",
               "NIJE OVO DŽAMIJA!","ZAPAMTIĆEŠ SRBINA IZ REPUBLIKE SRPSKE!","SVU ĆU ŠTETU BRANKU NAPLATITI!","VATAJ SE BURAZERU MESA ŽIVOG TO JE ZDRAVO!",
              "JA SAM DVADESET GODINA U BRANŠI!","PROVEO SAM STRUJU KROZ OLUKE!","DIGNITE TENDU ODUVAĆE JE VETAR!","JEBEM MUSLIMANKE I SVETIM IM SE ZA SVE!","LIZAĆEŠ TI MOJ KURAC!",
              "SVE JE MINIRANO!","JA SAM TUKO TUDJMANA U ZATVORU!","POSTAVIO SAM PAŠTETE PO DVORIŠTU!","ZAPAMTIĆEŠ SINOĆNU VEČE!","MIRA NIJE REPA BEZ KORENA!","ŠTO OVO MALO NE OČISTITE?","JA SAM TI SE ZATVORA NALEŽO!","JOJ KAKO BI JA TUKO ONOG SMOTANOG IGORA!","GLEDANJE U ŽENSKE DOJKE PRODUŽAVA ŽIVOT!","ONAJ KROKODIL MI JE KUĆU POTOPIO!",
              "OVO JE NJEMAČKA KVALITETA!","VISIO BI TI MENI NA BANDERI!","IMAL PIVE?","ŠTO MI SE BRANKO NE JAVLJA?",
                "MUŠTERIJE ŠALJU IMELA!","ODVEŠĆU TE NA FRUŠKU GORU!","JA SAM ROĐENOM BRATU UVEO ZABRANU!",
                "MUŠTERIJA KAD ODE MOŠ JE JEBAT!","ŠTO REĐATE OVE PALETE NA TERASU?","MIRO DAJ PIŠTOLJ!","JEBEM TI PORIJEKLO!",
                "U NEMAČKOJ SVI SAD SKIDAJU OVE BEHATON PLOČE!","JA SAM HTEO ĆERKI KOŽU DA ODEREM!"};
        int randomMsgIndex = new Random().nextInt(r1.length);

        //provera podesenja




        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String END_POINT = prefs.getString("PREF_LIST_SOUNDS", "1");

        if (END_POINT.matches("1")) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.drawable.quotezz).setContentIntent(pendingIntent).setPriority(Notification.PRIORITY_MAX).setContentTitle("DNEVNA DOZA SAVIĆA:").setWhen(System.currentTimeMillis()).setContentText(r1[randomMsgIndex]).setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.grok)).setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS).setAutoCancel(true);



            notificationManager.notify(100, builder.build());

        }

        if (END_POINT.matches("2")) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.drawable.quotezz).setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("DNEVNA DOZA SAVIĆA:")
                    .setWhen(System.currentTimeMillis())
                    .setContentText(r1[randomMsgIndex])
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                    .setAutoCancel(true);



            notificationManager.notify(100, builder.build());

        }

        if (END_POINT.matches("3")) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.drawable.quotezz).setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("DNEVNA DOZA SAVIĆA:")
                    .setWhen(System.currentTimeMillis())
                    .setContentText(r1[randomMsgIndex])
                    .setDefaults(Notification.DEFAULT_LIGHTS)
                    .setAutoCancel(true);



            notificationManager.notify(100, builder.build());

        }

        if (END_POINT.matches("4")) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.drawable.quotezz).setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("DNEVNA DOZA SAVIĆA:")
                    .setWhen(System.currentTimeMillis())
                    .setContentText(r1[randomMsgIndex])
                    .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                    .setAutoCancel(true);



            notificationManager.notify(100, builder.build());

        }







    }
}
