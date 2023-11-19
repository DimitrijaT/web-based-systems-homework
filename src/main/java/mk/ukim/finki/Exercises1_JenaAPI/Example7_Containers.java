package mk.ukim.finki.Exercises1_JenaAPI;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class Example7_Containers {

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

        // ===============================================================

        // create a bag
        Bag smiths = model.createBag();

        // select all the resources with a VCARD.FN property
        // whose value ends with "Smith"
        StmtIterator iter = model.listStatements(
                new SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                    @Override
                    public boolean selects(Statement s) {
                        return s.getString().endsWith("Smith");
                    }
                });

        // add the Smith's to the bag
        while (iter.hasNext()) {
            smiths.add(iter.nextStatement().getSubject());
        }

        // print out the members of the bag
        NodeIterator iter2 = smiths.iterator();
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
                System.out.println("  " + ((Resource) iter2.next()).getProperty(VCARD.FN).getString());
            }
        } else {
            System.out.println("The bag is empty");
        }


        System.out.println("Print as Turtle:");
        model.write(System.out, "TURTLE"); // or "TTL"


    }
}
