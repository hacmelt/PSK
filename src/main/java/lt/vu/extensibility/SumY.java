package lt.vu.extensibility;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Alternative
public class SumY implements Sum {
    @Override
    public int sumNumbers(int a, int b) {
        System.out.println("SumY: was working");
        return a+b;
    }
    @MyInterceptor
    public String getNumbers(String a, String b){
        return  a  + " ir " + b;
    }
}
