package com.synchronizedproject;

public class Computer {
    public static void main(String[] args) {
        final WebBrowser webBrowser = new WebBrowser(5);

        final Thread threadA = new Thread(new WebSite("Google", webBrowser));
        final Thread threadB = new Thread(new WebSite("Naver", webBrowser));
        final Thread threadC = new Thread(new WebSite("Github", webBrowser));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
