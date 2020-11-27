/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemaroommanager;

import java.util.Scanner;

/**
 *
 * @author Shafahat
 */
public class CinemaRoomManager {

    public static final Scanner scan = new Scanner(System.in); 
    public static int numberOfPurchasedTickets = 0, currentIncome = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scan.nextInt();
        String[][] cinema = createCinema(rows, cols);         
        final int numberOfTickets = rows * cols;  
        final int totalIncome = getTotalIncome(numberOfTickets, rows, cols);   
        
        while(true){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");            
            int command = scan.nextInt();
            
            if(command == 0){
                break;
            } 
            
            switch(command){
                case 1:
                    displaySeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema, numberOfTickets);
                    break;
                case 3:
                    displayStatics(numberOfTickets, totalIncome);
                    break;
                default: 
                    System.out.println("Please Enter correct command.");
                    break;
            }    
        }
    }

    public static String[][] createCinema(int rows, int cols){
        String[][] cinema = new String[rows][cols];
        
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++){
                    cinema[i][j] = "S";
            }    
        }
        return cinema;
    }

    public static void displaySeats(String[][] cinema){
        System.out.println("Cinema:");      
        
        for (int i = 0; i <= cinema.length; i++) {
            for (int j = 0; j <= cinema[0].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0 && j != 0) {
                    System.out.print(j + " ");
                } else if (i != 0 && j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(cinema[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
        
    }
    
    public static void buyTicket(String[][] cinema, int numberOfTickets){
        int seatY, seatX;
        String coordinate;
        while(true){
            System.out.println("Enter a row number:");
            seatY = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatX = scan.nextInt();
            
            if (seatY < 0 || seatY > cinema.length || seatX < 0 || seatX > cinema[0].length) {
                System.out.println("Wrong input!");
            } else {
                
                coordinate = cinema[seatY - 1][seatX - 1];
            
                if(coordinate.equals("S")){
                    cinema[seatY - 1][seatX - 1] = "B";
                    numberOfPurchasedTickets++;  
                    int price =  getTicketPrice(cinema, seatY, numberOfTickets);
                    currentIncome += price;
                    System.out.println("Ticket price: $" + price);
                    break;
                } else{
                    System.out.println("That ticket has already been purchased");
                }
            }
        }      
    }
    
    public static int getTicketPrice(String[][] cinema, int seatY, int numberOfTickets){
        int price;        
        if (numberOfTickets <= 60) {
            price = 10;
        } else {
            if(seatY <= cinema.length / 2){
                price = 10; 
            } else {
                price = 8; 
            }
        }
        return price;
    }    

    public static int getTotalIncome(int numberOfTickets, int rows , int cols){
        int totalIncome;
        if(numberOfTickets < 60){
            totalIncome = numberOfTickets * 10;
        } else {
            if(rows % 2 == 0){
                totalIncome = numberOfTickets / 2 * 10 + numberOfTickets / 2 * 8;
            } else {
                totalIncome = (rows / 2 + 1) * cols * 8 + rows / 2 * cols * 10;
            }
        }
        return totalIncome;
    }
    
    public static void displayStatics(int numberOfTickets, int totalIncome){
        double percentage = numberOfPurchasedTickets * 100.00 / numberOfTickets ;
        System.out.printf("Number of purchased tickets: %d\n", numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }
}
