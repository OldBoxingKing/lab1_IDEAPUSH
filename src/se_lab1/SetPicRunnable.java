package se_lab1;

// SetPicRunnable类实现了Runnable接口，用于设置图片显示
class SetPicRunnable implements Runnable {
    String graphPath;  // 图片路径
    int picNum;  // 图片数量

    // 设置图片路径的方法
    public void setgraphPath(String graph) {
        this.graphPath = graph;
    }

    // 获取图片路径的方法
    public String getgraphPath() {
        return this.graphPath;
    }

    // 构造方法，初始化图片路径和图片数量
    public SetPicRunnable(String graphPath, int picNum) {
        this.setPath(graphPath, picNum);
    }

    // 设置图片路径和图片数量的方法
    public void setPath(String graphPath, int picNum) {
        this.graphPath = graphPath;
        this.picNum = picNum;
    }

    // 重写run方法，定义线程执行的任务
    public void run() {
        for (int i = 0; i < this.picNum; ++i) {
            try {
                // 设置图片路径并显示图片
                PicDisplayPanel.setPic(this.graphPath.replace(".png", String.format("%d.png", i)));
                Thread.sleep(1000L);  // 线程休眠1秒
            } catch (InterruptedException var3) {
                var3.printStackTrace();  // 捕获并打印中断异常
            }
        }
    }
}

// 总结
// SetPicRunnable类实现了Runnable接口，主要用于在多线程环境下按顺序显示一组图片。其主要功能和特性包括：

// 成员变量：

// graphPath：表示图片路径。
// picNum：表示图片数量。
// 构造方法：

// SetPicRunnable(String graphPath, int picNum)：初始化图片路径和图片数量。
// 成员方法：

// setgraphPath(String graph)：设置图片路径。
// getgraphPath()：获取图片路径。
// setPath(String graphPath, int picNum)：设置图片路径和图片数量。
// 重写run方法：

// 在run方法中，按顺序显示图片，每张图片显示1秒。
// 使用PicDisplayPanel.setPic()方法设置并显示图片。
// 使用Thread.sleep(1000L)方法使线程休眠1秒。
// 使用场景
// 这个类主要用于需要按顺序显示多张图片的场景。通过实现Runnable接口，可以在独立线程中执行图片显示任务，避免阻塞主线程。
// 用户可以通过实例化SetPicRunnable对象并启动线程来实现图片的逐帧显示效果。这在实现幻灯片播放、动画展示等应用中非常有用。