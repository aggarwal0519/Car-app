import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static boolean validateEmail(String emailAddress) {
        Pattern emailRegexPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher emailMatcher = emailRegexPattern.matcher(emailAddress);

        if (emailMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateString(String inputString) {

        if (inputString != null && !inputString.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date = null;

        try {
            //Parsing the String
            date = dateFormat.parse(inputDate);
            return true;

        } catch (ParseException e) {
            return false;

        }
    }

    public static ArrayList<Car> readFile() {
        ArrayList<Car> list = new ArrayList<Car>();
        File file = new File("Fleet.csv");

        // Method to read file and parsing all the information
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String header = br.readLine();

            while ((line = br.readLine()) != null) {

                String[] lineParts = line.split(",");
                if (Objects.equals(lineParts[10], "N/A")) {
                    lineParts[10] = "0";
                }

                list.add(new Car(lineParts[0], lineParts[1], lineParts[2], lineParts[3], Integer.parseInt(lineParts[4]), Integer.parseInt(lineParts[5]), lineParts[6], Integer.parseInt(lineParts[7]), Integer.parseInt(lineParts[8]), Integer.parseInt(lineParts[9]), Integer.parseInt(lineParts[10])));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }


    public static void main(String[] args) {


        // read csv file using readFile function
        ArrayList<Car> list = readFile();

        // Passing car list to the menu
        showMenu(list);
    }
        // Menu option displaying all the options of selecting a car by brand, type, number of passengers

    // Passing object value for printing menu values
    public static void showMenu(ArrayList<Car> list) {
        // TODO Auto-generated method stub
        System.out.println("Welcome to MyCar!");
        System.out.println("------------------------------------------------------------------");
        while (true) {
            System.out.println("Select from the Main Menu");
            System.out.println("------------------------------------------------------------------");
            System.out.println("1)Search by brand\n2)Browse by vehicle type\n3)Filter by number of passengers\n4)Exit");
            System.out.println("Enter the option you want to choose: ");

            Scanner sc = new Scanner(System.in);

            int option = 0;
            String optionString = sc.nextLine();

            boolean done = false;
            while (!done) {
                // Validating option value if the user has input integer value
                try {
                    option = Integer.parseInt(optionString);
                    done = true;

                }
                catch (NumberFormatException ex) {
                    System.out.println("Please enter a valid input number: ");
                    optionString = sc.nextLine();
                }
            }

            // switch is used for selecting option values
            switch (option) {
                case 1:
                    searchByBrand(list);
                    break;
                case 2:
                    browseByVehicleType(list);
                    break;
                case 3:
                    numberOfPassengers(list);
                    break;
                case 4:
                    System.exit(0);
                default:
                    // Validating option
                    System.out.println("Invalid Selection!\nPlease select from the above option");
                    break;
            }
            System.out.println("");
        }
    }

    //The number of passengers are input
    public static void numberOfPassengers(ArrayList<Car> list) {
        // TODO Auto-generated method stub
        System.out.println("Please provide the number of passengers: ");
        Scanner sc = new Scanner(System.in);
        int seats = sc.nextInt();
        System.out.println("----------------------------------------------------");
        System.out.println("Browse from the number of passengers");
        System.out.println("----------------------------------------------------");

        HashMap<Integer, Car> map = new HashMap<Integer, Car>();
        int c = 1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNoOfSeats() >= seats) {
                map.put(c, list.get(i));
                c++;
            }
        }

        printFilteredCar(map);

    }
    //Users browsing vehicle by its type
    public static void browseByVehicleType(ArrayList<Car> list) {
        // TODO Auto-generated method stub
        System.out.println("----------------------------------------------------");
        System.out.println("Browse from the type of vehicle");
        System.out.println("----------------------------------------------------");


        ArrayList<String> listType = new ArrayList<String>();

        int c = 1;

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                listType.add(list.get(i).getType());
            } else {
                if (!listType.contains(list.get(i).getType())) {
                    listType.add(list.get(i).getType());
                }
            }
        }

        for (String type : listType) {
            System.out.println(c + ") " + type);
            c++;
        }
        System.out.println(listType.size() + 1 + ") Go to main menu");


        System.out.println("Please select: ");
        Scanner scan = new Scanner(System.in);
        int search = scan.nextInt();

        HashMap<Integer, Car> map = new HashMap<Integer, Car>();

        for (int j = 0; j < listType.size(); j++) {
            if (search == j + 1) {
                System.out.println("----------------------------------------------------");
                System.out.println("Select from the list of " + listType.get(search - 1) + "s");
                System.out.println("----------------------------------------------------");
                int d = 1;


                for (int k = 0; k < list.size(); k++) {
                    if ((list.get(k).getType()).equals(listType.get(search - 1))) {
                        map.put(d, list.get(k));
                        d++;
                    }

                }
            }
        }

        printFilteredCar(map);
    }


    //Users searching vehicle by its brand

    public static void searchByBrand(ArrayList<Car> list) {
        // TODO Auto-generated method stub
        System.out.println("Search by the given brand");
        Scanner sca = new Scanner(System.in);
        String search = sca.nextLine();
        System.out.println("----------------------------------------------------");
        System.out.println("Select from the matching list");
        System.out.println("----------------------------------------------------");

        HashMap<Integer, Car> map = new HashMap<Integer, Car>();

        int c = 1;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getBrand().equals(search)) {
                map.put(c, list.get(i));
                c++;
            }
        }

        printFilteredCar(map);


    }

    //Method for hashmap for its multiple use cases
    public static void printFilteredCar(HashMap<Integer, Car> map) {
        int input = 0;
        String inputString;

        do {
            for (Integer key : map.keySet()) {
                Car car = map.get(key);
                car.printCar(key);
            }

            if (map.size() == 0) {
                System.out.println("\nNo match found!");
            }

            // Going back to the main menu
            System.out.println(map.size() + 1 + ") Go to main menu");

            System.out.println("Please select: ");
            Scanner scan = new Scanner(System.in);
            inputString = scan.nextLine();


            boolean done = false;
            while (!done) {
                try {
                    input = Integer.parseInt(inputString);
                    done = true;
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter a valid input number: ");
                    inputString = scan.nextLine();
                }
            }


            if (input < 1 || input > map.size()) {
                System.out.println("Please select the above option");
            }
        } while (input < 1 || input > map.size()+1);

        if (input != map.size()+1) {
            Car selectedCar = map.get(input);
            bookCar(selectedCar);
        }
    }
    //Input the pick-up and return date
    public static void bookCar(Car selectedCar) {
        System.out.println("----------------------------------------------------");
        System.out.println("Provide the dates");
        System.out.println("----------------------------------------------------");
        Scanner sca = new Scanner(System.in);
        System.out.println("Please provide pick-up date(dd/mm/yyyy)");
        String pickupDate = sca.next();

        // Validating pick-up and return date
        while (!validateDate(pickupDate)) {
            System.out.println("Enter a valid date: ");
            pickupDate = sca.next();
        }

        System.out.println("Please provide return date(dd/mm/yyyy)");
        String returnDate = sca.next();

        while (!validateDate(returnDate)) {
            System.out.println("Enter a valid date: ");
            pickupDate = sca.next();

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date1 = null;
        Date date2 = null;
        long days = 0;

        try {

            date1 = dateFormat.parse(pickupDate);
            date2 = dateFormat.parse(returnDate);
            long diff = date2.getTime() - date1.getTime();
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        } catch (ParseException e) {
            e.printStackTrace();

        }
        // Printing all the details of the selected vehicle
        System.out.println("----------------------------------------------------");
        System.out.println("Show Vehicle details");
        System.out.println("----------------------------------------------------");
        selectedCar.printCarDetails(days);

        //Reservation confirmation
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to reserve the vehicle?(Y/N)");
        String reserve = scan.nextLine();

        do {
            if (!(reserve.equalsIgnoreCase("Y") || reserve.equalsIgnoreCase("N"))) {
                System.out.println("Please select from (Y/N)");
                reserve = scan.nextLine();
            }

            if (reserve.equalsIgnoreCase("Y")) {
                System.out.println("----------------------------------------------------");
                System.out.println("Provide personal information");
                System.out.println("----------------------------------------------------");
                System.out.println("Please provide your given name: ");
                String custName = scan.nextLine();

                while (!validateString(custName)) {
                    System.out.println("Given name cannot be empty: ");
                    custName = scan.nextLine();
                }

                System.out.println("Please provide your surname: ");
                String custSurname = scan.nextLine();

                while (!validateString(custSurname)) {
                    System.out.println("Surname cannot be empty: ");
                    custSurname = scan.nextLine();
                }

                System.out.println("Please provide your email address: ");
                String custEmail = scan.nextLine();
                while (!validateEmail(custEmail)) {
                    System.out.println("Please enter a valid email address: ");
                    custEmail = scan.nextLine();
                }

                System.out.println("Please provide number of passengers: ");
                String custNoOfpassengers = scan.nextLine();
                int custNoOfpassengersInt = 0;

                boolean done = false;
                while (!done) {
                    try {
                        custNoOfpassengersInt = Integer.parseInt(custNoOfpassengers);
                        done = true;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a valid input for number of passengers: ");
                        custNoOfpassengers = scan.nextLine();
                    }
                }
            // Taking input of the customer details and printing it.
                System.out.println("Confirm and pay (Y/N)");
                String confirm = scan.nextLine();

                Customer customer = new Customer(custName, custSurname, custEmail, custNoOfpassengersInt);

                do {
                    if (!(confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("N"))) {
                        System.out.println("Please select from (Y/N)");
                        confirm = scan.nextLine();
                    }
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Congratulations! You vehicle is booked. A receipt has been sent to your email. We will soon be in touch before your pick-up date. ");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

                        customer.printCustomerDetails(pickupDate, returnDate, days, selectedCar);
                    }
                    //Validating a valid input
                } while (!(confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("N")));

                //Validating a valid input
            }

        } while (!(reserve.equalsIgnoreCase("Y") || reserve.equalsIgnoreCase("N")));


    }
}
