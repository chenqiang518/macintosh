package com.vernon.reaper.service;

public class HungryIdMaker {

    // JAVA 静态属性只会加载一次，所有 instance 只会被初始化一次
    // 利用 JAVA 静态属性，可以避免多线程资源竞争问题
    // 饿汉式：静态属性，在类加载阶段，完成初始化
    private  static final HungryIdMaker instance =new HungryIdMaker();
    //ID 计数器，默认值使 -1
    private int id = -1;

    // 把 IdMaker 的构造函数，设为私有(用于阻止调用者实例化 IdMaker )
    private HungryIdMaker(){ }
    // 获取实例
    public static HungryIdMaker getInstance(){ return instance; }

    // 通过 ++ 操作，获取不一样的 ID 值
    public int getId(){
        return ++id;
    }

    public static void main(String[] args) {
        int id1 = HungryIdMaker.instance.getId();
        int id2 = HungryIdMaker.instance.getId();
        int id3 = HungryIdMaker.instance.getId();
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);
    }
}
