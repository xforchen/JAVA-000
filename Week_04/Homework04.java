public class Homework04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();

        ExecutorService executor = Executors.newCachedThreadPool();

        /*Future<Integer> result = executor.submit(Homework03::sum);
        if (result.get() != null) {
            System.out.println("异步计算结果为：" + result.get());
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }*/

        /*FutureTask<Integer> result = new FutureTask<>(Homework03::sum);
        new Thread(result).start();*/

        /*FutureTask<Integer> result = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.submit(result);*/



        if (result.get() != null) {
            System.out.println("异步计算结果为：" + result.get());
            System.out.println("使用时间："+ (System.currentTimeMillis() - start) + " ms");
        }
    }

    private static int sum() {
        return fib(36);
    }

    private static int fib(int a) {
        if ( a < 2)
            return 1;
        return fib(a-1) + fib(a-2);
    }

    private static <T> Future<T> submit(ExecutorService executor, Callable<T> callable) {
        return executor.submit(callable);
    }
}
