package com.summer.demos.classloader;


import java.io.File;
import java.lang.reflect.Method;

public class HelloMain {

    private static MethodExcuteThread methodExcuteThread = new MethodExcuteThread();
    private static ClassFileChangeListenerThread classFileChangeListenerThread = new ClassFileChangeListenerThread();

    private static volatile Class desClazz;//共享变量

    public static void main(String[] args) {
        //创建两个线程，一个线程负责运行方法  另一个线程负责监听观察的文件是否有变动

        /**启动类文件监听线程**/
        classFileChangeListenerThread.start();

        /**启动方法执行线程**/
        methodExcuteThread.start();

    }

    private static class ClassFileChangeListenerThread extends Thread {
        @Override
        public void run() {
            try {
                File file = new File(HelloMain.class.getResource("").getFile() + "Worker.class");
                long lastTime = file.lastModified();
                boolean isFirst = true;
                while (true) {
                    System.out.println("while true");
                    Thread.sleep(2000);
                    File newFile = new File(HelloMain.class.getResource("").getFile() + "Worker.class");
                    long nowModified = newFile.lastModified();
                    if (lastTime != nowModified) {
                        System.out.println("--->fileChanged（发现文件改变了）:" + nowModified);
                        lastTime = nowModified;
                        reloadFile(newFile, methodExcuteThread);
                    } else {
                        if (isFirst) {
                            System.out.println("首次，也应该加载文件");
                            reloadFile(newFile, methodExcuteThread);
                            isFirst = false;
                        } else {
                            System.out.println("--->文件没有改变");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 方法执行线程
     */
    private static class MethodExcuteThread extends Thread {
        volatile InheritableThreadLocal<Class> excuteClassLocal = new InheritableThreadLocal();

        @Override
        public void run() {
            while (true) {
                try {
                    Class excuteClazz = desClazz;
                    if (null == excuteClazz) {
                        Thread.sleep(2000);
                        System.out.println("还没有CLASS信息，[无法执行代码]");
                        continue;
                    }
                    System.out.println("MethodExcuteThread   要执行代码了");
                    Thread.sleep(1000);
                    Object objObject = excuteClazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
                    Method excuteClazzMethod = excuteClazz.getMethod("doit", null);
                    excuteClazzMethod.invoke(objObject, null);//执行
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public InheritableThreadLocal<Class> getExcuteClassLocal() {
            return excuteClassLocal;
        }

        public void setExcuteClassLocal(InheritableThreadLocal<Class> excuteClassLocal) {
            this.excuteClassLocal = excuteClassLocal;
        }
    }


    /**
     * 重新加载FILE
     * 在这里，将这个CLASS文件重新加载到内存中，从而替换掉之前的CLASS文件
     * 即将之前那个类重新new一下
     */
    private static void reloadFile(File newFile, MethodExcuteThread methodExcuteThread) {
        System.out.println("[reloadFile]");
        HotClassLoader hotClassLoader = new HotClassLoader();
        hotClassLoader.setObjFile(newFile);
        try {
            Class<?> objClass = hotClassLoader.findClass("com.test.classloader.Worker");
            //把这个新的CLASS设置到另一个线程中
            methodExcuteThread.getExcuteClassLocal().set(objClass);//把新的class设置上
            desClazz = objClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
