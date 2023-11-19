package mk.ukim.finki.Exercises1_JenaAPI;

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
        // VCARD.N's range is a composite of VCARD.Given and VCARD.Family
        Resource johnSmith =
                model.createResource(personURI)
                        .addProperty(VCARD.FN, fullName)
                        .addProperty(VCARD.N, model.createResource() // nested blank node
                                .addProperty(VCARD.Given, givenName)
                                .addProperty(VCARD.Family, familyName));

        Statement stmt = null;
        Resource subject = null;
        Property predicate = null;
        RDFNode object = null;

        // list the statements in the graph
        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            stmt = iter.nextStatement();        // get next statement
            subject = stmt.getSubject();        // get the subject (S)
            predicate = stmt.getPredicate();    // get the predicate (P)
            object = stmt.getObject();          // get the object (O) Either a Resource or a Literal

            // print the subject
            System.out.print(subject.toString());

            // print the predicate
            System.out.print(" " + predicate.toString() + " ");

            // print the object
            if (object instanceof Resource) System.out.print(object.toString()); // object is a resource
            else System.out.print(" \"" + object.toString() + "\""); // object is a literal
            System.out.println(" .");
        }

        System.out.println("=====================================");

        System.out.println("Print as RDF/XML");
        model.write(System.out);

        System.out.println("Print as pretty RDF/XML:");
        model.write(System.out, "RDF/XML-ABBREV");

        // Fully unpacked turtle, no abbreviations
        System.out.println("Print as N-TRIPLES:");
        model.write(System.out, "N-TRIPLES");

        System.out.println("Print as Turtle:");
        model.write(System.out, "TURTLE"); // or "TTL"

    }
}
