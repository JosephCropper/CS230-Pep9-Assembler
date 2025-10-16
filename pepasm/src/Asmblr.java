import java.io.*;

public class Asmblr {
    private int symbolNum = 0;

    public Asmblr(){}

    public void compile(File file){
        System.out.println("Compiling: " + file.getName() + "...");
        String encoded = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            boolean loop = true;
            String line;

            while (loop){

                line = br.readLine();

                if (line == null){
                    System.out.println(encoded);
                    return;
                }
                else {

                    try {
                        int blankcount = deleteblanks(line);
                        encoded= encoded + " " + encode(line.substring(blankcount, blankcount+4), line);

                    } catch (Exception e) {}

                }
            }
        }
        catch (Exception e) {
            System.err.println("Error:");
            System.err.println(e);
        }
    }



    public String encode(String inputSub, String input){
        String returnString = "";

        switch (inputSub){

            case "STBA":
                returnString = returnString + "F";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "LDBA":
                returnString = returnString + "D";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "LDWA":
                returnString = returnString + "C";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "STWA":
                returnString = returnString + "E";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "ASLA":
                returnString = returnString + "0A";
                return returnString;

            case "ASRA":
                returnString = returnString + "0C";
                return returnString;

            case "ADDA":
                returnString = returnString + "6";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "STOP":
                returnString = returnString + "00";
                return returnString;

            case "CPBA":
                returnString = returnString + "B";
                if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("d") ==0)) {
                    returnString = returnString + "1";
                }
                else if((input.substring(lengthData(input)+2, lengthData(input)+3).compareTo("i") ==0)) {
                    returnString = returnString + "0";
                }
                break;

            case "BRNE":
                symbolNum +=1;
                returnString = returnString + "1A 00 0" + symbolNum*3;
                return returnString;

            case ".END":
                returnString = returnString + "zz";
                return returnString;

            default:
                if (lengthDataHeader(input) != -1){
                    input = input.substring(lengthDataHeader(input)+1);
                    input = input.substring(deleteblanks(input));
                    returnString = returnString + "F1";
                    encode(input.substring(0, 4), input);
                }
                break;
        }
        int moveDataMax = 0;




        switch (lengthData(input) - lengthDataX(input)){

            case 5:
                returnString = returnString + " " + (input.substring(lengthDataX(input)+1, lengthDataX(input)+3) + " " + input.substring(lengthDataX(input)+3, lengthDataX(input)+5));
                break;

            case 4:
                returnString = returnString + " 0" + (input.substring(lengthDataX(input)+1, lengthDataX(input)+2) + " " + input.substring(lengthDataX(input)+2, lengthDataX(input)+4));
                break;

            case 3:
                returnString = returnString + " 00 " + input.substring(lengthDataX(input)+1, lengthDataX(input)+3);
                break;

            case 2:
                returnString = returnString + " 00 0" + input.substring(lengthDataX(input)+1, lengthDataX(input)+2);
                break;

        }
        return returnString;
    }

    public int deleteblanks(String line){
        boolean blankloop = true;
        int blankcount = 0;

        while (blankloop){

            if (line.substring(blankcount, blankcount+1).compareTo(" ") == 0){
                blankcount++;
            }
            else{
                blankloop = false;
            }
        }
        return blankcount;
    }

    public int lengthData(String line){
        boolean blankloop = true;
        int blankcount = 0;

        while (blankloop){

            if (line.substring(blankcount, blankcount+1).compareTo(",") != 0){
                blankcount++;
            }
            else {
                blankloop = false;
            }
        }
        return blankcount;
    }

    public int lengthDataHeader(String line){
        boolean blankloop = true;
        int blankcount = 0;

        while (blankloop){

            if (line.substring(blankcount, blankcount+1).compareTo(":") != 0){
                blankcount++;
            }
            else{
                blankloop = false;
            }
        }
        return blankcount;
    }

    public int lengthDataX(String line){
        boolean blankloop = true;
        int blankcount = 0;

        while (blankloop){

            if (line.substring(blankcount, blankcount+1).compareTo("x") != 0){
                blankcount++;
            }
            else{
                blankloop = false;
            }
        }
        return blankcount;
    }
}
