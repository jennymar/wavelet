import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

class Handler implements URLHandler {

    ArrayList<String> strs = new ArrayList<String>();

    public String handleRequest(URI url) {


        // print out array list
        if (url.getPath().equals("/")) {
            return "List of strings: " + strs.toString();
        } 

        // add a string to the array list
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strs.add(parameters[1]);
                //return String.format("Added %s!", parameters[1]);
                //return strs.toString();
            }
            return String.format("Added %s!", parameters[1]);
            
        } 

        // search for substring
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String result = "";

                // loop through the strs array list
                for (int i=0; i<strs.size(); i++) {
                    if (strs.get(i).contains(parameters[1])) {
                        result += strs.get(i) + " ";
                    }
                }

                return result;
            }

            
        }
        return "404 Not Found!";
    }
    
}



class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
