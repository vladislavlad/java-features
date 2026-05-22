package software.darkmatter.java24;

import javax.crypto.KEM;
import java.io.InputStream;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.NamedParameterSpec;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Gatherers.fold;
import static java.util.stream.Gatherers.mapConcurrent;
import static java.util.stream.Gatherers.scan;
import static java.util.stream.Gatherers.windowFixed;
import static java.util.stream.Gatherers.windowSliding;

public class Java24 {

    /*
     * JEP 485: Stream Gatherers (GA)
     */
    public void streamGatherers() {
        var lines = List.of("Hello", "World", "Foo", "Bar", "Baz");

        // windowFixed: group elements into fixed-size batches
        var chunked = lines.stream()
                           .gather(windowFixed(2))
                           .toList();
        System.out.println("Chunked: " + chunked);

        // windowSliding: sliding window of fixed size
        var windowed = lines.stream()
                            .gather(windowSliding(3))
                            .toList();
        System.out.println("Windowed: " + windowed);

        // scan: running aggregation (running sum)
        var numbers = List.of(1, 2, 3, 4, 5);
        var runningSum = numbers.stream()
                                .gather(scan(() -> 0, Integer::sum))
                                .toList();
        System.out.println("Running sum: " + runningSum);

        // fold: single final result from all elements
        var total = numbers.stream()
                           .gather(fold(() -> 0, Integer::sum))
                           .toList();
        System.out.println("Fold total: " + total);

        // mapConcurrent: concurrent mapping of stream elements
        var uppercased = lines.stream()
                              .gather(mapConcurrent(2, String::toUpperCase))
                              .toList();
        System.out.println("Concurrent upper: " + uppercased);
    }

    /*
     * JEP 484: Class-File API (GA)
     */
    public void classFileApi() throws Exception {

        ClassFile classFile = ClassFile.of();

        // Parse java/lang/String.class
        byte[] classBytes;

        try (InputStream in = String.class.getResourceAsStream("String.class")) {
            if (in == null) {
                throw new IllegalStateException("Cannot load String.class");
            }

            classBytes = in.readAllBytes();
        }

        ClassModel classModel = classFile.parse(classBytes);

        // Print class info
        System.out.println("Class: " + classModel.thisClass());
        System.out.println("Superclass: " + classModel.superclass());
        System.out.println("Flags: " + classModel.flags());

        // Iterate over methods
        for (ClassElement element : classModel) {
            if (element instanceof MethodModel method) {
                System.out.println(
                    "Method: "
                    + method.methodName().stringValue()
                    + " "
                    + method.methodTypeSymbol()
                );
            }
        }

        // Build a simple class
        byte[] generatedBytes = classFile.build(
            ClassDesc.of("Hello"),
            classBuilder -> {

                classBuilder.withSuperclass(
                    ClassDesc.of("java.lang.Object")
                );

                classBuilder.withMethod(
                    "<init>",
                    MethodTypeDesc.ofDescriptor("()V"),
                    ClassFile.ACC_PUBLIC,
                    methodBuilder -> methodBuilder.withCode(
                        codeBuilder ->
                            codeBuilder
                                .aload(0)
                                .invokespecial(
                                    ClassDesc.of("java.lang.Object"),
                                    "<init>",
                                    MethodTypeDesc.ofDescriptor("()V")
                                )
                                .return_()
                    )
                );
            }
        );

        System.out.println(
            "Generated class size: "
            + generatedBytes.length
            + " bytes"
        );
    }

    /*
     * JEP 496: Quantum-Resistant ML-KEM (Module-Lattice-Based Key Encapsulation Mechanism)
     */
    public void mlKem() throws Exception {
        // Generate ML-KEM key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM");
        kpg.initialize(NamedParameterSpec.ML_KEM_768);
        KeyPair keyPair = kpg.generateKeyPair();

        // Encapsulate: sender creates shared secret and encapsulation message
        KEM kemSender = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kemSender.newEncapsulator(keyPair.getPublic());
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();
        byte[] encapsulationMsg = encapsulated.encapsulation();
        var senderSecret = encapsulated.key();

        // Decapsulate: receiver recovers shared secret
        KEM kemReceiver = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kemReceiver.newDecapsulator(keyPair.getPrivate());
        var receiverSecret = decapsulator.decapsulate(encapsulationMsg);

        System.out.println("ML-KEM shared secrets match: " + Arrays.equals(senderSecret.getEncoded(), receiverSecret.getEncoded()));
    }

    /*
     * JEP 497: Quantum-Resistant ML-DSA (Module-Lattice-Based Digital Signature Algorithm)
     */
    public void mlDsa() throws Exception {
        // Generate ML-DSA key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-DSA");
        kpg.initialize(NamedParameterSpec.ML_DSA_65);
        KeyPair keyPair = kpg.generateKeyPair();

        // Sign a message
        Signature signer = Signature.getInstance("ML-DSA");
        signer.initSign(keyPair.getPrivate());
        byte[] message = "Hello, quantum-safe signatures!".getBytes();
        signer.update(message);
        byte[] signature = signer.sign();

        // Verify the signature
        Signature verifier = Signature.getInstance("ML-DSA");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(message);
        boolean verified = verifier.verify(signature);

        System.out.println("ML-DSA signature verified: " + verified);
    }
}
