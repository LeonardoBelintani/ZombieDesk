package com.zombie_desk.zombiedesk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Employee;

import java.util.List;

/**
 * Created by admin on 30/04/2017.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee>
{
    private List<Employee> employees;
    private int layout;

    public EmployeeAdapter(Context context, int resource, List<Employee> employees) {
        super(context, resource, employees);
        this.employees = employees;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View localView = contentView;

        if (localView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout, null);
        }

        Employee employee = employees.get(position);

        if (employee != null) {
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textGender = (TextView) localView.findViewById(R.id.textGender);
            TextView textId = (TextView) localView.findViewById(R.id.textID);

            if (textNome != null) {
                textNome.setText(employee.getName());
            }
            if (textGender != null) {
                textGender.setText(employee.getGender());
            }
            if (textId != null) {
                textId.setText(String.valueOf(employee.getId()));
            }
        }
        return localView;
    }
}
