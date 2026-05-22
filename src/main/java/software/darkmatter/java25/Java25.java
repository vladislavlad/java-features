package software.darkmatter.java25;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.security.spec.AlgorithmParameterSpec;

public class Java25 {

    /*
     * JEP 506: Scoped Values (GA)
     */
    static final ScopedValue<String> USER = ScopedValue.newInstance();
    static final ScopedValue<Integer> REQUEST_ID = ScopedValue.newInstance();

    public void scopedValues() {
        // Bind scoped values and execute code within their scope
        ScopedValue.where(USER, "alice")
                   .where(REQUEST_ID, 42)
                   .run(this::processRequest);

        // Scoped values are no longer accessible outside their scope
        // USER.get() would throw IllegalStateException here
    }

    private void processRequest() {
        String user = USER.get();
        int requestId = REQUEST_ID.get();
        System.out.println("Processing request " + requestId + " for user " + user);

        // Nested scope: rebind USER for downstream calls
        ScopedValue.where(USER, "bob").run(this::downstreamCall);

        // Original binding is restored after nested scope
        System.out.println("Back to user: " + USER.get());
    }

    private void downstreamCall() {
        System.out.println("Downstream sees user: " + USER.get());
    }

    /*
     * JEP 510: Key Derivation Function API (GA)
     */
    public void keyDerivationFunction() throws Exception {
        // Create HKDF instance
        KDF hkdf = KDF.getInstance("HKDF-SHA256");

        // Derive key using extract-then-expand
        byte[] ikm = "initial-key-material".getBytes();
        byte[] salt = "my-salt".getBytes();
        byte[] info = "context-info".getBytes();

        AlgorithmParameterSpec params =
            HKDFParameterSpec.ofExtract()
                             .addIKM(ikm)
                             .addSalt(salt)
                             .thenExpand(info, 32);

        // Derive an AES key
        SecretKey aesKey = hkdf.deriveKey("AES", params);
        System.out.println("Derived AES key algorithm: " + aesKey.getAlgorithm());
        System.out.println("Derived key size: " + aesKey.getEncoded().length + " bytes");

        // Derive raw bytes
        byte[] derivedData = hkdf.deriveData(params);
        System.out.println("Derived data length: " + derivedData.length);
    }

    /*
     * JEP 511: Module Import Declarations (GA)
     */
    public void moduleImports() {
        System.out.println("Module import declarations allow:");
        System.out.println("  import module java.base;  // imports all 54+ packages");
        System.out.println("  import module java.sql;   // imports java.sql, javax.sql, and transitive deps");
    }

    /*
     * JEP 512: Compact Source Files and Instance Main Methods (GA)
     */
    public void compactSourceFiles() {
        // String line = IO.readln();
        // String line = IO.readln("Prompt: ");
        System.out.println("Compact source files:");
        System.out.println("  - No class declaration needed");
        System.out.println("  - Instance main() methods (no public static void)");
        System.out.println("  - java.base module auto-imported");
        System.out.println("  - java.lang.IO for simple console I/O");
    }

    /*
     * JEP 513: Flexible Constructor Bodies (GA)
     */
    public void flexibleConstructors() {
        var employee = new Employee("Alice", 25, "123");

        System.out.println("Flexible constructor bodies:");
        System.out.println("  - Statements allowed before super()/this()");
        System.out.println("  - Enables fail-fast argument validation");
        System.out.println("  - Fields can be initialized before superclass constructor");
        System.out.println("  - Prevents superclass from seeing uninitialized subclass state");
        System.out.println("  - Employee constructed { name: " + employee.name + ", age: " + employee.age + ", officeId: " + employee.officeId + " }");
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class Employee extends Person {
        String officeId;

        Employee(String name, int age, String officeId) {
            if (age < 18 || age > 67)
                throw new IllegalArgumentException("Invalid age: " + age);

            this.officeId = officeId;  // Initialize before super()
            super(name, age);
        }
    }
}
