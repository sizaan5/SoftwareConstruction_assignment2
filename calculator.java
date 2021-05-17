package cal1;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cal1 {
    public static Character[] OPERATORS = {
        '/',
        '*',
        '+',
        '-'
    };

    public static final String REGEXOPERATORS = "[/+,-,/*,//,-]";
    public static final String REGEXDIGITS = "(\\d+)";

    public static ArrayList < Character > operators = new ArrayList < Character > ();
    public static ArrayList < Integer > digits = new ArrayList < Integer > ();

    public Cal1() {
        System.out.println("Constructor called");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an Equation: ");
        String math = sc.nextLine();
        getDigits(math);
        getOperators(math);
        getNextOperator(operators);

        System.out.print("Result is : ");

        Iterator < Integer > i = digits.iterator();

        while (i.hasNext()) {
            System.out.print(String.valueOf(i.next()) + ' ');
        }
        System.out.println();

        Iterator < Character > z = operators.iterator();
        while (z.hasNext()) {

            System.out.print(z.next());
        }
        //System.out.println(operators.size());
    }

    private static void getNextOperator(ArrayList < Character > operators) {
        for (Character op: OPERATORS) {

            A: for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '/') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) / digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                    continue A;
                }
            }

            B: for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '*') {
                    //                    System.out.println("if in for");
                    operators.remove(i);
                    digits.set(i, (digits.get(i) * digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                    continue B;
                }
            }

            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '+') {
                    //                System.out.println("if in for");
                    operators.remove(i);
                    digits.set(i, (digits.get(i) + digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                    continue;
                }
            }
            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '-') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) - digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                    continue;
                }
            }

        }

    }

    public static void getDigits(String math) {
        //        System.out.println("Getting digits ...");

        Pattern r = Pattern.compile(REGEXDIGITS);
        Matcher m = r.matcher(math);
        while (m.find()) {

            int t = Integer.parseInt(math.substring(m.start(), m.end()));

            digits.add(t);
        }
        //        System.out.println("\rFinished getting digits...");
    }


    public static void getOperators(String math) {
        //        System.out.println("Getting Operators..");
        Pattern r = Pattern.compile(REGEXOPERATORS);
        Matcher m = r.matcher(math);
        while (m.find()) {
            operators.add(math.charAt(m.start()));
        }
        //        System.out.println("Finished getting Operators..");
    }
}
