package mk.ukim.finki.Homework_1;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

public class Main_2_Read {

    static final String inputFileName = "C:\\Users\\dimit\\Documents\\7th Semester\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\src\\main\\java\\mk\\ukim\\finki\\Homework_1\\data\\data.xml";

    public static void main(String[] args) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        // write it
        model.write(System.out, "TTL");


        // Grab the resource that represents us
        Resource dimitrijaTimeski = model.getResource("https://github.com/DimitrijaT");

        // Get the fullname, givenName and familyName properties
        // Literals
        System.out.println("Fullname: " + dimitrijaTimeski.getProperty(VCARD.FN).getString());
        System.out.println("Given name: " + dimitrijaTimeski.getProperty(VCARD.Given).getString());
        System.out.println("Family name: " + dimitrijaTimeski.getProperty(VCARD.Family).getString());

        // Resources
        System.out.println("Knows: " + dimitrijaTimeski.getProperty(FOAF.knows).getResource().getURI());

    }
}
