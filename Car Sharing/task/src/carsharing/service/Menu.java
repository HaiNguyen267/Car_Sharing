package carsharing.service;

import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Menu {

    private String menuMessage;
    private int minChoice;
    private int maxChoice;

    private boolean isStop;
    public Menu(String menuMessage, int minChoice, int maxChoice) {
        this.menuMessage = menuMessage;
        this.minChoice = minChoice;
        this.maxChoice = maxChoice;
        this.isStop = false;
    }

    public int printMenuAndGetUserChoice() {
        Scanner sc = new Scanner(System.in);

        System.out.println(menuMessage);
        int choice = Integer.parseInt(sc.nextLine());

        while (choice < minChoice || choice > maxChoice) {
            System.out.println("Invalid number");
            choice = Integer.parseInt(sc.nextLine());
        }

        return choice;
    }


    public boolean isStopped() {
        return isStop;
    }

    public void stop() {
        isStop = true;
    }


}
