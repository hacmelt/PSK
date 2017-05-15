package lt.vu.extensibility;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@MyInterceptor
@Interceptor
public class MyInterceptorImp {
    @AroundInvoke
    public Object modifyString(InvocationContext ctx) throws Exception {
        Object[] parameters = ctx.getParameters();
        String param = (String) parameters[0];
        param = param.toLowerCase();
        parameters[0] = param;
        param = (String) parameters[1];
        param = param.toLowerCase();
        parameters[1] = param;
        ctx.setParameters(parameters);
        try {
            return ctx.proceed();
        } catch (Exception e) {
            return null;
        }
    }
}
