package se_lab1;

import java.awt.Color;  // 导入颜色类
import java.awt.GridBagConstraints;  // 导入GridBagConstraints类
import java.awt.GridBagLayout;  // 导入GridBagLayout类
import java.awt.event.ActionEvent;  // 导入ActionEvent类
import java.awt.event.ActionListener;  // 导入ActionListener接口
import javax.swing.JButton;  // 导入JButton类
import javax.swing.JLabel;  // 导入JLabel类
import javax.swing.JPanel;  // 导入JPanel类
import javax.swing.JTextArea;  // 导入JTextArea类
import javax.swing.border.LineBorder;  // 导入LineBorder类

// 定义NewTextPanel类，继承自JPanel
class NewTextPanel extends JPanel {
    private static final long serialVersionUID = -2862015187279261925L;  // 序列化版本号
    JTextArea taText = new JTextArea(3, 40);  // 创建文本区域
    JLabel lbText = new JLabel("新文本: ");  // 创建标签
    JLabel lbRst = new JLabel();  // 创建用于显示结果的标签
    JButton btnG = new JButton("开始生成");  // 创建按钮

    // 构造方法
    public NewTextPanel() {
        // 设置文本区域边框和换行
        this.taText.setBorder(new LineBorder(new Color(127, 157, 185), 1, false));
        this.taText.setLineWrap(true);

        // 为按钮添加动作监听器
        this.btnG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // 生成新文本并显示在结果标签上
                    NewTextPanel.this.lbRst.setText(Lab1.t.generateNewText(NewTextPanel.this.taText.getText()));
                } catch (Exception var3) {
                    var3.printStackTrace();  // 捕获并打印异常
                }
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
        this.add(this.lbText, gbc);  // 添加文本标签
        gbc.gridy = 1;
        this.add(this.taText, gbc);  // 添加文本区域
        gbc.gridy = 4;
        this.add(this.btnG, gbc);  // 添加按钮
        gbc.gridy = 5;
        this.add(this.lbRst, gbc);  // 添加结果标签
    }
}

// NewTextPanel类是一个继承自JPanel的自定义面板类，主要用于生成新文本，并显示在用户界面中。其主要功能包括：

// 组件初始化：

// 创建一个多行文本区域（JTextArea），用于用户输入文本。
// 创建一个标签（JLabel），用于显示“新文本”提示。
// 创建一个按钮（JButton），用于触发文本生成操作。
// 创建一个标签（JLabel），用于显示生成的新文本结果。
// 设置组件属性：

// 设置文本区域的边框和自动换行属性。
// 为按钮添加动作监听器，当按钮被点击时，调用Lab1类中的generateNewText方法生成新文本，并将结果显示在结果标签上。
// 布局管理：

// 使用GridBagLayout布局管理器，使得组件可以灵活地放置和调整。
// 设置各个组件的布局约束参数（GridBagConstraints），并将组件添加到面板中。
// 这个类实现了一个简单的用户界面，用户可以在文本区域输入文本，点击按钮后生成新文本，并在结果标签中显示。布局使用GridBagLayout，使得界面布局灵活、整齐。