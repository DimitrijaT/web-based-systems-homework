package mk.ukim.finki.Exercises1_JenaAPI;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Example4_ControllingPrefixes {

    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel();
        String nsA = "http://somewhere/else#";
        String nsB = "http://nowhere/else#";
        Resource root = m.createResource(nsA + "root");
        Property P = m.createProperty(nsA + "P");
        Property Q = m.createProperty(nsB + "Q");
        Property x = m.createProperty(nsA + "x");
        Property y = m.createProperty(nsA + "y");
        Property z = m.createProperty(nsA + "z");
        m.add(root, P, x).add(root, P, y).add(y, Q, z);

        System.out.println("# -- no special prefixes defined");
        m.write(System.out, "TTL");


        System.out.println("\n# -- nsA defined");
        m.setNsPrefix("nsA", nsA);
        m.write(System.out, "TTL");

        System.out.println("\n# -- nsA and cat defined");
        m.setNsPrefix("cat", nsB);
        m.write(System.out, "TTL");
    }
}
