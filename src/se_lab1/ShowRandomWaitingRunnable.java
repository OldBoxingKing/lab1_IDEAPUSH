package se_lab1;

// ShowRandomWaitingRunnable类实现了Runnable接口，用于显示随机图片时等待
class ShowRandomWaitingRunnable implements Runnable {
    String graphPath;  // 图片路径
    int picNum;  // 图片数量

    // 构造方法，初始化图片路径和图片数量
    public ShowRandomWaitingRunnable(String graphPath, int picNum) {
        this.setPath(graphPath, picNum);
    }

    // 设置图片路径和图片数量的方法
    public void setPath(String graphPath, int picNum) {
        this.graphPath = graphPath;
        this.picNum = picNum;
    }

    // 重写run方法，定义线程执行的任务
    public void run() {
        // 等待直到Lab1.imgState变为1
        while (Lab1.imgState == 0) {
            try {
                Thread.sleep(1000L);  // 线程休眠1秒
            } catch (InterruptedException var3) {
                var3.printStackTrace();  // 捕获并打印中断异常
            }
        }

        // 创建并启动SetPicRunnable线程
        SetPicRunnable setPicRunnable = new SetPicRunnable(this.graphPath, this.picNum);
        Thread setPicThread = new Thread(setPicRunnable);
        setPicThread.start();
    }
}

// ShowRandomWaitingRunnable类实现了Runnable接口，用于在等待图片生成过程中显示随机图片。其主要功能和特性包括：

// 成员变量：

// graphPath：表示图片路径。
// picNum：表示图片数量。
// 构造方法：

// ShowRandomWaitingRunnable(String graphPath, int picNum)：初始化图片路径和图片数量。
// 成员方法：

// setPath(String graphPath, int picNum)：设置图片路径和图片数量。
// 重写run方法：

// 在run方法中，等待Lab1.imgState变为1（表示图片生成完成）。
// 使用Thread.sleep(1000L)方法使线程休眠1秒，定期检查状态。
// 一旦Lab1.imgState变为1，创建并启动SetPicRunnable线程，按顺序显示图片。
// 使用场景
// 这个类主要用于在多线程环境下等待图片生成的场景。通过实现Runnable接口，可以在独立线程中执行等待任务，避免阻塞主线程。
// 等待图片生成完成后，启动SetPicRunnable线程按顺序显示图片。这在实现图片生成和显示的异步处理时非常有用，确保用户界面在图片生成过程中保持响应。