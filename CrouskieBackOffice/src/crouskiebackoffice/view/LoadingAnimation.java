package crouskiebackoffice.view;

import javax.swing.JLabel;

/**
 * Animation de chargement fait avec des charachtères.
 */
public class LoadingAnimation extends JLabel {

    String[] loadingChars = {"/", "-", "\\", "|"};
    int positionChar = 0;
    private Boolean isLoading = false;

    Runnable loadingRun = new Runnable() {
        @Override
        public void run() {
            while (isLoading) {

                setText(nextChar());
                try {
                    Thread.sleep(300L);

                } catch (InterruptedException ex) {
                    System.err.println("Error : Failed to wait for the animation of loading");
                }

            }
        }
    };
    private Thread loadingThread;

    public void start() {
        if (!isLoading) {
            isLoading = true;
            loadingThread = new Thread(loadingRun);
            loadingThread.start();
        }
    }

    public void stop() {
        if (isLoading) {
            try {
                isLoading = false;
                loadingThread.join();
                setText((" "));
            } catch (InterruptedException ex) {
                System.err.println("Error : Failed to the animation of loading");
            }
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
