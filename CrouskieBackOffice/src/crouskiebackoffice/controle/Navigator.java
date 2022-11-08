package crouskiebackoffice.controle;

import java.awt.CardLayout;
import java.util.Map;
import java.util.Stack;
import javax.swing.JPanel;

public class Navigator {

    private static Navigator instance;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private final Stack<JPanel> path = new Stack<>();
    private final Stack<String> pathName = new Stack<>();

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public Navigator() {
    }

    public void init(JPanel cardPanel, String root) {
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        path.push(cardPanel);
        pathName.push(root);
    }

    public void goTo(JPanel panel, String name) {
        path.push(panel);
        pathName.push(name);
        cardPanel.add(pathName.lastElement(), path.lastElement());
        cardLayout.show(cardPanel, pathName.lastElement());
    }

    public void goBack() {
        if (path.size() > 1) {
            path.pop();
            pathName.pop();
            cardLayout.show(cardPanel, pathName.lastElement());
        }
    }
}
