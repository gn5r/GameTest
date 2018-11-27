/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hal.tokyo.gametest;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author gn5r
 */
public class StartBGM extends Thread {

    private AudioInputStream ais;
    private DataLine.Info dataLine;
    private SourceDataLine boothBGM;
    private byte[] data;
    private final File file;

    private boolean flag;

    private int size;

    public StartBGM() {
        this.file = new File("level_0.wav");
    }

    @Override
    public void run() {

        try {

            this.ais = AudioSystem.getAudioInputStream(this.file);
            AudioFormat format = ais.getFormat();
            this.dataLine = new DataLine.Info(SourceDataLine.class, format);
            this.boothBGM = (SourceDataLine) AudioSystem.getLine(this.dataLine);
            this.boothBGM.open();
            this.boothBGM.start();
            this.size = -1;
            this.data = new byte[this.boothBGM.getBufferSize()];

            while (true) {
                this.size = this.ais.read(this.data);

                if (this.size == -1) {
                    ais.close();
                    ais = AudioSystem.getAudioInputStream(this.file);
                    continue;
                }
                
                this.boothBGM.write(this.data, 0, size);
                
                if (!this.flag) {
                    break;
                }
            }

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void musicPlay() {
        this.flag = true;

        start();
    }

    public void stopBGM() {
        this.flag = false;

        this.boothBGM.drain();
        this.boothBGM.stop();
        this.boothBGM.close();

    }
}
