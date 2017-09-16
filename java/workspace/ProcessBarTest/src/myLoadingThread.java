import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

class myLoadingThread extends Thread {
    public String[] s = new String[0];
    private JProgressBar bar;
 
    public myLoadingThread(JProgressBar bar) {
        this.bar = bar;
    }
 
    public void run() {
        for (int i = 0; i <= 50000; i++) {
            String[] temp = new String[s.length + 1];
            for (int j = 0; j < i; j++) {
                temp[j] = s[j];
            }
            temp[i] = "test";
            s = temp;
            final int value = (int) (i / 50001d * 100);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    bar.setValue(value);
                }
            });
        }
    }
 
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                JProgressBar bar = new JProgressBar(0, 100);
                bar.setValue(0);
                bar.setStringPainted(true);
                panel.add(bar);
                frame.add(panel);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                new myLoadingThread(bar).start();
            }
        });
    }
}