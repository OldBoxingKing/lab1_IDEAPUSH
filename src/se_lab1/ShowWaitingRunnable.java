package se_lab1;

// ShowWaitingRunnable类实现了Runnable接口，用于等待图片生成并显示图片
class ShowWaitingRunnable implements Runnable {
    String graphPath;  // 图片路径

    // 构造方法，初始化图片路径
    public ShowWaitingRunnable(String graphPath) {
        this.setPath(graphPath);
    }

    // 设置图片路径的方法
    public final void setPath(String graphPath) {
        this.graphPath = graphPath;
    }

    // 重写run方法，定义线程执行的任务
    public void run() {
        // 等待直到Lab1.imgState变为1
        while (Lab1.imgState == 0) {
            try {
                Thread.sleep(1000L);  // 线程休眠1秒
            } catch (InterruptedException var2) {
                var2.printStackTrace();  // 捕获并打印中断异常
            }
        }

        // 图片生成完成后设置图片
        PicDisplayPanel.setPic(this.graphPath);
    }
}

// ShowWaitingRunnable类实现了Runnable接口，用于等待图片生成并显示图片。其主要功能和特性包括：

// 成员变量：

// graphPath：表示图片路径。
// 构造方法：

// ShowWaitingRunnable(String graphPath)：初始化图片路径。
// 成员方法：

// setPath(String graphPath)：设置图片路径。
// 重写run方法：

// 在run方法中，等待Lab1.imgState变为1（表示图片生成完成）。
// 使用Thread.sleep(1000L)方法使线程休眠1秒，定期检查状态。
// 一旦Lab1.imgState变为1，调用PicDisplayPanel.setPic方法设置并显示图片。
// 使用场景
// 这个类主要用于在多线程环境下等待图片生成的场景。通过实现Runnable接口，可以在独立线程中执行等待任务，避免阻塞主线程。
// 等待图片生成完成后，自动显示生成的图片。这在实现图片生成和显示的异步处理时非常有用，确保用户界面在图片生成过程中保持响应。