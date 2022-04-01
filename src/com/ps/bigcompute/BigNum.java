package com.ps.bigcompute;

import java.util.Vector;

public class BigNum{

    private int radix; /* 基数 */
    private int[] data; /* 数据段 */
    private boolean signal; /* 符号位 */
    private int length;

    BigNum(){
        this.radix = 10;
        this.data = null;
        this.signal = true;
        this.length = 0;
    }
    BigNum(int[] num, boolean p, int b){
        this.setradix(b);
        this.setData(num, p);
        this.length = this.data == null ? 0 : data.length;
    }
    public void setData(int[] a,boolean p){
        for(int i = 0; i < a.length; i++){
            if( a[i]>=this.radix){
                System.out.println("Illeagle data! Each digit shoulde be under radix and positive.");
                return;
            }
        }
        this.signal = p;
        this.data = Num.skipFrontedZero(a); /* 先去除头部的零 */
        this.length = this.data.length;
    }
    public void setsignal(boolean p){
        this.signal = p;
    }
    public void setradix(int m){
        this.radix = m;
    }
    public int[] getData(){
        return this.data;
    }
    public boolean isPositive(){
        return this.signal;
    }
    public int getradix(){
        return this.radix;
    }
    public int getLength(){
        return this.length;
    }
    
    public static BigNum str2bigNum(String s, int radix) {
		if (radix > 36 || radix < 2 || s == null) {
			System.out.println("Can't tranform String to BigNum.");
			return null;
		}
		boolean signal;
		int i = 0;
		if (s.charAt(i) == '-') {
			signal = false;
			i++;
		}
		else if (s.charAt(i) == '+') {
			signal = true;
			i++;
		}
		else {
			signal = true;
		}
		for (; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				break;
			}
		}
		int length = s.length() - i;
		int[] bi_data = new int[length];
		for (length--; i < s.length(); i++, length--) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				bi_data[length] = c-'0';
			}
			else {
				bi_data[length] = c-'A'+10;
			}
		}
		// if (!checkLegalNum(bi_data, radix)) {
		// 	System.out.println("Can't tranform String to BigNum.");
		// }
		return new BigNum(bi_data, signal, radix);
	}

