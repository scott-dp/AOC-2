package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    SolutionTwo.readInputAndSaveSafeReports("src/main/resources/input.txt");
    System.out.println(SolutionTwo.safeReports.size());
  }
}

class SolutionOne {
  public static List<List<Integer>> safeReports = new ArrayList<>();

  public static void readInputAndSaveSafeReports(String path) {
    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] numbersAsStrings = line.split("\s");
        List<Integer> numbers = new ArrayList<>();
        for (String s : numbersAsStrings) {
          numbers.add(Integer.parseInt(s));
        }
        if (reportIsSafe(numbers)) safeReports.add(numbers);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean reportIsSafe(List<Integer> report) {
    int first = report.get(0);
    int last = report.get(report.size() - 1);
    if (first < last) {
      //supposed to be increasing
      for (int i = 1; i < report.size(); i++) {
        if (report.get(i) < report.get(i-1)) return false; //here it isnt increasing -> not safe
        if (!isSafeDistance(report.get(i), report.get(i-1))) return false; //distance is not safe
      }
    } else {
      //supposed to be decreasing
      for (int i = 1; i < report.size(); i++) {
        if (report.get(i) > report.get(i-1)) return false; //here it isnt increasing -> not safe
        if (!isSafeDistance(report.get(i), report.get(i-1))) return false; //distance is not safe
      }
    }
    return true;
  }

  private static boolean isSafeDistance(int i, int iMinusOne) {
    if(Math.abs(i-iMinusOne) > 3 || i == iMinusOne) return false;
    return true;
  }
}

class SolutionTwo {
  public static List<List<Integer>> safeReports = new ArrayList<>();

  public static void readInputAndSaveSafeReports(String path) {
    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] numbersAsStrings = line.split("\s");
        List<Integer> numbers = new ArrayList<>();
        for (String s : numbersAsStrings) {
          numbers.add(Integer.parseInt(s));
        }
        if (isReportSafeNewConditions(numbers)) safeReports.add(numbers);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean isReportSafeNewConditions(List<Integer> report) {
    for (int i = 0; i < report.size(); i++) {
      int num = report.get(i);
      report.remove(i);
      if(reportIsSafe(report)) return true;
      report.add(i, num);
    }
    return false;
  }

  private static boolean reportIsSafe(List<Integer> report) {
    int first = report.get(0);
    int last = report.get(report.size() - 1);
    if (first < last) {
      //supposed to be increasing
      for (int i = 1; i < report.size(); i++) {
        if (report.get(i) < report.get(i-1)) return false; //here it isnt increasing -> not safe
        if (!isSafeDistance(report.get(i), report.get(i-1))) return false; //distance is not safe
      }
    } else {
      //supposed to be decreasing
      for (int i = 1; i < report.size(); i++) {
        if (report.get(i) > report.get(i-1)) return false; //here it isnt increasing -> not safe
        if (!isSafeDistance(report.get(i), report.get(i-1))) return false; //distance is not safe
      }
    }
    return true;
  }

  private static boolean isSafeDistance(int i, int iMinusOne) {
    if(Math.abs(i-iMinusOne) > 3 || i == iMinusOne) return false;
    return true;
  }
}