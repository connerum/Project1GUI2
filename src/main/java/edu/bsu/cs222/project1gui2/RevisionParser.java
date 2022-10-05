package edu.bsu.cs222.project1gui2;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class RevisionParser {
    public InputStream encodedUrl(String Title) throws MalformedURLException {
        String apiBaseUrl = ("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=");
        String revisionLimit = ("&redirects=1&rvlimit=27");
        String encodedTitleString = URLEncoder.encode(Title, Charset.defaultCharset());
        String encodedUrl = apiBaseUrl + encodedTitleString + revisionLimit;
        try {
            URL url = new URL(encodedUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "WikipediaRevisionReader/0.1 (landen.finlinson@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            return inputStream;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> parseUsers(InputStream inputStream) throws IOException {
        JSONArray usersArray = JsonPath.read(inputStream, "$..user");
        int listLength = usersArray.size();
        ArrayList<String> usersList = new ArrayList<>();

        int i = 0;
        while (i < listLength){
            usersList.add(i,usersArray.get(i).toString());
            i++;
        }
        return usersList;
    }
    public ArrayList<String> parseTimestamp(InputStream inputStream) throws IOException {
        JSONArray timestampArray = JsonPath.read(inputStream, "$..timestamp");
        int listLength = timestampArray.size();
        ArrayList<String> timestampList = new ArrayList<>();

        int i = 0;
        while (i < listLength){
            timestampList.add(i, timestampArray.get(i).toString());
            i++;
        }

        return timestampList;
    }
}

