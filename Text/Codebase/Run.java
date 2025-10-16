import java.io.File;

public class Run {

    private String filepath;
    private File folder;
    private File[] files;
    private Asmblr asmblr;

    public Run(){
        try {
            filepath = ".././pepasm/src/Files";
            folder = new File(filepath);
            files = folder.listFiles();
            asmblr = new Asmblr();
        }
        catch(Exception e){System.err.println(e);}
    }

    public boolean command(String input){

        if (input.compareTo("loaded_files") ==0) {
            try {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            return true;
        }



        for (File file:files){

            if(input.compareTo(file.getName()) == 0){
                asmblr.compile(file);
                return true;
            }

        }

        System.out.println("FileNotFound or CommandNotRecognized");
        return false;
    }
}
