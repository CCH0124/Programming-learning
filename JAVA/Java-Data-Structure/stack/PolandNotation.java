package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}

public class PolandNotation {

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String el : split) {
            list.add(el);
        }

        return list;
    }

    public static double calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String ele : list) {
            if (ele.matches("[0-9]+") || ele.matches("[0-9]+\\.[0-9]+")) {
                stack.push(ele);
            } else {
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                double res = 0;
                switch (ele) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("error");
                }

                stack.push(Double.toString(res));
            }
        }
        return Double.parseDouble(stack.pop());
    }

    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        while (i < s.length()) {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(String.valueOf(c));
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }
        return ls;
    }
    /**
     * 小數點
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList2(String s) {
        List<String> ls = new ArrayList<>();
        String delimiters = "+-*/()";
        StringTokenizer st = new StringTokenizer(s, delimiters, true);
        String ch;
        while (st.hasMoreTokens()) {
            ch = st.nextToken();
            ls.add(ch);
        }
        return ls;
    }

    public static List<String> parseSuffixExpressionList(List<String> expression) {
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<>();
        for (String ele : expression) {
            if (ele.matches("[0-9]+") || ele.matches("[0-9]+\\.[0-9]+")){
                s2.add(ele);
            } else if (ele.equals("(")) {
                s1.push(ele);
            } else if (ele.equals(")")){
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(ele)) {
                    s2.add(s1.pop());
                }
                s1.push(ele);
            }
        }

        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }
    
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 - ";
        List<String> list = getListString(suffixExpression);
        System.out.println(calculate(list));
        System.out.println("---------------------------------------------------------------------------------------");
        String expression = "1+((2.5+3.5)*4)-5";
        List<String> ls = toInfixExpressionList2(expression);
        System.out.println(ls);
        System.out.println("---------------------------------------------------------------------------------------");
        List<String> ls2 = parseSuffixExpressionList(ls);
        System.out.println(ls2); 
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(calculate(ls2));
    }

}