package kr.ac.jbnu.babyseokarmy.flipbabe.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;

public class MusicService extends Service {

    MediaPlayer musicPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicPlayer = MediaPlayer.create(this, R.raw.baby_crying_04);
        musicPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
    }
}
