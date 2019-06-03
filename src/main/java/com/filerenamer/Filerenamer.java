package com.filerenamer;

public class Filerenamer {

    public static void main(String[] args) throws Exception {
        if(validate(args)) {
            ReadFiles readFiles = new ReadFiles();
            readFiles.readAll(args[0]);
        }
    }

    private static boolean validate(String[] args) {
        if (args == null || args.length != 1){
            System.out.println("Please use the following command line: java -jar file.jar <filename>");
            return false;
        }
        System.out.println("Filename: "+args[0]);

        return true;
    }
}
