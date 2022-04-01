# .txt -> .csv / .csv -> .txt converter/normalizer
This is a CLI assignment for my Programming II class.
This program will either convert from .txt file to .csv file or vice versa.
The .txt file contents are separated by tab spacing and the .csv file contents are separated by comma's.

The program can also "normalize" a file, formatting each value inside the value according to the criteria below:
1)  if cell is empty: write N/A instead
2) if cell contains an integer: normalization explicitly shows the sign (+ for positive and - for negative). 
Also, if the integer representation is shorter than 10 characters, it adds some leading zeros to make the representation 10 character long.
3) if cell contains a float/double: normalization shows two digit after decimal point. 
Also, it uses scientific notation if the number is greater than 100 or less than 0.01.
4) if cell contains a string longer than 13 characters, normalization shows the first 10 characters of the string followed by an ellipsis (three dots like this . . . )
5) otherwise, normalization causes no change.