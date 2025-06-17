package com.npci;

public class ValueTypesExample {
    public static void main(String[] args) {

        byte byteValue = 127; // 8-bit  , -128 to 127 // Local variable -> land on stack
        short shortValue = 32767; // 16-bit, -32,768 to 32,767
        int intValue = 2147483647; // 32-bit, -2,147,483,648 to 2,147,483,647
        long longValue = 9223372036854775807L; // 64-bit, -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807

        float floatValue = 3.4028235E38F; // 32-bit, -3.4028235E38 to 3.4028235E38
        double doubleValue = 1.7976931348623157E308; // 64-bit, -1.7976931348623157E308 to 1.7976931348623157E308

        char charValue1 = 'A'; // 16-bit Unicode character, 0 to 65,535
        char charValue2 = '\u0041'; // Unicode representation of 'A'
        char charValue3 = 65; // ASCII value of 'A' is 65
        char inrCurrencySymbol = '\u20B9'; // Indian Rupee symbol

        boolean booleanValueTrue = true; // Represents true or false // 1-bit


        //---------------------------------

        int decimalNotion=12;
        System.out.println(decimalNotion);

        int octalNotion = 012; // Octal notation (base 8)
        System.out.println(octalNotion); // Prints 10 in decimal

        int hexNotion = 0x12; // Hexadecimal notation (base 16)
        System.out.println(hexNotion); // Prints 10 in decimal

        int binaryNotion = 0b1100; // Binary notation (base 2)
        System.out.println(binaryNotion); // Prints 12 in decimal

        //-----------------------------------------------------------

        double myDoubleValue = 1_00_0000.00; // Using underscores for readability

        System.out.println(myDoubleValue);
        //-----------------------------------------------------------



    }
}
