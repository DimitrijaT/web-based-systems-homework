package mk.ukim.finki.Examples;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class Example5_NavigatingAModel {
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

        // retrieve the John Smith vcard resource from the model
        Resource jSmithVcard = model.getResource(personURI);

        // retrieve the value of the N property
        Resource name_1 = (Resource) jSmithVcard.getProperty(VCARD.N)
                .getObject();

        // retrieve the value of the N property
        Resource name_2 = jSmithVcard.getProperty(VCARD.N)
                .getResource(); // This returns the resource from the right side of the triple

        // retrieve the full name property
        String fullNameProperty = jSmithVcard.getProperty(VCARD.FN).getString();

        // RDF permits a resource to repeat a property, for example John might have more than one nickname:
        // Let's give him two:
        jSmithVcard.addProperty(VCARD.NICKNAME, "Smithy")
                .addProperty(VCARD.NICKNAME, "Adman");

        // The result of calling jSmithVcard.getProperty(VCARD.NICKNAME) is indeterminate.
        // If it is possible that a property may occur more than once, then the Resource.listProperties(Property p) method can be used to return an iterator which will list them all.

        // set up the output
        System.out.println("The nicknames of " + fullName + " are:");

        // list the nicknames
        StmtIterator iter_1 = jSmithVcard.listProperties(VCARD.NICKNAME);
        while (iter_1.hasNext())
            System.out.println("    " + iter_1.nextStatement().getObject().toString());


        // Model.listSubjectsWithProperty(Property p, RDFNode o)
        // select all the resources with a VCARD.FN property
        ResIterator iter_2 = model.listSubjectsWithProperty(VCARD.FN);

        if (iter_2.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (iter_2.hasNext())
                System.out.println("  " + iter_2.nextResource()
                        .getProperty(VCARD.FN)
                        .getString());
        } else
            System.out.println("No vcards were found in the database");


        queryingAModel(model);

    }

    private static void queryingAModel(Model model) {

        // SimpleSelector(subject, predicate, object)
        Selector selector = new SimpleSelector(null, VCARD.FN, (String) null);

        StmtIterator sIter = model.listStatements(selector);

        if (sIter.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (sIter.hasNext()) {
                System.out.println("  " + sIter.nextStatement()
                        .getObject()
                        .toString());
            }
        } else {
            System.out.println("No vcards were found in the database");
        }

    }
}
