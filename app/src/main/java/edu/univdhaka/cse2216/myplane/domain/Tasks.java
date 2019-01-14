package edu.univdhaka.cse2216.myplane.domain;



import java.io.Serializable;

public class Tasks implements Serializable{


    private int isAlarm;
    private String task_id,task_name,task_date,task_time,task_type,task_details;




    public Tasks(int isAlarm, String task_id, String task_name, String task_date, String task_time, String task_type) {
        this.isAlarm = isAlarm;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_date = task_date;
        this.task_time = task_time;
        this.task_type = task_type;
    }


    public Tasks(int isAlarm, String task_id, String task_name, String task_date, String task_time, String task_type,String task_details) {
        this.isAlarm = isAlarm;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_date = task_date;
        this.task_time = task_time;
        this.task_type = task_type;
        this.task_details = task_details;
    }

    public int getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(int isAlarm) {
        this.isAlarm = isAlarm;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_details() {
        return task_details;
    }

    public void setTask_details(String task_details) {
        this.task_details = task_details;
    }
}


