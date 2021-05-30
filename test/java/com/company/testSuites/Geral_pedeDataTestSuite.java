package com.company.testSuites;

import com.company.testCases.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("pedeData Test Suite")
@SelectClasses( Geral_pedeData_controlFlowTest.class )

public class Geral_pedeDataTestSuite {}