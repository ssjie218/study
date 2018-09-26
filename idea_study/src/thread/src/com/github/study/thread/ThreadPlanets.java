package thread.src.com.github.study.thread;

import java.util.TreeSet;

/**
 *
 *  集合是可变的 但是这个集合在构造函数退出后就不可变
 *  定义为final后 引用也不可改变
 *  线程安全
 * Created by pc on 2018/7/18.
 */
public class ThreadPlanets {
    private final TreeSet<String> planets = new TreeSet<String>();

    public ThreadPlanets() {
        planets.add("Mercury");
        planets.add("Venus");
    }

    public boolean isPlanet(String planetName) {
        return planets.contains(planetName);
    }

    public static void main(String[] args) {
        ThreadPlanets p=new ThreadPlanets();
        System.out.println(p.isPlanet("aa"));
    }
}
