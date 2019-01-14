package edu.univdhaka.cse2216.myplane.activities;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.univdhaka.cse2216.myplane.R;
import edu.univdhaka.cse2216.myplane.domain.Tasks;
import edu.univdhaka.cse2216.myplane.utils.DoneDatabase;

import java.util.ArrayList;


public class DoneList extends Fragment {


    private ListView listView;
    private CAdapter cAdapter;
    private DoneDatabase database;
    ArrayList<Tasks> tasksList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_done_list, container, false);
        bindWidget(view);
        updateList();
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void bindWidget(View view)
    {
        database = new DoneDatabase(getContext());
        listView = view.findViewById(R.id.list_done);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updateList()
    {
        tasksList = new ArrayList<>();
        Cursor cursor = database.getAllData();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String taskname = cursor.getString(1);
                String isAlarm = cursor.getString(5);
                String id = cursor.getString(0);
                String taskDate = cursor.getString(2);
                String taskTime = cursor.getString(3);
                String taskType = cursor.getString(4);

                if (isAlarm == null)
                {
                    tasksList.add(new Tasks(R.drawable.alarm_off,id, taskname,taskDate,taskTime,taskType));
                }
                else if (isAlarm.equalsIgnoreCase("yes")) {
                    tasksList.add(new Tasks(R.drawable.alarm_on,id, taskname,taskDate,taskTime,taskType));
                }
                else {
                    tasksList.add(new Tasks(R.drawable.alarm_off, id, taskname,taskDate,taskTime,taskType));
                }
            }
        }
        cAdapter = new CAdapter(getContext(),tasksList,listView);
        listView.setAdapter(cAdapter);
    }

    private class CAdapter extends ArrayAdapter<Tasks> {

        private Context mContext;
        private ArrayList<Tasks> tasksList = new ArrayList<>();

        ArrayList<Tasks> orig;

        ListView listView;

        public CAdapter(Context context, ArrayList<Tasks> list, ListView listView) {
            super(context, 0, list);
            mContext = context;
            tasksList = list;
            this.listView = listView;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.done_row,parent,false);


            final Tasks currentTask = tasksList.get(position);

            TextView name = (TextView) listItem.findViewById(R.id.task_title2);
            name.setText(currentTask.getTask_name());

            TextView taskDate = listItem.findViewById(R.id.DateText2);
            taskDate.setText(currentTask.getTask_date());

            TextView taskTime = listItem.findViewById(R.id.TimeText2);
            taskTime.setText(currentTask.getTask_time());

            LinearLayout linearLayout = (LinearLayout) listItem.findViewById(R.id.layout2);

            final TextView idText = listItem.findViewById(R.id.idText2);
            idText.setText(currentTask.getTask_id());

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();
                }
            });
            return listItem;
        }
    }


}
