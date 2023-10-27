package mk.ukim.finki.homework_1.examples;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class Example6_OperationsOnModels {

    static final String inputFileName_1 = "C:\\Users\\dimit\\Documents\\7th Semester\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\src\\main\\java\\mk\\ukim\\finki\\Examples\\vc-db-1.rdf";
    static final String inputFileName_2 = "C:\\Users\\dimit\\Documents\\7th Semester\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\src\\main\\java\\mk\\ukim\\finki\\Examples\\vc-db-2.rdf";

    public static void main(String[] args) {

        // create an empty model1
        Model model1 = ModelFactory.createDefaultModel();

        // create an empty model2
        Model model2 = ModelFactory.createDefaultModel();

        // read the input file

        InputStream in1 = FileManager.get().open(inputFileName_1);
        if (in1 == null) {
            throw new IllegalArgumentException("File: " + inputFileName_1 + " not found");
        }

        InputStream in2 = FileManager.get().open(inputFileName_2);
        if (in2 == null) {
            throw new IllegalArgumentException("File: " + inputFileName_2 + " not found");
        }

        // ===============================================================

        // We can read the two models and then create a union in  a third model.
        model1.read(in1, "");
        model2.read(in2, "");

        // merge the graphs
        Model model = model1.union(model2);

        // print the graph as Turtle ("TTL" or "Turtle")
        model.write(System.out, "TTL");

    }
}
