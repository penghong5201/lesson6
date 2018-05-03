package com.biz.lesson.vo.user;

import javax.validation.constraints.Pattern;

/**
 * Created by zhangjian on 17/3/20.
 */
public class PasswordVo implements java.io.Serializable  {


    private static final long serialVersionUID = -724869631953714920L;

    public static final String PASSWORD_REGEXP = "^(?=.{6,})(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$";

    private String passwordOld;

    @Pattern(regexp = PASSWORD_REGEXP, message = "common.password.regexp")
    private String passwordNew;

    @Pattern(regexp = PASSWORD_REGEXP, message = "common.password.regexp")
    private String passwordRepeat;

    /**
     * @return Returns the passwordNew.
     */
    public String getPasswordNew()
    {
        return passwordNew;
    }

    /**
     * @param passwordNew The passwordNew to set.
     */
    public void setPasswordNew(String passwordNew)
    {
        this.passwordNew = passwordNew;
    }

    /**
     * @return Returns the passwordOld.
     */
    public String getPasswordOld()
    {
        return passwordOld;
    }

    /**
     * @param passwordOld The passwordOld to set.
     */
    public void setPasswordOld(String passwordOld)
    {
        this.passwordOld = passwordOld;
    }

    /**
     * @return Returns the passwordRepeat.
     */
    public String getPasswordRepeat()
    {
        return passwordRepeat;
    }

    /**
     * @param passwordRepeat The passwordRepeat to set.
     */
    public void setPasswordRepeat(String passwordRepeat)
    {
        this.passwordRepeat = passwordRepeat;
    }

//    public ActionErrors validate(ActionMapping mapping,HttpServletRequest request)
//    {
//        ActionErrors errors = super.validate(mapping,request);
//        if(errors==null)
//        {
//            errors = new ActionErrors();
//        }
//
//        if((passwordOld==null)||(passwordOld.length()==0))
//        {
//            errors.add("passwordOld",new ActionError("error.password.passwordOld.required"));
//        }
//        if((passwordNew==null)||(passwordNew.length()==0))
//        {
//            errors.add("passwordNew",new ActionError("error.password.passwordNew.required"));
//        }
//        else
//        {
//            if(!passwordNew.equals(passwordRepeat))
//            {
//                errors.add("passwordNew",new ActionError("error.password.passwordRepeatNotMatched"));
//            }
//        }
//
//        return errors;
//    }

}
