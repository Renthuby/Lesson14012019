
class JThread implements Runnable {

    private boolean isActive;
    void disable(){         //выполняется при остановке потока
        isActive = false;
    }

    JThread(){              //конструктор
        isActive = true;
    }

    @Override
    public void run() {     //выполняется при запуске потока
        System.out.printf(" %s Запущен....\n", Thread.currentThread().getName());

        int count = 1;

        while (isActive){
            System.out.println("Loop" + count++);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
        }

        System.out.printf(" %s Остановлен....\n", Thread.currentThread().getName());
    }
}


class MyThread extends Thread{
    MyThread (String name){
        super(name);
    }

    public void run() {
        System.out.printf(" %s Запущен....\n", Thread.currentThread().getName());
        int[] a = new int[(int)(Math.random()*100)];
        try {
            for (int i = 0; i < a.length; i++) {
                a[i] = (int)(Math.random()*100);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Поток прерван");
        }
        System.out.printf(" %s Остановлен....\n", Thread.currentThread().getName());
        for (int i = 0; i < a.length; i++) {
            System.out.println();
        }
    }
}






public class Main {
    public static void main(String[] args) {



        System.out.println("запуск главного потока");
        for (int i = 0; i < 5; i++) {

//            MyThread myThread = new MyThread("MyThread" + i);
//            myThread.start();
            JThread jThread = new JThread();
            new Thread(jThread, "JThread" + i).start();
            try {
                Thread.sleep(2500);
                jThread.disable();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            /*try {
//                myThread.join();
                jThread.join();
            } catch (InterruptedException e) {
                System.out.printf("%s поток был прерван", jThread.getName());
            }*/

        }
        System.out.println("остановка главного потока");
    }
}
