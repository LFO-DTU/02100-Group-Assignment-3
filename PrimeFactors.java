import java.util.Scanner;

public class PrimeFactors
{
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        
        System.out.print("Enter integer greater than 1 (0 to terminate): ");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            //

            if (scanner.hasNextLong()) { 
                long input = scanner.nextLong();    
                if(input<=0)
                {
                    System.out.println("Exiting");
                    break;
                }
                System.out.println();
                Convert(input);
                System.out.println();

            } else {

                scanner.next(); // spring token over
            
            }
            
            System.out.print("Enter integer greater than 1 (0 to terminate): ");

        }

        scanner.close();    
    }

    public static void Convert(long n)
    {
        System.out.print("List of prime factors: ");
        while (n % 2 == 0) {
            System.out.print(2 + ", ");
            n /= 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                System.out.print(i + ", ");
                n /= i;
            }
        }

        if (n > 2){
            System.out.print(n);
        }

    }

}
