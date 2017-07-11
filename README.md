# CryptLib
A Java library for encrypting and decrypting text with AES
# Usage

Create an object

    CryptLib cipher = new CryptLib();

Printout encrypted string

    System.out.println(">" + cipher.encrypt("Test", "Test1234Test1234") + "<");
    
Printout decrypted string

    System.out.println(">" + cipher.decrypt("5c61eZ2k+CbeDThLD2bP/w==", "Test1234Test1234") + "<");
    
Output:
>\>5c61eZ2k+CbeDThLD2bP/w==<

>\>Test<

    
# License
![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)

This program is Free Software: You can use, study share and improve it at your
will. Specifically you can redistribute and/or modify it under the terms of the
[GNU General Public License](https://www.gnu.org/licenses/gpl.html) as
published by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
