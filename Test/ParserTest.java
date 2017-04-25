package Test;

import Parser.PersistenceParser;
import Parser.SearchParser;
import Parser.TransactionParser;
import Repository.MethodRepository;
import Repository.SoftwareRepository;
import com.book.Book;
import org.junit.Assert;

class ParserTest {
    PersistenceParser persistenceParser = new PersistenceParser();
    TransactionParser transactionParser = new TransactionParser(persistenceParser);
    SearchParser searchParser = new SearchParser(transactionParser);

    @org.junit.jupiter.api.Test
    void testeParserInsert() {
        MethodRepository methodRepository = searchParser.parse("insert");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Persistence");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserUpdate() {
        MethodRepository methodRepository = searchParser.parse("update");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Persistence");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserDelete() {
        MethodRepository methodRepository = searchParser.parse("delete");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Persistence");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserSelect() {
        MethodRepository methodRepository = searchParser.parse("select");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Persistence");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserBuy() {
        MethodRepository methodRepository = searchParser.parse("buy");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Transaction");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserSell() {
        MethodRepository methodRepository = searchParser.parse("sell");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Transaction");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserSearch() {
        MethodRepository methodRepository = searchParser.parse("search");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Search");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }
    @org.junit.jupiter.api.Test
    void testeParserSaarch() {
        //saarch anstatt search
        MethodRepository methodRepository = searchParser.parse("saarch");
        SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
        Class c1  = softwareRepository.getClass("Search");

        Assert.assertEquals(c1.getClass(),methodRepository.getClazz().getClass());
    }

}