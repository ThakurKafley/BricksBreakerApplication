package edu.gsu.httpcs.bricksbreakerapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gsu.httpcs.bricksbreakerapplication.R;

/**
 * Created by thaku on 7/4/2017.
 */

public class ListInLevelListAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    public ListInLevelListAdapter(Context context, ArrayList<String> list) {
        this.context=context;
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService
                (context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.list_normal_item,parent,false);
        TextView textView=(TextView)convertView.findViewById(R.id.item_normal_tv);
        textView.setText(list.get(position));
        return textView;
    }
}
