package edu.gsu.httpcs.bricksbreakerapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.gsu.httpcs.bricksbreakerapplication.Level1Activity;
import edu.gsu.httpcs.bricksbreakerapplication.Level2Activity;
import edu.gsu.httpcs.bricksbreakerapplication.Level4Activity;
import edu.gsu.httpcs.bricksbreakerapplication.Level5Activity;
import edu.gsu.httpcs.bricksbreakerapplication.R;
import edu.gsu.httpcs.bricksbreakerapplication.adapters.ListInLevelListAdapter;
import edu.gsu.httpcs.bricksbreakerapplication.resourcesView.MyHandlerClass;

public class LevelListFragment extends Fragment {
    private ArrayList<String>contant;
    private ListView listView;

    public LevelListFragment() {
        contant=new ArrayList<String>();
        contant.add("Level 1");
        contant.add("Level 2");
        contant.add("Level 3");
        contant.add("Level 4");
        contant.add("Level 5");
        contant.add("Level 6");
        contant.add("Level 7");
        contant.add("Level 8");
        contant.add("Level 9");
        contant.add("Level 10");
        contant.add("Level 11");
        contant.add("Level 12");
        contant.add("Level 13");
        contant.add("Level 14");

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_level_list, container, false);
        listView=(ListView)view.findViewById(R.id.level_list);
        ListInLevelListAdapter adapter=new ListInLevelListAdapter(getContext(),contant);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                       Intent intent=new Intent(getContext(), Level1Activity.class);
                       startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(getContext(), Level2Activity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(getContext(), MyHandlerClass.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(getContext(), Level4Activity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(getContext(), Level5Activity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent11);
                        break;
                    case 12:
                        Intent intent12=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent12);
                        break;
                    case 13:
                        Intent intent13=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14=new Intent(getContext(), Level1Activity.class);
                        startActivity(intent14);
                        break;
                    default:
                }
            }
        });
        return view;
    }

}
