import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String number;
    private Address address;
    private ArrayList<Order> orders;

    public Customer(String number, Address address, ArrayList<Order> list,String name) {
        this.number = number;
        this.address = address;
        this.orders = list;
        this.name=name;
    }

    public String getNumber() {
        return number;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Order> getOrder() {
        return orders;
    }

    @Override
    public String toString() {

        String pizzaa="";
        for(Order o:orders) {
                pizzaa += o.getOrdered_pizza().getName() + " ";
        }
        return name+ " "+number + " "+
                 address + " "+ pizzaa +"\n";
    }

}


