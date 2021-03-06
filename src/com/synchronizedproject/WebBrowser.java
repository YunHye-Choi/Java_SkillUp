package com.synchronizedproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebBrowser {
    private static final String SPACE = " ";
    private final List<String> webSiteNames = new ArrayList<>();
    private final int maxWebCount;

    public WebBrowser(final int maxWebCount) {
        this.maxWebCount = maxWebCount;
    }

    // π λ©μλ λκΈ°ν π
    public synchronized void createNewTab(final String webSiteName) {
        System.out.println("log -- μμ locked!");
        try{
            if (full()) {
                System.out.println("log -- ν­μ΄ (max)κ° μλλ€.");
                return;
            }
            System.out.println(webSiteName + " μ¬μ΄νΈκ° μΌμ§λ μ€ μλλ€.");
            Thread.sleep(new Random().nextInt(1000)); // ν­ μμ± μκ°(0~1μ΄)
            webSiteNames.add(webSiteName); // ν­ μμ±!
            showRunningBrowser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("log -- μμ released~");
    }
    private void showRunningBrowser() {
        // λΈλΌμ°μ  κ°μ μ’λ£ exception
        if (webSiteNames.size() > maxWebCount) {
            throw new UnsupportedOperationException("νμ¬ λΈλΌμ°μ  ν­μ΄ 6μ μ΄μ νμ±ν λμ΄ κ°μ  μ’λ£ν©λλ€.");
        }

        System.out.println("βββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
        System.out.println("β β Wilder Web Browser                                                    - β‘ x β");
        System.out.println("βββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");

        StringBuilder browserNameLine = new StringBuilder();
        StringBuilder browserUnderLine = new StringBuilder();

        if (webSiteNames.size() > 0) {
            browserNameLine.append("β");
            browserUnderLine.append("β");
        }

        for (int i = 0; i < webSiteNames.size(); i++) {
            browserNameLine.append(genWebSiteName(webSiteNames.get(i)))
                    .append(i + 1)
                    .append("   β");
            browserUnderLine.append("ββββββββββββββββ");
        }
        System.out.println(browserNameLine);
        System.out.println(browserUnderLine);
    }
    private String genWebSiteName(final String name) {
        StringBuilder builder = new StringBuilder();

        if (name.length() > 11) {
            return name.substring(0,11);
        }

        int space = 11 - name.length();
        int interval = space / 2;

        // μ΄λ¦ κ°μ΄λ° μ λ ¬
        builder.append(SPACE.repeat(interval))
                .append(name)
                .append(SPACE.repeat(11-interval-name.length()));

        return builder.toString();
    }

    public boolean hasSpace() {
        return webSiteNames.size() < maxWebCount;
    }
    public boolean full() {
        return !hasSpace();
    }
}
