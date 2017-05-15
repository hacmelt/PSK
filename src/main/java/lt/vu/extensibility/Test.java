package lt.vu.extensibility;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Test {
@Inject
private Sum sum;
@Inject
private Minus minus;

    public int alternativesAnswer(int a,int b){
        return  sum.sumNumbers(a,b);

    }
    public int specializesAnswer(int a,int b){
        return minus.minusNumbers(a,b);
    }
    public String interceptorAnswer(String a , String b) {return sum.getNumbers(a,b);}
}
