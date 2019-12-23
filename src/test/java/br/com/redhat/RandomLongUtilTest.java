package br.com.redhat;

import br.com.redhat.util.RandomLongUtil;
import org.junit.Assert;
import org.junit.Test;

public class RandomLongUtilTest {
    @Test
    public void randomLong() {
        Assert.assertNotNull(RandomLongUtil.get());
    }
}
