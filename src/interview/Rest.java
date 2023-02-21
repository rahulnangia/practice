package interview;

import com.google.gson.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Rest {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        for(String a: authors("olalonde", 1)){
            System.out.println(a);
        }
    }

    static int pages;
    public static List<String> authors(String username, int limit) throws URISyntaxException, IOException, InterruptedException {
        LinkedList<Pair<String, Integer>> list = new LinkedList<>();
        int curr = 1;
        do{
            list.addAll(getPage(username, 1));
            curr++;
        }while(curr<=pages);

        Collections.sort(list, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return -1 * Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        List<String> resp = new LinkedList<>();
        for(int i=0;i<limit;i++){
            resp.add(list.removeFirst().getKey());
        }
        return resp;
    }



    private static List<Pair<String, Integer>> getPage(String user, int page) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("https://jsonmock.hackerrank.com/api/articles?author=%s&page=%d", user, page)))
                .GET()
                .build();
        HttpResponse<String> resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        resp.body();

        String body = resp.body();
        // 1. JSON file to Java object


        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(resp.body());
        JsonObject asJsonObject = element.getAsJsonObject();
        pages = asJsonObject.get("total_pages").getAsInt();

        List<Pair<String, Integer>> list = new LinkedList<>();
        JsonArray array = asJsonObject.get("data").getAsJsonArray();
        for(JsonElement d: array){
            JsonElement storyTitleElement = d.getAsJsonObject().get("story_title");
            JsonElement titleElement = d.getAsJsonObject().get("title");
            if(storyTitleElement.isJsonNull() && titleElement.isJsonNull()){
                continue;
            }

            String name  = titleElement.isJsonNull() ? storyTitleElement.getAsString() : titleElement.getAsString();
            JsonElement numCommentsElements = d.getAsJsonObject().get("num_comments");
            list.add(new ImmutablePair<>(name, numCommentsElements.isJsonNull() ? 0 :  numCommentsElements.getAsInt()));
        }

        return list;


    }

    static class Resp {
        public int total_pages;
        public Data[] data;
    }

    static class Data {

        String title;
        String storyTitle;
        int numComments;

    }
}
