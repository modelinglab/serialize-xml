/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.utils.serialize.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.net.URL;
import org.modelinglab.utils.serialize.core.Serializer;
import org.modelinglab.utils.serialize.core.SerializerException;
import org.modelinglab.utils.serialize.core.SerializerIOException;

/**
 * An implementation of {@link Serializer} which stores object in XML files.
 *
 */
public class XMLSerializer implements Serializer {

    private final XStream xStream = new XStream(new DomDriver("UTF-8"));

    @Override
    public <E> E load(InputStream is, Class<E> expectedClass) throws SerializerException, ClassCastException {
        try {
            return (E) xStream.fromXML(is);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }

    public <E> E load(Reader reader, Class<E> expectedClass) throws SerializerException, ClassCastException {
        try {
            return (E) xStream.fromXML(reader);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }

    @Override
    public <E> E load(URL resourceUri, Class<E> expectedClass) throws SerializerException, ClassCastException {
        try {
            return (E) xStream.fromXML(resourceUri);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }

    @Override
    public <E> E load(File resourceFile, Class<E> expectedClass) throws SerializerException, ClassCastException {
        try (InputStream is = new FileInputStream(resourceFile)) {
            return (E) xStream.fromXML(resourceFile);
        } catch (IOException ex) {
            throw new SerializerIOException(ex);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }

    @Override
    public void save(Object object, OutputStream output) throws SerializerException {
        try {
            xStream.toXML(object, output);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }

    @Override
    public void save(Object object, File output) throws SerializerException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(output))) {
            xStream.toXML(object, writer);
        } catch (IOException ex) {
            throw new SerializerIOException(ex);
        } catch (RuntimeException ex) {
            throw new SerializerException(ex);
        }
    }
}
