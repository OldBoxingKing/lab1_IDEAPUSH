package se_lab1;

import java.io.BufferedWriter;  // 导入BufferedWriter类，用于写入文件
import java.io.File;  // 导入File类，用于文件操作
import java.io.FileWriter;  // 导入FileWriter类，用于写入文件
import java.io.IOException;  // 导入IOException类，用于捕获IO异常
import java.util.Scanner;  // 导入Scanner类，用于读取文件

// 定义有向图类
public class DirectedGraph {
    static String filetxt = "txt";  // 定义文件类型txt
    static String filedot = "dot";  // 定义文件类型dot

    // 无参构造方法
    public DirectedGraph() {
    }

    // 创建最短路径有向图的方法
    public static boolean createShortestDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize, String shortest, PathGraphAssist pga) {
        String[] shroads = shortest.split("\n");  // 将最短路径字符串按行分割

        // 处理最短路径字符串，去掉路径编号和句号
        for (int i = 0; i < shroads.length; ++i) {
            shroads[i] = shroads[i].replaceAll("Path [0-9]+ :", "");  // 去掉路径编号
            shroads[i] = shroads[i].replace(".", "");  // 去掉句号
        }

        // 定义颜色数组，用于不同路径的显示
        String[] colors = new String[]{"#1abc9c", "#3498db", "#f1c40f", "#8e44ad", "#c0392b"};  
        File dotFile = new File(fileUrl.replace(filetxt, filedot));  // 替换文件类型为dot，创建dot文件对象
        File sdotFile = new File(fileUrl.replace(".txt", "s.dot"));  // 创建新的dot文件对象，用于存储最短路径图

        try {
            sdotFile.createNewFile();  // 创建新文件
            Scanner in = new Scanner(dotFile);  // 创建Scanner对象，读取dot文件
            BufferedWriter outBuffer = new BufferedWriter(new FileWriter(sdotFile));  // 创建BufferedWriter对象，写入新dot文件

            // 逐行读取dot文件内容
            while (in.hasNextLine()) {
                String str = in.nextLine();  // 读取一行内容

                // 遍历所有节点，将边的颜色替换为灰色
                int i;
                for (i = 0; i < pga.allNodes.size(); ++i) {
                    for (int j = 0; j < pga.allNodes.size(); ++j) {
                        TreeNode node1 = (TreeNode) pga.allNodes.get(i);  // 获取节点1
                        TreeNode node2 = (TreeNode) pga.allNodes.get(j);  // 获取节点2
                        // 替换颜色
                        str = str.replace(String.format("%s -> %s [color = \"#3498db\"]", node1.getWord(), node2.getWord()), 
                                          String.format("%s -> %s [color = \"%s\"]", node1.getWord(), node2.getWord(), "#778899"));
                    }
                }

                // 遍历最短路径，将边的颜色替换为对应的颜色
                for (i = 0; i < shroads.length; ++i) {
                    str = replaceResult(str, shroads[i], colors[i], pga);  // 调用replaceResult方法替换颜色
                }

                outBuffer.write(str);  // 将处理后的字符串写入新dot文件
            }

            outBuffer.flush();  // 刷新缓冲区
            outBuffer.close();  // 关闭BufferedWriter
            in.close();  // 关闭Scanner
        } catch (IOException var17) {
            var17.printStackTrace();  // 捕获并打印异常
        }

        // 创建并启动线程，生成和显示最短路径图
        Runnable createGraphR = new CreateGraphRunnable(Lab1.fileUrl.replace(".txt", "s.dot"), Lab1.fileUrl.replace(".txt", "s.png"));
        Runnable showWaitingRunnable = new ShowWaitingRunnable(Lab1.fileUrl.replace(".txt", "s.png"));
        Thread createGraphThread = new Thread(createGraphR);  // 创建生成图的线程
        Thread showWaitingThread = new Thread(showWaitingRunnable);  // 创建显示图的线程
        Lab1.imgState = 0;  // 初始化图片状态
        createGraphThread.start();  // 启动生成图的线程
        showWaitingThread.start();  // 启动显示图的线程
        return true;  // 返回true表示成功创建最短路径有向图
    }

    // 创建随机有向图的方法
    public static boolean createRandomDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize, String random) {
        String[] randomWords = random.split(" ");  // 将随机字符串按空格分割成单词数组
        String[] colors = new String[]{"#1abc9c", "#3498db", "#f1c40f", "#8e44ad", "#c0392b"};  // 定义颜色数组
        File dotFile = new File(fileUrl.replace(filetxt, filedot));  // 替换文件类型为dot，创建dot文件对象
        File sdotFile = null;  // 初始化新dot文件对象

        try {
            // 遍历单词数组，创建多个随机文件
            for (int i = 0; i < randomWords.length; ++i) {
                sdotFile = new File(fileUrl.replace(".txt", String.format("%d.dot", i)));  // 创建新dot文件
                sdotFile.createNewFile();  // 创建新文件
                Scanner scannerin = new Scanner(dotFile);  // 创建Scanner对象，读取dot文件
                BufferedWriter outBuffer = new BufferedWriter(new FileWriter(sdotFile));  // 创建BufferedWriter对象，写入新dot文件

                // 逐行读取dot文件内容
                while (scannerin.hasNextLine()) {
                    String str = scannerin.nextLine();  // 读取一行内容
                    str = replaceRandomResult(str, randomWords, colors[2], i);  // 替换随机结果
                    outBuffer.write(str);  // 将处理后的字符串写入新dot文件
                }

                outBuffer.flush();  // 刷新缓冲区
                outBuffer.close();  // 关闭BufferedWriter
                scannerin.close();  // 关闭Scanner
            }
        } catch (IOException var13) {
            var13.printStackTrace();  // 捕获并打印异常
        }

        // 创建并启动线程，生成和显示随机图
        Runnable createRandGR = new CreateRandomGraphRunnable(Lab1.fileUrl.replace(filetxt, filedot), Lab1.fileUrl.replace(filetxt, "png"), randomWords.length);
        Runnable showRandWR = new ShowRandomWaitingRunnable(Lab1.fileUrl.replace(filetxt, "png"), randomWords.length);
        Thread createRandomGraphThread = new Thread(createRandGR);  // 创建生成随机图的线程
        Thread showWaitingThread = new Thread(showRandWR);  // 创建显示随机图的线程
        Lab1.imgState = 0;  // 初始化图片状态
        createRandomGraphThread.start();  // 启动生成随机图的线程
        showWaitingThread.start();  // 启动显示随机图的线程
        return true;  // 返回true表示成功创建随机有向图
    }

    // 替换随机结果的方法
    public static String replaceRandomResult(String ain, String[] random, String color, int num) {
        String intemp;
        // 如果num大于1，替换边的颜色
        if (num > 1) {
            intemp = ain.replace(String.format("%s -> %s", random[num - 1], random[num]), 
                                 String.format("%s -> %s [color = \"%s\"]", random[num - 1], random[num], color));
        } else {
            intemp = ain;  // 否则不替换
        }
        return intemp + "\n";  // 返回替换后的字符串
    }

    // 替换结果的方法
    public static String replaceResult(String ain, String shortroad, String color, PathGraphAssist pga) {
        String[] srNodes = shortroad.split("->");  // 将最短路径字符串按"->"分割成节点数组

        // 遍历节点数组，替换边的颜色
        for (int i = 0; i < srNodes.length - 1; ++i) {
            TreeNode node1 = pga.allNodes.nodeCheck(srNodes[i]);  // 获取节点1
            TreeNode node2 = pga.allNodes.nodeCheck(srNodes[i + 1]);  // 获取节点2
            int state = pga.queryNodeToNode(node1, node2);  // 查询节点1到节点2的状态
            if (state == Integer.MAX_VALUE) {
                color = "#B71C1C";  // 如果状态为无穷大，设置颜色为红色
            }
            // 替换边的颜色
            ain = ain.replace(String.format("%s -> %s", srNodes[i], srNodes[i + 1]), 
                              String.format("%s -> %s [color = \"%s\"]", srNodes[i], srNodes[i + 1], color));
        }
        return ain + "\n";  // 返回替换后的字符串
    }

    // 创建有向图的方法
    public static boolean createDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize) {
        File dotFile = new File(fileUrl.replace("txt", "dot"));  // 替换文件类型为dot，创建dot文件对象

        try {
            dotFile.createNewFile();  // 创建新文件
            BufferedWriter outBuffer = new BufferedWriter(new FileWriter(dotFile));  // 创建BufferedWriter对象，写入新dot文件
            outBuffer.write(String.format("digraph %s {\n\tfontname = \"%s\";\n\tfontsize = %d;\n\n", "test", fontname, fontsize));  // 写入图的基本信息
            outBuffer.write(String.format("\tnode [ fontname = \"%s\", fontsize = %d ]\n", fontname, fontsize));  // 写入节点的字体和字号
            outBuffer.write(String.format("\tedge [ fontname = \"%s\", fontsize = %d ]\n\n", fontname, fontsize));  // 写入边的字体和字号

            int i;
            // 遍历单词数组，写入每个单词作为节点
            for (i = 0; i < Lab1.words.length; ++i) {
                outBuffer.write(String.format("\t%s;\n", Lab1.words[i]));
            }

            // 遍历树节点，写入每个节点及其子节点之间的边
            for (i = 0; i < t.treeNodes.size(); ++i) {
                for (int j = 0; j < t.treeNodes.size(); ++j) {
                    TreeNode node1 = t.treeNodes.get(i);  // 获取节点1
                    TreeNode node2 = t.treeNodes.get(j);  // 获取节点2
                    if (node1.childList.indexOf(node2) != -1) {  // 如果节点2是节点1的子节点
                        outBuffer.write(String.format("\t%s -> %s [label=\"%d\"];\n", node1.getWord(), node2.getWord(), node1.getWeightOfNode(node2)));  // 写入边的信息
                    }
                }
            }

            outBuffer.write("}");  // 写入图的结束符号
            outBuffer.flush();  // 刷新缓冲区
            outBuffer.close();  // 关闭BufferedWriter
        } catch (IOException var10) {
            var10.printStackTrace();  // 捕获并打印异常
        }

        // 创建并启动线程，生成和显示图
        Runnable createGraphR = new CreateGraphRunnable(Lab1.fileUrl.replace("txt", "dot"), Lab1.fileUrl.replace("txt", "png"));
        Runnable showWaitingRunnable = new ShowWaitingRunnable(Lab1.fileUrl.replace("txt", "png"));
        Thread createGraphThread = new Thread(createGraphR);  // 创建生成图的线程
        Thread showWaitingThread = new Thread(showWaitingRunnable);  // 创建显示图的线程
        Lab1.imgState = 0;  // 初始化图片状态
        createGraphThread.start();  // 启动生成图的线程
        showWaitingThread.start();  // 启动显示图的线程
        return true;  // 返回true表示成功创建有向图
    }
}

// 这个DirectedGraph类的主要功能是根据输入的文本文件、随机字符串或最短路径信息，生成对应的有向图，并将这些图保存为dot文件和PNG图像文件。
// 为了不阻塞主线程，这些操作都是通过多线程异步完成的。该类的方法还会根据需要替换dot文件中的路径和颜色信息，以便更好地显示结果。