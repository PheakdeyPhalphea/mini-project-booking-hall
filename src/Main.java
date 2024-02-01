
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int rows, cols;
        do {
            System.out.print("Config total rows in hall: ");
            String rowsInput = scanner.nextLine();
            if (isValidNumber(rowsInput)) {
                rows = Integer.parseInt(rowsInput);
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for rows.");
            }
        } while (true);
        do {
            System.out.print("Config total seats per row in hall: ");
            String colsInput = scanner.nextLine();
            if (isValidNumber(colsInput)) {
                cols = Integer.parseInt(colsInput);
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for columns.");
            }
        } while (true);
        String[][] hall1 = new String[rows][cols];
        String[][] hall2 = new String[rows][cols];
        String[][] hall3 = new String[rows][cols];
        initCode(hall1, hall2, hall3);
    }
    private static void initCode(String[][] hall1, String[][] hall2, String[][] hall3) {
        String[][] bookingHistory = new String[20][20];
        do {
            welcomeMenu();
            String userDesire = menu(scanner);
            switch (userDesire.toLowerCase()) {
                case "a" -> {
                    bookingWelcome();
                    boolean isBoolean = true;
                    do {
                        String regex = "^[a-cA-C]+$";
                        System.out.print("> Please select show time (A | B | C) : ");
                        String userInputHall = scanner.nextLine();
                        if (isValidOptionInput(userInputHall, regex)) {
                            switch (userInputHall.toLowerCase()) {
                                case "a" -> {
                                    System.out.println("# HALL A");
                                    hallA(hall1);
                                    bookSeat("A ", scanner, hall1 , bookingHistory);
                                    isBoolean = false;
                                }
                                case "b" -> {
                                    System.out.println("# HALL B");
                                    hallB(hall2);
                                    bookSeat("B",scanner, hall2, bookingHistory);
                                    isBoolean = false;
                                }
                                case "c" -> {
                                    System.out.println("# HALL C");
                                    hallC(hall3);
                                    bookSeat("C ", scanner, hall3 , bookingHistory);
                                    isBoolean = false;
                                }
                                default -> System.out.println("There Is No Hall :" + userInputHall);
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid Option.");
                        }
                    } while (isBoolean);
                }
                case "b" -> {
                    System.out.println("-+".repeat(25));
                    System.out.println("# Hall Information");
                    System.out.println("-+".repeat(25));
                    System.out.println("# Hall - Morning ");
                    hallA(hall1);
                    System.out.println("-+".repeat(25));
                    System.out.println("# Hall - Afternoon ");
                    hallB(hall2);
                    System.out.println("-+".repeat(25));
                    System.out.println("# Hall - Evening");
                    hallC(hall3);
                }
                case "c" -> showtime();
                case "d" -> {
                    for (int i = 0; i < hall1.length; i++) {
                        for (int j = 0; j < hall1[i].length; j++) {
                            hall1[i][j] = null;
                            hall2[i][j] = null;
                            hall3[i][j] = null;
                        }
                    }
                    for (int i = 0; i < bookingHistory.length; i++) {
                        for (int j = 0; j < bookingHistory[i].length; j++) {
                            bookingHistory[i][j] = null;
                        }
                    }
                }
                case "e" -> displayBookingHistory(bookingHistory);
                case "f" -> System.exit(0);
            }
        }while(true);
    }
    public static void hallA(String[][] array) {
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    public static void hallB(String[][] array){
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    public static void hallC(String[][] array){
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    private static void showtime () {
        System.out.println("# Daily Showtime of CSTAD Hall:");
        System.out.println("# A) " + "Morning (10:00AM - 12:30PM)");
        System.out.println("# B) " + "Afternoon (3:00AM - 5:30PM)");
        System.out.println("# C) " + "Night (7:00AM - 9:30PM)");
    }
    private static void welcomeMenu(){
        System.out.println("-+".repeat(25));
        System.out.println("CSTAD HALL BOOKING");
        System.out.println("-+".repeat(25));
    }
    private static String menu (Scanner scanner){
        String userInput;
        String regex = "^[A-Fa-f]$";
      do {
          System.out.println("[[ Application Menu ]]");
          System.out.println("<A> Booking");
          System.out.println("<B> Hall");
          System.out.println("<C> Showtime");
          System.out.println("<D> Reboot");
          System.out.println("<E> History");
          System.out.println("<F> Exit");
          System.out.print("> Please select menu no:");
          userInput = scanner.nextLine();
          if(isValidOptionInput(userInput, regex)) {
              return  userInput;
          } else {
              System.out.println("Invalid input. Please enter a valid Option.");
          }
      }while(true);

    }
    private static void bookingWelcome (){
        System.out.println("-+".repeat(25));
        System.out.println("# Start booking process");
        System.out.println("-+".repeat(25));
        System.out.println("# Showtime Information");
        System.out.println("# A) " + "Morning (10:00AM - 12:30PM)");
        System.out.println("# B) " + "Afternoon (3:00AM - 5:30PM)");
        System.out.println("# C) " + "Night (7:00AM - 9:30PM)");
        System.out.println("-+".repeat(25));
    }
    private static String hallMenu(Scanner scanner) {
        String userInput;
        do {
            System.out.println("# INSTRUCTION");
            System.out.println("# Single: C-1");
            System.out.println("# Multiple (separate by comma) : C-1, C-2");
            System.out.print("> Please select available seat:");
            userInput = scanner.nextLine();
            if(isValidSeatInput(userInput)) {
                return  userInput;
            } else {
                System.out.println("There No Seat [" + userInput + "] (Please Input Again)" );
            }
        } while (!isValidSeatInput(userInput));
        return userInput;
    }
    private static void bookSeat(String hall , Scanner scanner, String[][] array ,String[][] bookingHistory) {
        String regex = "^[a-zA-Z]+$";
        String userInput = hallMenu(scanner);
        String[] seatEntries = userInput.split(",");
        System.out.print("> Please enter Student ID :");
        String userID = scanner.nextLine();
        System.out.println("-+".repeat(25));
        System.out.print("> Are You sure to book? (Y/n)");
        String userOp = scanner.nextLine();
        if(isValidOptionInput(userOp, regex) && userOp.equalsIgnoreCase("Y")) {
            for (String seatEntry : seatEntries) {
                String[] splitInput = seatEntry.split("-");
                int rowIndex = splitInput[0].charAt(0) - 'A';
                int colIndex = Integer.parseInt(splitInput[1]) - 1;
                if (array[rowIndex][colIndex] == null) {
                    array[rowIndex][colIndex] = "BO";
                    System.out.println("# [" + seatEntry.toUpperCase() + "] booked successfully");
                    addToHistory(hall, userID, seatEntry, bookingHistory);
                } else {
                    System.out.println("# [" + seatEntry.toUpperCase() + "] is already booked.");
                }
            }
        } else if(userOp.equalsIgnoreCase("N")) {
            System.out.println("Booking is Cancel");
        } else {
            System.out.println("Please Choose Option (Y/n)");
        }
    }
    private static void displayBookingHistory(String[][] bookingHistory) {
        boolean hasHistory = false;
        for (String[] strings : bookingHistory) {
            if (strings != null && strings[0] != null) {
                System.out.printf(
                        "#HALL:    #SEATS        #STU.ID         #CREATED AT" +
                                "\n%-8s    %-12s %-10s       %-18s%n",
                        strings[0], strings[2], strings[1], strings[3]);
                hasHistory = true;
            }
        }
        if (!hasHistory) {
            System.out.println("There Is No History");
        }
    }

    private static void addToHistory(String hall, String userID, String seat, String[][] bookingHistory) {
        for (int i = 0; i < bookingHistory.length; i++) {
            if (bookingHistory[i][0] == null) {
                bookingHistory[i][0] = hall;
                bookingHistory[i][1] = userID;
                bookingHistory[i][2] = seat;
                bookingHistory[i][3] = LocalDate.now().toString();
                break;
            }
        }
    }
    private static boolean isValidNumber(String input) {
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private static boolean isValidSeatInput(String input) {
        String regex = "^[A-Z]-[0-9]+(,[A-Z]-[0-9]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private static boolean isValidOptionInput (String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
