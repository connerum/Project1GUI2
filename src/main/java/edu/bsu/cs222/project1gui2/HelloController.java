package edu.bsu.cs222.project1gui2;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label article_lb;
    @FXML
    private Label revisions_lb;
    @FXML
    private Label result_lb;

    @FXML
    private TextField title_tb;

    @FXML
    private Button search_btn;

    @FXML
    private TextArea results_tb;

    @FXML
    protected void onButtonClick() throws IOException {
        result_lb.setText(title_tb.getText());
        String userInput = title_tb.getText();

        RevisionParser revisionParser = new RevisionParser();

        InputStream inputStream0 = revisionParser.encodedUrl(userInput);
        Redirects redirects = new Redirects();
        System.out.println(redirects.checkRedirect(inputStream0));

        InputStream inputStream = revisionParser.encodedUrl(userInput);
        ArrayList<String> usersList = revisionParser.parseUsers(inputStream);

        InputStream inputStream1 = revisionParser.encodedUrl(userInput);
        ArrayList<String> timestampsList = revisionParser.parseTimestamp(inputStream1);

        Formatter formatter = new Formatter();
        formatter.formatted(usersList, timestampsList);
        int count = 0;
        for (String user : usersList) {
            String Line = user + "\t\t" + timestampsList.get(count);
            results_tb.appendText(Line);
            results_tb.appendText("\n");
            count += 1;
        }
    }
}