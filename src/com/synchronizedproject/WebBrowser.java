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

    // 🌟 메서드 동기화 🌟
    public synchronized void createNewTab(final String webSiteName) {
        System.out.println("log -- 자원 locked!");
        try{
            if (full()) {
                System.out.println("log -- 탭이 (max)개 입니다.");
                return;
            }
            System.out.println(webSiteName + " 사이트가 켜지는 중 입니다.");
            Thread.sleep(new Random().nextInt(1000)); // 탭 생성 시간(0~1초)
            webSiteNames.add(webSiteName); // 탭 생성!
            showRunningBrowser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("log -- 자원 released~");
    }
    private void showRunningBrowser() {
        // 브라우저 강제종료 exception
        if (webSiteNames.size() > maxWebCount) {
            throw new UnsupportedOperationException("현재 브라우저 탭이 6새 이상 활성화 되어 강제 종료합니다.");
        }

        System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ ◆ Wilder Web Browser                                                    - □ x │");
        System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");

        StringBuilder browserNameLine = new StringBuilder();
        StringBuilder browserUnderLine = new StringBuilder();

        if (webSiteNames.size() > 0) {
            browserNameLine.append("│");
            browserUnderLine.append("└");
        }

        for (int i = 0; i < webSiteNames.size(); i++) {
            browserNameLine.append(genWebSiteName(webSiteNames.get(i)))
                    .append(i + 1)
                    .append("   │");
            browserUnderLine.append("───────────────┘");
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

        // 이름 가운데 정렬
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
