package mk.ukim.finki.homework_1.examples;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class Example1 {

    public static void main(String[] args) {
        String personURI = "https://plus.google.com/+JohnSmith";
        String fullName = "John Smith";

        Model model = ModelFactory.createDefaultModel();
        Resource johnSmith = model.createResource(personURI);

        johnSmith.addProperty(VCARD.FN, fullName);
    }
}
