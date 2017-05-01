package com.zombie_desk.zombiedesk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.User;

import java.util.List;

/**
 * Created by Belintani on 19/04/2017.
 */

public class UserAdapter extends ArrayAdapter<User>
{
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
            TextView textUsername = (TextView) localView.findViewById(R.id.textUsername);
            TextView textPassword = (TextView) localView.findViewById(R.id.textPassword);

            if(textUsername != null){
                textUsername.setText(String.valueOf(user.getUsername()));
            }
            if(textPassword != null){
                textPassword.setText(user.getPassword());
            }
        }
        return localView;
    }
}
