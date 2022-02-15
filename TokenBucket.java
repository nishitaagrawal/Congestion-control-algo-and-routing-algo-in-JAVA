import java.util.*;
import java.util.List;
import java.util.ArrayList;
public class TokenBucket {
    public static void main (String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Bucket Size: ");
        int bs=sc.nextInt();
        System.out.print("Enter the number of packets: ");
        int n=sc.nextInt();
        List<Integer> q = new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.println("Byte size of packet "+(i+1)+": ");
            q.add(sc.nextInt());
        }
        int token=bs;
        int ptr=1;
        int index=0;
        Thread.sleep(10);
        while(!q.isEmpty()){
            token++;
            System.out.println("! Token added !");
            Thread.sleep(10);
            if(token>bs){
                token=bs;
            }
            System.out.println("Number of tokens present in bucket: "+token);
            if(q.get(0)<=token){
                token-=q.get(0);
                q.remove(index);
                System.out.println("\nPacket "+ptr+" transferred! ("+token+" Tokens left)\n");
                ptr+=1;
            }
            if(!q.isEmpty()){
                if(q.get(0)>bs){
                    q.remove(index);
                    System.out.println("\nPacket "+ptr+" discarded!(Size>Bucket size)\n");
                    ptr+=1;
                }
            }
        }
        sc.close();
    }
}