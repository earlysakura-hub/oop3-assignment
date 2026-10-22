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
import utilities.OriginalPriceComparator;
import utilities.UpdatedPriceComparator;
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
    
    /**
     * Loads product data from a text file and returns it as an array of
     * {@link Product} objects.
     * <p>
     * The file is expected to have the following format:
     * <ul>
     *   <li>The first line contains an integer: the number of products in the file.</li>
     *   <li>Each following line contains three whitespace-separated values:
     *       the product type, the original price, and the updated price
     *       (e.g., {@code Jeans 49.99 39.99}).</li>
     * </ul>
     * Supported product types are {@code Jeans}, {@code TShirts}, {@code Skirts},
     * {@code Dresses}, {@code Shorts}, {@code Jackets}, and {@code Shoes}.
     * <p>
     * If the file cannot be found, is malformed, or contains an unrecognized
     * product type, an error message is printed to standard output and
     * {@code null} is returned.
     *
     * @param fileName the path to the input file containing the product data
     * @return an array of {@code Product} objects in the order they appear in the
     *         file, or {@code null} if the file was not found, the input was
     *         malformed (missing lines, missing fields, or non-numeric values),
     *         or an invalid product type was encountered
     */
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
    
    /**
     * Prints a summary of the given product array to standard output, showing
     * a different number of elements depending on the size of the array.
     * <ul>
     *   <li>If the array has 10 or fewer elements, every element is printed,
     *       labeled with its ordinal position (e.g., "1st element:",
     *       "2nd element:").</li>
     *   <li>If the array has between 11 and 1000 elements, the first element,
     *       every 100th element (100th, 200th, ...), and the last element
     *       are printed.</li>
     *   <li>If the array has more than 1000 elements, the first element,
     *       every 1000th element (1000th, 2000th, ...), and the last element
     *       are printed.</li>
     * </ul>
     * Each line begins with a label left-aligned in a 20-character field.
     * The product details are printed by
     * {@link #printProduct(Product, char)}.
     *
     * @param products    the array of products to display; must contain at
     *                    least one element
     * @param compareType a character indicating which comparison criterion is
     *                    used when printing each product; passed through to
     *                    {@code printProduct}
     * @throws ArrayIndexOutOfBoundsException if {@code products} is empty
     *         (the larger-array branches access {@code products[0]})
     * @throws NullPointerException if {@code products} is {@code null}
     */
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

    /**
     * Prints a single product's class name followed by one price-related value,
     * selected by the given comparison type.
     * <p>
     * The line begins with the product's fully qualified class name, left-aligned
     * in a 20-character field. It then continues with one of the following,
     * depending on {@code compareType}:
     * <ul>
     *   <li>{@code 'o'}: the original price ({@link Product#getOriginalPrice()})</li>
     *   <li>{@code 'u'}: the updated price ({@link Product#getUpdatedPrice()})</li>
     *   <li>{@code 'd'}: the real difference ({@link Product#calcRealDiff()})</li>
     * </ul>
     * If {@code compareType} is any other character, only the class name is
     * printed, with no value and no trailing line break.
     *
     * @param product     the product to print; must not be {@code null}
     * @param compareType a character selecting which value to print:
     *                    {@code 'o'} for original price, {@code 'u'} for updated
     *                    price, or {@code 'd'} for real difference
     * @throws NullPointerException if {@code product} is {@code null}
     */
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

    /**
     * Returns the ordinal suffix for the given number, including a leading hyphen.
     * <p>
     * The suffix is {@code "-st"} for 1, {@code "-nd"} for 2, {@code "-rd"} for 3,
     * and {@code "-th"} for every other value. For example, the result for 1 is
     * {@code "-st"} and the result for 4 is {@code "-th"}.
     * <p>
     * Note that this method only special-cases the values 1, 2, and 3. It does
     * not handle English ordinals such as 21 ("21st"), 22 ("22nd"), 23 ("23rd"),
     * or 101 ("101st"), which all receive {@code "-th"}.
     *
     * @param number the number to get the suffix for
     * @return the ordinal suffix, one of {@code "-st"}, {@code "-nd"},
     *         {@code "-rd"}, or {@code "-th"}
     */
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
    
    /**
     * Sorts the given array of products in place using the selected sorting
     * algorithm and comparator.
     * <p>
     * The algorithm is chosen by {@code algorithm}:
     * <ul>
     *   <li>{@code 'b'}: bubble sort ({@link BubbleSort})</li>
     *   <li>{@code 's'}: selection sort ({@link SelectionSort})</li>
     *   <li>{@code 'i'}: insertion sort ({@link InsertionSort})</li>
     *   <li>{@code 'm'}: merge sort ({@link MergeSort})</li>
     *   <li>{@code 'q'}: quick sort ({@link QuickSort})</li>
     *   <li>{@code 'z'}: heap sort ({@link HeapSort})</li>
     * </ul>
     * If {@code algorithm} is any other character, the array is left unchanged.
     *
     * @param products  the array of products to sort; modified in place
     * @param comp      the comparator that defines the desired ordering of
     *                  products
     * @param algorithm a character selecting the sorting algorithm to use
     */
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
    
    /**
     * Returns a {@link Comparator} that orders products by the value selected
     * by the given comparison type, in ascending order.
     * <p>
     * The ordering is chosen by {@code compareType}:
     * <ul>
     *   <li>{@code 'o'}: by original price ({@link Product#getOriginalPrice()})</li>
     *   <li>{@code 'u'}: by updated price ({@link Product#getUpdatedPrice()})</li>
     *   <li>any other character: by real difference
     *       ({@link Product#calcRealDiff()}), which is the default</li>
     * </ul>
     *
     * @param compareType a character selecting the value to compare:
     *                    {@code 'o'} for original price, {@code 'u'} for updated
     *                    price; any other value selects real difference
     * @return a comparator that sorts {@code Product} objects in ascending order
     *         by the selected value; never {@code null}
     */
    private static Comparator<Product> getComparator(char compareType) {
        Comparator<Product> comp;
        switch (compareType) {
            case 'o': comp = new OriginalPriceComparator(); break;
            case 'u': comp = new UpdatedPriceComparator();  break;
            default:  comp = Comparator.comparingDouble(Product::calcRealDiff);     break;
        }
        return comp;
    }
    
    /**
     * Returns the human-readable name of the sorting algorithm selected by the
     * given character code.
     * <p>
     * The mapping is:
     * <ul>
     *   <li>{@code 'b'}: {@code "Bubble Sort"}</li>
     *   <li>{@code 's'}: {@code "Selection Sort"}</li>
     *   <li>{@code 'i'}: {@code "Insertion Sort"}</li>
     *   <li>{@code 'm'}: {@code "Merge Sort"}</li>
     *   <li>{@code 'q'}: {@code "Quick Sort"}</li>
     *   <li>{@code 'z'}: {@code "Heap Sort"}</li>
     * </ul>
     * Any other character yields {@code "Unknown Sort"}.
     *
     * @param algorithm a character code identifying a sorting algorithm
     * @return the display name of the algorithm, or {@code "Unknown Sort"} if
     *         the code is not recognized; never {@code null}
     */
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
