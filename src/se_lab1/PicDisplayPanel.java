package se_lab1;

import java.awt.Color;  // 导入颜色类
import java.io.File;  // 导入文件类
import java.io.IOException;  // 导入IOException类
import javax.imageio.ImageIO;  // 导入ImageIO类
import javax.swing.ImageIcon;  // 导入ImageIcon类
import javax.swing.JLabel;  // 导入JLabel类
import javax.swing.JPanel;  // 导入JPanel类

// 定义PicDisplayPanel类，继承自JPanel，用于显示图片
class PicDisplayPanel extends JPanel {
    private static final long serialVersionUID = -466385864846654643L;  // 序列化版本号
    public static JLabel picLabel;  // 用于显示图片的标签
    public static int WIDTH;  // 图片宽度
    public static int height;  // 图片高度
    public static ImageIcon pic;  // 图片图标
    boolean isAltDown = false;  // 是否按下Alt键的标志
    int percent = 100;  // 缩放比例

    // 构造方法
    public PicDisplayPanel() {
        picLabel = new JLabel();  // 初始化图片标签
        this.setBackground(Color.WHITE);  // 设置背景颜色为白色
        this.add(picLabel);  // 将图片标签添加到面板
    }

    // 设置图片的方法
    public static void setPic(String path) {
        try {
            // 读取图片并创建ImageIcon对象
            pic = new ImageIcon(ImageIO.read(new File(path)));
            WIDTH = pic.getIconWidth();  // 获取图片宽度
            height = pic.getIconHeight();  // 获取图片高度
            picLabel.setIcon(pic);  // 设置图片标签的图标
            picLabel.repaint();  // 重绘图片标签
        } catch (IOException var2) {
            var2.printStackTrace();  // 捕获并打印异常
        }
    }

    // 改变图片大小的方法
    public static void changeSize(int percent) {
        // 缩放图片并设置为图片标签的图标
        pic.setImage(pic.getImage().getScaledInstance(percent * WIDTH / 100, percent * height / 100, java.awt.Image.SCALE_SMOOTH));
        picLabel.setIcon(pic);  // 更新图片标签的图标
    }
}

// PicDisplayPanel类是一个继承自JPanel的自定义面板类，主要用于显示和调整图片。其主要功能和特性包括：

// 成员变量：

// picLabel：用于显示图片的JLabel。
// WIDTH：图片的宽度。
// height：图片的高度。
// pic：存储图片的ImageIcon对象。
// isAltDown：标识是否按下Alt键（此变量未在代码中使用）。
// percent：图片缩放比例，初始值为100%。
// 构造方法：

// 初始化picLabel对象。
// 设置面板背景颜色为白色，并将picLabel添加到面板中。
// 静态方法：

// setPic(String path)：根据给定的文件路径读取图片，并将其显示在picLabel上。
// changeSize(int percent)：根据给定的百分比调整图片大小，并更新显示。
// 方法功能
// setPic方法：读取指定路径的图片文件，创建ImageIcon对象，并将图片设置到标签上显示。
// changeSize方法：根据给定的百分比缩放图片，并更新图片标签的图标。
// 使用场景
// 这个类主要用于图形用户界面（GUI）中，需要显示和调整图片大小的场景。
// 用户可以通过setPic方法设置要显示的图片，通过changeSize方法调整图片的显示比例。
// 这在实现图像浏览器、编辑器或其他需要动态显示和调整图片的应用程序中非常有用。