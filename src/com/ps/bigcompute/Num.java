package com.ps.bigcompute;
import java.util.Random;
public class Num{
    
    public static final int[] ZERO = new int[]{0};
    public static final int[] ONE = new int[]{1};

    public static int[] skipFrontedZero(int[] a){
        /* 去掉最开头的零 */
        int i;
        for(i = a.length - 1 ;i > 0; i--){
            if(a[i] != 0) break;
        }
        int[] result = new int[i+1];
        for(i = 0; i<result.length;i++){
            result[i] = a[i];
        }
        return result;
    }
    public static int compare(int[] a,int[] b) {
/*      比较两个大整数a,b
        a>b returns 1
        b>a returns -1
        a==b returns 0 */
        int i;
        if(a.length>=b.length){
            for( i = a.length - 1;i>=b.length;i--){
                if(a[i] > 0) return 1;
            }
        }
        else{
            for( i = b.length - 1; i >= a.length; i--){
                if(b[i] > 0) return -1;
            }
        }
    
        for(;i>=0 ;i--){
            if(a[i]>b[i]) return 1;
            else if(a[i]<b[i]) return -1;
        }
        return 0;
    }
    
    public static int[] plus (int[] a,int[] b,int m){
    
        int[] longer = a.length>b.length?a:b;
        int[] shorter = a.length>b.length?b:a;
        int c = 0;
        int[] result = new int[longer.length + 1];
    
        for(int i = 0;i < shorter.length ;i++ ){
            result[i] = a[i] + b[i] + c;
            c = 0;
            if(result[i]>=m) {
                c = 1;
                result[i] = result[i] - m;
            }
        }
        for(int i = shorter.length; i<longer.length; i++){
            result[i] = longer[i] + c;
            c = 0;
            if(result[i] >= m){
                 c = 1;
                 result[i] = result[i] - m;
            }
        }
        result[longer.length] = c;
    
        return result;
    }
    
    public static int[] sub (int[] a,int[] b,int m){
        /* 前提是 a > b */
        int c = 0;
        int[] result = new int[a.length];
        int i;
    
        if(a.length>b.length){
            for(i = 0;i < b.length ;i++ ){
                result[i] = a[i] - b[i] - c;
                c = 0;
                if(result[i] < 0) {
                    c = 1;
                    result[i] = result[i] + m;
                }
            }
            for(i = b.length; i < a.length; i++){
                result[i] = a[i] - c;
                c = 0;
                if(result[i] < 0){
                     c = 1;
                     result[i] = result[i] + m;
                }    
            }
        }
        else{
            for(i = 0; i < a.length; i++){
                result[i] = a[i] - b[i] - c;
                c = 0;
                if(result[i] < 0){
                     c = 1;
                     result[i] = result[i] + m;
                }    
            }
        }
        return result;
    }
    
    public static int[] mult(int[] a, int[] b, int m){
        /* multiply */
        int[] result = null;
        int i; //loop variable
        boolean allZero = true;
        for(i = 0;i < a.length; i++){
            if(a[i] != 0){
                allZero = false;
            }       
        }
        for(i = 0;i<b.length;i++){
            if(b[i] != 0){
                allZero = false;
            }       
        }
        if(allZero){
            result = new int[1];
            result[0] = 0;
            return result;    
        }
        result = new int[a.length + b.length + 2];
        int[] tmp = new int[a.length + 1];
        for(i = 0; i < b.length; i++){
            //tmp = b[i]*a;
            int c = 0;
            int j;//loop variable
            for(j = 0;j<a.length;j++){
                tmp[j] = a[j] * b[i] + c;
                c = 0;
                if(tmp[j] >= m){
                    c = tmp[j] / m;
                    tmp[j] = tmp[j] % m;
                }
            }
            tmp[a.length] = c;
            c = 0;
            for(j = i;j<i+tmp.length;j++){
                result[j] = result[j] + tmp[j - i] + c;
                c = 0;
                if(result[j]>=m){
                    c = 1;
                    result[j] = result[j] - m;
                }
            }
            for(j = i+tmp.length; j<result.length; j++){
                result[j] = result[j] + c;
                c = 0;
                if(result[j] >= m){
                    c = 1;
                    result[j] = result[j] - m;
                }
            }
        }
        return result;
    }

