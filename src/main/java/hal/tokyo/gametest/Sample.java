/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hal.tokyo.gametest;

import java.util.Scanner;

/**
 *
 * @author gn5r
 */
public class Sample {

    private static BGMPlayer bgmPlayer;

    public static void main(String[] args) throws Exception {
        System.out.println("演出サンプルを開始");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Thread.sleep(2000);
            startBGM("level_0");

            System.out.println("ゲーム結果受信待機中...");
            int level = scanner.nextInt();
            System.out.println("キレイドは" + level + "です");
            bgmPlayer.stopBGM();
            Thread.sleep(1000);
            mainPerform(level);
            Thread.sleep(1000);
            System.out.println("次のゲームへ移行します。");
        }

    }

    private static void mainPerform(int level) throws Exception {

        startBGM("level_" + level);

        while (true) {
            if (bgmPlayer.getSize() == -1) {
                break;
            }
        }
        bgmPlayer.stopBGM();
    }

    private static void startBGM(String fileName) {
        bgmPlayer = new BGMPlayer("BGM/" + fileName);
        bgmPlayer.musicPlay();
    }
}
