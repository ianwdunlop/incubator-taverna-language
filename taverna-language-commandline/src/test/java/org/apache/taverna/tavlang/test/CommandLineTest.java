package org.apache.taverna.tavlang.test;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import org.apache.taverna.tavlang.CommandLineTool;
import org.junit.Assert;
import org.junit.Test;

public class CommandLineTest {
	CommandLineTool commandLineTool = new CommandLineTool();
	
	@Test
	public void testHelp(){
//		Assert
		commandLineTool.parse();
		commandLineTool.parse("version");
		commandLineTool.parse("help");
		commandLineTool.parse("help", "convert");
		commandLineTool.parse("help", "inspect");
		commandLineTool.parse("help", "validate");
		commandLineTool.parse("help", "help");
	}
	
	@Test
	public void testConvert(){
		
		
//		CommandLineTool tool = new CommandLineTool();
		commandLineTool.parse("convert", "-r", "-structure",  "-i", "/home/menaka/conv/aaa");
//		CommandLineTool tool2 = new CommandLineTool();
//		commandLineTool.parse("convert", "-r", "-wfdesc", "-o", "/files/dir", "-i", "/files0/dir");
//		commandLineTool.parse();
//		commandLineTool.parse();
//		commandLineTool.parse();
		
	}
	

}
