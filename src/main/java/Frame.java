import javax.swing.*;

public class Frame {
    JFrame frame = new JFrame();

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.frame.setLayout(null);
        frame.frame.setVisible(true);
        frame.frame.setSize(200,200);
        JButton button = new JButton("kek");
        button.setSize(100,100);
        button.setLocation(150,150);
        frame.frame.add(button);
        frame.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
