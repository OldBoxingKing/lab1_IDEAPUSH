package se_lab1;

import java.awt.Color;  // 导入颜色类
import java.io.File;  // 导入文件类
import java.io.FileNotFoundException;  // 导入文件未找到异常类
import java.util.Scanner;  // 导入扫描器类
import javax.swing.JComponent;  // 导入JComponent类

public class Lab1 extends JComponent {
    private static final long serialVersionUID = -4654513992552014113L;  // 序列化版本号
    public static MyFrame f;  // 声明MyFrame类的静态变量f
    public static String fileUrl;  // 声明静态字符串变量fileUrl
    public static String[] words;  // 声明静态字符串数组变量words
    public static Tree t;  // 声明Tree类的静态变量t
    public static int imgState;  // 声明静态整型变量imgState

    // 静态方法：读取文件内容
    public static void readInFile() {
        File file = new File(fileUrl);  // 根据fileUrl创建文件对象
        String wordsStr = "";  // 初始化存储单词的字符串

        try {
            Scanner in;  // 声明扫描器变量
            String str;  // 声明字符串变量
            for(in = new Scanner(file); in.hasNextLine(); wordsStr = wordsStr.concat(replaceStr(str) + " ")) {
                str = in.nextLine();  // 读取文件中的每一行
            }

            words = wordsStr.split("\\s+");  // 将wordsStr按空格分割成数组
            t = new Tree(words);  // 根据words数组创建Tree对象
            DirectedGraph.createDirectedGraph(t, fileUrl, "Verdana", 12);  // 创建有向图
            in.close();  // 关闭扫描器
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();  // 捕获文件未找到异常并打印堆栈跟踪
        }

    }

    // 构造方法：设置背景颜色为白色
    public Lab1() {
        this.setBackground(Color.WHITE);
    }

    // 静态方法：将字符串按空格分割成数组
    public static String[] wordSplit(String str) {
        return str.split("\\s+");
    }

    // 静态方法：替换字符串中的非字母字符为空格，并将其转换为小写
    public static String replaceStr(String str) {
        return str.replaceAll("[^a-zA-Z]", " ").toLowerCase();
    }

    // 主方法：程序入口
    public static void main(String[] args) {
        f = new MyFrame();  // 创建MyFrame对象
    }

}
// 这个程序的主要功能是读取指定文件的内容，对其中的单词进行处理，然后通过一些辅助方法创建一个有向图并展示。
// Lab1类继承自JComponent，用于自定义Swing组件界面。
// 该程序通过读取文件中的文本数据，进行字符串处理，构建单词树并生成有向图，最后在GUI中显示结果。
// 程序入口为main方法，创建并显示MyFrame窗口。