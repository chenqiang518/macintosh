package com.vernon.reaper.service;

public class LazyIdMaker {

    // JAVA 引用类型的默认属性是 null
    // 在类加载阶段，不进行初始化
    private  static LazyIdMaker instance;
    //ID 计数器，默认值使 -1
    private int id = -1;

    // 把 IdMaker 的构造函数，设为私有(用于阻止调用者实例化 IdMaker )
    private LazyIdMaker(){ }
    // 懒汉式: 在获取实例阶段，进行初始化
    // synchronized 是互斥锁，为了保证在多线程时，只实例化一次
    public static synchronized LazyIdMaker getInstance(){
        // 如果发现 instance 没有被初始化，就完成初始化
        if(instance==null) return new LazyIdMaker();
        return instance;
    }

    // 通过 ++ 操作，获取不一样的 ID 值
    public int getId(){
        return ++id;
    }

    public static void main(String[] args) {
        int id1 = LazyIdMaker.instance.getId();
        int id2 = LazyIdMaker.instance.getId();
        int id3 = LazyIdMaker.instance.getId();
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);
    }

}
