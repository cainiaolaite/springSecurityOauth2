package com.hua.fileplat.cloud;

import org.junit.Assert.*;

public class HelloWordTest {
    @Test
    public void inputHelloWord(){
        Assert.assertEquals("HelloWord",new HelloWord().input());
    }
}
