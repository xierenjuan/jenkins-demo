package com.jenkins.distribution;

public class TestDistribution {
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(30);
//        for (int i = 0; i < 30; i++) {
//            executorService.execute(() -> {
//                executor();
//            });
//        }
//    }
//
//    public static void executor(){
//        // 构造redisson实现分布式锁必要的Config
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.0.193:6379").setDatabase(0);
//        // 构造RedissonClient
//        RedissonClient redissonClient = Redisson.create(config);
//        // 设置锁定资源名称
//        RLock disLock = redissonClient.getLock("DISLOCK");
//        boolean isLock;
//        try {
//            //尝试获取分布式锁
//            isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
//            if (isLock) {
//                //TODO if get lock success, do something;
//                System.out.println(Thread.currentThread().getName() +"获取到锁执行任务========");
//                Thread.sleep(5000);
//                System.out.println(Thread.currentThread().getName() +"任务执行完成=========");
//            }
//        } catch (Exception e) {
//        } finally {
//            // 无论如何, 最后都要解锁
//            disLock.unlock();
//            System.out.println(Thread.currentThread().getName() +"释放锁==========");
//        }
//    }
}
