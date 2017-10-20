package com.sdx.lx.common.dal;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StringTemplateLoader {

    public static String proccessToString(Map<String, ?> root, String sql){
        StringReader reader = null;
        StringWriter out = null;
        try {
            reader = new StringReader(sql);
            Configuration configurer = new Configuration();
            configurer.setNumberFormat("#");
            Template template = new Template("default_template_key", reader, configurer);
            out = new StringWriter();
            template.process(root, out);
            return out.toString();
        }catch(IOException|TemplateException e){
           return null;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
    }
}