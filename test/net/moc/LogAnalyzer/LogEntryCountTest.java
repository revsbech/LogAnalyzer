package net.moc.LogAnalyzer;

import java.io.*;
import net.moc.LogAnalyzer.LogEntryCount;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LogEntryCountTest extends TestCase {
	//@test
	public void testConstructorWithoutArguments() {
		LogEntryCount count = new LogEntryCount();
		assertEquals(0L, count.getCount());
		assertEquals(0L, count.getBytesCount());
	}
	
	//@test
	public void testConstructorWithArguments() {
		LogEntryCount count = new LogEntryCount(2566L, 999999L);
		assertEquals(2566L, count.getCount());
		assertEquals(999999L, count.getBytesCount());
	}
}
