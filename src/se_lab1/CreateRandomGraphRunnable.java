package se_lab1;

import java.io.IOException;  // 导入IOException类

// 创建一个实现Runnable接口的类，用于生成随机图像
class CreateRandomGraphRunnable implements Runnable {
    String txtPath;  // 文本文件路径
    String graphPath;  // 图像文件路径
    int picNum;  // 图像数量

    // 设置文本文件路径、图像文件路径和图像数量的方法
    public void setPath(String txtPath, String graphPath, int picNum) {
        this.txtPath = txtPath;  // 设置文本文件路径
        this.graphPath = graphPath;  // 设置图像文件路径
        this.picNum = picNum;  // 设置图像数量
    }

    // 构造方法：初始化路径和图像数量
    public CreateRandomGraphRunnable(String txtPath, String graphPath, int picNum) {
        this.setPath(txtPath, graphPath, picNum);  // 使用setPath方法设置路径和图像数量
    }

    // 重写run方法，定义线程执行的任务
    public void run() {
        Runtime run = Runtime.getRuntime();  // 获取Runtime对象

        try {
            Lab1.imgState = 0;  // 设置图像状态为0，表示开始生成图像
            Process process = null;  // 声明进程变量

            // 根据图像数量生成多个图像文件
            for(int i = 0; i < this.picNum; ++i) {
                // 执行命令行指令，将文本文件转换为图像文件，文件名中包含索引i
                process = run.exec(String.format("dot -Tpng %s -o %s", 
                    this.txtPath.replace(".dot", String.format("%d.dot", i)), 
                    this.graphPath.replace(".png", String.format("%d.png", i))));
            }

            process.waitFor();  // 等待最后一个进程完成
            Lab1.imgState = 1;  // 设置图像状态为1，表示图像生成完成
        } catch (IOException var4) {
            var4.printStackTrace();  // 捕获并打印IO异常
        } catch (InterruptedException var5) {
            var5.printStackTrace();  // 捕获并打印中断异常
        }

    }
}
// 这个类CreateRandomGraphRunnable实现了Runnable接口，主要用于生成多个随机图像文件。它的功能包括：

// 路径和数量设置：通过构造方法和setPath方法设置文本文件路径、图像文件路径和需要生成的图像数量。
// 执行任务：在run方法中，获取系统运行时环境，循环执行命令行指令dot -Tpng，将多个文本文件转换为PNG图像文件。
// 状态管理：在图像生成开始和完成时，更新Lab1类的静态变量imgState，分别将其设置为0和1。
// 这个类的主要用途是在多线程环境下异步生成多个图像文件，避免阻塞主线程。每个图像文件根据索引命名，以确保生成多个文件。