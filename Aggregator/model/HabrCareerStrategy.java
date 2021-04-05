package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy {
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        Vacancy vacancy;
        int i = 0;
        Document doc;
        try {
            while (true) {

                doc = getDocument(searchString, i++);
                Elements elements = doc.getElementsByClass("job");
                if (elements.isEmpty()) {
                    break;
                }
                for (Element elem : elements) {
                    Elements title = elem.getElementsByClass("title");
                    Elements links = title.get(0).getElementsByTag("a");
                    Elements locations = elem.getElementsByClass("location");
                    Elements companyName = elem.getElementsByClass("company_name");
                    Elements salary = elem.getElementsByClass("count");

                    vacancy = new Vacancy();
                    vacancy.setSiteName("career.habr.com");
                    vacancy.setTitle(links.get(0).text());
                    vacancy.setUrl("https://career.habr.com" + links.get(0).attr("href"));
                    vacancy.setCity(locations.size() > 0 ? locations.get(0).text() : "");
                    vacancy.setCompanyName(companyName.get(0).text());
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");

                    list.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        //return Jsoup.connect("http://javarush.ru/testdata/big28data2.html").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36").referrer("no-referrer-when-downgrade").get();
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36").referrer("no-referrer-when-downgrade").get();
    }
}
