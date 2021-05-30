package loader.two;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gyc
 * @Classname MyClassLoaderTest
 * @Description 自定义ClassLoader，
 * 由于是重新了findClass，要使其发生作用，则重写loadClass方法，然后调用findClass方法，使自定义生效
 * @Date 2021/5/22 8:52
 */
public class MyClassLoaderTest extends ClassLoader {
    private byte[] loadByte(String name) {
        name = name.replaceAll("\\.", "/");
        try (FileInputStream fileInputStream = new FileInputStream("E:\\java\\" + name + ".class")) {
            int available = fileInputStream.available();
            byte[] data = new byte[available];
            fileInputStream.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadByte(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t1 = System.nanoTime();
                if (!name.startsWith("loader.two")) {
                    c = this.getParent().loadClass(name);
                } else {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    c = findClass(name);
                }

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoaderTest classLoaderTest = new MyClassLoaderTest();
        Class aClass = classLoaderTest.loadClass("loader.two.User");
        Object o = aClass.newInstance();
        Method sout = aClass.getMethod("sout", null);
        sout.invoke(o, null);
        System.out.println(aClass);

        MyClassLoaderTest classLoaderTest1 = new MyClassLoaderTest();
        Class aClass1 = classLoaderTest1.loadClass("loader.two.User");
        Object o1 = aClass1.newInstance();
        Method sout1 = aClass1.getMethod("sout", null);
        sout1.invoke(o1, null);
        System.out.println(aClass1);

        System.out.println(aClass1==aClass);
    }
}
