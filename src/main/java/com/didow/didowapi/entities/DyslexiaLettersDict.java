package com.didow.didowapi.entities;

import java.util.List;
import java.util.Map;

public class DyslexiaLettersDict {
  private static final Map<Character, List<Character>> LETTERS_DICTIONARIES = Map.ofEntries(
    Map.entry('a', List.of('n', 'm', 'v', 'w')),
    Map.entry('b', List.of('p', 'r')),
    Map.entry('c', List.of('d', 'g', 'o', 'q')),
    Map.entry('d', List.of('b', 'p', 'q')),
    Map.entry('e', List.of('f', 't')),
    Map.entry('f', List.of('e', 't')),
    Map.entry('g', List.of('c', 'd', 'o', 'q')),
    Map.entry('h', List.of('k', 'm', 'n')),
    Map.entry('i', List.of('j', 'l', 't', 'y')),
    Map.entry('j', List.of('i', 'l', 'u', 'y')),
    Map.entry('k', List.of('c', 'r', 'x', 'y')),
    Map.entry('l', List.of('i', 'y')),
    Map.entry('m', List.of('a', 'n', 'u', 'v', 'w')),
    Map.entry('n', List.of('a', 'h', 'm', 'u', 'v', 'w')),
    Map.entry('o', List.of('c', 'd', 'g', 'q', 'u')),
    Map.entry('p', List.of('b', 'f', 'r')),
    Map.entry('q', List.of('c', 'd', 'g', 'o')),
    Map.entry('r', List.of('b', 'f', 'p')),
    Map.entry('s', List.of('n', 'z')),
    Map.entry('t', List.of('i', 'y')),
    Map.entry('u', List.of('n', 'm', 'v', 'w')),
    Map.entry('v', List.of('n', 'm', 'u', 'w')),
    Map.entry('w', List.of('n', 'm', 'u', 'v')),
    Map.entry('x', List.of('k')),
    Map.entry('y', List.of('i', 'j', 't', 'u', 'v')),
    Map.entry('z', List.of('n', 's')),
    Map.entry('-', List.of('-'))
  );

  private DyslexiaLettersDict() {
    throw new IllegalStateException("Utility Class");
  }

  public static List<Character> getLetters(char key) {
    return LETTERS_DICTIONARIES.get(key);
  }
}
