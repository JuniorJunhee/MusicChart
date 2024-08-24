package jsoup1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Music_chart {
    public static void main(String[] args) {
    	String url = "https://www.melon.com/chart/index.htm";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

            Elements element = doc.select("div.ellipsis>span"); 
            System.out.println("실시간 멜론차트 10위까지 출력하기.");
            System.out.println("---------------");

            int cnt = 0;
            String title = null;
            boolean status = true;
            List<Map<String, String>> songInfo = new ArrayList<>();

            for (Element el : element.select("a")) {
                cnt ++;
                if(cnt == 21){
                    break;
                }
                String a = el.text();
                String result = a.replaceFirst("������", "");

                if (!status) {
                    Map<String, String> data = new HashMap<>();

                    data.put(result, title);
                    songInfo.add(data);
                } else {
                    title = result;
                }
                
                status = !status;
            }

            for (Map<String, String> i : songInfo) {
                i.forEach((k, v) -> System.out.println("노래제목: " + v +" - 가수: " + k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}