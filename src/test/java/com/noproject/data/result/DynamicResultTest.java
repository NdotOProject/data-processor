/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package com.noproject.data.result;

import junit.framework.TestCase;

/**
 *
 * @author DELL
 */
public class DynamicResultTest extends TestCase {

    public DynamicResultTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of set method, of class DynamicResult.
     */
    public void testSet() {
        System.out.println("set");
        Object valueForLeft = null;
        Object valueForRight = null;
        DynamicResult instance = new DynamicResult();
        instance.set(valueForLeft, valueForRight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of result method, of class DynamicResult.
     */
    public void testResult() {
        System.out.println("result");
        DynamicResult instance = new DynamicResult();
        Data expResult = null;
        Data result = instance.request();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of require method, of class DynamicResult.
     */
    public void testRequire_Location() {
        System.out.println("require");
        Location of = null;
        DynamicResult instance = new DynamicResult();
        Data expResult = null;
        Data result = instance.require(of);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of require method, of class DynamicResult.
     */
    public void testRequire_3args() {
        System.out.println("require");
        Location of = null;
        Object defLeftVal = null;
        Object defRightVal = null;
        DynamicResult instance = new DynamicResult();
        Data expResult = null;
        Data result = instance.require(of, defLeftVal, defRightVal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
