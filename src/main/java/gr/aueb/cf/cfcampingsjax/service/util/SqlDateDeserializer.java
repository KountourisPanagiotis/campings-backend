package gr.aueb.cf.cfcampingsjax.service.util;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  SqlDateDeserializer is a custom JsonDeserializer class for Date objects.
 */
public class SqlDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if (node instanceof TextNode) {
            String dateStr = node.asText();
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                throw new IOException("Failed to parse date: " + dateStr, e);
            }
        }
        throw new IOException("Invalid date format");
    }
}
