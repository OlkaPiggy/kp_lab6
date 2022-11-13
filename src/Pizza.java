import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Pizza implements Serializable{
    private String name;
    private double weight;
    private int price;
    private ArrayList<String> ingridient;


    public Pizza(String name, double weight, int price, ArrayList<String> ingridient) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.ingridient = ingridient;
    }
    public Pizza(String name)
    {
        this.name=name;
    }

    public Pizza(String name,ArrayList<Pizza> listt)
    {
        for(Pizza i:listt)
        {
            if(i.name.equals(name)) {
                this.name = i.name;
                this.weight = i.weight;
                this.price = i.price;
                this.ingridient = i.ingridient;
            }
        }
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getIngridient() {
        return ingridient;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", ingridient=" + ingridient +
                '}';
    }

    public static ArrayList<Pizza> read(File f )
    {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
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
                pizzas.add(pizza);
            }
            return pizzas;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
