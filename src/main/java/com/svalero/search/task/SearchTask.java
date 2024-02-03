package com.svalero.search.task;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTask extends Task<HashMap<String, Integer>> {


    private String webFile;
    private List<String> keyFile;

    public SearchTask(String webFile, List<String> keyFile) {
        this.webFile = webFile;
        this.keyFile = keyFile;
    }

    @Override
    protected HashMap<String, Integer> call() {

        HashMap counter = new HashMap<String, Integer>();
        try {
            System.out.println(keyFile);
            Document document = Jsoup.connect(webFile).get();
            Elements elements = document.select("p");

            int totalElements = elements.size();
            System.out.println(totalElements);
            int processedElements = 0;
            int contador = 0;

            for (String keyWord : keyFile) {
                System.out.println(keyWord);
                contador = 0;
                processedElements =0;

                for (Element element : elements) {
                    Thread.sleep(1000);

                    String text = element.text();
                    contador += wordCount(text, keyWord);
                    processedElements++;
                    updateProgress(processedElements, totalElements);
                    updateMessage("Processed: " + processedElements + "/" + totalElements + ", Keyword: " + keyWord + ", Occurrences: " + contador);

                }
                counter.put(keyWord, contador);
            }

            updateMessage("Processing completed.");
            
        } catch (IOException e) {
            updateMessage("Error processing web page: "+ e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }
    private static int wordCount (String text, String word) {
        int count = 0;
        int index = text.indexOf(word);
        while (index != -1) {
            count++;
            index = text.indexOf(word, index + 1);
        }
        return count;
    }

}
