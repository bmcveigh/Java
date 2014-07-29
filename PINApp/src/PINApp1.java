import javax.swing.JFrame;

public class PINApp1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PINFrame1 pinFrame = new PINFrame1();
        pinFrame.setSize(200,100);
        pinFrame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        pinFrame.setVisible(true);
    }

}