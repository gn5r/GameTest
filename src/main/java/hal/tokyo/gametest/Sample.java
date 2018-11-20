/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hal.tokyo.gametest;

/**
 *
 * @author gn5r
 */
public class Sample {

    private static BGMPlayer bgmPlayer;

    public static void main(String[] args) throws Exception {
        System.out.println("演出サンプルを開始");

        while (true) {
            Thread.sleep(2000);
            startBGM("level_0");

            System.out.println("ゲーム結果受信待機中...");
            Thread.sleep(5000);
            int level = 1;
            System.out.println("キレイドは1です");

            bgmPlayer.stopBGM();
            Thread.sleep(1000);
            mainPerform(level);
            Thread.sleep(1000);
            System.out.println("次のゲームへ移行します。");
        }

    }

    private static void mainPerform(int level) throws Exception {

        switch (level) {
            case 1:
                startBGM("level_3");

                while (true) {
                    Thread.sleep(1000);
                    if (bgmPlayer.getSize() == -1) {
                        bgmPlayer.stopBGM();
                        break;
                    }
                }

            default:
                break;
        }
    }

    private static void startBGM(String fileName) {
        bgmPlayer = new BGMPlayer("BGM/" + fileName);
        bgmPlayer.musicPlay();
    }
}
