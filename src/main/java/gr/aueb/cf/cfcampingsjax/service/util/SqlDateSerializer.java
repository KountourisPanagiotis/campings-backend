package gr.aueb.cf.cfcampingsjax.service.util;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *  SqlDateSerializer class is used to serialize java.sql.Date objects to JSON
 */
public class SqlDateSerializer extends JsonSerializer<Date>{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}
