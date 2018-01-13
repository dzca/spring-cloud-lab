package ca.ocbl.elk;

import org.junit.Test;
import org.slf4j.LoggerFactory;

public class LogstashTest {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogstashTest.class);

    @Test
    public void test() {
        LogstashTest obj = new LogstashTest();
        try{
            obj.divide();
        }catch(ArithmeticException ex){
            log.error("ArithmeticException!", ex);
        }
    }
    private void divide(){
        int i = 10 /0;
        log.info("i = " + i);
    }
}
