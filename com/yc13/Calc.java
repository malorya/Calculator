package com.yc13;

public class Calc extends Compf {
    private StackInt s;
    private static int char2int(char c)
    {
        if (c >= '0' && c <= '9')
            return (int)c - (int)'0';
        else {
            switch (c)
            {
                case 'A':
                case 'a':
                    return 10;
                case 'B':
                case 'b':
                    return 11;
                case 'C':
                case 'c':
                    return 12;
                case 'D':
                case 'd':
                    return 13;
                case 'E':
                case 'e':
                    return 14;
                case 'F':
                case 'f':
                    return 15;
                default:
                    return 0;
            }
        }
    }
    protected int symOther(char c) {
        if ((c < '0' || c > '9') && (c < 'A' || c >'F') && (c < 'a' || c > 'f')) {
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }
        return SYM_OTHER;
    }
    protected void nextOper(char c) {
        int second = s.pop();
        int first = s.pop();
        switch (c) {
            case '+':
                s.push(first + second); break;
            case '-':
                s.push(first - second); break;
            case '*':
                s.push(first * second); break;
            case '/':
                s.push(first / second); break;
        }
    }
    protected void nextOther(char c) {
        if (NUM) {
            int number = ((s.pop()) * 16) + char2int(c);
            s.push(number);
            NUM = true;
        } else {
            s.push(char2int(c));
            NUM = true;
        }
    }
    public Calc()
    {
        s = new StackInt();
    }
    public final void compile(char[] str)
    {
        super.compile(str);
        System.out.println("" + s.top());
        NUM = false;
    }
}