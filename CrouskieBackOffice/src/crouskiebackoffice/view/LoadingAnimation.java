package crouskiebackoffice.view;

import javax.swing.JLabel;

public class LoadingAnimation extends JLabel {
    
    String[] loadingChars = {"/", "-", "\\", "|"};
    int positionChar = 0;
    private Boolean isLoading = false;
    private Thread loadingThread = new Thread(() -> {
        while (isLoading) {
            
            setText(nextChar());
            try {
                Thread.sleep(300L);
                
            } catch (InterruptedException ex) {
                System.err.println("Error : Failed to wait for the animation of loading");
            }
            
        }
    });
    
    public void start() {
        isLoading = true;
        loadingThread.start();
    }
    
    public void stop() {
        try {
            isLoading = false;
            loadingThread.join();
            setText((" "));
        } catch (InterruptedException ex) {
            System.err.println("Error : Failed to the animation of loading");
        }
    }
    
    public LoadingAnimation() {
        setText(" ");
    }
    
    private String nextChar() {
        positionChar = (positionChar + 1) % loadingChars.length;//to be sure their is no bit overflow
        return loadingChars[positionChar];
    }
    
}
