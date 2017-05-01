package com.zombie_desk.zombiedesk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Called;

import java.util.List;

/**
 * Created by admin on 01/05/2017.
 */

public class CalledAdapter extends ArrayAdapter<Called>
{
    private List<Called> calls;
    private int layout;

    public CalledAdapter(Context context, int resource, List<Called> calls){
        super(context,resource,calls);
        this.calls = calls;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        Called call = calls.get(position);

        if(call != null){
            TextView textId = (TextView) localView.findViewById(R.id.textId);
            TextView textLocation = (TextView) localView.findViewById(R.id.textLocation);
            TextView textPriority= (TextView) localView.findViewById(R.id.textPriority);
            TextView textStatus= (TextView) localView.findViewById(R.id.textStatus);

            if(textId != null){
                textId.setText(String.valueOf(call.getId()));
            }
            if(textLocation != null){
                textLocation.setText(call.getLocale());
            }
            if(textPriority != null){
                textPriority.setText(call.getPriority());
            }
            if(textStatus != null){
                textStatus.setText(call.getStatus());
            }
        }
        return localView;
    }
}
