package se_lab1;

import java.awt.Color;  // 导入颜色类
import javax.swing.Icon;  // 导入图标类
import javax.swing.JPanel;  // 导入JPanel类
import javax.swing.JTabbedPane;  // 导入JTabbedPane类

// 定义功能面板类，继承自JPanel
class FunctionPanel extends JPanel {
    private static final long serialVersionUID = -1104559035947942491L;  // 序列化版本号
    private JTabbedPane tp = new JTabbedPane(1);  // 创建一个选项卡面板，放置在位置1
    private String[] tabNames = new String[]{"查找桥接词", "生成新文本", "查找最短路", "随机游走"};  // 定义选项卡名称数组

    // 构造方法
    public FunctionPanel() {
        this.setBackground(Color.WHITE);  // 设置背景颜色为白色
        JPanel tab1 = new QueryBridgePanel();  // 创建查询桥接词面板
        this.tp.addTab(this.tabNames[0], (Icon)null, tab1);  // 添加查询桥接词选项卡
        JPanel tab2 = new NewTextPanel();  // 创建生成新文本面板
        this.tp.addTab(this.tabNames[1], (Icon)null, tab2);  // 添加生成新文本选项卡
        JPanel tab3 = new ShortestPanel();  // 创建查找最短路面板
        this.tp.addTab(this.tabNames[2], (Icon)null, tab3);  // 添加查找最短路选项卡
        JPanel tab4 = new RandomPanel();  // 创建随机游走面板
        this.tp.addTab(this.tabNames[3], (Icon)null, tab4);  // 添加随机游走选项卡
        this.add(this.tp);  // 将选项卡面板添加到功能面板
    }

}

// 构造方法中，设置了面板的背景颜色为白色，并创建了各个功能的子面板，然后将这些子面板作为选项卡添加到JTabbedPane中，最后将JTabbedPane添加到FunctionPanel中。
// 这样就实现了一个多功能的选项卡面板，用户可以通过点击不同的选项卡来切换不同的功能界面。