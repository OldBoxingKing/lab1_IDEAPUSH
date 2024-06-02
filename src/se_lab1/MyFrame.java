package se_lab1;

import java.awt.BorderLayout;  // 导入BorderLayout布局类
import java.awt.Color;  // 导入颜色类
import java.awt.Component;  // 导入组件类
import java.awt.Container;  // 导入容器类
import java.awt.GridLayout;  // 导入GridLayout布局类
import java.awt.event.ActionEvent;  // 导入ActionEvent类
import java.awt.event.ActionListener;  // 导入ActionListener接口
import java.io.File;  // 导入File类
import javax.swing.JFileChooser;  // 导入文件选择器类
import javax.swing.JFrame;  // 导入JFrame类
import javax.swing.JMenu;  // 导入JMenu类
import javax.swing.JMenuBar;  // 导入JMenuBar类
import javax.swing.JMenuItem;  // 导入JMenuItem类
import javax.swing.JPanel;  // 导入JPanel类
import javax.swing.JScrollPane;  // 导入JScrollPane类
import javax.swing.UIManager;  // 导入UIManager类
import javax.swing.filechooser.FileNameExtensionFilter;  // 导入文件名扩展过滤器类

// 定义MyFrame类，继承自JFrame
class MyFrame extends JFrame  {
    private static final long serialVersionUID = -6904245993409935448L;  // 序列化版本号
    private static final int WIDTH = 520;  // 窗口宽度
    private static final int HEIGHT = 550;  // 窗口高度
    PicDisplayPanel picPanel;  // 图片显示面板

    // 构造方法
    public MyFrame() {
        try {
            String feel = UIManager.getSystemLookAndFeelClassName();  // 获取系统外观设置
            UIManager.setLookAndFeel(feel);  // 设置外观
        } catch (Exception var10) {
            var10.printStackTrace();  // 捕获并打印异常
        }

        this.setBackground(Color.WHITE);  // 设置背景颜色为白色
        this.setTitle("Flow chart");  // 设置窗口标题
        this.setSize(WIDTH, HEIGHT);  // 设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 设置关闭操作
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  // 设置窗口最大化
        this.setLocationRelativeTo((Component)null);  // 设置窗口相对位置
        this.setLayout(new BorderLayout());  // 设置布局管理器为BorderLayout
        Container c = this.getContentPane();  // 获取内容面板

        // 创建菜单栏
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("文件(F)");  // 创建“文件”菜单
        mFile.setMnemonic('F');  // 设置助记符为F
        JMenuItem miOpen = new JMenuItem("打开(O)");  // 创建“打开”菜单项
        miOpen.setMnemonic('O');  // 设置助记符为O
        JMenuItem miReset = new JMenuItem("回复默认图(R)");  // 创建“回复默认图”菜单项
        miReset.setMnemonic('R');  // 设置助记符为R
        mFile.add(miOpen);  // 将“打开”菜单项添加到“文件”菜单
        mFile.add(miReset);  // 将“回复默认图”菜单项添加到“文件”菜单

        // 为“回复默认图”菜单项添加动作监听器
        miReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Lab1.readInFile();  // 调用读取文件方法
                } catch (NullPointerException var3) {
                    var3.printStackTrace();  // 捕获并打印空指针异常
                }
            }
        });

        // 为“打开”菜单项添加动作监听器
        miOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();  // 创建文件选择器
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", new String[]{"txt"});  // 创建文件过滤器
                fc.setFileFilter(filter);  // 设置文件过滤器
                fc.setDialogTitle("Choose file");  // 设置对话框标题
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);  // 设置文件选择模式
                fc.showOpenDialog(Lab1.f);  // 显示打开文件对话框
                File filename = fc.getSelectedFile();  // 获取选定的文件
                if (filename != null) {
                    Lab1.fileUrl = filename.getAbsolutePath();  // 设置文件路径
                    Lab1.readInFile();  // 调用读取文件方法
                }
            }
        });

        mb.add(mFile);  // 将“文件”菜单添加到菜单栏
        this.setJMenuBar(mb);  // 设置菜单栏

        // 创建主面板并设置布局为GridLayout
        JPanel mainPanel = new JPanel(new GridLayout());

        // 创建图片显示面板并添加到带滚动条的面板中
        PicDisplayPanel picPanel = new PicDisplayPanel();
        JScrollPane sp = new JScrollPane(picPanel);
        sp.validate();
        mainPanel.add(sp);  // 将带滚动条的面板添加到主面板

        // 创建功能面板并添加到主面板
        FunctionPanel funcPanel = new FunctionPanel();
        mainPanel.add(funcPanel);

        // 将主面板添加到内容面板
        c.add(mainPanel);

        // 设置窗口可见
        this.setVisible(true);
    }
}
// 这个MyFrame类是一个继承自JFrame的自定义窗口类，主要用于创建并显示应用程序的主界面。其主要功能包括：

// 设置窗口外观：尝试将窗口外观设置为系统默认外观。
// 设置窗口属性：设置窗口背景颜色、标题、大小、关闭操作、最大化状态以及相对位置。
// 创建菜单栏：包括“文件(F)”菜单，菜单中有两个选项：“打开(O)”和“回复默认图(R)”。
// 打开(O)：使用文件选择器选择一个文本文件，并读取其内容。
// 回复默认图(R)：调用Lab1类中的readInFile方法读取默认文件。
// 创建并添加主面板：主面板使用GridLayout布局，包含两个子面板：
// 图片显示面板：PicDisplayPanel，放置在带滚动条的面板中。
// 功能面板：FunctionPanel，包含多个选项卡，每个选项卡对应不同的功能模块。
// 显示窗口：将主面板添加到内容面板，并将窗口设置为可见。
// 这个类通过构造方法完成了窗口的初始化、菜单栏和主面板的创建与布局，以及各种功能的集成，为用户提供了一个图形界面，方便操作和显示数据。