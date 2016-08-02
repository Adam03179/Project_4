package ua.gerasymenko.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *The NumberFormatterTag class describes behavior of custom tag, which
 * required for formatting number to the required form.
 *
 * This class extends from SimpleTagSupport and overrides on of his method -
 * doTag().
 *
 * @author Igor Gerasymenko
 */
public class NumberFormatterTag extends SimpleTagSupport {
    private static final Logger logger = Logger.getLogger(NumberFormatterTag.class);
    private String format;
    private BigDecimal number;

    public NumberFormatterTag() {
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     * This method uses DecimalFormat class to show number to user in right form.
     */
    @Override
    public void doTag() {
        DecimalFormat formatter = new DecimalFormat(format);
        String formattedNumber = formatter.format(number);
        try {
            getJspContext().getOut().write(formattedNumber);
        } catch (IOException e) {
            logger.error("Tag error ", e);
        }


    }
}
