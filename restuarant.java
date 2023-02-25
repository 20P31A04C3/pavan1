import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.*;
class assignment3{
    Scanner sc=new Scanner(System.in);
    ArrayList<ArrayList<String>> b1=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> b2=new ArrayList<ArrayList<String>>();
    ArrayList<Integer> b3=new ArrayList<>();
    ArrayList<Integer> b4=new ArrayList<>();
    void fileread(){
    try {
        Scanner x1=new Scanner(new FileReader("assign.java/menuList.csv"));
        Scanner x2=new Scanner(new FileReader("assign.java/billingDetails.csv"));
        String x;
        while(x1.hasNext()){
            x=x1.nextLine();
            String[] str=x.split(",");
            List<String> dummy=Arrays.asList(str);
            ArrayList<String> b11=new ArrayList<>(dummy);
            
            b1.add(b11);
        }
        while(x2.hasNext()){
            String xx=x2.nextLine();
            String[] str1=xx.split(",");
            List<String> dummy1=Arrays.asList(str1);
            ArrayList<String> b21=new ArrayList<>(dummy1);
            b2.add(b21);

        }
    }
    catch (Exception e) {
        System.out.println("Running Error");
    }
    }
    void display(ArrayList<ArrayList<String>>a1){
        int n=b1.size();
        for(int i=0;i<n;i++){
            ArrayList<String> dum=b1.get(i);
            for (String string : dum) {
                System.out.print(string+" ");                
            }
            System.out.println();
        }
    }
    void menu(){
        String arr[]={"Generate new bill","View the total collection for today","Cancel the bill"};
        int k=arr.length;
        for(int i=0;i<k;i++){
            System.out.print(i+1+"-"+arr[i]+"\n");
        }
        System.out.print("Enter Your Choice: ");
    }
    void generate(){
        System.out.println("Generate new bill");
        display(b1);
        order();
    }
    void collect(){
        sc.nextLine();
        System.out.println("View the total collection for today");
        System.out.print("Enter date:: ");
        String x=sc.nextLine();
        Double collect=0.0;
        for(int i=0;i<(b2.size());i++){
            ArrayList<String> x1=b2.get(i);
            if((x1.get(1)).equals(x)){
                Double b=Double.parseDouble(x1.get(2));
                collect+=b;
                System.out.println(x1+"\n");
            }  
        }
        System.out.println("Total collection of day: "+collect);

    }
    void billcancel(){
        System.out.println("Cancel the bill:: ");
        display(b2);
        System.out.print("Enter the id in Above list:: ");
        int n=sc.nextInt();
        int t=b2.size();
        if(n>t){
            System.out.println("Enter Valid Id");
        }
        else{
            n=n-1;
            (b2.get(n)).set(4, "cancelled");
            try {
                FileWriter objq=new FileWriter("assign.java/billingDetails.csv",false);
                for(int i=0;i<(b2.size());i++){
                    ArrayList<String>str1=b2.get(i);
                    String str11=String.join(",",str1);
                    str11+="\n";
                    FileWriter obj1=new FileWriter("assign.java/billingDetails.csv",true);
                    obj1.write(str11);
                    obj1.close();
                }
                    objq.close();
                    display(b2);
                    System.out.println("Cancelled order");
            } catch (Exception e) {
                System.out.println("Error");
            }
        }


    }
    void details(){
        int k=sc.nextInt();
        switch (k) {
            case 1:
                generate();
                break;
            case 2:
                collect();
                break;
            case 3:
                billcancel();
                break;
        
            default:
                System.out.println("Enter Valid number");
                break;
        }
    }
    void order(){
        System.out.print("Enter order Id: ");
        int k=sc.nextInt();
        System.out.print("Enter Quantity: ");
        int p=sc.nextInt();
        b3.add(k);
        b4.add(p);
        System.out.print("If you to order again Yes->y or No->n: ");
        char ch=sc.next().charAt(0);
        if(ch=='y'){
            order();
        }
        else{
            int ordcount=b3.size();
            Double total=0.0;
            System.out.print("check your details:: ");
            for(int i=0;i< ordcount;i++){
                int m=b3.get(i);
                int n=b4.get(i);
                ArrayList<String> dump2=b1.get(m-1);
                Double a=Double.parseDouble(dump2.get(2));
                total+=a*n;
                System.out.println(b3.get(i)+" ");
            }
            System.out.println("total:  "+total);
            System.out.print("Do you want to confirm order Yes-y or No-n: ");
            char ch1=sc.next().charAt(0);
            if(ch1=='y'){
                String s=",";
                LocalDate date1= LocalDate.now();
                DateTimeFormatter obj22=DateTimeFormatter.ofPattern("d-MMM-yy");
                String date=date1.format(obj22);
                int x=b2.size()+1;
                String a=x+s+date+s+total+.00+s;
                for(int i=0;i<(b3.size());i++){
                    a+=b3.get(i)+" ";
                    a+=b4.get(i)+" ";
                }
                a+=s+"Approved";
                try {
                    File newn=new File("assign.java/billingDetails.csv");
                    FileWriter obj2=new FileWriter(newn,true);
                    obj2.write("\n"+a);
                    obj2.close();
                } catch (Exception e) {
                    System.out.println("error");
                }
                
                System.out.println("Thank You Visit Again");
            }
            else{
                menu();
                details();
            }      
            
        }
    }
    
}
public class restuarant {
 public static void main(String[] args) {
    assignment3 obj=new assignment3();
    obj.fileread();
    obj.menu();
    obj.details();  
    
 }   
}
