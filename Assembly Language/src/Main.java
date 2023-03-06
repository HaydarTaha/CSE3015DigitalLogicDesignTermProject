/**Haydar Taha Tunç 150119745
 * Emir Ege Eren 150119739
 * Burak Dursun 150119743
 * Mehmet Emre Şengüler 150119781
*/
import java.io.*;
import java.util.*;

public class Main {
    public static int instructionNumberGetter(String insStr) {
        switch (insStr) {
            case "ADD":
                return 0;
            case "SUB":
                return 1;
            case "AND":
                return 2;
            case "OR":
                return 3;
            case "XOR":
                return 4;
            case "ADDI":
                return 5;
            case "SUBI":
                return 6;
            case "ANDI":
                return 7;
            case "ORI":
                return 8;
            case "XORI":
                return 9;
            case "JUMP":
                return 10;
            case "LD":
                return 11;
            case "ST":
                return 12;
            case "BE":
                return 13;
            case "BNE":
                return 14;
            case "PUSH":
                return 15;
            case "POP":
                return 16;
            default:
                return -1;
        }
    }

    public static void setNullValues(ArrayList<Integer> binary, int size){
        int i = 0;
        while (i < size){
            binary.add(0);
            i++;
        }
    }

    public static void arithmeticOpcode(int opcode, ArrayList<Integer> binary){
        ArrayList<Integer> binaryArithmeticOpcode = new ArrayList<Integer>();
        while (opcode > 0){
            int bit = opcode % 2;
            opcode = opcode / 2;
            binaryArithmeticOpcode.add(bit);
        }
        while (binaryArithmeticOpcode.size() != 3){
            binaryArithmeticOpcode.add(0);
        }
        Collections.reverse(binaryArithmeticOpcode);
        binary.addAll(binaryArithmeticOpcode);
    }

    public static void registerBinary(String register, ArrayList<Integer> binary){
        ArrayList<Integer> binaryRegister = new ArrayList<Integer>();
        register =  register.replace("R", "");
        int number = Integer.parseInt(register);
        while (number > 0){
            int bit = number % 2;
            number = number / 2;
            binaryRegister.add(bit);
        }
        while (binaryRegister.size() != 4){
            binaryRegister.add(0);
        }
        Collections.reverse(binaryRegister);
        binary.addAll(binaryRegister);
    }

    public static void addOpcode(int opcode, ArrayList<Integer> binary, int opcodeSize){
        ArrayList<Integer> binaryOpcode = new ArrayList<Integer>();
        while (opcode > 0){
            int bit  = opcode % 2;
            opcode = opcode / 2;
            binaryOpcode.add(bit);
        }
        if (opcodeSize == 2){
            while (binaryOpcode.size() != 2){
                binaryOpcode.add(0);
            }
        } else {
            while (binaryOpcode.size() != 5){
                binaryOpcode.add(0);
            }
        }
        Collections.reverse(binaryOpcode);
        binary.addAll(binaryOpcode);
    }

    public static void twosComplement(ArrayList<Integer> binary, int number, int size){
        ArrayList<Integer> twosComplementBinary = new ArrayList<Integer>();
        if (number >= 0){
            while (number > 0){
                int bit = number % 2;
                number = number / 2;
                twosComplementBinary.add(bit);
            }
            while (twosComplementBinary.size() != size){
                twosComplementBinary.add(0);
            }
            Collections.reverse(twosComplementBinary);
        } else {
            number = -number;
            ArrayList<Integer> bitBinary = new ArrayList<Integer>();
            int tempNum = number;
            while (number > 0){
                int bit = number % 2;
                number = number / 2;
                bitBinary.add(bit);
            }
            int square = (int) Math.pow(2, bitBinary.size() + 1);
            square-=tempNum;
            ArrayList<Integer> binarySquare = new ArrayList<Integer>();
            while (square > 0){
                int bit = square % 2;
                square = square / 2;
                binarySquare.add(bit);
            }
            if(tempNum == 64){
                binarySquare.remove(binarySquare.size() - 1);
            } else {
                while (binarySquare.size() != size){
                    binarySquare.add(1);
                }
            }
            Collections.reverse(binarySquare);
            twosComplementBinary.addAll(binarySquare);
        }
        binary.addAll(twosComplementBinary);
    }

