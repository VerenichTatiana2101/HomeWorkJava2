import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class calculator {
    public static void main(String[] args) throws SecurityException, IOException {
        Logger logger = Logger.getLogger(calculator.class.getName());
        FileHandler fh = new FileHandler("calcul.log");
    
        logger.addHandler(fh); // что куда сохр
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            System.out.print("\033[H\033[2J");
            choice();
    
            int userChoice = userCh(5);
            if (userChoice > 0 && userChoice < 5) {
                double num1 = inputNum(userChoice);
                double num2 = inputNum(userChoice);
                logger.log(Level.INFO, "User entered {0} and {1}", new Object[]{num1, num2});
                System.out.println("Result: " + rec(userChoice, num1, num2));
            } else if (userChoice == 5) {
                double num3 = inputNum(userChoice);
                logger.log(Level.INFO, "User entered {0}", num3);
                System.out.println("Result: " + rootNum(num3));
            } else
                System.out.println("Wrong Choice");
            System.out.println();
    
            System.out.println("Do you want to continue? (y/n)");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("n")) {
                repeat = false;
            }
        }
        sc.close();
    }

    static void choice() {
        System.out.println("1 - addition ");
        System.out.println("2 - subtraction ");
        System.out.println("3 - multiplication");
        System.out.println("4 - division");
        System.out.println("5 - the root of a number");
    }

    static int userCh(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number from 1 to 5: ");
        while (!sc.hasNextInt()) {
            System.out.println("Error! Please enter a number: ");
            sc.next();
        }
        int userChoice = sc.nextInt();
        return userChoice;
    }

    static double inputNum(int userChoice) {
        Scanner sc = new Scanner(System.in);
        {
            System.out.println("Enter a number: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Error! Please enter a number: ");
                sc.next();
            }
            double number = sc.nextDouble();
            return number;
        }
    }

    static double rec(int userCh, double number1, double number2) {
        double result;
        switch (userCh) {
            case 1:
                result = sumNum(number1, number2);
                break;
            case 2:
                result = differenceNum(number1, number2);
                break;
            case 3:
                result = multiplicationNum(number1, number2);
                break;
            case 4:
                result = divisionNum(number1, number2);
                break;
            default:
                throw new IllegalArgumentException("Wrong Choice");
        }
        return result;
    }

    static double sumNum(double a, double b) {
        return a + b;
    }

    static double differenceNum(double a, double b) {
        return a - b;
    }

    static double multiplicationNum(double a, double b) {
        return a * b;
    }

    static double divisionNum(double a, double b) {
        return a / b;
    }

    static double rootNum(double a) {
        double temp;
        double squareRoot = a / 2;
        do {
            temp = squareRoot;
            squareRoot = (temp + (a / temp)) / 2;
        } while ((temp - squareRoot) != 0);
        return squareRoot;
    }
}
