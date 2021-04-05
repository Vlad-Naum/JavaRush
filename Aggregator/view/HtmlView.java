package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";


    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    private String getUpdatedFileContent(List<Vacancy> list) {
        Document dok = null;
        try {
            dok = getDocument();
            Element elem = dok.getElementsByClass("template").first();
            Element elemClone = elem.clone();
            elemClone.removeClass("template");
            elemClone.removeAttr("style");
            dok.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            for (Vacancy vacancy : list) {
                Element currentElement = elemClone.clone();
                currentElement.getElementsByClass("city").first().text(vacancy.getCity());
                currentElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                currentElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = currentElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                elem.before(currentElement.outerHtml());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return dok.html();
    }

    private void updateFile(String str){
        try(FileWriter fw = new FileWriter(filePath)){
            fw.write(str);
        }
        catch(Exception e){

        }

    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        Document html = Jsoup.parse(file, "UTF-8");
        return html;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
