package com.therealspoljo.antiautosoup.utilities;

public class Violation {
    
    private int violationLevel = 0;
    private long lastNotified = 0L;

    public void raiseViolationLevel() {
	this.violationLevel += 1;
    }

    public int getViolationLevel() {
	return this.violationLevel;
    }

    public void updateNotify() {
	this.lastNotified = System.currentTimeMillis();
    }

    public boolean shouldNotify() {
	return System.currentTimeMillis() - this.lastNotified >= ConfigUtils.getMessageDelay();
    }
}