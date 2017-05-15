package lt.vu.extensibility;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;

@Local
public  interface Sum {
    //     public int sumNumbers(int a,int b){
//         return 0;
//     };
//     public String getNumbers(String a,String b) {return "Nesuveikė";}
    public int sumNumbers(int a, int b);

    public String getNumbers(String a, String b);
}