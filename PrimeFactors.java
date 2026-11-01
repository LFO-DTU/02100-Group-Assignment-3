import java.util.Scanner;

public class PrimeFactors
{
    public static void main(String[] args)
    {
       
        
        System.out.print("Enter integer greater than 1 (0 to terminate): ");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            //Loop. Læs input og send til Primfaktor funktion

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
        //tjek om n går op med 2, divider og print 
        while (n % 2 == 0) {
            System.out.print(2 + ", ");
            n /= 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            //tjek i går op i n, divider og print 
            while (n % i == 0) {
                System.out.print(i + ", ");
                n /= i;
            }
        }
            //print resten, da n nu må være et primtal
        if (n > 2){
            System.out.print(n);
        }

    }

}
