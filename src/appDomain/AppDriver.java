package appDomain;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;

import products.Dresses;
import products.Jackets;
import products.Jeans;
import products.Product;
import products.Shoes;
import products.Shorts;
import products.Skirts;
import products.TShirts;
import utilities.BubbleSort;
import utilities.HeapSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.QuickSort;
import utilities.SelectionSort;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
    /**
     * The main method is the entry point of the application.
     *
     * @param args The input to control the execution of the application.
     */
    public static void main(String[] args)
    {
        String fileName = null;
        char compareType = '\0';
        char sortAlgorithm = '\0';

        for (String arg : args)
        {
            if (arg.length() < 3)
            {
                System.out.println("Invalid argument: " + arg);
                return;
            }

            String option = arg.substring(0, 2).toLowerCase();
            String value = arg.substring(2);

            switch (option)
            {
                case "-f":
                    fileName = value;
                    break;

                case "-t":
                    if (value.length() != 1)
                    {
                        System.out.println("Invalid compare type.");
                        return;
                    }

                    compareType = Character.toLowerCase(value.charAt(0));

                    if (compareType != 'o' &&
                        compareType != 'u' &&
                        compareType != 'd')
                    {
                        System.out.println("Compare type must be o, u, or d.");
                        return;
                    }

                    break;

                case "-s":
                    if (value.length() != 1)
                    {
                        System.out.println("Invalid sorting algorithm.");
                        return;
                    }

                    sortAlgorithm = Character.toLowerCase(value.charAt(0));

                    if (sortAlgorithm != 'b' &&
                        sortAlgorithm != 's' &&
                        sortAlgorithm != 'i' &&
                        sortAlgorithm != 'm' &&
                        sortAlgorithm != 'q' &&
                        sortAlgorithm != 'z')
                    {
                        System.out.println(
                            "Sorting algorithm must be b, s, i, m, q, or z."
                        );
                        return;
                    }

                    break;

                default:
                    System.out.println("Unknown argument: " + arg);
                    return;
            }
        }
        
        

        if (fileName == null ||
            compareType == '\0' ||
            sortAlgorithm == '\0')
        {
            System.out.println(
                "Missing required arguments. Please provide -f, -t, and -s."
            );
            return;
        }
        
        Product[] products = loadProducts(fileName);
        
        if (products == null) {return;}
        
        Comparator<Product> comparator = getComparator(compareType);

        long start = System.nanoTime();
        sortProducts(products, comparator, sortAlgorithm);
        long end = System.nanoTime();

        displayResults(products, compareType);
        System.out.println(getAlgorithmName(sortAlgorithm) + " run time was: " + ((end - start) / 1_000_000) + " milliseconds");
    }
    
    private static Product[] loadProducts(String fileName) {
        try (Scanner input = new Scanner(new File(fileName))) {
            int count = Integer.parseInt(input.nextLine().trim());
            Product[] products = new Product[count];

            for (int i = 0; i < count; i++) {
                String[] data = input.nextLine().trim().split("\\s+");

                String type = data[0];
                double originalPrice = Double.parseDouble(data[1]);
                double updatedPrice = Double.parseDouble(data[2]);

                switch (type) {
                    case "Jeans":
                        products[i] = new Jeans(originalPrice, updatedPrice);
                        break;
                    case "TShirts":
                        products[i] = new TShirts(originalPrice, updatedPrice);
                        break;
                    case "Skirts":
                        products[i] = new Skirts(originalPrice, updatedPrice);
                        break;
                    case "Dresses":
                        products[i] = new Dresses(originalPrice, updatedPrice);
                        break;
                    case "Shorts":
                        products[i] = new Shorts(originalPrice, updatedPrice);
                        break;
                    case "Jackets":
                        products[i] = new Jackets(originalPrice, updatedPrice);
                        break;
                    case "Shoes":
                        products[i] = new Shoes(originalPrice, updatedPrice);
                        break;
                    default:
                        System.out.println("Invalid product type: " + type);
                        return null;
                }
            }
            return products;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        catch (NoSuchElementException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed input file: " + e.getMessage());
        }

        return null;
    }
    
    

    private static void displayResults(Product[] products, char compareType)
    {
        int length = products.length;

        if (length <= 10)
        {
            for (int i = 0; i < length; i++)
            {
            	System.out.printf("%-20s", (i + 1) + getSuffix(i + 1) + " element:");
                printProduct(products[i], compareType);
            }
        }
        else if (length <= 1000)
        {
        	System.out.printf("%-20s", "First element is:");
            printProduct(products[0], compareType);

            for (int i = 99; i < length - 1; i += 100)
            {
            	System.out.printf("%-20s", (i + 1) + "-th element:");
                printProduct(products[i], compareType);
            }

            System.out.printf("%-20s", "Last element is:");
            printProduct(products[length - 1], compareType);
        }
        else
        {
        	System.out.printf("%-20s", "First element is:");
            printProduct(products[0], compareType);

            for (int i = 999; i < length - 1; i += 1000)
            {
            	System.out.printf("%-20s", (i + 1) + "-th element:");
                printProduct(products[i], compareType);
            }

            System.out.printf("%-20s", "Last element is:");
            printProduct(products[length - 1], compareType);
        }
    }

    private static void printProduct(Product product, char compareType)
    {
        System.out.printf("%-20s", product.getClass().getName());

        switch (compareType)
        {
            case 'o':
                System.out.println(
                    "Original Price: " + product.getOriginalPrice()
                );
                break;

            case 'u':
                System.out.println(
                    "Updated Price: " + product.getUpdatedPrice()
                );
                break;

            case 'd':
                System.out.println(
                    "Real Difference: " + product.calcRealDiff()
                );
                break;
        }
    }

    private static String getSuffix(int number)
    {
        if (number == 1)
        {
            return "-st";
        }
        else if (number == 2)
        {
            return "-nd";
        }
        else if (number == 3)
        {
            return "-rd";
        }
        else
        {
            return "-th";
        }
    }
    
    private static void sortProducts(Product[] products, Comparator<Product> comp, char algorithm) {
        switch (algorithm) {
            case 'b': BubbleSort.sort(products, comp);    break;
            case 's': SelectionSort.sort(products, comp); break;
            case 'i': InsertionSort.sort(products, comp); break;
            case 'm': MergeSort.sort(products, comp);     break;
            case 'q': QuickSort.sort(products, comp);     break;
            case 'z': HeapSort.sort(products, comp);      break;
        }
    }
    
    private static Comparator<Product> getComparator(char compareType) {
        Comparator<Product> comp;
        switch (compareType) {
            case 'o': comp = Comparator.comparingDouble(Product::getOriginalPrice); break;
            case 'u': comp = Comparator.comparingDouble(Product::getUpdatedPrice);  break;
            default:  comp = Comparator.comparingDouble(Product::calcRealDiff);     break;
        }
        return comp;
    }
    
    private static String getAlgorithmName(char algorithm) {
        switch (algorithm) {
            case 'b': return "Bubble Sort";
            case 's': return "Selection Sort";
            case 'i': return "Insertion Sort";
            case 'm': return "Merge Sort";
            case 'q': return "Quick Sort";
            case 'z': return "Heap Sort";
            default:  return "Unknown Sort";
        }
    }
}