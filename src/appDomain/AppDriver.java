package appDomain;

import products.Product;

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

        /*
         * Temporary testing output.
         * These lines can be removed later when the file reading
         * and sorting parts are connected.
         */
        System.out.println("File: " + fileName);
        System.out.println("Compare type: " + compareType);
        System.out.println("Sorting algorithm: " + sortAlgorithm);
    }

    private static void displayResults(Product[] products, char compareType)
    {
        int length = products.length;

        if (length <= 10)
        {
            for (int i = 0; i < length; i++)
            {
                System.out.print((i + 1) + getSuffix(i + 1) + " element:\t");
                printProduct(products[i], compareType);
            }
        }
        else if (length <= 1000)
        {
            System.out.print("First element is:\t");
            printProduct(products[0], compareType);

            for (int i = 99; i < length - 1; i += 100)
            {
                System.out.print((i + 1) + "-th element:\t");
                printProduct(products[i], compareType);
            }

            System.out.print("Last element is:\t");
            printProduct(products[length - 1], compareType);
        }
        else
        {
            System.out.print("First element is:\t");
            printProduct(products[0], compareType);

            for (int i = 999; i < length - 1; i += 1000)
            {
                System.out.print((i + 1) + "-th element:\t");
                printProduct(products[i], compareType);
            }

            System.out.print("Last element is:\t");
            printProduct(products[length - 1], compareType);
        }
    }

    private static void printProduct(Product product, char compareType)
    {
        System.out.print(product.getClass().getName() + "\t");

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
}
