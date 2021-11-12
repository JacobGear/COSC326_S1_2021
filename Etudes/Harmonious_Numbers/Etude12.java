import java.lang.*;

public class Etude12{

    public static void main(String args[]){        
        process(2000000);
    }

    private static void process(int max){
        for(int i=1; i<=max; i++){
            int p1 = sumDivisors(i);
            if(p1 > i){
                int p2 = sumDivisors(p1);
                if(i == p2 && p2 != p1){
                    System.out.println(p2 + " " + p1);
                }
            }            
        }
    }

    public static int sumDivisors(int n){
        int sum = 0;
        double sq = Math.sqrt(n);
        int i = 0;
        for(i=2; i<sq; i++){
            if(n % i == 0){
                sum += (i + n / i);                
            }                                  
        }
        if(n / i == i){
            sum += i;
        }
        return sum;
    }
}
