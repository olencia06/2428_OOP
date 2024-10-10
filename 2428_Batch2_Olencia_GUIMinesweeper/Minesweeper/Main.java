/*
 * Author: Olencia Fernandes
 * Roll no: 2429
 * Topic: Minesweeper Board Game
 * Start Date: 23 August 2024
 * Modified Date: 24 August 2024
 * Description: Creating a minesweeper game including  gui
 */
package Minesweeper;

public class Main implements Runnable {

    GUI gui = new GUI();

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }

    @Override
    public void run() {

        while (true) {
            gui.repaint();
        }
    }

}
