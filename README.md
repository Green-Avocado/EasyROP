# EasyROP

## Description

*EasyROP* is an exploit development tool targeted at stack buffer overflow vulnerabilities in Linux executables.
This tool supports i386 and amd64 architectures and techniques such as Return-Oriented Programming and Ret2Libc.
Exploit scripts created by this program are intended for use with [pwntools](https://github.com/Gallopsled/pwntools).

Features include:

- Adding/removing ROP chains to payloads.
- Moving ROP chains within a payload
- Adding/removing gadgets to ROP chains.
- Adding/removing padding to ROP chains.
- Moving gadgets and padding within a ROP chain.
- Outputting code for a payload.
- Outputting code for a ROP chain.

*EasyROP* is primarily intended for new CTF players learning the basics of binary exploitation.
It can be used to teach fundamentals behind techniques such as Return-Oriented Programming,
as reduces the learning curve of writing an exploit.
This project is of interest to me as a member of the UBC CTF team, [Maple Bacon](https://ubcctf.github.io/),
because of the influx of new members recently.
Many of our newer members would like to learn concepts such as stack buffer overflows,
but find them very complex and difficult to pick up.
The goal of EasyROP is to simplify the learning process by making it easier to start developing exploits.

## User Stories

- As a user, I want to be able to add a ROP chain to a payload
- As a user, I want to be able to select and move ROP chains within a payload.
- As a user, I want to be able to remove a ROP chain from a payload.
- As a user, I want to be able to view the ROP chains of a payload.
- As a user, I want to be able to output an exploit script of a payload.
- As a user, I want to be able to add a gadget to a ROP chain using an address, symbol, instructions, or string.
- As a user, I want to be able to select and move gadgets within a ROP chain.
- As a user, I want to be able to a ROP gadget from a ROP chain.
- As a user, I want to be able to view the gadgets of a ROP chain.
- As a user, I want to be able to output an exploit script of a ROP chain.
- As a user, I want to be able to save my payload to a file.
- As a user, I want to be able to load my payload from a file.
- As a user, I want to be able to save my ROP chain to a file.
- As a user, I want to be able to load my ROP chain from a file.

## Phase 4: Task 2

Sample log:

```
Wed Nov 24 14:27:05 PST 2021
Created new (PAYLOAD) Ret2Libc
Wed Nov 24 14:27:05 PST 2021
Cleared (PAYLOAD) Ret2Libc
Wed Nov 24 14:27:05 PST 2021
Created new (ROP_CHAIN) leakLibc
Wed Nov 24 14:27:05 PST 2021
Cleared (ROP_CHAIN) leakLibc
Wed Nov 24 14:27:06 PST 2021
Added (INSTRUCTIONS_GADGET) InstructionsGadget (ret;) to (ROP_CHAIN) leakLibc at index 0
Wed Nov 24 14:27:06 PST 2021
Added (INSTRUCTIONS_GADGET) InstructionsGadget (pop rdi; ret;) to (ROP_CHAIN) leakLibc at index 1
Wed Nov 24 14:27:06 PST 2021
Added (SYMBOL_GADGET) SymbolGadget (exe.got.puts) to (ROP_CHAIN) leakLibc at index 2
Wed Nov 24 14:27:06 PST 2021
Added (SYMBOL_GADGET) SymbolGadget (exe.plt.printf) to (ROP_CHAIN) leakLibc at index 3
Wed Nov 24 14:27:06 PST 2021
Added (ROP_CHAIN) leakLibc to (PAYLOAD) Ret2Libc at index 0
Wed Nov 24 14:27:06 PST 2021
Created new (ROP_CHAIN) callSystemBinSh
Wed Nov 24 14:27:06 PST 2021
Cleared (ROP_CHAIN) callSystemBinSh
Wed Nov 24 14:27:06 PST 2021
Added (INSTRUCTIONS_GADGET) InstructionsGadget (pop rdi; ret;) to (ROP_CHAIN) callSystemBinSh at index 0
Wed Nov 24 14:27:06 PST 2021
Added (STRING_GADGET) StringGadget (/bin/sh\x00) to (ROP_CHAIN) callSystemBinSh at index 1
Wed Nov 24 14:27:06 PST 2021
Added (SYMBOL_GADGET) SymbolGadget (libc.sym.system) to (ROP_CHAIN) callSystemBinSh at index 2
Wed Nov 24 14:27:06 PST 2021
Added (ROP_CHAIN) callSystemBinSh to (PAYLOAD) Ret2Libc at index 1
```

## Phase 4: Task 3

Potential improvements:

- Add helper methods to reduce repetitive code in GUI functions for saving and loading from disk.
- Add helper methods for creating UI elements to reduce repetitive code when adding elements and assigning listeners.
- Extract methods from the constructor of CollectionEditorGui to capture the behaviour of its parts.
- Enforce typesafety by making Payloads, RopChains, and their respective UI elements generic types.
