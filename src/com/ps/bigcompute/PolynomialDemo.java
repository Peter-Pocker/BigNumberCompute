package com.ps.bigcompute;

import java.util.Scanner;

public class PolynomialDemo {
    public static void main(String args[]){
        String s1 = "10111011";
        String s2 = "00101111";
        String s3 = "00001110";
        Polynomial p1;
        Polynomial p2;
        Polynomial duv;
        Polynomial ansMultMod;
        Polynomial m;
        
        System.out.println("---Below is an automatic demonstration.---");
        System.out.println("----------------EXAMPLE 1-----------------");
        p1 = Polynomial.str2Poly(s1);
        p2 = Polynomial.str2Poly(s2);
        m = Polynomial.str2Poly(s3);
        duv = Polynomial.inverseElement(p1, p2);
        ansMultMod = Polynomial.polyMultMod(p1, p2, m);
        
        System.out.print("Polynomial 1:          ");
        p1.printPoly();
        System.out.print("Polynomial 2:          ");
        p2.printPoly();
        System.out.print("mod polynomial:        ");
        m.printPoly();
        System.out.print("poly1 * polly2 mod m = ");
        ansMultMod.printPoly();
        System.out.print("f9x) inverse element = ");
        duv.printPoly();
        System.out.println("------Automatic demonstration ends.-------");
        
        System.out.println("-------------Manual Demonstration---------");
        System.out.println("Please input your first polynomial:  ");
        Scanner sc = new Scanner(System.in);
        String f1 = sc.nextLine();
        p1 = Polynomial.str2Poly(f1.replace(" ", ""));
        System.out.println("Please input your second polynomial: ");
        f1 = sc.nextLine();
        p2 = Polynomial.str2Poly(f1.replace(" ", ""));
        System.out.println("Please input the mod polynomial: ");
        f1 = sc.nextLine();
        m = Polynomial.str2Poly(f1.replace(" ", ""));        
        //sc.close();
        
        duv = Polynomial.inverseElement(p1, p2);
        ansMultMod = Polynomial.polyMultMod(p1, p2, m);
        
        System.out.print("Polynomial 1:          ");
        p1.printPoly();
        System.out.print("Polynomial 2:          ");
        p2.printPoly();
        System.out.print("mod polynomial:        ");
        m.printPoly();
        System.out.print("poly1 * polly2 mod m = ");
        ansMultMod.printPoly();
        System.out.print("f(x) inverse element = ");
        duv.printPoly();
        System.out.println("Show index logarithm table ? (input 1 to show)");
      
        if(sc.hasNext()) {
            try {
                int test = sc.nextInt();
                if(test == 1) {
                    Table.printTable();  
                } 
            } catch (Exception e) {}
        }
        sc.close();
            
    }
}
