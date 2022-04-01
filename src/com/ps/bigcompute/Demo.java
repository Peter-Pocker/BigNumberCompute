package com.ps.bigcompute;

public class Demo{
    public static void main(String[] args){
        //EXAMPLE1
        System.out.println("------------Example1-----------");

        int base = 10;
        String a = "+60451235456220001";
        String b = "-30152002";
        BigNum num1 = BigNum.str2bigNum(a,base);
        BigNum num2 = BigNum.str2bigNum(b,base);
        BigNum ansPlus = BigNum.bigPlus(num1, num2);
        BigNum ansSub = BigNum.bigSub(num1, num2);
        BigNum ansMult = BigNum.bigMult(num1, num2);
        BigNum[] ansDivise = BigNum.bigDivise(num1, num2);
        BigNum ansMod = BigNum.bigMod(num1, num2);

        System.out.print("a=        ");
        num1.printBigNum();
        System.out.print("b=        ");
        num2.printBigNum();
        System.out.print("a + b   = ");       
        ansPlus.printBigNum();
        System.out.print("a - b   = ");       
        ansSub.printBigNum();
        System.out.print("a * b   = ");       
        ansMult.printBigNum();
        System.out.print("a / b   = ");       
        ansDivise[0].printBigNum();
        System.out.print("a mod b = ");
        ansDivise[1].printBigNum();
        System.out.print("a mod(b)= ");
        ansMod.printBigNum();

        //EXAMPLE2
        System.out.println("------------Example2-----------");
        base = 2;
        a = "+10111011111110001";
        b = "+10111101";
        num1 = BigNum.str2bigNum(a,base);
        num2 = BigNum.str2bigNum(b,base);
        ansPlus = BigNum.bigPlus(num1, num2);
        ansSub = BigNum.bigSub(num1, num2);
        ansMult = BigNum.bigMult(num1, num2);
        ansDivise = BigNum.bigDivise(num1, num2);
        ansMod = BigNum.bigMod(num1, num2);

        System.out.print("a=        ");
        num1.printBigNum();
        System.out.print("b=        ");
        num2.printBigNum();
        System.out.print("a + b   = ");       
        ansPlus.printBigNum();
        System.out.print("a - b   = ");       
        ansSub.printBigNum();
        System.out.print("a * b   = ");       
        ansMult.printBigNum();
        System.out.print("a / b   = ");
        ansDivise[0].printBigNum();
        System.out.print("a mod b = ");
        ansDivise[1].printBigNum();
        System.out.print("a mod(b)= ");
        ansMod.printBigNum();

        // BigNum rand1 = BigNum.randBigNum(num2);
        // System.out.print("rand1 =    ");
        // rand1.printBigNum();
        // BigNum ansSquare = BigNum.indexMod(num1,rand1,num2);
        // System.out.print("a^rand1 mod b =    ");
        // ansSquare.printBigNum();

        //EXAMPLE3
        System.out.println("------------Example3-----------");
        base = 8;
        a = "+456212721233002";
        b = "-1257523315";
        num1 = BigNum.str2bigNum(a,base);
        num2 = BigNum.str2bigNum(b,base);
        ansPlus = BigNum.bigPlus(num1, num2);
        ansSub = BigNum.bigSub(num1, num2);
        ansMult = BigNum.bigMult(num1, num2);
        ansDivise = BigNum.bigDivise(num1, num2);
        ansMod = BigNum.bigMod(num1, num2);

        System.out.print("a=        ");
        num1.printBigNum();
        System.out.print("b=        ");
        num2.printBigNum();
        System.out.print("a + b   = ");       
        ansPlus.printBigNum();
        System.out.print("a - b   = ");       
        ansSub.printBigNum();
        System.out.print("a * b   = ");       
        ansMult.printBigNum();
        System.out.print("a / b   = ");
        ansDivise[0].printBigNum();
        System.out.print("a mod b = ");
        ansDivise[1].printBigNum();
        System.out.print("a mod(b)= ");
        ansMod.printBigNum();


    }
}