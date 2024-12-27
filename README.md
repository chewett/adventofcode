Chewett Advent of Code repo
===========================

This holds all my solutions for Advent of code including unit tests.

While I normally program in less strongly typed languages I decided that I wanted to solve these problems in a
 strongly typed language. Java has a lot of nice abstractions while also allowing you to pick the
 implementation of various structures (e.g. List can be backed by various types of list, depending on need).

While doing this I have built up a library of helpers which allow me to solve the problems abstracting away a lot
 of the boilerplate focussing on the algorithms. See
 [Discrete2DPositionGrid](src/main/java/net/chewett/adventofcode/datastructures/Discrete2DPositionGrid.java)

## Completion progress

* [2024] 50 Stars - Just casually doing these
* [2023] 50 Stars - Did most of these at 5am 
* [2022] 50 Stars 
* [2021] 50 Stars 
* [2020] 50 Stars 
* [2019] 34 Stars - Started competing in Advent of Code (didn't finish)
* [2018] 0  Stars - Not yet started   
* [2017] 0  Stars - Not yet started
* [2016] 0  Stars - Not yet started   
* [2015] 20 Stars

### 2023 "5 am" start results

In 2023 I woke around 5am most morning to code the puzzles.
I was very pleased to get in the top 1000 some days. My leaderboard results are:

| Day | Part One Time | Part One Rank | Part Two Time | Part Two Rank |
|-----|---------------|---------------|---------------|---------------|
| 25  | 00:28:42      | 644           | 00:28:49      | 558           |
| 24  | 01:11:21      | 1788          | 05:32:06      | 2355          |
| 23  | 01:17:53      | 2905          | 01:57:17      | 1396          |
| 22  | 00:52:04      | 1093          | 01:11:30      | 1048          |
| 21  | 00:25:40      | 2582          | 07:20:59      | 3271          |
| 20  | 01:48:15      | 3237          | 02:46:42      | 2251          |
| 19  | 00:27:48      | 1416          | 01:01:58      | 1004          |
| 18  | 00:13:30      | 354           | 00:40:54      | 623           |
| 17  | 01:12:42      | 1767          | 04:12:39      | 3888          |
| 16  | 00:23:30      | 972           | 00:39:38      | 1597          |
| 15  | 00:12:07      | 4182          | 00:32:50      | 2516          |
| 14  | 00:08:14      | 765           | 00:37:18      | 1053          |
| 13  | 00:47:02      | 3714          | 01:22:48      | 3813          |
| 12  | 00:18:05      | 668           | 01:29:22      | 1666          |
| 11  | 00:15:49      | 1327          | 00:26:38      | 1734          |
| 10  | 00:28:26      | 1209          | 01:20:20      | 1240          |
| 9   | 00:09:47      | 1185          | 00:12:28      | 1007          |
| 8   | 00:06:39      | 932           | 00:18:09      | 552           |
| 7   | 00:48:47      | 5365          | 01:25:02      | 5895          |
| 6   | 00:09:00      | 2171          | 00:14:10      | 2540          |
| 5   | 00:24:00      | 1978          | 16:52:08      | 33259         |
| 4   | 00:09:28      | 2878          | 00:26:24      | 3596          |
| 3   | 00:57:11      | 6547          | 01:08:39      | 5053          |
| 2   | 00:12:28      | 2020          | 00:15:40      | 1691          |
| 1   | 00:15:35      | 7015          | 00:42:07      | 4838          |

## Getting Z3

I used Z3 for one of the problems

* Download it here: https://github.com/Z3Prover/z3/releases
* Copy the com.microsoft.z3.jar to a libs folder in the root directory
* Then it should all work


