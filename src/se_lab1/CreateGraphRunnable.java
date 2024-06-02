package se_lab1;

import java.io.IOException;  // 导入IOException类

// 创建一个实现Runnable接口的类，用于生成图像
class CreateGraphRunnable implements Runnable {
    String txtPath;  // 文本文件路径
    public String graphPath;  // 图像文件路径

    // 设置文本文件路径和图像文件路径的方法
    public final void setPath(String txtPath, String graphPath) {
        this.txtPath = txtPath;  // 设置文本文件路径
        this.graphPath = graphPath;  // 设置图像文件路径
    }

    // 构造方法：初始化路径
    public CreateGraphRunnable(String txtPath, String graphPath) {
        this.setPath(txtPath, graphPath);  // 使用setPath方法设置路径
    }

    // 重写run方法，定义线程执行的任务
    public void run() {
        Runtime run = Runtime.getRuntime();  // 获取Runtime对象

        try {
            Lab1.imgState = 0;  // 设置图像状态为0，表示开始生成图像
            // 执行命令行指令，将文本文件转换为图像文件
            Process process = run.exec(String.format("dot -Tpng %s -o %s", this.txtPath, this.graphPath));
            process.waitFor();  // 等待进程完成
            Lab1.imgState = 1;  // 设置图像状态为1，表示图像生成完成
        } catch (IOException var3) {
            var3.printStackTrace();  // 捕获并打印IO异常
        } catch (InterruptedException var4) {
            var4.printStackTrace();  // 捕获并打印中断异常
        }

    }

}
// 这个类CreateGraphRunnable实现了Runnable接口，主要用于生成图像文件。它的作用是通过执行系统命令将一个文本文件转换为图像文件，并更新图像状态。具体功能包括：

// 路径设置：通过构造方法和setPath方法设置文本文件和图像文件的路径。
// 执行任务：在run方法中，获取系统运行时环境，执行命令行指令dot -Tpng将指定的文本文件转换为PNG图像文件。
// 状态管理：在图像生成开始和完成时，更新Lab1类的静态变量imgState，分别将其设置为0和1。
// 这个类的主要用途是在多线程环境下异步生成图像文件，避免阻塞主线程。