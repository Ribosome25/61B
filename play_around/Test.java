public class Test{

	public static int fib(int n) {
		int ii = 3;
		int aa = 0;
		int bb = 1;
		int ans = 1;
		while(ii<n){
			aa = bb;
			bb = ans;
			ans = aa + bb;
			ii+=1;
		}
		return ans;
	}

	public static void main(String[] arg){
		int n = Integer.parseInt(arg[0]);
		int answer = fib(n);
		System.out.println(answer);
	}
}


