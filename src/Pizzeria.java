import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Pizzeria implements Serializable{

    private ArrayList<Pizza> pizza_list;
    private ArrayList<Customer> customer_list;

    public Pizzeria(ArrayList<Pizza> pizza_list, ArrayList<Customer> customer_list) {
        this.pizza_list = pizza_list;
        this.customer_list = customer_list;
    }
    public Pizzeria()
    {
        pizza_list=new ArrayList<>();
        customer_list=new ArrayList<>();
    }

    public ArrayList<Pizza> getPizza_list() {
        return pizza_list;
    }

    public ArrayList<Customer> getCustomer_list() {
        return customer_list;
    }

    @Override
    public String toString() {
        return "Pizzeria{" +
                "pizza_list=" + pizza_list +
                ", customer_list=" + customer_list +
                '}';
    }

    public void readPizza(File f )
    {
        this.pizza_list = new ArrayList<Pizza>();
        try (FileReader reader = new FileReader(f)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arr = tmp.split(" ");

                String name =arr[0];
                int weight=Integer.parseInt(arr[1]);
                int price=Integer.parseInt(arr[2]);
                ArrayList<String> ingridients=new ArrayList<String>();
                for(int i=3;i<arr.length;i++)
                {
                    ingridients.add(arr[i]);
                }
                Pizza pizza=new Pizza(name,weight,price,ingridients);
                pizza_list.add(pizza);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void readCustomer(File f )
    {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try (FileReader reader = new FileReader(f)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arr = tmp.split(" ");

                String name =arr[0];
                String number=arr[1];

                String city=arr[2];
                String street=arr[3];
                int hnumber=Integer.parseInt(arr[4]);
                Address address1=new Address(city,street,hnumber);

                String datte=arr[5];
                datte+=" 00:00";
                LocalDateTime datetime = LocalDateTime.parse(datte, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m"));
                ArrayList<Order> orders=new ArrayList<Order>();
                for(int i=6;i<arr.length;i++)
                {
                    Pizza pizza=new Pizza(arr[i],pizza_list);
                    orders.add(new Order(pizza,datetime));
                }

                Customer customer=new Customer(number,address1,orders,name);
                customers.add(customer);
            }
            customer_list=customers;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void Task_1()
    {
        var orders = customer_list.stream().map(Customer::getOrder).flatMap(List::stream).toList();
        List<Order> sortedList = orders.stream()
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());

        sortedList.forEach(System.out::print);
        System.out.print("\n");
    }

    public void Task_2()
    {
        List<Customer> checkedList = customer_list.stream()
                .filter(c -> c.getOrder().size() > 2)
                .collect(Collectors.toList());

        checkedList.forEach(System.out::print);
        System.out.print("\n");
    }

    public void Task_3(){
        Scanner scanner=new Scanner(System.in);
        String pizza=scanner.nextLine();
        Pizza p=new Pizza("peperoni");
        int k=0;
        long count= customer_list.stream()
                .filter(c -> c.getOrder().stream()
                        .anyMatch(o -> o.getOrdered_pizza().getName().equals(pizza))).count();
        System.out.print("Count of users that ordered this pizza:" +count);
        System.out.print("\n");
    }

    public void Task_4() {

        var max_number = customer_list.stream()
                .map(customer -> customer.getOrder().size()).max(Integer::compare).get();

        System.out.print("Max count of pizza:" +max_number);
        System.out.print("\n");
    }

    public void Task_5() {

        HashMap<Pizza, List<Customer>> pizza_customers = new HashMap<>();

        for (Pizza pizza : pizza_list) {
            var res = customer_list.stream().
                    filter(customer -> customer.getOrder().stream()
                            .anyMatch(order -> pizza.getName().equals(order.getOrdered_pizza().getName()))
                    ).toList();
            pizza_customers.put(pizza, res);
        }

        for(Pizza pizza:pizza_customers.keySet())
        {
            System.out.println(pizza.getName());
            List<Customer> c=pizza_customers.get(pizza);
            c.forEach(System.out::print);
            System.out.print("\n");
        }
    }

    public void Task_6() {

        System.out.print("Введіть дату за зразком:\"yyyy-MM-dd HH:m\": ");
        Scanner in=new Scanner(System.in);
        String input= in.nextLine();
        //String input="2002-01-18 00:00";

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m");
            LocalDateTime dateTime = LocalDateTime.parse(input, dateTimeFormatter);

            var orders = customer_list.stream().map(Customer::getOrder).flatMap(List::stream).toList();

            var not_completed = orders.stream().filter(x -> dateTime.isAfter(x.getDate())).toList();

            var not_completed_duration = not_completed.stream()
                    .collect(Collectors.toMap(x -> x, y -> Duration.between(y.getDate(), dateTime).toDays()));

            for(Order o:not_completed_duration.keySet())
            {
                System.out.print(o+" "+not_completed_duration.get(o) +" days\n" );
            }
            System.out.print("\n");
    }
}
