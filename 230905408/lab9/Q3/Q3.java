class Shop {
    private int product;
    private boolean available = false;

    public synchronized int get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted.");
            }
        }
        System.out.println("Consumer got: " + product);
        available = false;
        notify();
        return product;
    }

    public synchronized void put(int product) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted.");
            }
        }
        this.product = product;
        available = true;
        System.out.println("Producer put: " + product);
        notify();
    }
}

class Producer implements Runnable {
    private Shop shop;

    Producer(Shop shop) {
        this.shop = shop;
        new Thread(this).start();
    }

    public void run() {
        int i = 0;
        while (true) {
            shop.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Producer sleep interrupted.");
            }
        }
    }
}

class Consumer implements Runnable {
    private Shop shop;

    Consumer(Shop shop) {
        this.shop = shop;
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            shop.get();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Consumer sleep interrupted.");
            }
        }
    }
}

class ProducerConsumer {
    public static void main(String[] args) {
        Shop shop = new Shop();
        new Producer(shop);
        new Consumer(shop);
        System.out.println("Press Ctrl+C to stop.");
    }
}
