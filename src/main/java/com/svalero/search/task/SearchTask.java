package com.svalero.search.task;

import javafx.concurrent.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SearchTask extends Task<Void> {

    private File webFile;
    private File keyFile;

    public SearchTask(File webFile, File keyFile) {
        this.webFile = webFile;
        this.keyFile = keyFile;
    }

    @Override
    protected Void call() {
        updateMessage("Processing...");
        try {
            Document document = Jsoup.connect(webAddress).get();
            Elements elements = document.select("p");

            int totalElements = elements.size();
            int processedElements = 0;

            for (Element element : elements) {
                // Perform keyword search logic here
                // Update statistics and progress
                processedElements++;
                updateProgress(processedElements, totalElements);
                updateMessage("Keyword: " + keyword + ", Occurrences: " + occurrences);
            }

            // Process results if needed
            List<String> results = processResults();
            String statistics = getStatisticsFromResults(results);

            // Update UI with statistics
            updateMessage("Processing completed.");
            updateResultArea(statistics);
        } catch (IOException e) {
            updateMessage("Error processing web page.");
            e.printStackTrace();
        }
        return null;
    }

    private List<String> processResults() {
        // Perform any additional processing on results if needed
        return null;
    }

    private String getStatisticsFromResults(List<String> results) {
        // Process the results and generate statistics
        StringBuilder statistics = new StringBuilder();
        // ...
        return statistics.toString();
    }

    private void updateResultArea(String statistics) {
        // Update the result area with statistics
        resultArea.appendText(statistics);
    }
}
