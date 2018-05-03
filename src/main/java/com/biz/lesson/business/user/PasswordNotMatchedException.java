package com.biz.lesson.business.user;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * 密码不匹配异常
 */
public class PasswordNotMatchedException extends Exception {

	private static final long serialVersionUID = 5073604425585642129L;

	private Throwable nestedThrowable = null;

	public PasswordNotMatchedException() {
		super();
	}

	public PasswordNotMatchedException(String msg) {
		super(msg);
	}

	public PasswordNotMatchedException(Throwable nestedThrowable) {
		this.nestedThrowable = nestedThrowable;
	}

	public PasswordNotMatchedException(String msg, Throwable nestedThrowable) {
		super(msg);
		this.nestedThrowable = nestedThrowable;
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace(pw);
		}
	}
}
