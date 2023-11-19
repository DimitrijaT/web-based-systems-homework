package mk.ukim.finki.Exercises1_JenaAPI;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

import java.io.PrintWriter;

public class Example8_MoreAboutLiterals {

    public static void main(String[] args) {

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // ===============================================================

        // Jena supports denoting a language and the datatype of a literal
        Resource r = model.createResource("http://dbpedia.org/resource/Macedonia");

        // add the rdfs:label properties in different languages
        r.addProperty(RDFS.label, model.createLiteral("Macedonia", "en"))
                .addProperty(RDFS.label, model.createLiteral("Македонија", "mk"));

        model.write(new PrintWriter(System.out), "TTL");

        // ===============================================================

        // create the resource
        r = model.createResource("http://dbpedia.org/resource/Eleven");

        // 1. add the rdfs:label properties
        r.addLiteral(RDFS.label, "11")
                .addLiteral(RDFS.label, (float) 11);

        // 2. write out the graph
        System.out.println("First print:");
        model.write(System.out, "TTL");

        // 3. add a new rdfs:label property
        r.addProperty(RDFS.label, "11");

        // 4. write out the graph, again
        System.out.println("Second print:");
        model.write(System.out, "TTL");
    }
}
