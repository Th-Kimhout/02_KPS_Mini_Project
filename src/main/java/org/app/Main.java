package org.app;

import org.app.utilies.DBConfig;
import org.app.utilies.UserInput;
import org.app.view.ClientView;


public class Main {
    public static void main(String[] args) {
        ClientView.mainView();
        UserInput.closeScanner();
    }
}