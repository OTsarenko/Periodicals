package app.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter print = getJspContext().getOut();
        print.println("<p style=\"color: darkred\">NEWS</p>");
    }
}
