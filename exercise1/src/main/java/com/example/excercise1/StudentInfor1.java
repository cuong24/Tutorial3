package com.example.excercise1;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StudentInfor1 extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private static final String CHANNEL_ID = "2";
    String submitDateString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_infor);

        EditText name = (EditText) findViewById(R.id.nameInput);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            name.setText((String) intent.getExtras().get("name"));
        }


        TextView dob = (TextView) findViewById(R.id.ageInput);
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        dob.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(StudentInfor1.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void updateLabel() {

        TextView editText = (TextView) findViewById(R.id.ageInput);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
        myCalendar.add(Calendar.DATE, 5);
        Date submitDate = myCalendar.getTime();
        submitDateString = sdf.format(submitDate);
    }

    public void onButtonClick(View view){
        if (submitDateString != null) {
            createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent notificationPendingIntent = PendingIntent.getActivity(
                    this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder
                    .setSmallIcon(R.mipmap.ic_launcher) //Must have an icon :D
                    .setContentTitle("Exercise 1 notification")
                    .setContentText("submit hardcopy of the documents on " + submitDateString)
                    .setAutoCancel(false)
                    .setContentIntent(notificationPendingIntent);

            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);

            EditText name = (EditText) findViewById(R.id.nameInput);
            Intent intent1 = new Intent(StudentInfor1.this, MainActivity.class);
            intent1.putExtra("response", name.getText().toString());
            setResult(RESULT_OK, intent1);
            finish();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StudentInfor1.this);

            alertDialogBuilder
                    .setTitle("Please add a date")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
    }

    // To create notification for Android 8.0 and above, you must register a notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}