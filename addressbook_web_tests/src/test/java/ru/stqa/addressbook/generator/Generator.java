package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.stqa.addressbook.tests.TestBase.randomFile;

public class Generator {
    //--type groups --output groups.json --format json --count 3
    //--type contacts --output contacts.json --format json --count 3
    //--type contacts --output contacts.xml --format xml --count 3
    //--type contacts --output contacts.yaml --format yaml --count 3
    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            return new NullPointerException("Неизвестный тип данных " + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withMiddleName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images")));

//        ArrayList<ContactData> result = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            result.add(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(i*10))
//                    .withLastName(CommonFunctions.randomString(i*10))
//                    .withMiddleName(CommonFunctions.randomString(i*10))
//                    .withAddress(CommonFunctions.randomString(i*10))
//                    .withEmail(CommonFunctions.randomString(i*10))
//                    .withMobile(CommonFunctions.randomString(i*10))
//                    .withPhoto(randomFile("src/test/resources/images"))
//            );
//        }
//        return result;
    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                    .withName(CommonFunctions.randomString(10))
                    .withHeader(CommonFunctions.randomString(10))
                    .withFooter(CommonFunctions.randomString(10)));

//        ArrayList<GroupData> result = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            result.add(new GroupData()
//                    .withName(CommonFunctions.randomString(i * 10))
//                    .withHeader(CommonFunctions.randomString(i * 10))
//                    .withFooter(CommonFunctions.randomString(i * 10))
//            );
//        }
//        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            //mapper.writeValue(new File(output), data);
            var json = mapper.writeValueAsString(data);
            try (var writer = new FileWriter(output);) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