/*  n: 数字的输出位数
    c: 填充空位的字符 
*/
    public void printBigNum(int n ,char c){
        if(this.getData() == null){
            System.out.println("Null!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }
        if(n < this.getLength()){
            System.out.println("Several highest bits won't be shown.");
        }
        System.out.print(this.isPositive()?"+":"-");
        for(int i = this.getLength()>=n ? n-1 : this.getLength()-1; i>=0; i--){
            System.out.print(this.getData()[i]);
        }        
        for(int i = 0; i < n-this.getLength(); i++){
            System.out.print(c);
        }
        System.out.println("     (whose radix is "+ this.getradix()+")" );
    }

    public void printBigNum(){
        /* print big number in default */
        if(this.getData() == null){
            System.out.println("Null!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }
        int n = 32; /* 默认打印长度 */
        if(n < this.getLength()){
            System.out.println("Several highest bits won't be shown.");
        }
        System.out.print(this.isPositive()?"+":"-");
        for(int i = this.getLength()>=n ? n-1 : this.getLength()-1; i>=0; i--){
            System.out.print(this.getData()[i]);
        }        
        for(int i = 0; i < n-this.getLength(); i++){
            System.out.print(' ');
        }
        System.out.println("     (whose radix is "+ this.getradix()+")" );
    }

    public static BigNum bigPlus(BigNum a, BigNum b){
        if(a.getradix()!=b.getradix()){
            System.out.println("Different radix!");
            return null;
        }
        BigNum result = new BigNum();
        result.setradix(a.getradix());    
        if((a.isPositive()&&b.isPositive()) || (!a.isPositive() && !b.isPositive()) ){
            /* 因为符号位和数据位分开计算，所以需要先判断是否符号相同。符号相同则可以直接相加 */
            boolean p = a.isPositive()?true:false;
            result.setData( Num.skipFrontedZero(Num.plus(a.data, b.data, result.radix)), p );
        }
        else if( (a.isPositive()&& ! b.isPositive()) || (!a.isPositive() && b.isPositive()) ){
            int[] pArray = a.isPositive()?a.getData():b.getData();
            int[] nArray = a.isPositive()?b.getData():a.getData();
            if(Num.compare(pArray, nArray)==1){
                /* 两个数相等，直接返回零 */
                result.setData( Num.skipFrontedZero(Num.sub(pArray, nArray, result.radix)),true );
            }
            else if(Num.compare(pArray, nArray)==-1){
                result.setData( Num.skipFrontedZero(Num.sub(nArray, pArray, result.radix)),false );
            }
            else if(Num.compare(a.getData(), b.getData())==0){
                int[] tmp = new int[1];
                tmp[0] = 0;
                result.setData(tmp, true);
            }
        }
        return result;
    }

    public static BigNum bigSub(BigNum a, BigNum b){
        /* big numbers' substract */
        if(a.getradix()!=b.getradix()){
            System.out.println("Different radix!");
            return null;
        }
        BigNum result = new BigNum();
        result.setradix(a.getradix());    
        if(a.isPositive() && b.isPositive()){
            if(Num.compare(a.getData(),b.getData())==1){
                result.setData(Num.skipFrontedZero(Num.sub(a.getData(),b.getData(),result.getradix())), true);
            }
            else if(Num.compare(a.getData(),b.getData())==-1){
                result.setData(Num.skipFrontedZero((Num.sub(b.getData(),a.getData(),result.getradix()))), false);
            }
            else if(Num.compare(a.getData(),b.getData())==0){
                int[] tmp = new int[1];
                tmp[0] = 0;
                result.setData(tmp, true);
            }
        }
        else if(!a.isPositive() && !b.isPositive()){
            if(Num.compare(a.getData(),b.getData())==1){
                result.setData(Num.skipFrontedZero(Num.sub(a.getData(),b.getData(),result.getradix())), false);
            }
            else if(Num.compare(a.getData(),b.getData())==-1){
                result.setData(Num.skipFrontedZero(Num.sub(b.getData(),a.getData(),result.getradix())), true);
            }
            else if(Num.compare(a.getData(),b.getData())==0){
                int[] tmp = new int[1];
                tmp[0] = 0;
                result.setData(tmp, true);
            }
        }
        else if(!a.isPositive() && b.isPositive()){
            result.setData(Num.skipFrontedZero(Num.plus(a.getData(), b.getData(), result.getradix())), false);
        }
        else if(a.isPositive() && !b.isPositive()){
            result.setData(Num.skipFrontedZero(Num.plus(a.getData(), b.getData(), result.getradix())), true);
        }
        return result;
    }

    public static BigNum bigMult(BigNum a, BigNum b){
        if(a.getradix()!=b.getradix()){
            System.out.println("Different radix!");
            return null;
        }
        BigNum result = new BigNum();
        result.setradix(a.getradix());    
        result.setData(Num.skipFrontedZero(Num.mult(a.getData(), b.getData(), a.getradix())), (a.isPositive()&&b.isPositive() || (!a.isPositive()&& !b.isPositive()) ?true:false));
        return result;
    }
     
    public static BigNum[] bigDivise(BigNum a, BigNum b){
        /* [0] 代表商，[1]代表余数 */
        if(a.getradix() != b.getradix()){
            System.out.println("Different radix!");
            return null;
        }
        if(Num.compare(b.getData(),Num.ZERO) == 0){ 
            System.out.println("Dividend cannot be zero!");
            return null;
        }
        BigNum[] result = new BigNum[2];
        result[0] = new BigNum();
        result[1] = new BigNum();
        result[0].setradix(a.getradix());
        result[1].setradix(a.getradix());

        if(Num.compare(a.getData(),Num.ZERO) == 0){
            //if a == 0 then q=0,r=0
            result[0].setData(Num.ZERO, true);
            result[1].setData(Num.ZERO, true);
        }
        
        int[] r = new int[ b.getData().length];
        int[] q = Num.divise(a.getData(), b.getData(), r, a.getradix());
        
        if(a.isPositive() && b.isPositive()){
            //a>0,b>0 then Q = q, R = r
            result[0].setData(q, true);
            result[1].setData(r, true);
        }
        else if(a.isPositive() && !b.isPositive()){
            //a>0,b<0, Q = -q, R = r
            result[0].setData(q, false);
            result[1].setData(r, true);
        }
        else if(!a.isPositive() && b.isPositive()){
            //a<0,b>0            
            if(Num.compare(r, Num.ZERO)!=0){
                //if r!=0 then Q = -(q+1), R = b-r
                result[0].setData(Num.plus(q, Num.ONE, a.getradix()), false);
                result[1].setData(Num.sub(b.getData(),r,a.getradix()), true);    
            }
            else{
                //if r==0 then Q = -q, R = 0                
                result[0].setData(q, false);
                result[1].setData(Num.ZERO, true);
            }
        }      
        else if(!a.isPositive() && !a.isPositive()){
            //a<0,b<0
            if(Num.compare(r, Num.ZERO)!=0){
                //if r!=0 then Q = q+1, R = (-b)-r
                result[0].setData(Num.plus(q, Num.ONE, a.getradix()), true);
                result[1].setData(Num.sub(b.getData(),r,a.getradix()), true);    
            }
            else{
                //if r==0 then Q = q, R = 0                
                result[0].setData(q, true);
                result[1].setData(Num.ZERO, true);
            }
        }
        return result;
    }

    public static BigNum bigMod(BigNum a, BigNum b) {
        /* return a mod b */
        if(a.getradix() != b.getradix()){
            System.out.println("Different radix!");
            return null;
        }
        BigNum result = new BigNum();
        result.setradix(a.getradix());    

        if(Num.compare(a.getData(),Num.ZERO)==0){
            //if a == 0 then q=0,r=0
            result.setData(Num.ZERO, true);
            return result;
        }
        int[] r = Num.mod(a.getData(), b.getData(), a.getradix());
        
        if(a.isPositive()){
            //if a>0 then R = r
            result.setData(r, true);
        }
        else if(!a.isPositive() && b.isPositive()){
            //a<0,b>0            
            if(Num.compare(r, Num.ZERO)!=0){
                //if r!=0 then Q = -(q+1), R = b-r
                result.setData(Num.sub(b.getData(),r,a.getradix()), true);    
            }
            else{
                //if r==0 then Q = -q, R = 0                
                result.setData(Num.ZERO, true);
            }
        }      
        else if(!a.isPositive() && !a.isPositive()){
            //a<0,b<0
            if(Num.compare(r, Num.ZERO)!=0){
                //if r!=0 then Q = q+1, R = (-b)-r
                result.setData(Num.sub(b.getData(),r,a.getradix()), true);    
            }
            else{
                //if r==0 then Q = q, R = 0                
                result.setData(Num.ZERO, true);
            }
        }
        return result;      
    }

    public BigNum transradix(int radix) {
		/* transpose big number in different radix */
		int r = radix;
		Vector<Integer> s = new Vector<Integer>();
		while (r > 0) {
			s.add(r % this.radix);
			r /= this.radix;
		}
		int[] radix_integer_data = new int[s.size()];
		for (int i = 0; i < s.size(); i++) {
			radix_integer_data[i] = s.elementAt(i);
		}
		s.clear();
		BigNum radix_Integer = new BigNum(radix_integer_data, true, this.radix);
		
		BigNum a = new BigNum(this.getData(), true, this.getradix());
		while (Num.compare(a.getData(), Num.ZERO) != 0) {
			BigNum[] bis = bigDivise(a, radix_Integer);
			a = bis[0];
			int k = 0;
			for (int i = 0; i < bis[1].length; i++) {
				k += bis[1].data[i] * (int)(Math.pow(this.radix, i));
			}
			s.add(k);
		}
		int[] transformed_data = new int[s.size()];
		for (int i = 0; i < s.size(); i++) {
			transformed_data[i] = s.elementAt(i);
        }
        return new BigNum(transformed_data, this.signal, radix);
	}

    public static BigNum indexMod(BigNum a,BigNum k,BigNum m) {
        /* 用连续平方乘方法求a^k mod m */
        if(k.getradix() != 2){
            k = k.transradix(2);
        }
        if(!a.isPositive()||!k.isPositive()||!m.isPositive()){
            System.out.println("All operating numbers should be positive.");
            return null;
        }
        BigNum result = new BigNum();
        result.setradix(a.getradix());
        result.setData(Num.squrMult(a.getData(), a.getradix(), m.getData(), k.getData()), true);
        return result;
    }

    public static BigNum randBigNum(BigNum bound) {
        /* generate random number */
        BigNum result = new BigNum();
        result.setradix(bound.getradix());
        result.setData(Num.randNum(bound.getData(), result.getradix()), true);
        return result;
    }
    
}