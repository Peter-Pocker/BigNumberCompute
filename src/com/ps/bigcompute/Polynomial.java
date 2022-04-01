package com.ps.bigcompute;

public class Polynomial{
    private int[] frequency;
    private int degree;
    Polynomial(){
        this.frequency = null;
        this.degree = -1;
    }
    Polynomial(int[] a){
        if(!leagleAssign(a)){
            return;
        }
        this.frequency = skipFrontedZero(a);
        this.degree = this.frequency.length - 1;
    }
    public static boolean leagleAssign(int[] a){
        for(int i = 0; i < a.length; i++){
            if(a[i] != 0 && a[i] != 1){
                System.out.println("Illeagle assignment, each digit should be 0 or 1 !");
                return false;
            }
        }
        return true;
    }
    protected boolean setFrequency(int[] a){
        if(!leagleAssign(a)){
            return false;
        }
        this.frequency = skipFrontedZero(a);
        this.degree = this.frequency.length - 1;
        return true;
    }
    protected int[] getFrequency(){
        return this.frequency;
    }
    protected int getDegree(){
        return this.degree;
    }
    
    public void printPoly(){
        if(this.getFrequency() == null){
            System.out.println("Null!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }
        int n = 30;
        // System.out.print("Polynomial: ");
        if(n <= this.getDegree()){
            System.out.println("Several highest bits won't be shown.");
        }
        for(int i = this.getDegree() < n ? n - 1 : this.getDegree(); i > this.getDegree(); i--){
            System.out.print(' ');
        }        
        for(int i = this.getDegree(); i >= 0; i--){
            System.out.print(this.getFrequency()[i]);
        }
        System.out.println();
    }
    
    public Polynomial clone(){
        return new Polynomial(this.getFrequency());
    }
    
    public void printPoly(int n){
        if(this.getFrequency() == null){
            System.out.println("Null!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }
        // System.out.print("Polynomial: ");
        if(n <= this.getDegree()){
            System.out.println("Several highest bits won't be shown.");
        }
        for(int i = this.getDegree() < n ? n - 1 : this.getDegree(); i > this.getDegree(); i--){
            System.out.print(' ');
        }        
        for(int i = this.getDegree(); i >= 0; i--){
            System.out.print(this.getFrequency()[i]);
        }
        System.out.println();
    }
    
    public boolean isZero(){
        return this.getFrequency()[0] == 0 && this.getDegree() == 0;
    }
    
    public static Polynomial str2Poly(String s) {
        /* 字符串转多项式 */
		if ( s == null) {
			System.out.println("Can't tranform String to Polynomial.");
			return null;
		}
		int i;
		for (i = 0; i < s.length(); i++) {
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
		if (!leagleAssign(bi_data)){
			System.out.println("Can't tranform String to Polynomial.");
		}
		return new Polynomial(bi_data);
    }
    
    public static int[] skipFrontedZero(int[] a){
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

    public static int[] xor(int[] a, int[] b) {
        
        if(a == null || b == null) return null;
        boolean ab = a.length > b.length;
        int i = ab ? a.length : b.length;
        int[] result = new int[i];
        if(ab){
            for(i = 0; i < b.length; i++){
                result[i] = a[i] == b[i] ? 0 : 1;
            }
            for(;i < a.length; i++){
                result[i] = a[i];
            }
        }
        else{
            for(i = 0; i < a.length; i++){
                result[i] = a[i] == b[i] ? 0 : 1;
            }
            for(;i < b.length; i++){
                result[i] = b[i];
            }
        }
        return result;
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

    public static Polynomial polySub(Polynomial a, Polynomial b){
        if(a == null || b == null) return null;
        return new Polynomial(xor(a.getFrequency(), b.getFrequency()));
    }

    public static Polynomial polyMult(Polynomial a, Polynomial b) {
        if(a == null || b == null) return null;
        if(a.isZero() || b.isZero()) return new Polynomial(new int[]{0});
        int ad = a.getDegree();
        int bd = b.getDegree();
        int[] r = new int[ad + bd + 1];
        int i, j;
        for(i = 0; i < r.length; i++){
            for(j = i > b.getDegree() ? i - b.getDegree() : 0; j <= i && j <= a.getDegree(); j++){
                r[i] += a.getFrequency()[j] * b.getFrequency()[i - j];
                r[i] %= 2;
            }
        }
        return new Polynomial(r);
    }
    
    public static Polynomial[] polyDivise(Polynomial a, Polynomial b) {
        if(a == null || b == null) return null;

        int ad = a.getDegree();
        int bd = b.getDegree();
        Polynomial[] result = new Polynomial[2];
        if(b.isZero()){
            System.out.println("Dividend should not be 0 !");
            return null;
        }
        if(ad < bd){
            result[0] = new Polynomial(new int[]{0});
            result[1] = new Polynomial(a.getFrequency());
            return result;
        }
        if(ad == bd){
            result[0] = new Polynomial(new int[]{1});
            result[1] = polySub(a,b);
            return result;
        }
        int i;
        int[] q = new int[a.getDegree() - b.getDegree() + 1];
        int[] x = new int[a.getDegree() + 1];
        //x = a
        for(i = 0; i < x.length; i++){
            x[i] = a.getFrequency()[i];
        }
        int[] moved = new int[a.getDegree() + 1];
        //moved = b^(a.getDegree() - b.getDegree())
        for(i = 0; i <= b.getDegree(); i++){
            moved[i + ad - bd] = b.getFrequency()[i];
        }
        for(i = q.length - 1; i >= 0; i--){
            if(x[i + b.getDegree()] == 1){
                q[i] = 1;
                x = xor(x, moved);
            }
            else{
                q[i] = 0;
            }
            if(i == 0){
                break;
            }
            rightMove(moved, 1);
        }
        result[0] = new Polynomial(q);
        result[1] = new Polynomial(x);
        return result;
    }

    public static Polynomial polyMultMod(Polynomial a, Polynomial b, Polynomial m) {
    	return polyDivise(polyMult(a,b),m)[1];
    }
    
    public int poly2int(){
        if(isZero()) return 0;
        int result = 0;
        int[] a = this.getFrequency();
        for(int i = 0; i <= this.getDegree(); i++){
            result += a[i] * (10 ^ i);
        }
        return result;
    }

    public static Polynomial inverseElement(Polynomial fx, Polynomial gx){
        /* [0] = gcd(fx,gx), [1] = ux, [2] = vx fx * ux + gx * vx = gcd(fx,gx) */
        if(fx == null || gx == null){
            System.out.println("ERROR: f(x) or g(x) should neither be null.");
            return null;
        }
        Polynomial ux;
        Polynomial vx;
        if(gx.isZero()){
            ux = new Polynomial(new int[]{1});
            return ux;
        }
        Polynomial u1 = new Polynomial(new int[]{0});
        Polynomial u2 = new Polynomial(new int[]{1});
        Polynomial v1 = new Polynomial(new int[]{1});
        Polynomial v2 = new Polynomial(new int[]{0});
        Polynomial qx;
        Polynomial rx;
        Polynomial[] tmp;
        while( !gx.isZero() ){
            tmp = polyDivise(fx, gx);
            qx = tmp[0];
            rx = tmp[1];
            ux = polySub(u2, polyMult(qx, u1));
            vx = polySub(v2, polyMult(qx, v1));
            fx = gx;
            gx = rx;
            u2 = u1;
            u1 = ux;
            v2 = v1;
            v1 = vx;
        }
        if(fx.getDegree() != 1 && fx.getFrequency()[0] != 1) {
        	System.out.println("gcd(f(x), g(x)) is not 1 ");
        	return null;
        }
        ux = u2;
        return ux;
    }
}