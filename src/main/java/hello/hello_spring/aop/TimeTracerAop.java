package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTracerAop {

  @Around("execution(* hello.hello_spring..*(..) )")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    try {

      Object result = joinPoint.proceed();
      return result;
    } finally {
      long end = System.currentTimeMillis();
      long ms = end - start;
      System.out.println(joinPoint.toString() + ms + "ms");
    }
  }
}
// public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
// long start = System.currentTimeMillis();
// System.out.println("START: " + joinPoint.toString());
// try {
// return joinPoint.proceed();
// } finally {
// long finish = System.currentTimeMillis();
// long timeMs = finish - start;
// System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
// "ms");
// }
// }