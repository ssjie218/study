package com.github.study.base;

/**
 * JVM内存空间包含：方法区、java堆、java栈、本地方法栈。
 * <p>
 * 方法区是各个线程共享的区域，存放类信息、常量、静态变量。
 * <p>
 * java堆也是线程共享的区域，我们的类的实例就放在这个区域，可以想象你的一个系统会产生很多实例，因此java堆的空间也是最大的。如果java堆空间不足了，程序会抛出OutOfMemoryError异常。
 * <p>
 * java栈是每个线程私有的区域，它的生命周期与线程相同，一个线程对应一个java栈，每执行一个方法就会往栈中压入一个元素，这个元素叫“栈帧”，而栈帧中包括了方法中的局部变量、用于存放中间状态值的操作栈，这里面有很多细节，我们以后再讲。如果java栈空间不足了，程序会抛出StackOverflowError异常，想一想什么情况下会容易产生这个错误，对，递归，递归如果深度很深，就会执行大量的方法，方法越多java栈的占用空间越大。
 * <p>
 * 本地方法栈角色和java栈类似，只不过它是用来表示执行本地方法的，本地方法栈存放的方法调用本地方法接口，最终调用本地方法库，实现与操作系统、硬件交互的目的。
 * <p>
 * PC寄存器，说到这里我们的类已经加载了，实例对象、方法、静态变量都去了自己改去的地方，那么问题来了，程序该怎么执行，哪个方法先执行，哪个方法后执行，这些指令执行的顺序就是PC寄存器在管，它的作用就是控制程序指令的执行顺序。
 * <p>
 * 执行引擎当然就是根据PC寄存器调配的指令顺序，依次执行程序指令。
 * Created by pc on 2018/10/25.
 */
public class JvmTest {

    public static void main(String[] args) {
//        byte[] bytes = null;
//        for (int i = 0; i < 100; i++) {
//            bytes = new byte[1 * 1024 * 1024];
//        }

        System.out.println("最大堆：" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("空闲堆：" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("总的堆：" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
//        https://www.cnblogs.com/leefreeman/p/7364030.html
//        -Xmx20m -Xms20m -Xmn8m -XX:SurvivorRatio=6 -XX:+PrintGCDetails -XX:NewRatio=4
//        Xmx 堆上限 Xms 堆值 Xmn新生代大小 -XX:SurvivorRatio=6 新生代 eden:survivor=2：6 尹甸区：幸存区（from+to）
//        -XX:NewRatio=4 生代和老年代比值为1:4     -Xss 栈大小
//        -XX:PermSize -XX:MaxPermSize 永久区分配 1.8后不用 存在元空间本地内存
//        堆分配参数的总结
//
//         根据实际事情调整新生代和幸存代的大小
//
//        官方推荐新生代占堆的3/8   -Xmx20m -Xmx10m -XX:NewRatio=4 -XX:SurvivorRatio=6
//
//        幸存代占新生代的1/10
//
//        在OOM时，记得Dump出堆，确保可以排查现场问题

    }


}
