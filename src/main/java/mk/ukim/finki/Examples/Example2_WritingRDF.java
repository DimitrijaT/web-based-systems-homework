package mk.ukim.finki.homework_1.examples;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class Example2_WritingRDF {

    public static void main(String[] args) {
        // definitions
        String personURI = "https://plus.google.com/+JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        // create an empty Model
        // this default Model is memory based
        Model model = ModelFactory.createDefaultModel();

        // create the resource and add the properties cascading style
        Resource johnSmith =
                model.createResource(personURI)
                        .addProperty(VCARD.FN, fullName)
                        .addProperty(VCARD.N, model.createResource()
                                .addProperty(VCARD.Given, givenName)
                                .addProperty(VCARD.Family, familyName));

        Statement stmt = null;
        Resource subject = null;
        Property predicate = null;
        RDFNode object = null;

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            stmt = iter.nextStatement(); // get next statement
            subject = stmt.getSubject(); // get the subject
            predicate = stmt.getPredicate(); // get the predicate
            object = stmt.getObject(); // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }

        System.out.println("=====================================");

        System.out.println("Print as RDF/XML");
        model.write(System.out);

        System.out.println("Print as pretty RDF/XML:");
        model.write(System.out, "RDF/XML-ABBREV");

        System.out.println("Print as N-TRIPLES:");
        model.write(System.out, "N-TRIPLES");

        System.out.println("Print as Turtle:");
        model.write(System.out, "TURTLE"); // or "TTL"

    }
}
