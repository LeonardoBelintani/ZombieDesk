package com.zombie_desk.zombiedesk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Role;

import java.util.List;

/**
 * Created by admin on 30/04/2017.
 */

public class RoleAdapter extends ArrayAdapter<Role> {
    private List<Role> roles;
    private int layout;

    public RoleAdapter(Context context, int resource, List<Role> roles) {
        super(context, resource, roles);
        this.roles = roles;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View localView = contentView;

        if (localView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout, null);
        }

        Role role = roles.get(position);

        if (role != null) {
            TextView textId = (TextView) localView.findViewById(R.id.textId);
            TextView textName = (TextView) localView.findViewById(R.id.textDescription);

            if (textId != null) {
                textId.setText(String.valueOf(role.getId()));
            }
            if (textName != null) {
                textName.setText(role.getDescription());
            }
        }
        return localView;
    }
}
