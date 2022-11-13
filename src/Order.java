import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Order implements Comparable<Order>, Serializable {
    private Pizza pizza;
    private LocalDateTime date;

    public Order(Pizza ordered_pizza_list, LocalDateTime date) {
        this.pizza = ordered_pizza_list;
        this.date = date;
    }

    public Pizza getOrdered_pizza() {
        return pizza;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {

        String datastring = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String pizzaa="";
        pizzaa+=pizza.getName()+" ";
        return datastring + " "+
                pizzaa+"\n";
    }

    @Override
    public int compareTo(Order employee) {
        return getDate().compareTo(employee.getDate());
    }
}
