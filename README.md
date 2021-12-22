# EasyROP

*EasyROP* is an exploit development tool targeted at stack buffer overflow vulnerabilities in Linux executables.
This tool supports i386 and amd64 architectures and techniques such as Return-Oriented Programming and Ret2Libc.
Exploit scripts created by this program are intended for use with [pwntools](https://github.com/Gallopsled/pwntools).

## Uses

*EasyROP* is primarily intended for new CTF players learning the basics of binary exploitation.
It can be used to teach fundamentals behind techniques such as Return-Oriented Programming,
as reduces the learning curve of writing an exploit.
This project is of interest to me as a member of the UBC CTF team, [Maple Bacon](https://ubcctf.github.io/),
because of the influx of new members recently.
Many of our newer members would like to learn concepts such as stack buffer overflows,
but find them very complex and difficult to pick up.
The goal of EasyROP is to simplify the learning process by making it easier to start developing exploits.

## Features

- Adding/removing ROP chains to payloads.
- Moving ROP chains within a payload
- Adding/removing gadgets to ROP chains.
- Adding/removing padding to ROP chains.
- Moving gadgets and padding within a ROP chain.
- Outputting code for a payload.
- Outputting code for a ROP chain.
