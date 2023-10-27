package mk.ukim.finki.homework_1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class Main_3_OutsideData {

    static String inputFileName = "C:\\Users\\dimit\\Documents\\7th Semester\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\WBS_1_JenaRDFAPI_DimitrijaTimeski_203235\\src\\main\\java\\mk\\ukim\\finki\\hifm-dataset.ttl";
    static String drugbankPrefix = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/";

    static String hifmOntPrefix = "http://purl.org/net/hifm/ontology#";

    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        model.read(in, null, "TTL"); // Third parameter tells us the type!

//        model.write(System.out, "TTL");

        readAllBrandName(model);

        readOneDrug(model, "http://purl.org/net/hifm/data#987972");

        findSimilarDrugs(model, "http://purl.org/net/hifm/data#987972");

        findDrugPriceAndSimilarProducts(model, "http://purl.org/net/hifm/data#987972");
    }


    public static void readAllBrandName(Model model) {

        Property brandNameProperty = model.createProperty(drugbankPrefix, "brandName");
        List<String> drugs = new ArrayList<>();

        Selector selector = new SimpleSelector(null, brandNameProperty, (String) null);
        StmtIterator sIter = model.listStatements(selector);
        if (sIter.hasNext()) {
            System.out.println("The database contains these drugs:");
            while (sIter.hasNext()) {
                drugs.add("  " + sIter.nextStatement()
                        .getObject()
                        .toString());
            }
        } else {
            System.out.println("No drugs were found in the database");
        }
        drugs.sort(String::compareTo);
        System.out.println(drugs);
    }

    public static void readOneDrug(Model model, String drug) {

        Property brandNameProperty = model.createProperty(drugbankPrefix, "brandName");

        // List all relations and values for a drug
        Resource drugResource = model.getResource(drug);
        String drugName = drugResource.getProperty(brandNameProperty).getString();

        StmtIterator sIter = drugResource.listProperties(); // All Relations
        System.out.println("The drug " + drugName + " has these relations:");
        while (sIter.hasNext()) {
            Statement stmt = sIter.nextStatement();
            System.out.println(stmt.getPredicate().getLocalName() + " - " + stmt.getObject());
        }

    }

    public static void findSimilarDrugs(Model model, String drug) {

        Property brandNameProperty = model.createProperty(drugbankPrefix, "brandName");
        Property similarToProperty = model.createProperty(hifmOntPrefix, "similarTo");

        // List all relations and values for a drug
        Resource drugResource = model.getResource(drug);
        String drugName = drugResource.getProperty(brandNameProperty).getString();

        Selector selector = new SimpleSelector(drugResource, similarToProperty, (String) null);
        StmtIterator sIter = model.listStatements(selector);
        System.out.println("The following drugs are similar to " + drugName + ":");
        while (sIter.hasNext()) {
            Statement stmt = sIter.nextStatement();
            System.out.print(stmt.getObject() + " Name: ");
            System.out.println(stmt.getResource().getProperty(brandNameProperty).getString());
        }

    }

    public static void findDrugPriceAndSimilarProducts(Model model, String drug) {

        Property brandNameProperty = model.createProperty(drugbankPrefix, "brandName");
        Property refPriceWithVATProperty = model.createProperty(hifmOntPrefix, "refPriceWithVAT");
        Property similarToProperty = model.createProperty(hifmOntPrefix, "similarTo");

        Resource drugResource = model.getResource(drug);
        String drugPrice = drugResource.getProperty(refPriceWithVATProperty).getString();
        String drugName = drugResource.getProperty(brandNameProperty).getString();

        Selector selector = new SimpleSelector(drugResource, similarToProperty, (String) null);
        StmtIterator sIter = model.listStatements(selector);
        System.out.println("NAME: " + drugName + "\t PRICE: " + drugPrice + "\nSee Also:");

        while (sIter.hasNext()) {
            Statement stmt = sIter.nextStatement();

            String name = stmt.getProperty(brandNameProperty).getString();
            String price = stmt.getProperty(refPriceWithVATProperty).getString();

            System.out.print(stmt.getObject() + " ");
            System.out.println("NAME: " + name + "\t PRICE: " + price);
        }
    }


}


