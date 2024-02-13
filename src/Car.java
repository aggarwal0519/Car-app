public class Car {

    private String vehicleId;
    private String brand;
    private String model;
    private String type;
    private int yearOfManufacture;
    private int noOfSeats;
    private String color;
    private int rentalPerDay;
    private int insurancePerDay;
    private int serviceFee;
    private int discount;


    //Constructor initializing the car details
    public Car(String vehicleId, String brand, String model, String type, int yearOfManufacture, int noOfSeats, String color, int rentalPerDay,
               int insurancePerDay, int serviceFee, int discount) {
        // TODO Auto-generated constructor stub
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
        this.noOfSeats = noOfSeats;
        this.color = color;
        this.rentalPerDay = rentalPerDay;
        this.insurancePerDay = insurancePerDay;
        this.serviceFee = serviceFee;
        this.discount = discount;

    }
    //Getters
    public String getvehicleid() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    //Printing all the car details
    public void printCar(int number) {
        System.out.println(number + ") " + this.vehicleId + " - " + this.brand + "   " + this.model + "   " + this.type + " with " + this.noOfSeats + " seats ");
    }

    //Method for calculating total price
    public double calculateTotalPrice(long days, boolean print){
        double discountedPrice, totalPrice = 0.0;
        float discountVal = 0;

        if (days >= 7) {
            //System.out.println(this.discount);
            discountVal = 100 - this.discount;
            //System.out.println(discountVal);
            discountedPrice = (discountVal / 100) * this.rentalPerDay * days;

            if(print){
                System.out.println("Discounted price: " + discountedPrice);
            }


        } else {
            this.discount = 0;
            discountVal = 100 - this.discount;

            if(print){
                System.out.println("Discounted price: " + this.rentalPerDay * days + "  (Discount is not applicable)");
            }
        }

        totalPrice = ((this.rentalPerDay * (discountVal / 100) + this.insurancePerDay) * days) + this.serviceFee;
        return totalPrice;
    }
    public void printCarDetails(long days) {


        System.out.println("Vehicle: " + this.vehicleId);
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Type of vehicle:  " + this.type);
        System.out.println("Year of manufacture: " + this.yearOfManufacture);
        System.out.println("No.of seats:  " + this.noOfSeats);
        System.out.println("Colour: " + this.color);
        System.out.println("Rental: " + this.rentalPerDay * days + "     ($" + this.rentalPerDay + " * " + days + " days)");
        double totalPrice = calculateTotalPrice(days, true);
        System.out.println("Insurance: " + this.insurancePerDay * days + "     ($" + this.insurancePerDay + " * " + days + " days)");
        System.out.println("Service fee: " + this.serviceFee);
        System.out.println("Total: " + totalPrice);

    }
}
