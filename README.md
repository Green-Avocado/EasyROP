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
It can be used to teach fundamentals behind techniques such as Return-Oriented Programming, as reduces the learning curve of writing an exploit.
This project is of interest to me as a member of the UBC CTF team, [Maple Bacon](https://ubcctf.github.io/), because of the influx of new members recently.
Many of our newer members would like to learn concepts such as stack buffer overflows, but find them very complex and difficult to pick up.
I wish to simplify the learning process my making it easier to start developing exploits without extensive experience in more complex tools.

## User Stories

- As a user, I want to be able to add a ROP gadget to a payload using an address, list of instructions, or string.
- As a user, I want to be able to select a ROP gadget and change whether it uses an address, list of instructions, or string.
- As a user, I want to be able to select a ROP gadget and change its address.
- As a user, I want to be able to select a ROP gadget and change what instructions it searches for.
- As a user, I want to be able to select a ROP gadget and change what string it searches for.
- As a user, I want to be able to remove a ROP gadget from a payload.
- As a user, I want to be able to visualise padding and ROPchain of a payload.
- As a user, I want to be able to output an exploit script using a payload.
