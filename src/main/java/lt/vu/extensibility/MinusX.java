package lt.vu.extensibility;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

/**
 * Created by Justinas on 2017-05-14.
 */
@ApplicationScoped
@Specializes
public class MinusX extends Minus{
    @Override
    public int minusNumbers(int a, int b) {
        return a-b;
    }
}
