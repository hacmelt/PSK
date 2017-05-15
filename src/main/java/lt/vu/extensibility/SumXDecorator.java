package lt.vu.extensibility;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@ApplicationScoped
@Decorator
public abstract class SumXDecorator implements Sum {

    @Inject
    @Delegate
    @Any
    Sum sum;

    public String getNumbers(String a, String b){
        return "Sudėta buvo " + a  + " ir " + b;
    }
}
