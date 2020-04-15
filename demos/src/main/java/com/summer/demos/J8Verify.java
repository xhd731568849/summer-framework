package com.summer.demos;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Clock;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class J8Verify {

    public static FN createFN(final Supplier<FN> supplier) {
        return supplier.get();
    }

    public static class FN {
        public void callback(String result) {
            System.out.println("callback running with result:" + result);
        }
    }

    public static void process(Consumer<String> action) {
        System.out.println("running process.");
        action.accept("OK");
    }

    static void verifyFn() {
        System.out.println("==========verifyFn=========");
        FN fn = createFN(FN::new);
        process(fn::callback);
    }

    static void verifyMethodName() throws NoSuchMethodException, SecurityException {
        System.out.println("==========verifyMethodName=========");
        Method method = FN.class.getMethod("callback", String.class);
        for (Parameter para : method.getParameters()) {
            System.out.println("parameter name:" + para.getName());
        }
    }

    static void verifyOptional() {
        System.out.println("==========verifyOptional=========");
        Optional<String> fullname = Optional.of("MyName");
        System.out.println("fullname.isPresent():" + fullname.isPresent());
        System.out.println("fullname.isPresent():" + fullname.orElseGet(() -> "TheOther"));
    }

    static void verifyDateTime() {
        System.out.println("==========verifyDateTime=========");

        final Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.getZone());
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
    }

    static void verifyJS() throws ScriptException {
        System.out.println("==========verifyJS=========");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        System.out.println(engine.getClass().getName());
        System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
    }

    static void verifyParallel() {
        System.out.println("==========verifyParallel=========");
        long[] arrayOfLong = new long[2000000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
    }

    public static void main(String[] argv) throws NoSuchMethodException, SecurityException, ScriptException {
        verifyFn();
        verifyMethodName();
        verifyOptional();
        verifyDateTime();
        verifyJS();
        verifyParallel();
    }
}
