package mk.ukim.finki.Examples;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.IOException;
import java.io.InputStream;

public class Example3_ReadingRDF {

    static final String inputFileName = "C:\\Users\\dimit\\Documents\\7th Semester\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\src\\main\\java\\mk\\ukim\\finki\\Examples\\vc-db-1.rdf";

    public static void main(String[] args) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        // The empty parameter can make all relative URI's in the file absolute
        // Example: to transform "Teacher" to "http://example.com/Teacher"
        // But because we don't want to do that, we pass an empty string
        model.read(in, "");

        // write it to standard out, in Turtle format
        model.write(System.out, "TTL"); // or "TURTLE"

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
