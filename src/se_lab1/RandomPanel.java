package se_lab1;

import java.awt.GridBagConstraints;  // 导入GridBagConstraints类
import java.awt.GridBagLayout;  // 导入GridBagLayout类
import java.awt.event.ActionEvent;  // 导入ActionEvent类
import java.awt.event.ActionListener;  // 导入ActionListener接口
import javax.swing.JButton;  // 导入JButton类
import javax.swing.JLabel;  // 导入JLabel类
import javax.swing.JPanel;  // 导入JPanel类

// 定义RandomPanel类，继承自JPanel，用于随机游走
class RandomPanel extends JPanel {
    private static final long serialVersionUID = -2862015187279261925L;  // 序列化版本号
    JLabel lbRst = new JLabel();  // 显示随机游走结果的标签
    JButton btnG = new JButton("开始生成");  // 开始生成按钮

    // 构造方法
    public RandomPanel() {
        // 为按钮添加动作监听器
        this.btnG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String random = Lab1.t.randomWalk();  // 调用随机游走方法
                RandomPanel.this.lbRst.setText(random);  // 显示随机游走结果
                DirectedGraph.createRandomDirectedGraph(Lab1.t, Lab1.fileUrl, "Verdana", 12, random);  // 创建随机有向图
            }
        });

        // 创建GridBagConstraints对象，设置布局约束
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // 设置网格的x坐标
        gbc.gridy = 0;  // 设置网格的y坐标
        gbc.weightx = 1.0;  // 设置网格的x方向权重
        gbc.weighty = 1.0;  // 设置网格的y方向权重
        gbc.fill = GridBagConstraints.HORIZONTAL;  // 设置填充方式为水平填充

        // 设置布局管理器为GridBagLayout
        this.setLayout(new GridBagLayout());

        // 将各组件按约束添加到面板
        gbc.gridy = 1;
        this.add(this.btnG, gbc);  // 添加开始生成按钮
        gbc.gridy = 2;
        this.add(this.lbRst, gbc);  // 添加结果标签
    }
}
// RandomPanel类是一个继承自JPanel的自定义面板类，主要用于触发随机游走并显示结果。其主要功能和特性包括：

// 成员变量：

// lbRst：显示随机游走结果的JLabel。
// btnG：用于触发随机游走的JButton。
// 构造方法：

// 初始化组件，并设置它们的布局和属性。
// 使用GridBagLayout布局管理器，使得组件可以灵活地放置和调整。
// 为按钮添加动作监听器，当按钮被点击时，调用Lab1类中的randomWalk方法进行随机游走，并将结果显示在结果标签上，同时调用DirectedGraph.createRandomDirectedGraph方法创建随机有向图。
// 方法功能
// 构造方法：
// 设置布局管理器为GridBagLayout。
// 设置各个组件的布局约束参数（GridBagConstraints），并将组件添加到面板中。
// 为生成按钮添加动作监听器，处理按钮点击事件，进行随机游走并显示结果，同时生成随机有向图。
// 使用场景
// 这个类主要用于图形用户界面（GUI）中，需要触发随机游走操作并显示结果的场景。
// 用户可以通过点击按钮触发随机游走，系统将进行随机游走操作，并在结果标签中显示结果，同时生成对应的随机有向图。这在实现随机图生成、路径模拟等应用中非常有用。