    public static int[] divise(int[] a, int[] b, int[] r, int m){
        /* a: 除数, b: 被除数, r: 余数, m: 基数
            r.length = b.length 
         */
            if(compare(a, b)==-1){
                //if a<b then r = a,q = 0
                for(int i = 0;i<a.length;i++){
                    r[i] = a[i];
                }
                return new int[]{0};
            }
            else if(compare(a, b)==0){
                //if a==b then r=0,q = 1
                for(int i = 0; i<r.length;i++){
                    r[i] = 0;
                }
                return new int[]{1}; 
            }
            int i,j;//loop variables
            //x = a
            int[] x = new int[a.length];
            for( i = 0;i<a.length;i++){
                x[i] = a[i];
            }            
            int[] moved = new int[a.length];
            int[] q = new int[a.length - b.length + 1];
            //x = b*m^(a.length-b.length)
            for(i = b.length - 1; i >= 0; i--){
                moved[i + a.length - b.length] = b[i];
            }       
            for(i = a.length - b.length ; i >= 0; i--){
                for(j = 0; j < m; j++){
                    if(compare(x, moved) != -1){
                        //if x >= moved then x = x - moved
                        x = sub(x, moved, m);
                    }
                    else{
                        //x<moved
                        break;
                    }
                }
                q[i] = j;
                if(i == 0){
                    break;
                } 
                rightMove(moved,1);
            }
            for(i = 0; i<r.length;i++){
                r[i] = x[i];
            }
            return skipFrontedZero(q);
        } 
    


    public static int[] mod(int[] a, int[] b, int m) {
        //return a mod(b)
        if(compare(a, b)==-1){
            //if a<b then r = a
            int[] r = new int[a.length];
            for(int i = 0;i<a.length;i++){
                r[i] = a[i];
            }
            return r;
        }
        else if(compare(a, b) == 0){
            //if a==b then r=0
            return new int[]{0}; 
        }
        //x = a
        int[] x = new int[a.length];
        int i,j;//loop variables
        for(i = 0; i < a.length; i++){
            x[i] = a[i];
        }            
        int[] moved = new int[a.length];
        //x = b*m^(a.length-b.length)
        for(i = b.length - 1;i>=0; i--){
            moved[i + a.length - b.length] = b[i];
        }
        for(i = a.length - b.length ; i >= 0; i--){
            for(j = 0; j < m; j++){
                if(compare(x, moved) != -1){
                    //if x >= moved then x = x - moved
                    x = sub(x, moved, m);
                }
                else{
                    //x<moved
                    break;
                }
            }
            if(i == 0){
                break;
            }
            rightMove(moved,1);
        }
        return skipFrontedZero(x);
    } 

    public static boolean leftMove(int[] a,int n ){
        if(a == null){
            System.out.println("Error occurred during leftMove: input int array is null!");
            return false;
        }
        if(n<=0||n>=a.length){
            System.out.println("Error occurred during leftMove: moving step must be positive and less than array length!");
            return false;
        }
        int i;
        for(i = a.length - 1; i >= a.length - n; i--){
            if(a[i] != 0){
                System.out.println("leftMove: High numbers will be abandoned!");
                break;
            }
        }
        for(i = a.length - 1; i >= n; i--){
            a[i] = a[i - n];
        }
        for(i = n - 1; i >= 0; i--){
            a[i] = 0;
        }
        return true;
    }

    public static boolean rightMove(int[] a,int n ){
        if(a == null){
            System.out.println("Error occurred during rightMove: input int array is null!");
            return false;
        }
        if(n<=0||n>=a.length){
            System.out.println("Error occurred during rightMove: moving step must be positive and less than array length!");
            return false;
        }
        int i;
        for(i = n-1; i >= 0; i--){
            if(a[i] != 0){
                System.out.println("rightMove: Low numbers will be abandoned!");
                break;
            }
        }
        for(i = 0; i < a.length - n; i++){
            a[i] = a[i + n];
        }
        for(i = a.length - n; i < a.length; i++){
            a[i] = 0;
        }
        return true;
    }
    

    public static int[] squrMult(int[] a, int b, int[] m, int[] k) {
        //return (a^k)mod(m)
        if(compare(k,ZERO)==0){
            return new int[]{1};
        }
        else if(compare(k,ONE)==0){
            //if k = 1 then return a
            int[] result = new int[a.length];
            for(int i = 0;i<a.length;i++) result[i] = a[i];
            return result;
        }
        int[] result = new int[]{1};
        int[] A = a;
        for(int i = 1; i<k.length;i++){
            A = mod(mult(A,A,b),m,b);
            if(k[i] == 0){
                result = mod(mult(A,result,b),m,b);
            }
        }
        return result;
    }

    public static void prin(int[] a){
        for(int i = a.length-1 ;i>=0; i--){
            System.out.print(a[i]);
        }
        System.out.println();
    }
    
    public static int[] randNum(int[] bound ,int base) {

        Random rand = new Random();
        int len; //随机数位数[1,bound.length]
        len = bound[bound.length-1]>1 ? bound.length : bound.length - 1;
        len = rand.nextInt(bound.length)+1;
        int[] result = new int[len];
        if(len == bound.length){
            result[len-1] = rand.nextInt(bound[bound.length-1]);
            for(int i = 0;i< len-1;i++){
                result[i] = rand.nextInt(base);
            }
        }
        else{
            result[len-1] = rand.nextInt(base-1)+1;
            for(int i = 0;i< len-1; i++){
                result[i] = rand.nextInt(base);
            }    
        }
        return result; 
    }
}