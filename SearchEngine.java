import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // Sdding path queries for specific search engines
    List<String> list =new ArrayList<String>(); 
    list.add("pineapple");
    list.add("app");
    list.add("apple");

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("String: %s", list);
        } //else if (url.getPath().equals("/increment")) {
            //list += 1;
            //return String.format("Number added!"); }
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("pineapple")) {
                    list += Integer.parseInt(parameters[1]);
                    return String.format("String added! It's now %s", parameters[1]);
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing String!");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}