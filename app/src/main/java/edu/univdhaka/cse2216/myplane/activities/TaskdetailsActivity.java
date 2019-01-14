package edu.univdhaka.cse2216.myplane.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.univdhaka.cse2216.myplane.R;
import edu.univdhaka.cse2216.myplane.domain.Tasks;

public class TaskdetailsActivity extends AppCompatActivity {


    TextView heading,type,date,time,details;
    String id;
    Tasks tasks;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);
        if(getIntent().getExtras()!=null)
        {
            Object task = getIntent().getExtras().get("task");
            if (task!=null)
            {
                this.tasks = (Tasks) task;
            }
        }

        bindWidget();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
            {
                Toast.makeText(this,"works",Toast.LENGTH_LONG).show();
                this.onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void bindWidget()
    {
        heading = findViewById(R.id.heading);
        type = findViewById(R.id.tasktype);
        date = findViewById(R.id.taskdate);
        time = findViewById(R.id.tasktime);
        details = findViewById(R.id.taskdetails);
        button = findViewById(R.id.ButtonId1);
        heading.setText(tasks.getTask_name());
        type.setText(tasks.getTask_type());
        date.setText(tasks.getTask_date());
        time.setText(tasks.getTask_time());
        details.setText(tasks.getTask_details());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
