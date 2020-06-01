package com.ys.tool;


import junit.framework.TestCase;

public class HttpClientTest extends TestCase{
	
	public void test() throws Exception{
		HttpClientUtil.doGet("http://www.baidu.com");
	}
}
