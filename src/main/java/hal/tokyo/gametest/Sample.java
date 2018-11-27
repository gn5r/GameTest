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

    private static StartBGM startBGM;
    private static BGMPlayer performBGM;

    public static void main(String[] args) throws Exception {
        System.out.println("演出サンプルを開始");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Thread.sleep(2000);
            startBGM("level_0");

            System.out.println("ゲーム結果受信待機中...");
            int level = scanner.nextInt();
            System.out.println("キレイドは" + level + "です");
            startBGM.stopBGM();
            Thread.sleep(1000);
            mainPerform(level);
            Thread.sleep(1000);
            System.out.println("次のゲームへ移行します。");
        }

    }

    private static void mainPerform(int level) throws Exception {

        performBGM("level_" + level);

        while (true) {
            if (performBGM.getSize() == -1) {
                performBGM.stopBGM();
                break;
            }
        }        
    }

    private static void startBGM(String fileName) {
        startBGM = new StartBGM("BGM/" + fileName);
        startBGM.musicPlay();
    }

    private static void performBGM(String fileName) {
        performBGM = new BGMPlayer("BGM/" + fileName);
        performBGM.musicPlay();
    }
}
