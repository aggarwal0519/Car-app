
class Customer {

    private String custName;
    private String custSurname;
    private String custEmail;
    private int custNoOfpassengers;

    //Constructor for initializing customer details
    public Customer(String custName, String custSurname, String custEmail, int custNoOfpassengers) {
        this.custName = custName;
        this.custSurname = custSurname;
        this.custEmail = custEmail;
        this.custNoOfpassengers = custNoOfpassengers;

    }
    //Method to print all the customer details
    public void printCustomerDetails(String pickupDate, String returnDate, long days, Car selectedCar) {

        System.out.println("Name: " + this.custName + " " + this.custSurname);
        System.out.println("Email: " + this.custEmail);
        System.out.println("Your vehicle: " + selectedCar.getBrand() + " " + selectedCar.getModel() + " " + selectedCar.getType() + " with " + selectedCar.getNoOfSeats() + "seats");
        System.out.println("Number of passengers: " + this.custNoOfpassengers);
        System.out.println("Pick-up Date: " + pickupDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Total payment: " + selectedCar.calculateTotalPrice(days, false));

    }
    //Getters
    public String getCustName() {
        return custName;
    }

    public String getSurname() {
        return custSurname;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public int getCustNoOfpassengers() {
        return custNoOfpassengers;
    }

}