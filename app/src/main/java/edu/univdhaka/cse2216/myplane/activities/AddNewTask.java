package edu.univdhaka.cse2216.myplane.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.univdhaka.cse2216.myplane.R;
import edu.univdhaka.cse2216.myplane.domain.Tasks;
import edu.univdhaka.cse2216.myplane.utils.DataBase;

import java.util.Calendar;

public class AddNewTask extends AppCompatActivity implements View.OnClickListener {



    private ImageButton clockButton,calenderButton;
    private EditText clockText,calenderText,taskName,taskDetails;
    private Button button;

    String[] taskstypes;
    Spinner spinner;
    Switch alarmSwitch;
    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;
    Tasks tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        bindWidgets();
        button.setOnClickListener(this);
        if(getIntent().getExtras()!=null)
        {
            Object task = getIntent().getExtras().get("task");
            if (task!=null)
            {
                this.tasks = (Tasks) task;
                taskName.setText(tasks.getTask_name());
                calenderText.setText(tasks.getTask_date());
                clockText.setText(tasks.getTask_time());
                spinner.setPrompt("type");
                taskDetails.setText(tasks.getTask_details());
            }
        }

        setTitle("New Task");
    }
    private void bindWidgets()
    {
        dataBase = new DataBase(this);
        sqLiteDatabase = dataBase.getWritableDatabase();

        taskName = (EditText) findViewById(R.id.taskName);
        clockButton = (ImageButton) findViewById(R.id.clock);
        calenderButton = (ImageButton) findViewById(R.id.calender);
        clockText = (EditText) findViewById(R.id.clockText);
        calenderText = (EditText) findViewById(R.id.calenderText);
        alarmSwitch = (Switch) findViewById(R.id.alarmSwitch);
        button = findViewById(R.id.addButton);



        taskstypes = getResources().getStringArray(R.array.Lists);
        spinner = findViewById(R.id.typeID);

        taskDetails = (EditText)findViewById(R.id.task_details);


        clockButton.setOnClickListener(this);
        calenderButton.setOnClickListener(this);
        calenderText.setOnClickListener(this);
        clockText.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_row,R.id.typeRowID,taskstypes);
        spinner.setAdapter(adapter);
        return;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.clock)
        {
            Calendar calendar = Calendar.getInstance();
            final int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int AM_PM = calendar.get(Calendar.AM_PM);

            TimePickerDialog dialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String time = updateTime(hourOfDay,minute);
                            clockText.setText(time);
                        }
                    },hour,minute,true);
            dialog.show();
        }

        else if (view.getId() == R.id.calender  || view.getId() == R.id.calenderText)
        {

            Calendar cal = Calendar.getInstance();
            int year1 = cal.get(Calendar.YEAR);
            int month1 = cal.get(Calendar.MONTH);
            int day1 = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    AddNewTask.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calenderText.setText(month+1 + "/" + dayOfMonth + "/" + year);
                        }
                    },
                    year1, month1, day1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

        else if (view.getId() == R.id.clockText)
        {
            Toast.makeText(getApplicationContext(),"Clock Text",Toast.LENGTH_LONG).show();
        }
        else if (view.getId() == R.id.addButton) {
            saveTask();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_task)
        {
            saveTask();
        }

        return super.onOptionsItemSelected(item);
    }

    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "pm";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "am";
        } else if (hours == 12)
            timeSet = "pm";
        else
            timeSet = "am";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();


        return aTime;
    }

    public void saveTask()
    {
        if (validateInput()) {
            String task = taskName.getText().toString();
            String taskDate = calenderText.getText().toString();
            String taskTime = clockText.getText().toString();
            String taskType = spinner.getSelectedItem().toString();
            String task_details = taskDetails.getText().toString();
            int al = 0;
            if (alarmSwitch.isChecked()) {
                al = 1;
            }
            if (tasks == null) {
                Tasks newTask = new Tasks(al, "0", task, taskDate, taskTime, taskType, task_details);
                long flag = dataBase.insertData(newTask);
            } else {
                tasks.setTask_name(task);
                tasks.setTask_date(taskDate);
                tasks.setTask_time(taskTime);
                tasks.setIsAlarm(al);
                tasks.setTask_details(task_details);
                dataBase.updateData(tasks);
            }
            finish();
        }
    }

    private boolean validateInput() {
        boolean allInputsValid = true;

        for(EditText input
                : new EditText[]{taskName, calenderText, clockText, taskDetails}) {
            if (input.getText().toString().isEmpty()) {
                showError(input, R.string.required);
                allInputsValid = false;
            }
        }

        return allInputsValid;
    }
    private void showError(EditText field, int messageRes) {
        field.setError(getString(messageRes));
    }
}
