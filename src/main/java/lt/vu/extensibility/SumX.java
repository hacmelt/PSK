package lt.vu.extensibility;


import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;


@ApplicationScoped
public class SumX implements Sum {

    public int sumNumbers(int a,int b) {
        System.out.println("SumX: was working");
        return a*2 + b*2;
    }
    @MyInterceptor
    public String getNumbers(String a, String b){
        return  a  + " ir " + b;
    }
}
