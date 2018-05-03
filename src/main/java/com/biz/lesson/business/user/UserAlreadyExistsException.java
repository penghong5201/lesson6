package com.biz.lesson.business.user;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 3678437600041617762L;
	private Throwable nestedThrowable = null;

	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}

	public UserAlreadyExistsException(Throwable nestedThrowable) {
		this.nestedThrowable = nestedThrowable;
	}

	public UserAlreadyExistsException(String msg, Throwable nestedThrowable) {
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
