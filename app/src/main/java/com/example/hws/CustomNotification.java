package com.example.hws;

// Код для создания кастомного макета оповещения
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

public class CustomNotification {

    private static final String CHANNEL_ID = "custom_notification_channel";
    private static final String CHANNEL_NAME = "Custom Notification Channel";
    private static final int NOTIFICATION_ID = 100;

    public static void showCustomNotification(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Создание канала оповещения для версий Android 8 и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Создание макета для уведомления
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.custom_notification_layout);

        // Настройка действий для кнопок
        notificationLayout.setOnClickPendingIntent(R.id.play_button, PendingIntent.getActivities(context, 0, new Intent[]{new Intent(context, MainActivity.class)}, 0));
        notificationLayout.setOnClickPendingIntent(R.id.pause_button, PendingIntent.getActivities(context, 0, new Intent[]{new Intent(context, MainActivity.class)}, 0));
        notificationLayout.setOnClickPendingIntent(R.id.previous_button, PendingIntent.getActivities(context, 0, new Intent[]{new Intent(context, MainActivity.class)}, 0));
        notificationLayout.setOnClickPendingIntent(R.id.next_button, PendingIntent.getActivities(context, 0, new Intent[]{new Intent(context, MainActivity.class)}, 0));

        // Создание уведомления с кастомным макетом
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseline_notifications_24))
                .setCustomContentView(notificationLayout)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();

        // Отображение уведомления
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