    public static ArrayList<Integer> converter(ArrayList<String> stringArrayList, ArrayList<Integer> binary){
        int i = 0;
        int opcode = instructionNumberGetter(stringArrayList.get(0));
        if (opcode == 0){
            addOpcode(0, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            arithmeticOpcode(0, binary);
            registerBinary(stringArrayList.get(2), binary);
            registerBinary(stringArrayList.get(3), binary);
        } else if (opcode == 1) {
            addOpcode(0, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            arithmeticOpcode(1, binary);
            registerBinary(stringArrayList.get(2), binary);
            registerBinary(stringArrayList.get(3), binary);
        } else if (opcode == 2) {
            addOpcode(0, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            arithmeticOpcode(2, binary);
            registerBinary(stringArrayList.get(2), binary);
            registerBinary(stringArrayList.get(3), binary);
        } else if (opcode == 3) {
            addOpcode(0, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            arithmeticOpcode(3, binary);
            registerBinary(stringArrayList.get(2), binary);
            registerBinary(stringArrayList.get(3), binary);
        } else if (opcode == 4) {
            addOpcode(0, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            arithmeticOpcode(4, binary);
            registerBinary(stringArrayList.get(2), binary);
            registerBinary(stringArrayList.get(3), binary);
        } else if (opcode == 5) {
            addOpcode(1, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 7);
        } else if (opcode == 6) {
            addOpcode(2, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 7);
        } else if (opcode == 7) {
            addOpcode(3, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 7);
        } else if (opcode == 8) {
            addOpcode(4, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 7);
        } else if (opcode == 9) {
            addOpcode(5, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 7);
        } else if (opcode == 10) {
            addOpcode(6, binary, 5);
            setNullValues(binary, 5);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(1)), 10);
        } else if (opcode == 11) {
            addOpcode(7, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            binary.add(0);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(2)), 10);
        } else if (opcode == 12) {
            addOpcode(7, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            binary.add(1);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(2)), 10);
        } else if (opcode == 13) {
            addOpcode(1, binary, 2);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 10);
        } else if (opcode == 14) {
            addOpcode(2, binary, 2);
            registerBinary(stringArrayList.get(1), binary);
            registerBinary(stringArrayList.get(2), binary);
            twosComplement(binary, Integer.parseInt(stringArrayList.get(3)), 10);
        } else if (opcode == 15) {
            addOpcode(31, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            setNullValues(binary, 10);
            binary.add(0);
        } else if (opcode == 16) {
            addOpcode(31, binary, 5);
            registerBinary(stringArrayList.get(1), binary);
            setNullValues(binary, 10);
            binary.add(1);
        }
        return binary;
    }

    public static ArrayList<String> tokenizer(String str){
        ArrayList<String> stringArrayList = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()){
            stringArrayList.add(stringTokenizer.nextToken().replace(",", ""));
        }
        return stringArrayList;
    }

    public static void hexConverter(ArrayList<Integer> binary, ArrayList<String> hex){
        int count = 0;
        int binaryCount = 0;
        while (count != 5){
            List<Integer> fourBinary = binary.subList(binaryCount, binaryCount + 4);
            int number = 0, i = 3;
            for (int numberBit : fourBinary){
                if (numberBit != 0){
                    number += Math.pow(2, i);
                }
                i--;
            }
            hex.add(Integer.toHexString(number));
            binaryCount+=4;
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        Scanner fileScanner = new Scanner(file);
        ArrayList<String> assembly = new ArrayList<String>();
        while (fileScanner.hasNextLine()){
            assembly.add(fileScanner.nextLine());
        }
        int size = assembly.size();
        FileWriter fileWriter = new FileWriter("output.hex");
        fileWriter.write("v2.0 raw\n");
        int i = 0;
        int newLineCount = 0;
        while (i < size){
            ArrayList<String> temp = tokenizer(assembly.get(i));
            ArrayList<Integer> binary = new ArrayList<Integer>();
            converter(temp, binary);
            ArrayList<String> hex = new ArrayList<String>();
            hexConverter(binary, hex);
            for (String str : hex){
                fileWriter.write(str);
            }
            newLineCount++;
            if (newLineCount == 6){
                if (i != size - 1){
                    fileWriter.write("\n");
                }
                newLineCount = 0;
            } else {
                if (i != size - 1){
                    fileWriter.write(" ");
                }
            }
            i++;
        }
        fileWriter.close();
    }
}