package site.weatherapplication;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by mnarvaez on 4/28/16.
 */
public class FullTestSuite extends TestSuite {
    public static Test suite(){
        return new TestSuiteBuilder(FullTestSuite.class).includeAllPackagesUnderHere().build();
    }

    public FullTestSuite(){
        super();
    }
}
