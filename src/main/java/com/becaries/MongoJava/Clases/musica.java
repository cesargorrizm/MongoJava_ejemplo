package com.becaries.MongoJava.Clases;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musica extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            playMp3();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void playMp3() throws Exception {
        String directoryName = System.getProperty("user.dir");
        new ProcessBuilder()
                .command("cmd.exe", "/c",
                        "start Wmplayer " + directoryName + "/src/main/java/com/becaries/MongoJava/Clases/nonoonon.mp3")
                .inheritIO().start().waitFor();

    }
}
