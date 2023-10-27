package mk.ukim.finki.homework_1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main_1_Write {
    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel(); // 2 The model in Jena is the Graph in RDF

        String personURI = "https://github.com/DimitrijaT";

        Resource dimitrijaTimeski = model.createResource(personURI); // 3

        String fullName = "Dimitrija Timeski";
        dimitrijaTimeski.addProperty(VCARD.FN, fullName);

        String address = "Skopje, Macedonia";
        dimitrijaTimeski.addProperty(VCARD.ADR, address);

        String email = "dimitrijatimeskidimitrija@gmail.com";
        dimitrijaTimeski.addProperty(VCARD.EMAIL, email);

        String birthday = "20001129";
        dimitrijaTimeski.addProperty(VCARD.BDAY, birthday);

        String givenName = "Dimitrija";
        dimitrijaTimeski.addProperty(VCARD.Given, givenName);

        String familyName = "Timeski";
        dimitrijaTimeski.addProperty(VCARD.Family, familyName);

        //FOAF

        dimitrijaTimeski.addProperty(FOAF.name, fullName);
        dimitrijaTimeski.addProperty(FOAF.birthday, birthday);
        dimitrijaTimeski.addProperty(FOAF.accountName, givenName);
        dimitrijaTimeski.addProperty(FOAF.givenName, givenName);
        dimitrijaTimeski.addProperty(FOAF.familyName, familyName);

        String person2URI = "https://github.com/DimeJovanovski";
        Resource anotherPerson = model.createResource(person2URI);

        dimitrijaTimeski.addProperty(FOAF.knows, anotherPerson);


        System.out.println("Printing with model.listStatements():");
        StmtIterator iter = model.listStatements();
        // In format Subject - Predicate - Object
        while (iter.hasNext()) {

            Statement stmt = iter.nextStatement(); // Ја земаме тројката (SPO)
            Resource subject = stmt.getSubject(); // Го земаме S
            Property predicate = stmt.getPredicate(); // Го земаме P
            RDFNode object = stmt.getObject(); // Го земаме O


            System.out.print(subject.toString()); // Печатење на S
            System.out.print(" " + predicate.toString() + " "); // Печатење на P

            // Печатење на O (О може да биде Resource или Literal)
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }

        System.out.println("=====================================");

        System.out.println("Print with model.write() in RDF/XML");
        model.write(System.out);

        System.out.println("Print with model.write() in pretty RDF/XML:");
        model.write(System.out, "RDF/XML-ABBREV");

        System.out.println("Print with model.write() in N-TRIPLES:");
        model.write(System.out, "N-TRIPLES");

        System.out.println("Print with model.write() in Turtle:");
        model.write(System.out, "TURTLE"); // or "TTL"

        System.out.println("Print with model.write() in N3:");
        model.write(System.out, "N3");

    }
}