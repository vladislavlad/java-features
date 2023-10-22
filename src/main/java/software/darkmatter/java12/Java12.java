package software.darkmatter.java12;

public class Java12 {

    public void stringNewMethods() {
        var s = "Just a string";
        s = s.indent(4);
        System.out.println("String ident works like: " + s);

        String oneString = " 1 ";
        var l = s.transform(z -> Long.parseLong(oneString.trim()));
        System.out.println("String transform works like: " + l);
    }
}
