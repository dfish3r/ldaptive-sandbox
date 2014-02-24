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

import java.lang.reflect.Method;
import java.util.Collection;
import org.ldaptive.SortBehavior;

/**
 * Attribute mutator associated with the {@link Method} of an object.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class MethodAttributeValueMutator extends AbstractAttributeValueMutator
{

  /** Method to get data from. */
  private final Method getterMethod;

  /** Method to set data on. */
  private final Method setterMethod;


  /**
   * Creates a new method attribute value mutator.
   *
   * @param  transcoder  for mutating the methods
   * @param  getter  method to read data
   * @param  setter  method to write data
   */
  public MethodAttributeValueMutator(
    final ReflectionTranscoder transcoder, final Method getter,
    final Method setter)
  {
    super(null, false, null, transcoder);
    getterMethod = getter;
    getterMethod.setAccessible(true);
    setterMethod = setter;
    setterMethod.setAccessible(true);
  }


  /**
   * Creates a new method attribute value mutator.
   *
   * @param  name  of the attribute
   * @param  binary  whether the attribute is binary
   * @param  sortBehavior  sort behavior of the attribute
   * @param  transcoder  to mutate the methods
   * @param  getter  method to read data
   * @param  setter  method to write data
   */
  public MethodAttributeValueMutator(
    final String name, final boolean binary, final SortBehavior sortBehavior,
    final ReflectionTranscoder transcoder, final Method getter,
    final Method setter)
  {
    super(name, binary, sortBehavior, transcoder);
    getterMethod = getter;
    getterMethod.setAccessible(true);
    setterMethod = setter;
    setterMethod.setAccessible(true);
  }


  /** {@inheritDoc} */
  @Override
  public Collection<String> getStringValues(final Object object)
  {
    return getReflectionTranscoder().encodeStringValues(
      ReflectionUtils.invokeGetterMethod(getterMethod, object));
  }


  /** {@inheritDoc} */
  @Override
  public Collection<byte[]> getBinaryValues(final Object object)
  {
    return getReflectionTranscoder().encodeBinaryValues(
      ReflectionUtils.invokeGetterMethod(getterMethod, object));
  }


  /** {@inheritDoc} */
  @Override
  public void setStringValues(
    final Object object,
    final Collection<String> values)
  {
    ReflectionUtils.invokeSetterMethod(
      setterMethod,
      object,
      getReflectionTranscoder().decodeStringValues(values));
  }


  /** {@inheritDoc} */
  @Override
  public void setBinaryValues(
    final Object object,
    final Collection<byte[]> values)
  {
    ReflectionUtils.invokeSetterMethod(
      setterMethod,
      object,
      getReflectionTranscoder().decodeBinaryValues(values));
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return String.format(
      "[%s@%d::name=%s, binary=%s, sortBehavior=%s, reflectionTranscoder=%s, " +
        "getterMethod=%s, setterMethod=%s]",
      getClass().getName(),
      hashCode(),
      getName(),
      isBinary(),
      getSortBehavior(),
      getReflectionTranscoder(),
      getterMethod,
      setterMethod);
  }
}
