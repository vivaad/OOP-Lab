
import java.util.Scanner;
class EMPLOYEE{
    String Ename;
    String email="";
    int Eid, Basic;
    float DA, Gross_Sal, Net_Sal, IT;
    
    EMPLOYEE(){
        Ename = "def_construct";
        Eid=0;
        Basic=15000;
    }
    EMPLOYEE(String Ename,int Eid, int Basic){
        this.Ename = Ename;
        this.Eid = Eid;
        this.Basic = Basic;
    }
    void formatEmployeeName(){
    Ename = Ename.toLowerCase();
    String format="";
    if(Ename.charAt(0)>='a' && Ename.charAt(0)<='z'){
        format+= (char) (Ename.charAt(0)-32);
    } else {
        format  +=Ename.charAt(0);
    }for(int i=1;i<Ename.length();i++){
        if(Ename.charAt(i)>='a' && Ename.charAt(i)<='z' &&
            Ename.charAt(i-1)==' '){
            format+= (char) (Ename.charAt(i)-32);
        } else {
            format+=Ename.charAt(i);
        }
    }
    Ename = format;
}
    void generateEmail(){
        String name = Ename.toLowerCase();
        email+=name.charAt(0);
        for(int i=1;i<name.length();i++){
            if(name.charAt(i-1)==' '){
                email+= name.substring(i);
                break;
            }
        }
        email+="@manipal.edu";
    }

    void display(){
        System.out.println();
        System.out.println("Ename: "+Ename);
        System.out.println("Email: "+email);
        System.out.println("Eid: "+Eid);
        System.out.println("Basic: "+Basic);
        System.out.println("DA: "+DA);
        System.out.println("Gross_Sal: "+Gross_Sal);
        System.out.println("IT: "+IT);
        System.out.println("Net_Sal: "+Net_Sal);
        System.out.println();
    }

    void compute_net_sal(){
        DA = 0.52f * Basic;
        Gross_Sal = Basic + DA;
        IT=0.3f * Gross_Sal;
        Net_Sal=Gross_Sal - IT;
    }
}

class l5_1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String name="akshay shetty";
        int id=1,Basic=12000;
        EMPLOYEE E = new EMPLOYEE(name,id,Basic);
        E.compute_net_sal();
        E.formatEmployeeName();
        E.generateEmail();
        E.display();
        sc.close();
    }
}