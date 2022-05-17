package com.synchronizedproject;

import java.util.Random;

public class WebSite implements Runnable{
    private final String webSiteName;
    private final WebBrowser webBrowser;

    public WebSite(final String webSiteName, final WebBrowser webBrowser) {
        this.webSiteName = webSiteName;
        this.webBrowser = webBrowser;
    }

    @Override
    public void run() {
        // 브라우저 로딩 후 탭 수가 갱신됨.
        // 메서드 블럭 동기화: 새로운 탭이 추가될 때는 다른 탭이 추가되는 것을 막는다!
        synchronized (this) {
            while(webBrowser.hasSpace()) {
                try{
                    Thread.sleep(new Random().nextInt(1000));// 브라우저 로딩 시간(0~1초)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                webBrowser.createNewTab(webSiteName); // 탭 생성 (탭 수 갱신)
            }
        }
    }
}
