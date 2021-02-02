package io.klib.log.toolings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;

public class Main {
	private static String name = "ProcessSubstitute";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static File workingDir;
	private static Path logPath;
	public static void main(String[] args) throws Exception {
		
		workingDir = new File(args[1]);
		logPath = workingDir.toPath().resolve(name.concat("_out_" + Utilities.getDateTimeStamp("yyyy.MM.dd-HH.mm.ss") + ".txt"));
		
		logger.debug("starting execution of {} and creating log file {}", name, logPath);
		LinkedList<String> cmds = new LinkedList<String>(Arrays.asList("cmd", "/c", "java", "-jar", args[0], "<", args[2]));
		
		startListener("TestString");
		variantPB(cmds);
		
		
		}

	private static void variantExecutor(LinkedList<String> cmds)
			throws IOException, InterruptedException, TimeoutException {
		logPath = workingDir.toPath().resolve(name.concat("_out_Exec.txt"));
		logger.debug("starting execution of {} and creating log file {}", name, logPath);
		ProcessResult pr = new ProcessExecutor()
			.command(cmds)
			.readOutput(true)
			.redirectOutput(Slf4jStream.of(logger).asInfo())
			.directory(logPath.toFile())
			.execute();
	}

	private static void variantPB(LinkedList<String> cmds) throws IOException, InterruptedException {
		logPath = workingDir.toPath().resolve(name.concat("_out_" + Utilities.getDateTimeStamp("yyyy.MM.dd-HH.mm.ss") + ".txt"));
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.directory(workingDir);
		pb.redirectErrorStream(true)
		.redirectOutput(logPath.toFile());
		Process process = pb.start();
	}
	
	private static void startListener(String triggerString) {
		LogTail_Listener listener = new LogTail_Listener(triggerString);
		Tailer tailer = new Tailer(logPath.toFile(), listener);
		Thread tListener = new Thread(tailer);
		tListener.start();
	}

}