package se_lab1;

import java.awt.GridBagConstraints;  // 导入GridBagConstraints类
import java.awt.GridBagLayout;  // 导入GridBagLayout类
import java.awt.event.ActionEvent;  // 导入ActionEvent类
import java.awt.event.ActionListener;  // 导入ActionListener接口
import javax.swing.JButton;  // 导入JButton类
import javax.swing.JLabel;  // 导入JLabel类
import javax.swing.JPanel;  // 导入JPanel类
import javax.swing.JTextField;  // 导入JTextField类

// 定义QueryBridgePanel类，继承自JPanel，用于查询桥接词
class QueryBridgePanel extends JPanel {
    private static final long serialVersionUID = 7264749733574435443L;  // 序列化版本号
    JTextField tfWord1 = new JTextField(112);  // 输入单词1的文本框
    JLabel lbWord1 = new JLabel("单词1: ");  // 单词1的标签
    JTextField tfWord2 = new JTextField(112);  // 输入单词2的文本框
    JLabel lbWord2 = new JLabel("单词2: ");  // 单词2的标签
    JLabel lbRst = new JLabel();  // 显示查询结果的标签
    JButton btnQB = new JButton("开始查询");  // 开始查询按钮

    // 构造方法
    public QueryBridgePanel() {
        // 创建GridBagConstraints对象，设置布局约束
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 为查询按钮添加动作监听器
        this.btnQB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 查询桥接词并显示在结果标签上
                    QueryBridgePanel.this.lbRst.setText(Lab1.t.queryBridgeWords(QueryBridgePanel.this.tfWord1.getText(), QueryBridgePanel.this.tfWord2.getText()));
                } catch (Exception var3) {
                    var3.printStackTrace();  // 捕获并打印异常
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
        this.add(this.btnQB, gbc);  // 添加开始查询按钮
        gbc.gridy = 5;
        this.add(this.lbRst, gbc);  // 添加结果标签
    }
}
// QueryBridgePanel类是一个继承自JPanel的自定义面板类，主要用于查询两个单词之间的桥接词。其主要功能和特性包括：

// 成员变量：

// tfWord1：用于输入单词1的文本框。
// lbWord1：单词1的标签。
// tfWord2：用于输入单词2的文本框。
// lbWord2：单词2的标签。
// lbRst：显示查询结果的标签。
// btnQB：开始查询的按钮。
// 构造方法：

// 初始化各个组件，并设置它们的布局和属性。
// 使用GridBagLayout布局管理器，使得组件可以灵活地放置和调整。
// 为查询按钮添加动作监听器，当按钮被点击时，调用Lab1类中的queryBridgeWords方法进行查询，并将结果显示在结果标签上。
// 方法功能
// 构造方法：
// 设置布局管理器为GridBagLayout。
// 设置各个组件的布局约束参数（GridBagConstraints），并将组件添加到面板中。
// 为查询按钮添加动作监听器，处理按钮点击事件，进行桥接词查询并显示结果。
// 使用场景
// 这个类主要用于图形用户界面（GUI）中，需要查询两个单词之间桥接词的场景。用户可以在文本框中输入两个单词，点击按钮后，系统将查询两个单词之间的桥接词，并在结果标签中显示查询结果。
// 这在实现语言处理工具、词典应用或其他需要桥接词查询功能的应用程序中非常有用。