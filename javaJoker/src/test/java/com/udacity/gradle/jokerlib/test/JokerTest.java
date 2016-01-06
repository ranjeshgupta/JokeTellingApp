package com.udacity.gradle.jokerlib.test;

import com.udacity.gradle.jokerlib.Joker;
import org.junit.Test;

public class JokerTest {
    @Test
    public void test() {
        Joker joker = new Joker();
        assert joker.getJoke().length() != 0;
    }
}