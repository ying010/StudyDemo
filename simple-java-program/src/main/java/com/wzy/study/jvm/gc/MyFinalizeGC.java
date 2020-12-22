package com.wzy.study.jvm.gc;

/**
 * 示例展示两点：
 * 1.对象可以被GC回收时自我拯救
 * 2.自我拯救机会只有一次，finalize()方法只能被系统自动执行一次
 * @author 王志英
 */
public class MyFinalizeGC {
    public static MyFinalizeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        MyFinalizeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new MyFinalizeGC();

        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }

        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }
    }
}
