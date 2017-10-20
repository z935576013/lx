package com.sdx.lx.common.dal;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

public class XmlParse {
    private Map<String, Map<String, String>> sqlMapResult = new HashMap<String, Map<String, String>>();

    public synchronized void parse(Resource[] resources) {
        Map<String, Document> documents = createDocuments(resources);
        parseDocuments(documents);
    }

    public String getSql(String nameSpace, String sqlId) {
        Map<String, String> sqlMap = this.sqlMapResult.get(nameSpace);
        return sqlMap.get(sqlId);
    }

    public String getSql(String sqlIdentify) {
        if ((sqlIdentify != null) && (sqlIdentify.indexOf('.') > 0)) {
            String nameSpace = sqlIdentify.substring(0, sqlIdentify.lastIndexOf('.'));
            String sqlId = sqlIdentify.substring(sqlIdentify.lastIndexOf('.') + 1);

            return getSql(nameSpace, sqlId);
        }
       // return null;
        return sqlIdentify;
    }

    private Map<String, Document> createDocuments(Resource[] resources) {
        Map<String, Document> documents = new HashMap<String, Document>();

        if ((resources != null) && (resources.length > 0)) {
            SAXReader saxReader = new SAXReader();
            for (Resource resource : resources) {
                try {
                    String fileName = resource.getFilename();
                    InputStream reader = resource.getInputStream();
                    Document doc = saxReader.read(reader);
                    documents.put(fileName, doc);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return documents;
    }

    private void parseDocuments(Map<String, Document> documents) {
        try {
            this.sqlMapResult.clear();
            for (Map.Entry<String, Document> entry : documents.entrySet()) {
                parseDocument(entry.getValue().getRootElement());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void parseDocument(Element rootElement) {
        if (rootElement != null) {
            String namespace = rootElement.attributeValue("namespace");
            Map<String, String> sqlMap = this.sqlMapResult.get(namespace);
            if (sqlMap == null) {
                sqlMap = new HashMap<String, String>();
                this.sqlMapResult.put(namespace, sqlMap);
            }

            parseNode(rootElement, sqlMap);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseNode(Element rootElement, Map<String, String> sqlMap) {
        List<Element> sqlElements = rootElement.elements();
        if (sqlElements != null) {
            for (Element sqlElement : sqlElements) {
                String id = sqlElement.attributeValue("id");
                String sql = sqlElement.getTextTrim();
                sqlMap.put(id, sql);
            }
        }
    }
}