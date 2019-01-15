package edu.univdhaka.cse2216.myplane.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.univdhaka.cse2216.myplane.R;
import edu.univdhaka.cse2216.myplane.domain.Tasks;

public class TaskdetailsActivity extends AppCompatActivity {


    TextView heading,type,date,time,details;
    String id;
    Tasks tasks;
    Button button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);
        setTitle("Task Details");
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
        imageView = findViewById(R.id.imageID);
        heading.setText(tasks.getTask_name());
        type.setText(tasks.getTask_type());
        date.setText(tasks.getTask_date());
        time.setText(tasks.getTask_time());

        details.setText(tasks.getTask_details());
        setImage(tasks.getTask_type());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setImage(String type)
    {
        if (type.equalsIgnoreCase("Education"))  imageView.setImageResource(R.drawable.study);
        else if (type.equalsIgnoreCase("Family")) imageView.setImageResource(R.drawable.family);
        else if (type.equalsIgnoreCase("Fun")) imageView.setImageResource(R.drawable.fun);
        else if (type.equalsIgnoreCase("Sports")) imageView.setImageResource(R.drawable.sports);
        else if (type.equalsIgnoreCase("Works")) imageView.setImageResource(R.drawable.office);
    }
}
