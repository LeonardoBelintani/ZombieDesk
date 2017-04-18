package com.zombie_desk.zombiedesk.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Lab. Desenvolvimento on 17/04/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private List<User> users;
    private int layout;

    public UserAdapter(Context context, int resource, List<User> users){
        super(context,resource,users);
        this.users = users;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        User user = users.get(position);

        if(user != null){
            TextView textID = (TextView) localView.findViewById(R.id.textID);
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textCidade = (TextView) localView.findViewById(R.id.textCidade);
            TextView textAno = (TextView) localView.findViewById(R.id.textAno);

            if(textID != null){
                textID.setText(String.valueOf(clube.getID()));
            }
            if(textNome != null){
                textNome.setText(clube.getNome());
            }
            if(textCidade != null){
                textCidade.setText(clube.getCidade());
            }
            if(textAno != null){
                textAno.setText(String.valueOf(clube.getAno()));
            }
        }
        return localView;
    }
}
