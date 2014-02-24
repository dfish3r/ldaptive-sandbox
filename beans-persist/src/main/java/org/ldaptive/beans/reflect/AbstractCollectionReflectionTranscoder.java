/*
  $Id$

  Copyright (C) 2003-2014 Virginia Tech.
  All rights reserved.

  SEE LICENSE FOR MORE INFORMATION

  Author:  Middleware Services
  Email:   middleware@vt.edu
  Version: $Revision$
  Updated: $Date$
*/
package org.ldaptive.beans.reflect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Reflection transcoder for an object that implements a {@link Collection}.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public abstract class AbstractCollectionReflectionTranscoder
  implements ReflectionTranscoder
{

  /** Type that is a collection. */
  private final Class<?> type;

  /** Used for collections that do not contain arrays. */
  private final SingleValueReflectionTranscoder<?> singleValueTranscoder;

  /** Used for collections that contain arrays. */
  private final ArrayReflectionTranscoder arrayTranscoder;


  /**
   * Creates a new abstract collection reflection transcoder.
   *
   * @param  c  class that is a collection
   * @param  transcoder  to operate on elements of the collection
   */
  public AbstractCollectionReflectionTranscoder(
    final Class<?> c,
    final SingleValueReflectionTranscoder<?> transcoder)
  {
    type = c;
    singleValueTranscoder = transcoder;
    arrayTranscoder = null;
  }


  /**
   * Creates a new abstract collection reflection transcoder.
   *
   * @param  c  class that is a collection
   * @param  transcoder  to operate on elements of the collection
   */
  public AbstractCollectionReflectionTranscoder(
    final Class<?> c,
    final ArrayReflectionTranscoder transcoder)
  {
    type = c;
    singleValueTranscoder = null;
    arrayTranscoder = transcoder;
  }


  /** {@inheritDoc} */
  @Override
  public Object decodeStringValues(final Collection<String> values)
  {
    final Collection<Object> decoded = createCollection(Object.class);
    if (arrayTranscoder != null) {
      decoded.add(arrayTranscoder.decodeStringValues(values));
    } else {
      for (String value : values) {
        final List<String> l = new ArrayList<String>(1);
        l.add(value);
        decoded.add(singleValueTranscoder.decodeStringValues(l));
      }
    }
    return decoded;
  }


  /** {@inheritDoc} */
  @Override
  public Object decodeBinaryValues(final Collection<byte[]> values)
  {
    final Collection<Object> decoded = createCollection(Object.class);
    if (arrayTranscoder != null) {
      decoded.add(arrayTranscoder.decodeBinaryValues(values));
    } else {
      for (byte[] value : values) {
        final List<byte[]> l = new ArrayList<byte[]>(1);
        l.add(value);
        decoded.add(singleValueTranscoder.decodeBinaryValues(l));
      }
    }
    return decoded;
  }


  /** {@inheritDoc} */
  @Override
  public Collection<String> encodeStringValues(final Object values)
  {
    final Collection<String> encoded = createCollection(String.class);
    for (Object o : (Collection<?>) values) {
      if (arrayTranscoder != null) {
        encoded.addAll(arrayTranscoder.encodeStringValues(o));
      } else {
        encoded.addAll(singleValueTranscoder.encodeStringValues(o));
      }
    }
    return encoded;
  }


  /** {@inheritDoc} */
  @Override
  public Collection<byte[]> encodeBinaryValues(final Object values)
  {
    final Collection<byte[]> encoded = createCollection(byte[].class);
    for (Object o : (Collection<?>) values) {
      if (arrayTranscoder != null) {
        encoded.addAll(arrayTranscoder.encodeBinaryValues(o));
      } else {
        encoded.addAll(singleValueTranscoder.encodeBinaryValues(o));
      }
    }
    return encoded;
  }


  /**
   * Returns a collection implementation of the correct type for this
   * transcoder.
   *
   * @param  <T>  type of collection
   * @param  clazz  type of collection
   *
   * @return  collection implementation
   */
  protected abstract <T> Collection<T> createCollection(Class<T> clazz);


  /** {@inheritDoc} */
  @Override
  public Class<?> getType()
  {
    return type;
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return String.format(
      "[%s@%d::type=%s, singleValueTranscoder=%s, arrayTranscoder=%s]",
      getClass().getName(),
      hashCode(),
      type,
      singleValueTranscoder,
      arrayTranscoder);
  }
}
