package core.languageHandler;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilities.ExceptionUtility;
import utilities.FileUtility;
import core.UserDefinedAction;
import core.controller.Core;

public class DynamicPythonCompiler implements DynamicCompiler {

	private static final Logger LOGGER = Logger.getLogger(DynamicPythonCompiler.class.getName());
	private File interpreter;

	static {
		LOGGER.setLevel(Level.ALL);
	}

	public DynamicPythonCompiler() {
		interpreter = new File("python.exe");
	}

	@Override
	public void setPath(File file) {
		interpreter = file;
	}

	@Override
	public File getPath() {
		return interpreter;
	}

	@Override
	public UserDefinedAction compile(final String source) {
		return new UserDefinedAction() {
			@Override
			public void action(Core controller) {
				File sourceFile = new File("custom_action.py");
				FileUtility.writeToFile(source, sourceFile, false);

				String[] cmd = { interpreter.getAbsolutePath(), sourceFile.getPath() };
				ProcessBuilder pb = new ProcessBuilder(cmd);
				pb.redirectOutput(Redirect.INHERIT);
				pb.redirectError(Redirect.INHERIT);

		        Process p;
				try {
					p = pb.start();

					p.waitFor();
				} catch (InterruptedException | IOException e) {
					LOGGER.warning(ExceptionUtility.getStackTrace(e));
				}
			}
		};
	}

	@Override
	public String getName() {
		return "python";
	}

	@Override
	public String getRunArgs() {
		return "";
	}

	@Override
	public void setRunArgs(String args) {
		// TODO Auto-generated method stub

	}
}
