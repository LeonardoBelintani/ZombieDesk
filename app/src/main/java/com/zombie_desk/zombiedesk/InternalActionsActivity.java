package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class InternalActionsActivity extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.actions));
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Uri uri;
        Intent intent;

        switch (position)
        {
            //Abrir URL
            case 0:
                uri = Uri.parse("http://www.google.com.br");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;

            //Fazer uma chamada
            case 1:
                uri = Uri.parse("tel:998844689");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                dispararIntent(intent);
                break;

            //Pesquisar no mapa
            case 2:
                uri = Uri.parse("geo:0,0?q=CasaSoft+Curitiba");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;

            //Reproduzir musica
            case 3:
                uri = Uri.parse("file:///storage/emulated/0/bluetooth/peru glugluglu de boas vindas.mp3");
                intent = new Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .setDataAndType(uri, "audio/mp3");
                dispararIntent(intent);
                break;

            //Abrir SMS
            case 4:
                uri = Uri.parse("tel:988546485");
                intent = new Intent(Intent.ACTION_VIEW, uri).putExtra("sms_body", "corpo do sms");
                dispararIntent(intent);
                break;

            //Compartilhar
            case 5:
                intent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, "Compartilhando via internet")
                        .setType("text/plain");
                dispararIntent(intent);
                break;

            //Alarme
            case 6:
                ArrayList<Integer> dias = new ArrayList<Integer>();
                dias.add(Calendar.MONDAY);
                dias.add(Calendar.WEDNESDAY);
                dias.add(Calendar.FRIDAY);

                intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "hora de acordar")
                        .putExtra(AlarmClock.EXTRA_HOUR, 20)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 0)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                        .putExtra(AlarmClock.EXTRA_DAYS, dias);

                dispararIntent(intent);
                break;

            //Configurações do aparelho
            case 7:
                intent = new Intent(Settings.ACTION_SETTINGS);
                dispararIntent(intent);
                break;

            default:
                finish();
        }
    }

    private void dispararIntent(Intent intent)
    {
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, R.string.ErrorIntent, Toast.LENGTH_SHORT).show();
    }
}