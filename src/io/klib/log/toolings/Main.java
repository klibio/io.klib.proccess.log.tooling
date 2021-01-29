package io.klib.log.toolings;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import org.slf4j.LoggerFactory;
import org.zeroturnaround.*;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;

public class Main {

	public static void main(String[] args) throws Exception {
		LinkedList<String> cmds = new LinkedList<String>(Arrays.asList("java", "-jar", "D:/GitLib/GitHub/io.klib.proccess.log.tooling/ProcessSubstitute.jar"));
		String pr = new ProcessExecutor().command(cmds)
			.redirectOutput(Slf4jStream.ofCaller().asInfo())
			.execute().outputUTF8();
		System.out.println(pr);
		
		
//		Process process;
//		File workingDir = new File("D:/GitLib/GitHub/io.klib.proccess.log.tooling/");
//		ProcessBuilder pb = new ProcessBuilder(cmds);
//		pb.directory(workingDir);
//		pb.inheritIO();
//		process = pb.start();
//		process.waitFor();
		}

}