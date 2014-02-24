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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.ldaptive.beans.AbstractClassDescriptor;
import org.ldaptive.beans.Attribute;
import org.ldaptive.beans.AttributeValueMutator;
import org.ldaptive.beans.DnValueMutator;
import org.ldaptive.beans.Entry;

/**
 * Creates DN and attribute mutators for the {@link Field}s on a type.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class FieldClassDescriptor extends AbstractClassDescriptor
{


  /** {@inheritDoc} */
  @Override
  public void initialize(final Class<?> type)
  {
    final Map<String, Field> fields = getDeclaredFields(type);
    final Entry entryAnnotation = type.getAnnotation(Entry.class);
    if (fields.containsKey(entryAnnotation.dn())) {
      setDnValueMutator(createDnValueMutator(fields.get(entryAnnotation.dn())));
    }
    for (Attribute attr : entryAnnotation.attributes()) {
      if (fields.containsKey(attr.property())) {
        addAttributeValueMutator(
          createAttributeValueMutator(fields.get(attr.property()), attr));
      }
    }
  }


  /**
   * Returns a map of all the field names to the field.
   *
   * @param  type  to read declared fields from
   *
   * @return  map of field names to field
   */
  protected Map<String, Field> getDeclaredFields(final Class<?> type)
  {
    final Map<String, Field> fields = new HashMap<String, Field>();
    for (Field field : type.getDeclaredFields()) {
      fields.put(field.getName(), field);
    }
    return fields;
  }


  /**
   * Returns a dn value mutator for the supplied field.
   *
   * @param  field  to create dn value mutator for
   *
   * @return  dn value mutator
   */
  protected DnValueMutator createDnValueMutator(final Field field)
  {
    return new DefaultDnValueMutator(
      new FieldAttributeValueMutator(
        new DefaultReflectionTranscoder(field.getGenericType()),
        field));
  }


  /**
   * Returns an attribute value mutator for the supplied field.
   *
   * @param  field  to create attribute value mutator for
   * @param  attribute  attribute containing metadata
   *
   * @return  attribute value mutator
   */
  protected AttributeValueMutator createAttributeValueMutator(
    final Field field, final Attribute attribute)
  {
    final String name = "".equals(attribute.name()) ?
      field.getName() : attribute.name();
    return new FieldAttributeValueMutator(
      name,
      attribute.binary(),
      attribute.sortBehavior(),
      new DefaultReflectionTranscoder(field.getGenericType()),
      field);
  }
}
