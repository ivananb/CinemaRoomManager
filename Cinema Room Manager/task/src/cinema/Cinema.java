package cinema;

import java.util.Scanner;

public class Cinema {

    public static int chooseRow(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int chosenRow = scan.nextInt();
        return chosenRow;
    }
    public static int chooseSeat(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = scan.nextInt();
        return chosenSeat;
    }

    public static void buyTicket(String[][] cinemaSeats){
        Scanner scan = new Scanner(System.in);
        int firstHalfRows, nSeats, price = 0;

        int chosenRow = chooseRow();
        int chosenSeat = chooseSeat();


        for (int i = 0; i < cinemaSeats.length; i++){
            for (int j = 0; j < cinemaSeats[0].length; j++){
                if (i == chosenRow-1 && j == chosenSeat-1 && cinemaSeats[i][j] == "B") {
                    System.out.println("That ticket has already been purchased!");
                    chosenRow = chooseRow();
                    chosenSeat = chooseSeat();
                } else if (chosenRow - 1 >= cinemaSeats.length || chosenSeat-1 >= cinemaSeats[0].length){
                    System.out.println("Wrong input!");
                    chosenRow = chooseRow();
                    chosenSeat = chooseSeat();
                }
            }
        }


        //ticket price
        nSeats = cinemaSeats.length * cinemaSeats[0].length;
        firstHalfRows = cinemaSeats.length/2;

        if (nSeats <= 60 || chosenRow <= firstHalfRows){
            price = 10;
        } else {
            price = 8;
        }

        System.out.printf("\nTicket price: $%d" , price);
        //System.out.println("\nTicket price: $" + price);

        for (int i = 0; i < cinemaSeats.length; i++){
            for (int j = 0; j < cinemaSeats[0].length; j++){
                if(i == chosenRow-1 && j == chosenSeat-1) {
                    cinemaSeats[i][j] = "B";
                }
            }
        }
    }


    public static void showSeats(String[][] cinemaSeats){
        //printing seating arrangement
        int nR = 1, nC = 0; //number rows and columns
        System.out.println("\nCinema:");
        for (int i = 0; i <= cinemaSeats[0].length; i++){
            if (i == 0){
                System.out.print(" ");
            }else{
                System.out.print(" " + nC);
            }
            nC++;
        }
        System.out.print("");

        for (int i = 0; i < cinemaSeats.length; i++){
            System.out.print("\n" + nR + " ");
            nR++;
            for (int j = 0; j < cinemaSeats[0].length; j++){
                    System.out.print(cinemaSeats[i][j] + " ");

            }

        }
        System.out.print("\n");
    }


    public static void statistics(String[][] cinemaSeats){

        int nSeats = cinemaSeats.length * cinemaSeats[0].length;
        int nTickets = 0;
        float percentage = 0;
        int income = 0;
        int totIncome = 0;
        int firstHalfRows = cinemaSeats.length/2;
        int firstHalfSeats = cinemaSeats[0].length * firstHalfRows;
        int secondHalfSeats = nSeats - firstHalfSeats;

        //purchased tickets

        for (int i = 0; i < cinemaSeats.length; i++){
            for (int j = 0; j < cinemaSeats[0].length; j++){
                if(cinemaSeats[i][j] == "B") {
                    nTickets++;
                    if (nSeats <= 60 || i < firstHalfRows) {
                        income += 10;
                    } else {
                        income += 8;
                    }
                }
            }
        }

        percentage = (100 * (float)nTickets) / (float)nSeats;

        if (nSeats <= 60){
            totIncome = nSeats * 10;
        } else if(nSeats > 60) {
            totIncome = (firstHalfSeats * 10) + (secondHalfSeats * 8);
        }


        System.out.printf("\nNumber of purchased tickets: %d" , nTickets);
        System.out.printf("\nPercentage: %.2f%%", percentage);
        System.out.printf("\nCurrent income: $%d" , income);
        System.out.printf("\nTotal income: $%d", totIncome);
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nRows,nSeatsInARow, menu;
        System.out.println("Enter the number of rows:");
        nRows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        nSeatsInARow= scan.nextInt();
        String[][] cinemaSeats = new String[nRows][nSeatsInARow];

        for (int i = 0; i < cinemaSeats.length; i++){
            for (int j = 0; j < cinemaSeats[0].length; j++){
                cinemaSeats[i][j] = "S";
            }
        }
        for (;;){
            System.out.println("\n\n1. Show the seats\n" + "2. Buy a ticket\n"
                                + "3. Statistics\n" + "0. Exit");
            menu = scan.nextInt();
            if (menu == 1){
                showSeats(cinemaSeats);
            } else if (menu == 2){
                buyTicket(cinemaSeats);
            } else if (menu == 3){
                statistics(cinemaSeats);
            } else {
                break ;
            }
        }
    }
}