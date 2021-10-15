# EasyROP

## Description

*EasyROP* is an exploit development tool targeted at stack buffer overflow vulnerabilities in Linux executables.
This tool supports i386 and amd64 architectures and techniques such as Return-Oriented Programming and Ret2Libc.
Exploit scripts created by this program are intended for use with [pwntools](https://github.com/Gallopsled/pwntools).

Features include:

- Adding padding to payloads to overflow stack buffers.
- Modifying current padding.
- Adding return addresses to a payload.
- Modifying current return addresses.
- Displaying the current payload.

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
