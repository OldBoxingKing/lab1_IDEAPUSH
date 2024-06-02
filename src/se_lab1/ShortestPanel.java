package se_lab1;

import java.awt.GridBagConstraints;  // 导入GridBagConstraints类
import java.awt.GridBagLayout;  // 导入GridBagLayout类
import java.awt.event.ActionEvent;  // 导入ActionEvent类
import java.awt.event.ActionListener;  // 导入ActionListener接口
import javax.swing.JButton;  // 导入JButton类
import javax.swing.JLabel;  // 导入JLabel类
import javax.swing.JPanel;  // 导入JPanel类
import javax.swing.JTextArea;  // 导入JTextArea类
import javax.swing.JTextField;  // 导入JTextField类

// 定义ShortestPanel类，继承自JPanel，用于计算两个单词之间的最短路径
class ShortestPanel extends JPanel {
    private static final long serialVersionUID = 7264749733574435443L;  // 序列化版本号
    JTextField tfWord1 = new JTextField(112);  // 输入单词1的文本框
    JLabel lbWord1 = new JLabel("单词1: ");  // 单词1的标签
    JTextField tfWord2 = new JTextField(12);  // 输入单词2的文本框
    JLabel lbWord2 = new JLabel("单词2: ");  // 单词2的标签
    JTextArea txRst = new JTextArea(3, 30);  // 显示计算结果的文本区域
    JButton btnQB = new JButton("开始计算");  // 开始计算按钮
    PathGraphAssist pga;  // 路径图辅助对象

    // 构造方法
    public ShortestPanel() {
        // 创建GridBagConstraints对象，设置布局约束
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 为按钮添加动作监听器
        this.btnQB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 初始化路径图辅助对象
                    ShortestPanel.this.pga = new PathGraphAssist(Lab1.t.treeNodes);
                    // 计算最短路径
                    String shortest = Lab1.t.calcShortestPath(ShortestPanel.this.tfWord1.getText(), ShortestPanel.this.tfWord2.getText(), ShortestPanel.this.pga);
                    // 显示最短路径
                    ShortestPanel.this.txRst.setText(shortest);
                    // 创建最短路径有向图
                    DirectedGraph.createShortestDirectedGraph(Lab1.t, Lab1.fileUrl, "Verdana", 12, shortest, ShortestPanel.this.pga);
                } catch (CloneNotSupportedException var3) {
                    var3.printStackTrace();  // 捕获并打印克隆不支持异常
                } catch (NullPointerException var4) {
                    var4.printStackTrace();  // 捕获并打印空指针异常
                }
            }
        });

        // 设置GridBagConstraints的布局属性
        gbc.gridx = 0;  // 设置网格的x坐标
        gbc.gridy = 0;  // 设置网格的y坐标
        gbc.weightx = 1.0;  // 设置网格的x方向权重
        gbc.weighty = 1.0;  // 设置网格的y方向权重
        gbc.fill = GridBagConstraints.HORIZONTAL;  // 设置填充方式为水平填充

        // 设置布局管理器为GridBagLayout
        this.setLayout(new GridBagLayout());

        // 将各组件按约束添加到面板
        this.add(this.lbWord1, gbc);  // 添加单词1标签
        gbc.gridy = 1;
        this.add(this.tfWord1, gbc);  // 添加单词1文本框
        gbc.gridy = 2;
        this.add(this.lbWord2, gbc);  // 添加单词2标签
        gbc.gridy = 3;
        this.add(this.tfWord2, gbc);  // 添加单词2文本框
        gbc.gridy = 4;
        this.add(this.btnQB, gbc);  // 添加开始计算按钮
        gbc.gridy = 5;
        this.add(this.txRst, gbc);  // 添加结果文本区域
    }
}
// ShortestPanel类是一个继承自JPanel的自定义面板类，主要用于计算两个单词之间的最短路径并显示结果。其主要功能和特性包括：

// 成员变量：

// tfWord1：用于输入单词1的文本框。
// lbWord1：单词1的标签。
// tfWord2：用于输入单词2的文本框。
// lbWord2：单词2的标签。
// txRst：用于显示计算结果的文本区域。
// btnQB：用于触发计算最短路径的按钮。
// pga：路径图辅助对象，用于辅助路径计算。
// 构造方法：

// 初始化组件，并设置它们的布局和属性。
// 使用GridBagLayout布局管理器，使得组件可以灵活地放置和调整。
// 为按钮添加动作监听器，当按钮被点击时，调用Lab1类中的calcShortestPath方法计算最短路径，并将结果显示在结果文本区域，同时调用DirectedGraph.createShortestDirectedGraph方法生成最短路径有向图。
// 方法功能
// 构造方法：
// 设置布局管理器为GridBagLayout。
// 设置各个组件的布局约束参数（GridBagConstraints），并将组件添加到面板中。
// 为计算按钮添加动作监听器，处理按钮点击事件，进行最短路径计算并显示结果，同时生成最短路径有向图。
// 使用场景
// 这个类主要用于图形用户界面（GUI）中，需要计算和显示两个单词之间的最短路径的场景。
// 用户可以在文本框中输入两个单词，点击按钮后，系统将计算两个单词之间的最短路径，并在结果文本区域中显示结果，同时生成对应的最短路径有向图。
// 这在实现语言处理工具、词典应用或其他需要路径计算功能的应用程序中非常有用。