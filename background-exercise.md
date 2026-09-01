# Binary to Binary-Coded Decimal Conversion

This exercise is to check yourself where you stand in digital design.
Implement the solution and I will give you feedback. This exercise will not be graded.

Implement it in any HDL you know (e.g., Chisel, VHDL, or Verilog).
Also implement some testing (test bench).

Binary-Coded Decimal (BCD) is a way of explicitly representing decimal numbers in computer 
systems. Each digit of a decimal number is represented by its own 4-bit binary equivalent. 
Besides being useful for displaying purposes, BCD can also play a role in applications 
where exact decimal precision is required, such as in financial calculations.

## The BCD Representation

In BCD, each decimal digit is represented by a 4-bit binary number. Some examples for a 
two-digit decimal number are:

- `0x00` is represented as `0000` `0000`, i.e. `0` `0`
- `0x0A` is represented as `0001` `0000`, i.e. `1` `0`
- `0x45` is represented as `0110` `1001`, i.e. `6` `9`

## Conversion Process

To convert a binary number to its BCD representation, the *shift-and-add-3* algorithm can 
be used, also known as the *double-dabble* algorithm. The process involves shifting the 
binary number left and adding 3 to any BCD digit that exceeds 4. Here is pseudo-code for 
the conversion:

```python
def binaryToBcd(binary: Int, width: Int, digits: Int):
  bcd = 0                              # 1. initialize BCD number
  for i from 0 to width - 1:           # 2. iterate over each bit of the binary number
    for j from digits - 1 down to 0:   # 3. iterate over BCD digits from most to least significant
      if bcd[j * 4 + 3, j * 4] > 4:    # 4. extract jth BCD digit and check if it exceeds 4
        bcd += 3 << (j * 4)            # 5. add 3 to the jth BCD digit
    bcd = bcd << 1 | binary[width - 1] # 6. shift left and add MSB of binary
    binary = binary << 1               # 7. shift binary left to get the next bit at MSB
  return bcd
```

At each step, the algorithm checks if any BCD digit exceeds 4 and adds 3 to that digit 
if necessary (the carry propagates to the next digits). Then it left shifts the BCD 
number, shifting in the MSB of the binary number. This process is repeated for each 
bit of the binary number.

## FSMD Design

Design a Finite State Machine with Datapath (FSMD) that implements the double-dabble 
algorithm to convert binary numbers to BCD. The input binary number has 16 bits and 
the output BCD number has 5 digits, i.e. 20 bits in total. The FSMD should have the 
following inputs and outputs:

```verilog
input         clock,
input         reset,
input         binary_valid,
output        binary_ready,
input  [15:0] binary,
output        bcd_valid,
input         bcd_ready,
output [19:0] bcd
```

The `binary_valid` and `binary_ready` signal are used as a handshake mechanism to 
transfer the binary number to the FSMD. Your FSMD should signal `binary_ready` when 
idle. When `binary_valid` is high and `binary_ready` is high, the FSMD should 
start the conversion process and lower `binary_ready` while being busy. The converted 
number should be passed on to a consumer using `bcd_valid` and `bcd_ready` for handshaking. `bcd_valid` should be high when the conversion is done and may be lowered when `bcd_ready` is high. The `bcd` output should present the BCD number while `bcd_valid` is high. After the handshake is complete, `bcd_valid` should be lowered and the FSMD should return to the idle state, ready to accept a new binary number. The following timing diagram illustrates the handshaking and data flow for two conversions:

<img src="https://svg.wavedrom.com/{ 'signal': [{ 'name': 'clk',        'wave': 'p...............' },{ 'name': 'reset',      'wave': '10..............' },{ 'name': 'binary_valid','wave':'x0101|....0|....' },{ 'name': 'binary_ready','wave':'x1.0.|...10|....' },{ 'name': 'binary[15:0]','wave':'x.5x5|...x.|....', 'data':['0xAA','0x24'] },{ 'name': 'bcd_valid',   'wave':'0....|1..0.|..10' },{ 'name': 'bcd_ready',   'wave':'0....|..10.|1..0' },{ 'name': 'bcd[19:0]',   'wave':'x....|4..x.|..4x', 'data':['0x170','0x36'] }]}"/>

## Design Steps

Now, follow these steps to design your FSMD:

1. Decide on which state elements you need in your datapath to implement the 
   double-dabble algorithm.
2. Decide which operations you need to perform on your state elements. **NOTE**: The 
   inner for-loop (statement 3) in the pseudo-code can be unrolled, allowing for 
   updating all BCD digits in parallel.
3. Design a state machine that controls the handshaking and the operations in the datapath.

