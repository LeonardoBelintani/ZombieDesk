package com.zombie_desk.zombiedesk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Department;

import java.util.List;

/**
 * Created by admin on 30/04/2017.
 */

public class DepartmentAdapter extends ArrayAdapter<Department>
{
    private List<Department> departments;
    private int layout;

    public DepartmentAdapter(Context context, int resource, List<Department> departments){
        super(context,resource,departments);
        this.departments = departments;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        Department department = departments.get(position);

        if(department != null){
            TextView textId = (TextView) localView.findViewById(R.id.textId);
            TextView textDescription = (TextView) localView.findViewById(R.id.textDescription);

            if(textId != null){
                textId.setText(String.valueOf(department.getId()));
            }
            if(textDescription != null){
                textDescription.setText(department.getDescription());
            }
        }
        return localView;
    }
}
