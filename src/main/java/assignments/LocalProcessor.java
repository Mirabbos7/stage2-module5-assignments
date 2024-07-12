package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private String processorName;
    private Long period = 10000000000000L;
    protected String ProcessorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private LinkedList<String> stringArrayList = new LinkedList<>();

    public LocalProcessor(String processorName, Long period, String processorVersion, Integer valueOfCheap,
                          Scanner informationScanner, LinkedList<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.ProcessorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void listIterator(LinkedList<String> stringList) {
        stringArrayList = new LinkedList<>(stringList);
        for (int i = 0; i < Math.min(period, stringArrayList.size()); i++) {
            System.out.println(stringArrayList.get(i).hashCode());
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(LinkedList<String> stringList) {
        StringBuilder fullName = new StringBuilder(processorName);
        for (String str : stringList) {
            fullName.append(str).append(' ');
        }
        processorName = fullName.toString();
        return processorName;
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) throws FileNotFoundException {
        informationScanner = new Scanner(file);
        StringBuilder versionBuilder = new StringBuilder(ProcessorVersion);
        while (informationScanner.hasNext()) {
            versionBuilder.append(informationScanner.nextLine());
        }
        ProcessorVersion = versionBuilder.toString();
        System.out.println(ProcessorVersion);
    }

    public Scanner getInformationScanner(){
        return new Scanner(System.in); // Example: return a new Scanner from System.in
    }
    public static void main(String[] args) {
        LocalProcessor localProcessor = new LocalProcessor("Processor1", 10L, "v1.0", 100, null, new LinkedList<>());

        // Test listIterator method
        LinkedList<String> stringList = new LinkedList<>();
        stringList.add("Item1");
        stringList.add("Item2");
        stringList.add("Item3");
        localProcessor.listIterator(stringList);

        // Test fullNameProcessorGenerator method
        LinkedList<String> nameList = new LinkedList<>();
        nameList.add("John");
        nameList.add("Doe");
        System.out.println(localProcessor.fullNameProcessorGenerator(nameList));

        // Test readFullProcessorName method
        try {
            File file = new File("C:\\Users\\mirab\\stage2-module5-assignments\\src\\main\\resources\\text.txt"); // Ensure this file exists with some content
            localProcessor.readFullProcessorName(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Test getInformationScanner method
        Scanner scanner = localProcessor.getInformationScanner();
        System.out.println("Enter some text:");
        System.out.println("You entered: " + scanner.nextLine());
    }
}